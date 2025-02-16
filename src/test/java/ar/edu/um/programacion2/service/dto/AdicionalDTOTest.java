package ar.edu.um.programacion2.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.web.rest.TestUtil;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class AdicionalDTOTest {

    @Test
    void equalsVerifier() throws Exception {
        AdicionalDTO adicionalDTO1 = new AdicionalDTO();
        adicionalDTO1.setId(1L);
        AdicionalDTO adicionalDTO2 = new AdicionalDTO();
        assertThat(adicionalDTO1).isNotEqualTo(adicionalDTO2);
        adicionalDTO2.setId(adicionalDTO1.getId());
        assertThat(adicionalDTO1).isEqualTo(adicionalDTO2);
    }

    @Test
    void hashCodeVerifier() {
        AdicionalDTO adicionalDTO1 = new AdicionalDTO();
        adicionalDTO1.setId(1L);
        AdicionalDTO adicionalDTO2 = new AdicionalDTO();
        adicionalDTO2.setId(1L);
        assertThat(adicionalDTO1.hashCode()).isEqualTo(adicionalDTO2.hashCode());
        adicionalDTO2.setId(2L);
        assertThat(adicionalDTO1.hashCode()).isNotEqualTo(adicionalDTO2.hashCode());
    }

    @Test
    void toStringVerifier() {
        AdicionalDTO adicionalDTO = new AdicionalDTO();
        adicionalDTO.setId(1L);
        adicionalDTO.setNombre("Nombre");
        adicionalDTO.setDescripcion("Descripcion");
        adicionalDTO.setPrecio(BigDecimal.valueOf(100));
        adicionalDTO.setPrecioGratis(BigDecimal.ZERO);
        String expected = "AdicionalDTO{id=1, nombre='Nombre', descripcion='Descripcion', precio=100, precioGratis=0}";
        assertThat(adicionalDTO.toString()).isEqualTo(expected);
    }
}
