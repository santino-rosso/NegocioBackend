package ar.edu.um.programacion2.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Opcion.
 */
@Entity
@Table(name = "opcion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Opcion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "codigo", nullable = false)
    private String codigo;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @Column(name = "precio_adicional", precision = 21, scale = 2, nullable = false)
    private BigDecimal precioAdicional;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "opcion", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "opcion", "personalizacion", "venta" }, allowSetters = true)
    @JsonManagedReference
    private Set<VentaPersonalizacionOpcion> ventas = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = { "opciones", "ventas", "dispositivo" }, allowSetters = true)
    @JsonBackReference
    private Personalizacion personalizacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Opcion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public Opcion codigo(String codigo) {
        this.setCodigo(codigo);
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Opcion nombre(String nombre) {
        this.setNombre(nombre);
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Opcion descripcion(String descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioAdicional() {
        return this.precioAdicional;
    }

    public Opcion precioAdicional(BigDecimal precioAdicional) {
        this.setPrecioAdicional(precioAdicional);
        return this;
    }

    public void setPrecioAdicional(BigDecimal precioAdicional) {
        this.precioAdicional = precioAdicional;
    }

    public Set<VentaPersonalizacionOpcion> getVentas() {
        return this.ventas;
    }

    public void setVentas(Set<VentaPersonalizacionOpcion> ventaPersonalizacionOpcions) {
        if (this.ventas != null) {
            this.ventas.forEach(i -> i.setOpcion(null));
        }
        if (ventaPersonalizacionOpcions != null) {
            ventaPersonalizacionOpcions.forEach(i -> i.setOpcion(this));
        }
        this.ventas = ventaPersonalizacionOpcions;
    }

    public Opcion ventas(Set<VentaPersonalizacionOpcion> ventaPersonalizacionOpcions) {
        this.setVentas(ventaPersonalizacionOpcions);
        return this;
    }

    public Opcion addVentas(VentaPersonalizacionOpcion ventaPersonalizacionOpcion) {
        this.ventas.add(ventaPersonalizacionOpcion);
        ventaPersonalizacionOpcion.setOpcion(this);
        return this;
    }

    public Opcion removeVentas(VentaPersonalizacionOpcion ventaPersonalizacionOpcion) {
        this.ventas.remove(ventaPersonalizacionOpcion);
        ventaPersonalizacionOpcion.setOpcion(null);
        return this;
    }

    public Personalizacion getPersonalizacion() {
        return this.personalizacion;
    }

    public void setPersonalizacion(Personalizacion personalizacion) {
        this.personalizacion = personalizacion;
    }

    public Opcion personalizacion(Personalizacion personalizacion) {
        this.setPersonalizacion(personalizacion);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Opcion)) {
            return false;
        }
        return id != null && id.equals(((Opcion) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Opcion{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", precioAdicional=" + getPrecioAdicional() +
            "}";
    }
}
