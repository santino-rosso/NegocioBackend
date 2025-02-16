package ar.edu.um.programacion2.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AdicionalServicioDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AdicionalServicioDTO.class);
        AdicionalServicioDTO adicionalServicioDTO1 = new AdicionalServicioDTO();
        adicionalServicioDTO1.setId(1L);
        AdicionalServicioDTO adicionalServicioDTO2 = new AdicionalServicioDTO();
        assertThat(adicionalServicioDTO1).isNotEqualTo(adicionalServicioDTO2);
        adicionalServicioDTO2.setId(adicionalServicioDTO1.getId());
        assertThat(adicionalServicioDTO1).isEqualTo(adicionalServicioDTO2);
        adicionalServicioDTO2.setId(2L);
        assertThat(adicionalServicioDTO1).isNotEqualTo(adicionalServicioDTO2);
        adicionalServicioDTO1.setId(null);
        assertThat(adicionalServicioDTO1).isNotEqualTo(adicionalServicioDTO2);
    }

    @Test
    void dtoHashCodeVerifier() throws Exception {
        TestUtil.equalsVerifier(AdicionalServicioDTO.class);
        AdicionalServicioDTO adicionalServicioDTO1 = new AdicionalServicioDTO();
        adicionalServicioDTO1.setId(1L);
        AdicionalServicioDTO adicionalServicioDTO2 = new AdicionalServicioDTO();
        adicionalServicioDTO2.setId(adicionalServicioDTO1.getId());
        assertThat(adicionalServicioDTO1).isEqualTo(adicionalServicioDTO2);
        assertThat(adicionalServicioDTO1.hashCode()).isEqualTo(adicionalServicioDTO2.hashCode());
        adicionalServicioDTO2.setId(2L);
        assertThat(adicionalServicioDTO1).isNotEqualTo(adicionalServicioDTO2);
        assertThat(adicionalServicioDTO1.hashCode()).isNotEqualTo(adicionalServicioDTO2.hashCode());
        adicionalServicioDTO1.setId(null);
        adicionalServicioDTO2.setId(null);
        assertThat(adicionalServicioDTO1.hashCode()).isEqualTo(adicionalServicioDTO2.hashCode());
    }
}
