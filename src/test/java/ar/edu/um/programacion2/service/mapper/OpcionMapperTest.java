package ar.edu.um.programacion2.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import ar.edu.um.programacion2.domain.Opcion;
import ar.edu.um.programacion2.domain.Personalizacion;
import ar.edu.um.programacion2.service.dto.OpcionDTO;
import ar.edu.um.programacion2.service.dto.PersonalizacionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OpcionMapperTest {

    private OpcionMapper opcionMapper;

    @BeforeEach
    public void setUp() {
        opcionMapper = new OpcionMapperImpl();
    }

    @Test
    void toDtoMapsOpcionToOpcionDTO() {
        Opcion opcion = new Opcion();
        opcion.setId(1L);
        Personalizacion personalizacion = new Personalizacion();
        personalizacion.setId(2L);
        opcion.setPersonalizacion(personalizacion);

        OpcionDTO result = opcionMapper.toDto(opcion);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertNotNull(result.getPersonalizacion());
        assertEquals(Long.valueOf(2L), result.getPersonalizacion().getId());
    }

    @Test
    void toDtoHandlesNullPersonalizacion() {
        Opcion opcion = new Opcion();
        opcion.setId(1L);
        opcion.setPersonalizacion(null);

        OpcionDTO result = opcionMapper.toDto(opcion);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertNull(result.getPersonalizacion());
    }

    @Test
    void toDtoPersonalizacionIdMapsPersonalizacionToPersonalizacionDTO() {
        Personalizacion personalizacion = new Personalizacion();
        personalizacion.setId(1L);

        PersonalizacionDTO result = opcionMapper.toDtoPersonalizacionId(personalizacion);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
    }

    @Test
    void toDtoPersonalizacionIdHandlesNullPersonalizacion() {
        PersonalizacionDTO result = opcionMapper.toDtoPersonalizacionId(null);

        assertNull(result);
    }
}
