package ar.edu.um.programacion2.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

@SuppressWarnings("common-java:DuplicatedBlocks")
public class VentaServicioDTO implements Serializable {

    @NotNull
    private Long idDispositivo;

    @NotNull
    private Set<PersonalizacionServicioDTO> personalizaciones;

    private Set<AdicionalServicioDTO> adicionales;

    @NotNull
    private BigDecimal precioFinal;

    @NotNull
    private ZonedDateTime fechaVenta;

    public Long getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public Set<PersonalizacionServicioDTO> getPersonalizaciones() {
        return personalizaciones;
    }

    public void setPersonalizaciones(Set<PersonalizacionServicioDTO> personalizaciones) {
        this.personalizaciones = personalizaciones;
    }

    public Set<AdicionalServicioDTO> getAdicionales() {
        return adicionales;
    }

    public void setAdicionales(Set<AdicionalServicioDTO> adicionales) {
        this.adicionales = adicionales;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }

    public ZonedDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(ZonedDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public VentaServicioDTO idDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VentaServicioDTO ventaServicioDTO = (VentaServicioDTO) o;
        if (ventaServicioDTO.getIdDispositivo() == null || getIdDispositivo() == null) {
            return false;
        }
        return Objects.equals(getIdDispositivo(), ventaServicioDTO.getIdDispositivo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.idDispositivo);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VentaServiceDTO{" +
            ", idDispositivo=" + getIdDispositivo() +
            ", personalizaciones='" + getPersonalizaciones() + "'" +
            ", adicionales='" + getAdicionales() + "'" +
            ", precioFinal=" + getPrecioFinal() +
            ", fechaVenta='" + getFechaVenta() + "'" +
            "}";
    }
}
