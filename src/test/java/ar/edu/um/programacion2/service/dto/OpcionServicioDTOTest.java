package ar.edu.um.programacion2.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OpcionServicioDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OpcionServicioDTO.class);
        OpcionServicioDTO opcionServicioDTO1 = new OpcionServicioDTO();
        opcionServicioDTO1.setId(1L);
        OpcionServicioDTO opcionServicioDTO2 = new OpcionServicioDTO();
        assertThat(opcionServicioDTO1).isNotEqualTo(opcionServicioDTO2);
        opcionServicioDTO2.setId(opcionServicioDTO1.getId());
        assertThat(opcionServicioDTO1).isEqualTo(opcionServicioDTO2);
        opcionServicioDTO2.setId(2L);
        assertThat(opcionServicioDTO1).isNotEqualTo(opcionServicioDTO2);
        opcionServicioDTO1.setId(null);
        assertThat(opcionServicioDTO1).isNotEqualTo(opcionServicioDTO2);
    }

    @Test
    void dtoHashCodeVerifier() throws Exception {
        TestUtil.equalsVerifier(OpcionServicioDTO.class);
        OpcionServicioDTO opcionServicioDTO1 = new OpcionServicioDTO();
        opcionServicioDTO1.setId(1L);
        OpcionServicioDTO opcionServicioDTO2 = new OpcionServicioDTO();
        assertThat(opcionServicioDTO1.hashCode()).isNotEqualTo(opcionServicioDTO2.hashCode());
        opcionServicioDTO2.setId(opcionServicioDTO1.getId());
        assertThat(opcionServicioDTO1.hashCode()).isEqualTo(opcionServicioDTO2.hashCode());
        opcionServicioDTO2.setId(2L);
        assertThat(opcionServicioDTO1.hashCode()).isNotEqualTo(opcionServicioDTO2.hashCode());
        opcionServicioDTO1.setId(null);
        assertThat(opcionServicioDTO1.hashCode()).isNotEqualTo(opcionServicioDTO2.hashCode());
    }
}
