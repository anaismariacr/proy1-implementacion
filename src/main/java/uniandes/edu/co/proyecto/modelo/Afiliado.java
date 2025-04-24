package uniandes.edu.co.proyecto.modelo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "AFILIADOS")
public class Afiliado {

    @Id
    @Column(name = "NUMERO_DOCUMENTO")
    private Integer numDoc;

    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDoc;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNac;
    
    @Column(name = "CIUDAD")
    private String ciudad;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "TELEFONO")
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "EPS", referencedColumnName = "NIT")
    private Eps epsAsociada;


    public Afiliado(String tipoDoc, Integer numDoc, String nombre, Date fechaNac, String ciudad, String direccion, 
                            String telefono, Eps epsAsociada){
        //this.pk = new AfiliadoPK(tipoDoc, numDoc);
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.epsAsociada = epsAsociada;

    }

    public Afiliado()
    {;}

    /*public AfiliadoPK getPK() {
        return pk;
    }

    public void setPK(AfiliadoPK pk) {
        this.pk = pk;
    }*/
    
    public String getNombre() {
        return nombre;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Eps getEpsAsociada() {
        return epsAsociada;
    }

    public void setEpsAsociada(Eps epsAsociada) {
        this.epsAsociada = epsAsociada;
    }

}
