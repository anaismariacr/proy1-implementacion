package uniandes.edu.co.proyecto.modelo;

import java.sql.Time;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "CITAS")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private String id;

    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @Column(name = "HORA")
    private Time hora;

    @ManyToOne
    @JoinColumn(name = "ATENDIDA_EN", referencedColumnName = "NIT")
    private Ips ipsNit;

    @Column(name = "TIPODOCAFILIADO")
    private String tipoDocAfiliado;

    @ManyToOne
    @JoinColumn(name = "NUMDOCAFILIADO", referencedColumnName = "NUMERO_DOCUMENTO")
    private Afiliado numDocAfiliado;

    @ManyToOne
    @JoinColumn(name = "ORDENSERVICIO", referencedColumnName = "NUMERO")
    private OrdenServicio numOrden;

    public Cita(Date fecha, Time hora, Ips ipsNit, String tipoDocAfiliado, Afiliado numDocAfiliado, OrdenServicio numOrden) {
        this.fecha = fecha;
        this.hora = hora;
        this.ipsNit = ipsNit;
        this.tipoDocAfiliado = tipoDocAfiliado;
        this.numDocAfiliado = numDocAfiliado;
        this.numOrden = numOrden;
    }

    public Cita() {
        // Default constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Ips getIpsNit() {
        return ipsNit;
    }

    public void setIpsNit(Ips ipsNit) {
        this.ipsNit = ipsNit;
    }

    public String getTipoDocAfiliado() {
        return tipoDocAfiliado;
    }

    public void setTipoDocAfiliado(String tipoDocAfiliado) {
        this.tipoDocAfiliado = tipoDocAfiliado;
    }

    public Afiliado getNumDocAfiliado() {
        return numDocAfiliado;
    }

    public void setNumDocAfiliado(Afiliado numDocAfiliado) {
        this.numDocAfiliado = numDocAfiliado;
    }

    public String getNumOrden() {
        return numOrden.getNumero();
    }

    public void setNumOrden(OrdenServicio numOrden) {
        this.numOrden = numOrden;
    }
}
