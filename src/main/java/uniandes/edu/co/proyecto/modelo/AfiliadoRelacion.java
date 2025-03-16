package uniandes.edu.co.proyecto.modelo;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "afiliadosrelaciones")
public class AfiliadoRelacion {
    
    public enum TipoAfiliado{
        contribuyente,
        beneficiario
    }

    /*@Id
    private String tipoDoc;
    @Id
    private Integer numDoc;*/
    @EmbeddedId
    private AfiliadoPK pk;

    @Enumerated(EnumType.STRING)
    private TipoAfiliado tipoAfiliado;
    private String relacionParentesco;

    @ManyToOne
    @MapsId("pkAfiliadoRel")
    @JoinColumns({
            @JoinColumn(name = "tipoDocAf", referencedColumnName = "tipoDoc"),
            @JoinColumn(name = "numDocAf", referencedColumnName = "numDoc")
    })
    private Afiliado pkAfiliadoRel;


    public AfiliadoRelacion(String tipoDoc, Integer numDoc, TipoAfiliado tipoAfiliado, String relacionParentesco, 
                            Afiliado pkAfiliadoRel){
        this.pk = new AfiliadoPK(tipoDoc, numDoc);
        this.tipoAfiliado = tipoAfiliado;
        this.relacionParentesco = relacionParentesco;
        this.pkAfiliadoRel = pkAfiliadoRel;
    }

    public AfiliadoRelacion()
    {;}

    public AfiliadoPK getPK() {
        return pk;
    }

    public void setPK(AfiliadoPK pk) {
        this.pk = pk;
    }

    public TipoAfiliado getTipoAfiliado() {
        return tipoAfiliado;
    }

    public void setTipoAfiliado(TipoAfiliado tipoAfiliado) {
        this.tipoAfiliado = tipoAfiliado;
    }

    public String getRelacionParentesco() {
        return relacionParentesco;
    }

    public void setRelacionParentesco(String relacionParentesco) {
        this.relacionParentesco = relacionParentesco;
    }

    public Afiliado getPkAfiliadoRel() {
        return pkAfiliadoRel;
    }

    public void setPkAfiliadoRel(Afiliado pkAfiliadoRel) {
        this.pkAfiliadoRel = pkAfiliadoRel;
    }
    
}
