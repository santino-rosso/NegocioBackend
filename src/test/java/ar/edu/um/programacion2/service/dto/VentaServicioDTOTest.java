package ar.edu.um.programacion2.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VentaServicioDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VentaServicioDTO.class);
        VentaServicioDTO ventaServicioDTO1 = new VentaServicioDTO();
        ventaServicioDTO1.setIdDispositivo(1L);
        VentaServicioDTO ventaServicioDTO2 = new VentaServicioDTO();
        assertThat(ventaServicioDTO1).isNotEqualTo(ventaServicioDTO2);
        ventaServicioDTO2.setIdDispositivo(ventaServicioDTO1.getIdDispositivo());
        assertThat(ventaServicioDTO1).isEqualTo(ventaServicioDTO2);
        ventaServicioDTO2.setIdDispositivo(2L);
        assertThat(ventaServicioDTO1).isNotEqualTo(ventaServicioDTO2);
        ventaServicioDTO1.setIdDispositivo(null);
        assertThat(ventaServicioDTO1).isNotEqualTo(ventaServicioDTO2);
    }

    @Test
    void dtoHashCodeVerifier() throws Exception {
        TestUtil.equalsVerifier(VentaServicioDTO.class);
        VentaServicioDTO ventaServicioDTO1 = new VentaServicioDTO();
        ventaServicioDTO1.setIdDispositivo(1L);
        VentaServicioDTO ventaServicioDTO2 = new VentaServicioDTO();
        assertThat(ventaServicioDTO1.hashCode()).isNotEqualTo(ventaServicioDTO2.hashCode());
        ventaServicioDTO2.setIdDispositivo(ventaServicioDTO1.getIdDispositivo());
        assertThat(ventaServicioDTO1.hashCode()).isEqualTo(ventaServicioDTO2.hashCode());
        ventaServicioDTO2.setIdDispositivo(2L);
        assertThat(ventaServicioDTO1.hashCode()).isNotEqualTo(ventaServicioDTO2.hashCode());
        ventaServicioDTO1.setIdDispositivo(null);
        assertThat(ventaServicioDTO1.hashCode()).isNotEqualTo(ventaServicioDTO2.hashCode());
    }
}
