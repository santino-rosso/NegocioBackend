package ar.edu.um.programacion2.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.*;

@SuppressWarnings("common-java:DuplicatedBlocks")
public class AdicionalServicioDTO implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    private BigDecimal precio;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdicionalServicioDTO adicionalServicioDTO = (AdicionalServicioDTO) o;
        if (adicionalServicioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adicionalServicioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AdicionalServicioDTO{" +
            "id=" + getId() +
            ", precio=" + getPrecio() +
            "}";
    }
}
