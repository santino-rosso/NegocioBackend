package ar.edu.um.programacion2.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A VentaPersonalizacionOpcion.
 */
@Entity
@Table(name = "venta_personalizacion_opcion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VentaPersonalizacionOpcion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = { "ventas", "personalizacion" }, allowSetters = true)
    @JsonManagedReference
    private Opcion opcion;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = { "opciones", "ventas", "dispositivo" }, allowSetters = true)
    @JsonBackReference
    private Personalizacion personalizacion;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = { "personalizaciones", "adicionales", "caracteristicas" }, allowSetters = true)
    @JsonBackReference
    private Venta venta;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VentaPersonalizacionOpcion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Opcion getOpcion() {
        return this.opcion;
    }

    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
    }

    public VentaPersonalizacionOpcion opcion(Opcion opcion) {
        this.setOpcion(opcion);
        return this;
    }

    public Personalizacion getPersonalizacion() {
        return this.personalizacion;
    }

    public void setPersonalizacion(Personalizacion personalizacion) {
        this.personalizacion = personalizacion;
    }

    public VentaPersonalizacionOpcion personalizacion(Personalizacion personalizacion) {
        this.setPersonalizacion(personalizacion);
        return this;
    }

    public Venta getVenta() {
        return this.venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public VentaPersonalizacionOpcion venta(Venta venta) {
        this.setVenta(venta);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VentaPersonalizacionOpcion)) {
            return false;
        }
        return id != null && id.equals(((VentaPersonalizacionOpcion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VentaPersonalizacionOpcion{" +
            "id=" + getId() +
            "}";
    }
}
