package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "IPS")
public class Ips {
    
    @Id
    @Column(name = "NIT")
    private String nit;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "CIUDAD")
    private String ciudad;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "TELEFONO")
    private Integer telefono;

    @ManyToOne
    @JoinColumn(name = "EPS_NIT", referencedColumnName = "NIT")
    private Eps eps;

    public Ips(String nit, String nombre, String ciudad, String direccion, Integer telefono, Eps eps){
        this.nit = nit;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.eps = eps;
    }

    public Ips()
        {;}

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Eps getEps() {
        return eps;
    }

    public void setEpsNit(Eps eps) {
        this.eps = eps;
    }

    public String getEpsNit(Eps eps) {
        return eps.getNit();
    }

}
