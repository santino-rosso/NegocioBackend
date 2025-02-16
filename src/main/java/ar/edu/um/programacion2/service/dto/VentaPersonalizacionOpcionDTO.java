package ar.edu.um.programacion2.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ar.edu.um.programacion2.domain.VentaPersonalizacionOpcion} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VentaPersonalizacionOpcionDTO implements Serializable {

    private Long id;

    private Long personalizacionId;

    private String nombre;

    private String descripcion;

    @JsonIgnore
    private PersonalizacionVentaDTO personalizacion;

    private OpcionVentaDTO opcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonalizacionId() {
        return (personalizacion != null) ? personalizacion.getId() : personalizacionId;
    }

    public void setPersonalizacionId(Long personalizacionId) {
        this.personalizacionId = personalizacionId;
        if (this.personalizacion != null) {
            this.personalizacion.setId(personalizacionId);
        }
    }

    public String getNombre() {
        return (personalizacion != null) ? personalizacion.getNombre() : nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        if (this.personalizacion != null) {
            this.personalizacion.setNombre(nombre);
        }
    }

    public String getDescripcion() {
        return (personalizacion != null) ? personalizacion.getDescripcion() : descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        if (this.personalizacion != null) {
            this.personalizacion.setDescripcion(descripcion);
        }
    }

    public PersonalizacionVentaDTO getPersonalizacion() {
        return personalizacion;
    }

    public void setPersonalizacion(PersonalizacionVentaDTO personalizacion) {
        this.personalizacion = personalizacion;
    }

    public OpcionVentaDTO getOpcion() {
        return opcion;
    }

    public void setOpcion(OpcionVentaDTO opcion) {
        this.opcion = opcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VentaPersonalizacionOpcionDTO that = (VentaPersonalizacionOpcionDTO) o;
        if (that.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonalizacionDTO{" +
            "id=" + getId() +
            ", personalizacionId=" + getPersonalizacionId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", personalizacion=" + getPersonalizacion() +
            ", opcion=" + getOpcion() +
            "}";
    }
}
