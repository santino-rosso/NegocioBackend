package ar.edu.um.programacion2.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VentaPersonalizacionOpcionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VentaPersonalizacionOpcionDTO.class);
        VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO1 = new VentaPersonalizacionOpcionDTO();
        ventaPersonalizacionOpcionDTO1.setId(1L);
        VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO2 = new VentaPersonalizacionOpcionDTO();
        assertThat(ventaPersonalizacionOpcionDTO1).isNotEqualTo(ventaPersonalizacionOpcionDTO2);
        ventaPersonalizacionOpcionDTO2.setId(ventaPersonalizacionOpcionDTO1.getId());
        assertThat(ventaPersonalizacionOpcionDTO1).isEqualTo(ventaPersonalizacionOpcionDTO2);
        ventaPersonalizacionOpcionDTO2.setId(2L);
        assertThat(ventaPersonalizacionOpcionDTO1).isNotEqualTo(ventaPersonalizacionOpcionDTO2);
        ventaPersonalizacionOpcionDTO1.setId(null);
        assertThat(ventaPersonalizacionOpcionDTO1).isNotEqualTo(ventaPersonalizacionOpcionDTO2);
    }
}
