package ar.edu.um.programacion2.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PersonalizacionVentaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonalizacionVentaDTO.class);
        PersonalizacionVentaDTO personalizacionVentaDTO1 = new PersonalizacionVentaDTO();
        personalizacionVentaDTO1.setId(1L);
        PersonalizacionVentaDTO personalizacionVentaDTO2 = new PersonalizacionVentaDTO();
        assertThat(personalizacionVentaDTO1).isNotEqualTo(personalizacionVentaDTO2);
        personalizacionVentaDTO2.setId(personalizacionVentaDTO1.getId());
        assertThat(personalizacionVentaDTO1).isEqualTo(personalizacionVentaDTO2);
        personalizacionVentaDTO2.setId(2L);
        assertThat(personalizacionVentaDTO1).isNotEqualTo(personalizacionVentaDTO2);
        personalizacionVentaDTO1.setId(null);
        assertThat(personalizacionVentaDTO1).isNotEqualTo(personalizacionVentaDTO2);
    }

    @Test
    void dtoHashCodeVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonalizacionVentaDTO.class);
        PersonalizacionVentaDTO personalizacionVentaDTO1 = new PersonalizacionVentaDTO();
        personalizacionVentaDTO1.setId(1L);
        PersonalizacionVentaDTO personalizacionVentaDTO2 = new PersonalizacionVentaDTO();
        personalizacionVentaDTO2.setId(personalizacionVentaDTO1.getId());
        assertThat(personalizacionVentaDTO1).hasSameHashCodeAs(personalizacionVentaDTO2);
        personalizacionVentaDTO2.setId(2L);
        assertThat(personalizacionVentaDTO1).isNotEqualTo(personalizacionVentaDTO2);
        personalizacionVentaDTO1.setId(null);
        assertThat(personalizacionVentaDTO1).isNotEqualTo(personalizacionVentaDTO2);
    }
}
