package ar.edu.um.programacion2.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.*;

@SuppressWarnings("common-java:DuplicatedBlocks")
public class PersonalizacionServicioDTO implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    private BigDecimal precio;

    @NotNull
    private OpcionServicioDTO opcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public OpcionServicioDTO getOpcion() {
        return opcion;
    }

    public void setOpcion(OpcionServicioDTO opcion) {
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
        PersonalizacionServicioDTO personalizacionServicioDTO = (PersonalizacionServicioDTO) o;
        if (personalizacionServicioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personalizacionServicioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonalizacionServicioDTO{" +
            "id=" + getId() +
            ", precio=" + getPrecio() +
            ", opcion='" + getOpcion() + "'"+
            "}";
    }
}
