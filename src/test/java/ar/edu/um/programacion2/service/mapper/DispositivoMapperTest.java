package ar.edu.um.programacion2.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.*;

import ar.edu.um.programacion2.domain.*;
import ar.edu.um.programacion2.service.dto.AdicionalDTO;
import ar.edu.um.programacion2.service.dto.DispositivoDTO;
import java.math.BigDecimal;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DispositivoMapperTest {

    private DispositivoMapper dispositivoMapper;

    @BeforeEach
    public void setUp() {
        dispositivoMapper = new DispositivoMapperImpl();
    }

    @Test
    void toDtoMapsDispositivoToDispositivoDTO() {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setId(1L);
        dispositivo.setCodigo("D001");
        dispositivo.setNombre("Dispositivo 1");
        dispositivo.setDescripcion("Descripcion 1");
        dispositivo.setPrecioBase(BigDecimal.valueOf(100.0));
        dispositivo.setMoneda("USD");

        Caracteristica caracteristica = new Caracteristica();
        caracteristica.setId(1L);
        caracteristica.setNombre("Caracteristica 1");
        caracteristica.setDescripcion("Descripcion 1");
        dispositivo.setCaracteristicas(Set.of(caracteristica));

        Personalizacion personalizacion = new Personalizacion();
        personalizacion.setId(1L);
        personalizacion.setNombre("Personalizacion 1");
        personalizacion.setDescripcion("Descripcion 1");
        Opcion opcion = new Opcion();
        opcion.setId(1L);
        opcion.setCodigo("O001");
        opcion.setNombre("Opcion 1");
        opcion.setDescripcion("Descripcion 1");
        opcion.setPrecioAdicional(BigDecimal.valueOf(10.0));
        personalizacion.setOpciones(Set.of(opcion));
        dispositivo.setPersonalizaciones(Set.of(personalizacion));

        Adicional adicional = new Adicional();
        adicional.setId(1L);
        adicional.setNombre("Adicional 1");
        adicional.setDescripcion("Descripcion 1");
        adicional.setPrecio(BigDecimal.valueOf(5.0));
        adicional.setPrecioGratis(BigDecimal.valueOf(5.0));
        dispositivo.setAdicionales(Set.of(adicional));

        DispositivoDTO result = dispositivoMapper.toDto(dispositivo);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertEquals("D001", result.getCodigo());
        assertEquals("Dispositivo 1", result.getNombre());
        assertEquals("Descripcion 1", result.getDescripcion());
        assertEquals(BigDecimal.valueOf(100.0), result.getPrecioBase());
        assertEquals("USD", result.getMoneda());
        assertEquals(1, result.getCaracteristicas().size());
        assertEquals(1, result.getPersonalizaciones().size());
        assertEquals(1, result.getAdicionales().size());
    }

    @Test
    void toDtoHandlesNullFields() {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setId(1L);

        DispositivoDTO result = dispositivoMapper.toDto(dispositivo);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertNull(result.getCodigo());
        assertNull(result.getNombre());
        assertNull(result.getDescripcion());
        assertNull(result.getPrecioBase());
        assertNull(result.getMoneda());
        assertTrue(result.getCaracteristicas().isEmpty());
        assertTrue(result.getPersonalizaciones().isEmpty());
        assertTrue(result.getAdicionales().isEmpty());
    }

    @Test
    void toEntityMapsDispositivoDTOToDispositivo() {
        DispositivoDTO dispositivoDTO = new DispositivoDTO();
        dispositivoDTO.setId(1L);
        dispositivoDTO.setCodigo("D001");
        dispositivoDTO.setNombre("Dispositivo 1");
        dispositivoDTO.setDescripcion("Descripcion 1");
        dispositivoDTO.setPrecioBase(BigDecimal.valueOf(100.0));
        dispositivoDTO.setMoneda("USD");

        Dispositivo result = dispositivoMapper.toEntity(dispositivoDTO);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertEquals("D001", result.getCodigo());
        assertEquals("Dispositivo 1", result.getNombre());
        assertEquals("Descripcion 1", result.getDescripcion());
        assertEquals(BigDecimal.valueOf(100.0), result.getPrecioBase());
        assertEquals("USD", result.getMoneda());
    }

    @Test
    void toEntityHandlesNullFields() {
        DispositivoDTO dispositivoDTO = new DispositivoDTO();
        dispositivoDTO.setId(1L);

        Dispositivo result = dispositivoMapper.toEntity(dispositivoDTO);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertNull(result.getCodigo());
        assertNull(result.getNombre());
        assertNull(result.getDescripcion());
        assertNull(result.getPrecioBase());
        assertNull(result.getMoneda());
        assertTrue(result.getCaracteristicas().isEmpty());
        assertTrue(result.getPersonalizaciones().isEmpty());
        assertTrue(result.getAdicionales().isEmpty());
    }

    @Test
    void toDtoAdicionalIdMapsAdicionalToAdicionalDTO() {
        Adicional adicional = new Adicional();
        adicional.setId(1L);

        AdicionalDTO result = dispositivoMapper.toDtoAdicionalId(adicional);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
    }

    @Test
    void toDtoAdicionalIdHandlesNullAdicional() {
        AdicionalDTO result = dispositivoMapper.toDtoAdicionalId(null);

        assertNull(result);
    }
}
