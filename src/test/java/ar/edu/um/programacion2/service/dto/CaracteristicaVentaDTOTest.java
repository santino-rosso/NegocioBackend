package ar.edu.um.programacion2.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CaracteristicaVentaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CaracteristicaVentaDTO.class);
        CaracteristicaVentaDTO caracteristicaVentaDTO1 = new CaracteristicaVentaDTO();
        caracteristicaVentaDTO1.setId(1L);
        CaracteristicaVentaDTO caracteristicaVentaDTO2 = new CaracteristicaVentaDTO();
        assertThat(caracteristicaVentaDTO1).isNotEqualTo(caracteristicaVentaDTO2);
        caracteristicaVentaDTO2.setId(caracteristicaVentaDTO1.getId());
        assertThat(caracteristicaVentaDTO1).isEqualTo(caracteristicaVentaDTO2);
        caracteristicaVentaDTO2.setId(2L);
        assertThat(caracteristicaVentaDTO1).isNotEqualTo(caracteristicaVentaDTO2);
        caracteristicaVentaDTO1.setId(null);
        assertThat(caracteristicaVentaDTO1).isNotEqualTo(caracteristicaVentaDTO2);
    }

    @Test
    void dtoHashCodeVerifier() throws Exception {
        TestUtil.equalsVerifier(CaracteristicaVentaDTO.class);
        CaracteristicaVentaDTO caracteristicaVentaDTO1 = new CaracteristicaVentaDTO();
        caracteristicaVentaDTO1.setId(1L);
        CaracteristicaVentaDTO caracteristicaVentaDTO2 = new CaracteristicaVentaDTO();
        assertThat(caracteristicaVentaDTO1.hashCode()).isNotEqualTo(caracteristicaVentaDTO2.hashCode());
        caracteristicaVentaDTO2.setId(caracteristicaVentaDTO1.getId());
        assertThat(caracteristicaVentaDTO1.hashCode()).isEqualTo(caracteristicaVentaDTO2.hashCode());
        caracteristicaVentaDTO2.setId(2L);
        assertThat(caracteristicaVentaDTO1.hashCode()).isNotEqualTo(caracteristicaVentaDTO2.hashCode());
        caracteristicaVentaDTO1.setId(null);
        assertThat(caracteristicaVentaDTO1.hashCode()).isNotEqualTo(caracteristicaVentaDTO2.hashCode());
    }
}
