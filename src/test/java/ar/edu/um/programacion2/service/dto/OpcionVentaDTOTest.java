package ar.edu.um.programacion2.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OpcionVentaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OpcionVentaDTO.class);
        OpcionVentaDTO opcionVentaDTO1 = new OpcionVentaDTO();
        opcionVentaDTO1.setId(1L);
        OpcionVentaDTO opcionVentaDTO2 = new OpcionVentaDTO();
        assertThat(opcionVentaDTO1).isNotEqualTo(opcionVentaDTO2);
        opcionVentaDTO2.setId(opcionVentaDTO1.getId());
        assertThat(opcionVentaDTO1).isEqualTo(opcionVentaDTO2);
        opcionVentaDTO2.setId(2L);
        assertThat(opcionVentaDTO1).isNotEqualTo(opcionVentaDTO2);
        opcionVentaDTO1.setId(null);
        assertThat(opcionVentaDTO1).isNotEqualTo(opcionVentaDTO2);
    }

    @Test
    void dtoHashCodeVerifier() throws Exception {
        TestUtil.equalsVerifier(OpcionVentaDTO.class);
        OpcionVentaDTO opcionVentaDTO1 = new OpcionVentaDTO();
        opcionVentaDTO1.setId(1L);
        OpcionVentaDTO opcionVentaDTO2 = new OpcionVentaDTO();
        assertThat(opcionVentaDTO1).isNotEqualTo(opcionVentaDTO2);
        opcionVentaDTO2.setId(opcionVentaDTO1.getId());
        assertThat(opcionVentaDTO1).isEqualTo(opcionVentaDTO2);
        opcionVentaDTO2.setId(2L);
        assertThat(opcionVentaDTO1).isNotEqualTo(opcionVentaDTO2);
        opcionVentaDTO1.setId(null);
        assertThat(opcionVentaDTO1).isNotEqualTo(opcionVentaDTO2);
    }
}
