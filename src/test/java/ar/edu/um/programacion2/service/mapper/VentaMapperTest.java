package ar.edu.um.programacion2.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.*;

import ar.edu.um.programacion2.domain.Adicional;
import ar.edu.um.programacion2.domain.Caracteristica;
import ar.edu.um.programacion2.domain.Venta;
import ar.edu.um.programacion2.service.dto.AdicionalVentaDTO;
import ar.edu.um.programacion2.service.dto.CaracteristicaVentaDTO;
import ar.edu.um.programacion2.service.dto.VentaDTO;
import java.math.BigDecimal;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VentaMapperTest {

    private VentaMapper ventaMapper;

    @BeforeEach
    public void setUp() {
        ventaMapper = new VentaMapperImpl();
    }

    @Test
    void toDtoMapsVentaToVentaDTO() {
        Venta venta = new Venta();
        venta.setId(1L);
        Adicional adicional = new Adicional();
        adicional.setId(2L);
        Caracteristica caracteristica = new Caracteristica();
        caracteristica.setId(3L);
        venta.setAdicionales(Set.of(adicional));
        venta.setCaracteristicas(Set.of(caracteristica));

        VentaDTO result = ventaMapper.toDto(venta);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertEquals(1, result.getAdicionales().size());
        assertEquals(1, result.getCaracteristicas().size());
    }

    @Test
    void toDtoHandlesNullFields() {
        Venta venta = new Venta();
        venta.setId(1L);

        VentaDTO result = ventaMapper.toDto(venta);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertTrue(result.getAdicionales().isEmpty());
        assertTrue(result.getCaracteristicas().isEmpty());
    }

    @Test
    void toEntityMapsVentaDTOToVenta() {
        VentaDTO ventaDTO = new VentaDTO();
        ventaDTO.setId(1L);
        AdicionalVentaDTO adicionalDTO = new AdicionalVentaDTO();
        adicionalDTO.setId(2L);
        CaracteristicaVentaDTO caracteristicaDTO = new CaracteristicaVentaDTO();
        caracteristicaDTO.setId(3L);
        ventaDTO.setAdicionales(Set.of(adicionalDTO));
        ventaDTO.setCaracteristicas(Set.of(caracteristicaDTO));

        Venta result = ventaMapper.toEntity(ventaDTO);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertEquals(1, result.getAdicionales().size());
        assertEquals(1, result.getCaracteristicas().size());
    }

    @Test
    void toDtoAdicionalIdMapsAdicionalToAdicionalVentaDTO() {
        Adicional adicional = new Adicional();
        adicional.setId(1L);
        adicional.setNombre("Adicional 1");
        adicional.setDescripcion("Descripcion 1");
        adicional.setPrecio(BigDecimal.valueOf(10.0));

        AdicionalVentaDTO result = ventaMapper.toDtoAdicionalId(adicional);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertEquals("Adicional 1", result.getNombre());
        assertEquals("Descripcion 1", result.getDescripcion());
        assertEquals(BigDecimal.valueOf(10.0), result.getPrecio());
    }

    @Test
    void toDtoAdicionalIdHandlesNullAdicional() {
        AdicionalVentaDTO result = ventaMapper.toDtoAdicionalId(null);

        assertNull(result);
    }

    @Test
    void toDtoAdicionalIdSetMapsAdicionalSetToAdicionalVentaDTOSet() {
        Adicional adicional = new Adicional();
        adicional.setId(1L);

        Set<AdicionalVentaDTO> result = ventaMapper.toDtoAdicionalIdSet(Set.of(adicional));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Long.valueOf(1L), result.iterator().next().getId());
    }

    @Test
    void toDtoAdicionalIdSetHandlesEmptySet() {
        Set<AdicionalVentaDTO> result = ventaMapper.toDtoAdicionalIdSet(Set.of());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void toDtoCaracteristicaIdMapsCaracteristicaToCaracteristicaVentaDTO() {
        Caracteristica caracteristica = new Caracteristica();
        caracteristica.setId(1L);
        caracteristica.setNombre("Caracteristica 1");
        caracteristica.setDescripcion("Descripcion 1");

        CaracteristicaVentaDTO result = ventaMapper.toDtoCaracteristicaId(caracteristica);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertEquals("Caracteristica 1", result.getNombre());
        assertEquals("Descripcion 1", result.getDescripcion());
    }

    @Test
    void toDtoCaracteristicaIdHandlesNullCaracteristica() {
        CaracteristicaVentaDTO result = ventaMapper.toDtoCaracteristicaId(null);

        assertNull(result);
    }

    @Test
    void toDtoCaracteristicaIdSetMapsCaracteristicaSetToCaracteristicaVentaDTOSet() {
        Caracteristica caracteristica = new Caracteristica();
        caracteristica.setId(1L);

        Set<CaracteristicaVentaDTO> result = ventaMapper.toDtoCaracteristicaIdSet(Set.of(caracteristica));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Long.valueOf(1L), result.iterator().next().getId());
    }

    @Test
    void toDtoCaracteristicaIdSetHandlesEmptySet() {
        Set<CaracteristicaVentaDTO> result = ventaMapper.toDtoCaracteristicaIdSet(Set.of());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
