package ar.edu.um.programacion2.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import ar.edu.um.programacion2.domain.Opcion;
import ar.edu.um.programacion2.domain.Personalizacion;
import ar.edu.um.programacion2.domain.VentaPersonalizacionOpcion;
import ar.edu.um.programacion2.service.dto.OpcionVentaDTO;
import ar.edu.um.programacion2.service.dto.PersonalizacionVentaDTO;
import ar.edu.um.programacion2.service.dto.VentaPersonalizacionOpcionDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VentaPersonalizacionOpcionMapperTest {

    private VentaPersonalizacionOpcionMapper ventaPersonalizacionOpcionMapper;

    @BeforeEach
    public void setUp() {
        ventaPersonalizacionOpcionMapper = new VentaPersonalizacionOpcionMapperImpl();
    }

    @Test
    void toDtoMapsVentaPersonalizacionOpcionToVentaPersonalizacionOpcionDTO() {
        VentaPersonalizacionOpcion ventaPersonalizacionOpcion = new VentaPersonalizacionOpcion();
        ventaPersonalizacionOpcion.setId(1L);
        Opcion opcion = new Opcion();
        opcion.setId(2L);
        Personalizacion personalizacion = new Personalizacion();
        personalizacion.setId(3L);
        ventaPersonalizacionOpcion.setOpcion(opcion);
        ventaPersonalizacionOpcion.setPersonalizacion(personalizacion);

        VentaPersonalizacionOpcionDTO result = ventaPersonalizacionOpcionMapper.toDto(ventaPersonalizacionOpcion);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertNotNull(result.getOpcion());
        assertEquals(Long.valueOf(2L), result.getOpcion().getId());
        assertNotNull(result.getPersonalizacion());
        assertEquals(Long.valueOf(3L), result.getPersonalizacion().getId());
    }

    @Test
    void toDtoHandlesNullFields() {
        VentaPersonalizacionOpcion ventaPersonalizacionOpcion = new VentaPersonalizacionOpcion();
        ventaPersonalizacionOpcion.setId(1L);

        VentaPersonalizacionOpcionDTO result = ventaPersonalizacionOpcionMapper.toDto(ventaPersonalizacionOpcion);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertNull(result.getOpcion());
        assertNull(result.getPersonalizacion());
    }

    @Test
    void toDtoPersonalizacionVentaIdMapsPersonalizacionToPersonalizacionVentaDTO() {
        Personalizacion personalizacion = new Personalizacion();
        personalizacion.setId(1L);

        PersonalizacionVentaDTO result = ventaPersonalizacionOpcionMapper.toDtoPersonalizacionVentaId(personalizacion);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
    }

    @Test
    void toDtoPersonalizacionVentaIdHandlesNullPersonalizacion() {
        PersonalizacionVentaDTO result = ventaPersonalizacionOpcionMapper.toDtoPersonalizacionVentaId(null);

        assertNull(result);
    }

    @Test
    void toDtoOpcionVentaIdMapsOpcionToOpcionVentaDTO() {
        Opcion opcion = new Opcion();
        opcion.setId(1L);

        OpcionVentaDTO result = ventaPersonalizacionOpcionMapper.toDtoOpcionVentaId(opcion);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
    }

    @Test
    void toDtoOpcionVentaIdHandlesNullOpcion() {
        OpcionVentaDTO result = ventaPersonalizacionOpcionMapper.toDtoOpcionVentaId(null);

        assertNull(result);
    }
}
