package ar.edu.um.programacion2.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import ar.edu.um.programacion2.domain.Dispositivo;
import ar.edu.um.programacion2.domain.Personalizacion;
import ar.edu.um.programacion2.service.dto.DispositivoDTO;
import ar.edu.um.programacion2.service.dto.PersonalizacionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonalizacionMapperTest {

    private PersonalizacionMapper personalizacionMapper;

    @BeforeEach
    public void setUp() {
        personalizacionMapper = new PersonalizacionMapperImpl();
    }

    @Test
    void toDtoMapsPersonalizacionToPersonalizacionDTO() {
        Personalizacion personalizacion = new Personalizacion();
        personalizacion.setId(1L);
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setId(2L);
        personalizacion.setDispositivo(dispositivo);

        PersonalizacionDTO result = personalizacionMapper.toDto(personalizacion);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertNotNull(result.getDispositivo());
        assertEquals(Long.valueOf(2L), result.getDispositivo().getId());
        assertNull(result.getOpciones());
    }

    @Test
    void toDtoHandlesNullDispositivo() {
        Personalizacion personalizacion = new Personalizacion();
        personalizacion.setId(1L);
        personalizacion.setDispositivo(null);

        PersonalizacionDTO result = personalizacionMapper.toDto(personalizacion);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertNull(result.getDispositivo());
        assertNull(result.getOpciones());
    }

    @Test
    void toDtoDispositivoIdMapsDispositivoToDispositivoDTO() {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setId(1L);

        DispositivoDTO result = personalizacionMapper.toDtoDispositivoId(dispositivo);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
    }

    @Test
    void toDtoDispositivoIdHandlesNullDispositivo() {
        DispositivoDTO result = personalizacionMapper.toDtoDispositivoId(null);

        assertNull(result);
    }
}
