package ar.edu.um.programacion2.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import ar.edu.um.programacion2.domain.Caracteristica;
import ar.edu.um.programacion2.domain.Dispositivo;
import ar.edu.um.programacion2.service.dto.CaracteristicaDTO;
import ar.edu.um.programacion2.service.dto.DispositivoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaracteristicaMapperTest {

    private CaracteristicaMapper caracteristicaMapper;

    @BeforeEach
    public void setUp() {
        caracteristicaMapper = new CaracteristicaMapperImpl();
    }

    @Test
    void toDtoMapsCaracteristicaToCaracteristicaDTO() {
        Caracteristica caracteristica = new Caracteristica();
        caracteristica.setId(1L);
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setId(2L);
        caracteristica.setDispositivo(dispositivo);

        CaracteristicaDTO result = caracteristicaMapper.toDto(caracteristica);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertNotNull(result.getDispositivo());
        assertEquals(Long.valueOf(2L), result.getDispositivo().getId());
    }

    @Test
    void toDtoDispositivoIdMapsDispositivoToDispositivoDTO() {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setId(1L);

        DispositivoDTO result = caracteristicaMapper.toDtoDispositivoId(dispositivo);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
    }

    @Test
    void toDtoHandlesNullDispositivo() {
        Caracteristica caracteristica = new Caracteristica();
        caracteristica.setId(1L);
        caracteristica.setDispositivo(null);

        CaracteristicaDTO result = caracteristicaMapper.toDto(caracteristica);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertNull(result.getDispositivo());
    }
}
