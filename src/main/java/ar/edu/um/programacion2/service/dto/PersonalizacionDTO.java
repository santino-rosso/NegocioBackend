package ar.edu.um.programacion2.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ar.edu.um.programacion2.domain.Personalizacion} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PersonalizacionDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String descripcion;

    @JsonIgnore
    private DispositivoDTO dispositivo;

    private Set<OpcionDTO> opciones;

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

    public DispositivoDTO getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(DispositivoDTO dispositivo) {
        this.dispositivo = dispositivo;
    }

    public Set<OpcionDTO> getOpciones() {
        return opciones;
    }

    public void setOpciones(Set<OpcionDTO> opciones) {
        this.opciones = opciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalizacionDTO that = (PersonalizacionDTO) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(nombre, that.nombre) &&
            Objects.equals(descripcion, that.descripcion) &&
            Objects.equals(dispositivo, that.dispositivo) &&
            Objects.equals(opciones, that.opciones)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, dispositivo, opciones);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonalizacionDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            //", dispositivo=" + getDispositivo() +
            "}";
    }
}
