package ar.edu.um.programacion2.domain;

import com.fasterxml.jackson.annotation.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Personalizacion.
 */
@Entity
@Table(name = "personalizacion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Personalizacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personalizacion", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "ventas", "personalizacion" }, allowSetters = true)
    @JsonManagedReference
    private Set<Opcion> opciones = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personalizacion", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "opcion", "personalizacion", "venta" }, allowSetters = true)
    @JsonManagedReference
    private Set<VentaPersonalizacionOpcion> ventas = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = { "caracteristicas", "personalizaciones", "adicionales" }, allowSetters = true)
    @JsonBackReference
    private Dispositivo dispositivo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Personalizacion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Personalizacion nombre(String nombre) {
        this.setNombre(nombre);
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Personalizacion descripcion(String descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Opcion> getOpciones() {
        return this.opciones;
    }

    public void setOpciones(Set<Opcion> opcions) {
        if (this.opciones != null) {
            this.opciones.forEach(i -> i.setPersonalizacion(null));
        }
        if (opcions != null) {
            opcions.forEach(i -> i.setPersonalizacion(this));
        }
        this.opciones = opcions;
    }

    public Personalizacion opciones(Set<Opcion> opcions) {
        this.setOpciones(opcions);
        return this;
    }

    public Personalizacion addOpciones(Opcion opcion) {
        this.opciones.add(opcion);
        opcion.setPersonalizacion(this);
        return this;
    }

    public Personalizacion removeOpciones(Opcion opcion) {
        this.opciones.remove(opcion);
        opcion.setPersonalizacion(null);
        return this;
    }

    public Set<VentaPersonalizacionOpcion> getVentas() {
        return this.ventas;
    }

    public void setVentas(Set<VentaPersonalizacionOpcion> ventaPersonalizacionOpcions) {
        if (this.ventas != null) {
            this.ventas.forEach(i -> i.setPersonalizacion(null));
        }
        if (ventaPersonalizacionOpcions != null) {
            ventaPersonalizacionOpcions.forEach(i -> i.setPersonalizacion(this));
        }
        this.ventas = ventaPersonalizacionOpcions;
    }

    public Personalizacion ventas(Set<VentaPersonalizacionOpcion> ventaPersonalizacionOpcions) {
        this.setVentas(ventaPersonalizacionOpcions);
        return this;
    }

    public Personalizacion addVentas(VentaPersonalizacionOpcion ventaPersonalizacionOpcion) {
        this.ventas.add(ventaPersonalizacionOpcion);
        ventaPersonalizacionOpcion.setPersonalizacion(this);
        return this;
    }

    public Personalizacion removeVentas(VentaPersonalizacionOpcion ventaPersonalizacionOpcion) {
        this.ventas.remove(ventaPersonalizacionOpcion);
        ventaPersonalizacionOpcion.setPersonalizacion(null);
        return this;
    }

    public Dispositivo getDispositivo() {
        return this.dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public Personalizacion dispositivo(Dispositivo dispositivo) {
        this.setDispositivo(dispositivo);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Personalizacion)) {
            return false;
        }
        return getId() != null && getId().equals(((Personalizacion) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Personalizacion{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
