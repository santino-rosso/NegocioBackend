package ar.edu.um.programacion2.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdicionalVentaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdicionalVentaDTO.class);
        AdicionalVentaDTO adicionalVentaDTO1 = new AdicionalVentaDTO();
        adicionalVentaDTO1.setId(1L);
        AdicionalVentaDTO adicionalVentaDTO2 = new AdicionalVentaDTO();
        assertThat(adicionalVentaDTO1).isNotEqualTo(adicionalVentaDTO2);
        adicionalVentaDTO2.setId(adicionalVentaDTO1.getId());
        assertThat(adicionalVentaDTO1).isEqualTo(adicionalVentaDTO2);
        adicionalVentaDTO2.setId(2L);
        assertThat(adicionalVentaDTO1).isNotEqualTo(adicionalVentaDTO2);
        adicionalVentaDTO1.setId(null);
        assertThat(adicionalVentaDTO1).isNotEqualTo(adicionalVentaDTO2);
    }

    @Test
    void dtoHashCodeVerifier() throws Exception {
        TestUtil.equalsVerifier(AdicionalVentaDTO.class);
        AdicionalVentaDTO adicionalVentaDTO1 = new AdicionalVentaDTO();
        adicionalVentaDTO1.setId(1L);
        AdicionalVentaDTO adicionalVentaDTO2 = new AdicionalVentaDTO();
        assertThat(adicionalVentaDTO1.hashCode()).isNotEqualTo(adicionalVentaDTO2.hashCode());
        adicionalVentaDTO2.setId(adicionalVentaDTO1.getId());
        assertThat(adicionalVentaDTO1.hashCode()).isEqualTo(adicionalVentaDTO2.hashCode());
        adicionalVentaDTO2.setId(2L);
        assertThat(adicionalVentaDTO1.hashCode()).isNotEqualTo(adicionalVentaDTO2.hashCode());
        adicionalVentaDTO1.setId(null);
        assertThat(adicionalVentaDTO1.hashCode()).isNotEqualTo(adicionalVentaDTO2.hashCode());
    }
}
