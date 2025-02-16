package ar.edu.um.programacion2.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

@SuppressWarnings("common-java:DuplicatedBlocks")
public class PersonalizacionVentaDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonalizacionVentaDTO personalizacionDTO = (PersonalizacionVentaDTO) o;
        if (personalizacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personalizacionDTO.getId());
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
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
