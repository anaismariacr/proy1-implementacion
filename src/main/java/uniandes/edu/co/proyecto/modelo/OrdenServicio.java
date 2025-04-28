package uniandes.edu.co.proyecto.modelo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "ordenServicioSeq", sequenceName = "ORDENESSERVICIO_SEQ", allocationSize = 1)
@Table(name = "ORDENESSERVICIO")
public class OrdenServicio {
    
    public enum Estado {
        vigente,
        cancelada,
        completada
    }

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordenServicioSeq")
    @Id
    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @Column(name = "ESTADO")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    // @ManyToOne
    // @JoinColumn(name = "tipoAfiliadoReceptor", referencedColumnName = "tipoDoc")
    // private Afiliado tipoAfiliadoReceptor;

    @ManyToOne
    @JoinColumn(name = "AFILIADO_RECEPTOR_NUM", referencedColumnName = "NUMERO_DOCUMENTO")
    private Afiliado numAfiliadoReceptor;

    @ManyToOne
    @JoinColumn(name = "MEDICO_REMITENTE", referencedColumnName = "REGISTRO_MEDICO")
    private Medico medicoRemitente;

    @ManyToOne
    @JoinColumn(name = "SERVICIO_SALUD", referencedColumnName = "NOMBRE")
    private ServicioSalud servicioNombre;


    public OrdenServicio(Date fecha, Estado estado, 
                        Afiliado numAfiliadoReceptor, Medico medicoRemitente, ServicioSalud servicioNombre){
        //this.numero = numero; Eliminado pq la secuencia lo genera automaticamente
        this.fecha = fecha;
        this.estado = estado;
        // this.tipoAfiliadoReceptor = tipoAfiliadoReceptor;
        this.numAfiliadoReceptor = numAfiliadoReceptor;
        this.medicoRemitente = medicoRemitente;
        this.servicioNombre = servicioNombre;
    }

    public OrdenServicio()
    {;}

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    // public Afiliado getTipoAfiliadoReceptor() {
    //     return tipoAfiliadoReceptor;
    // }

    // public void setTipoAfiliadoReceptor(Afiliado tipoAfiliadoReceptor) {
    //     this.tipoAfiliadoReceptor = tipoAfiliadoReceptor;
    // }

    public Afiliado getNumAfiliadoReceptor() {
        return numAfiliadoReceptor;
    }

    public void setNumAfiliadoReceptor(Afiliado numAfiliadoReceptor) {
        this.numAfiliadoReceptor = numAfiliadoReceptor;
    }

    public Medico getMedicoRemitente() {
        return medicoRemitente;
    }

    public void setMedicoRemitente(Medico medicoRemitente) {
        this.medicoRemitente = medicoRemitente;
    }

    public ServicioSalud getServicioNombre() {
        return servicioNombre;
    }

    public void setServicioNombre(ServicioSalud servicioNombre) {
        this.servicioNombre = servicioNombre;
    }

    public Integer getNumDoc(Afiliado numAfiliadoReceptor){
        return numAfiliadoReceptor.getNumDoc();
    }

    public String getRegistroMedico(Medico medicoRemitente){
        return medicoRemitente.getRegistroMedico();
    }

    public String getNombreServicio(ServicioSalud servicioNombre){
        return servicioNombre.getNombre();
    }
    
}
