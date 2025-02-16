package ar.edu.um.programacion2.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import ar.edu.um.programacion2.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PersonalizacionServicioDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonalizacionServicioDTO.class);
        PersonalizacionServicioDTO personalizacionServicioDTO1 = new PersonalizacionServicioDTO();
        personalizacionServicioDTO1.setId(1L);
        PersonalizacionServicioDTO personalizacionServicioDTO2 = new PersonalizacionServicioDTO();
        assertThat(personalizacionServicioDTO1).isNotEqualTo(personalizacionServicioDTO2);
        personalizacionServicioDTO2.setId(personalizacionServicioDTO1.getId());
        assertThat(personalizacionServicioDTO1).isEqualTo(personalizacionServicioDTO2);
        personalizacionServicioDTO2.setId(2L);
        assertThat(personalizacionServicioDTO1).isNotEqualTo(personalizacionServicioDTO2);
        personalizacionServicioDTO1.setId(null);
        assertThat(personalizacionServicioDTO1).isNotEqualTo(personalizacionServicioDTO2);
    }

    @Test
    void dtoHashCodeVerifier() throws Exception {
        TestUtil.equalsVerifier(PersonalizacionServicioDTO.class);
        PersonalizacionServicioDTO personalizacionServicioDTO1 = new PersonalizacionServicioDTO();
        personalizacionServicioDTO1.setId(1L);
        PersonalizacionServicioDTO personalizacionServicioDTO2 = new PersonalizacionServicioDTO();
        assertThat(personalizacionServicioDTO1).isNotEqualTo(personalizacionServicioDTO2);
        personalizacionServicioDTO2.setId(personalizacionServicioDTO1.getId());
        assertThat(personalizacionServicioDTO1).isEqualTo(personalizacionServicioDTO2);
        personalizacionServicioDTO2.setId(2L);
        assertThat(personalizacionServicioDTO1).isNotEqualTo(personalizacionServicioDTO2);
        personalizacionServicioDTO1.setId(null);
        assertThat(personalizacionServicioDTO1).isNotEqualTo(personalizacionServicioDTO2);
    }
}
