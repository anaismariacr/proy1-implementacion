package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MEDICOS")
public class Medico {
    
    @Id
    @Column(name = "REGISTRO_MEDICO")
    private String registroMedico;

    @Column(name = "ESPECIALIDAD")
    private String especialidad;

    @ManyToOne
    @JoinColumn(name = "EMPLEADO_POR", referencedColumnName = "NIT")
    private Ips nitIps;
    
    public Medico(String registroMedico, String especialidad, Ips nitIps){
            this.registroMedico = registroMedico;
            this.especialidad = especialidad;
            this.nitIps = nitIps;
    }

    public Medico()
    {;}

    public String getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(String registroMedico) {
        this.registroMedico = registroMedico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNitIps() {
        return nitIps.getNit();
    }

    public void setNitIps(Ips nitIps) {
        this.nitIps = nitIps;
    }

}
