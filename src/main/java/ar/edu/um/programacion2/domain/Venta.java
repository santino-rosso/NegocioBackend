package ar.edu.um.programacion2.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Venta.
 */
@Entity
@Table(name = "venta")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "idDispositivo", nullable = false)
    private Long idDispositivo;

    @NotNull
    @Column(name = "codigo", nullable = false)
    private String codigo;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Lob
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @Column(name = "precio_base", precision = 21, scale = 2, nullable = false)
    private BigDecimal precioBase;

    @NotNull
    @Column(name = "moneda", nullable = false)
    private String moneda;

    @NotNull
    @Column(name = "fecha_venta", nullable = false)
    private ZonedDateTime fechaVenta;

    @Column(name = "precio_final", precision = 21, scale = 2)
    private BigDecimal precioFinal;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venta", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "opcion", "personalizacion", "venta" }, allowSetters = true)
    @JsonManagedReference
    private Set<VentaPersonalizacionOpcion> personalizaciones = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "rel_venta__adicionales",
        joinColumns = @JoinColumn(name = "venta_id"),
        inverseJoinColumns = @JoinColumn(name = "adicionales_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "dispositivos", "ventas" }, allowSetters = true)
    @JsonManagedReference
    private Set<Adicional> adicionales = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "rel_venta__caracteristicas",
        joinColumns = @JoinColumn(name = "venta_id"),
        inverseJoinColumns = @JoinColumn(name = "caracteristicas_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "dispositivo", "ventas" }, allowSetters = true)
    @JsonManagedReference
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Venta id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDispositivo() {
        return this.idDispositivo;
    }

    public Venta idDispositivo(Long idDispositivo) {
        this.setIdDispositivo(idDispositivo);
        return this;
    }

    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public Venta codigo(String codigo) {
        this.setCodigo(codigo);
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Venta nombre(String nombre) {
        this.setNombre(nombre);
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Venta descripcion(String descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioBase() {
        return this.precioBase;
    }

    public Venta precioBase(BigDecimal precioBase) {
        this.setPrecioBase(precioBase);
        return this;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public String getMoneda() {
        return this.moneda;
    }

    public Venta moneda(String moneda) {
        this.setMoneda(moneda);
        return this;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public ZonedDateTime getFechaVenta() {
        return this.fechaVenta;
    }

    public Venta fechaVenta(ZonedDateTime fechaVenta) {
        this.setFechaVenta(fechaVenta);
        return this;
    }

    public void setFechaVenta(ZonedDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public BigDecimal getPrecioFinal() {
        return this.precioFinal;
    }

    public Venta precioFinal(BigDecimal precioFinal) {
        this.setPrecioFinal(precioFinal);
        return this;
    }

    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }

    public Set<VentaPersonalizacionOpcion> getPersonalizaciones() {
        return this.personalizaciones;
    }

    public void setPersonalizaciones(Set<VentaPersonalizacionOpcion> ventaPersonalizacionOpcions) {
        if (this.personalizaciones != null) {
            this.personalizaciones.forEach(i -> i.setVenta(null));
        }
        if (ventaPersonalizacionOpcions != null) {
            ventaPersonalizacionOpcions.forEach(i -> i.setVenta(this));
        }
        this.personalizaciones = ventaPersonalizacionOpcions;
    }

    public Venta personalizaciones(Set<VentaPersonalizacionOpcion> ventaPersonalizacionOpcions) {
        this.setPersonalizaciones(ventaPersonalizacionOpcions);
        return this;
    }

    public Venta addPersonalizaciones(VentaPersonalizacionOpcion ventaPersonalizacionOpcion) {
        this.personalizaciones.add(ventaPersonalizacionOpcion);
        ventaPersonalizacionOpcion.setVenta(this);
        return this;
    }

    public Venta removePersonalizaciones(VentaPersonalizacionOpcion ventaPersonalizacionOpcion) {
        this.personalizaciones.remove(ventaPersonalizacionOpcion);
        ventaPersonalizacionOpcion.setVenta(null);
        return this;
    }

    public Set<Adicional> getAdicionales() {
        return this.adicionales;
    }

    public void setAdicionales(Set<Adicional> adicionals) {
        this.adicionales = adicionals;
    }

    public Venta adicionales(Set<Adicional> adicionals) {
        this.setAdicionales(adicionals);
        return this;
    }

    public Venta addAdicionales(Adicional adicional) {
        this.adicionales.add(adicional);
        adicional.getVentas().add(this);
        return this;
    }

    public Venta removeAdicionales(Adicional adicional) {
        this.adicionales.remove(adicional);
        adicional.getVentas().remove(this);
        return this;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return this.caracteristicas;
    }

    public void setCaracteristicas(Set<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Venta caracteristicas(Set<Caracteristica> caracteristicas) {
        this.setCaracteristicas(caracteristicas);
        return this;
    }

    public Venta addCaracteristicas(Caracteristica caracteristica) {
        this.caracteristicas.add(caracteristica);
        caracteristica.getVentas().add(this);
        return this;
    }

    public Venta removeCaracteristicas(Caracteristica caracteristica) {
        this.caracteristicas.remove(caracteristica);
        caracteristica.getVentas().remove(this);
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public Venta user(User user) {
        this.setUser(user);
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Venta)) {
            return false;
        }
        return id != null && id.equals(((Venta) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Venta{" +
            "id=" + getId() +
            ", idDispositivo=" + getIdDispositivo() +
            ", codigo='" + getCodigo() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", precioBase=" + getPrecioBase() +
            ", moneda='" + getMoneda() + "'" +
            ", fechaVenta='" + getFechaVenta() + "'" +
            ", precioFinal=" + getPrecioFinal() +
            "}";
    }
}
