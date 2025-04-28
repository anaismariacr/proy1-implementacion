package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MEDICOSPERSONAL")
public class MedicoPersonal {
    
    @Id
    @Column(name = "REGISTRO_MEDICO")
    private String registroMedico;

    @OneToOne
    @JoinColumn(name = "REGISTRO_MEDICO", referencedColumnName = "REGISTRO_MEDICO", insertable = false, updatable = false)
    private Medico medico;

    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDoc;

    @Column(name = "NUMERO_DOCUMENTO")
    private Integer numDoc;

    @Column(name = "NOMBRE")
    private String nombre;

    public MedicoPersonal(String registroMedico, String tipoDoc, Integer numDoc, String nombre){
        this.registroMedico = registroMedico;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.nombre = nombre;

    }

    public MedicoPersonal()
    {;}

    public String getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(String registroMedico) {
        this.registroMedico = registroMedico;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public Integer getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(Integer numDoc) {
        this.numDoc = numDoc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

}
