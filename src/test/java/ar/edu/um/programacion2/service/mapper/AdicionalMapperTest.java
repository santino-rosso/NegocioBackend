package ar.edu.um.programacion2.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ar.edu.um.programacion2.domain.Adicional;
import ar.edu.um.programacion2.domain.Dispositivo;
import ar.edu.um.programacion2.service.dto.AdicionalDTO;
import ar.edu.um.programacion2.service.dto.DispositivoDTO;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdicionalMapperTest {

    private AdicionalMapper adicionalMapper;

    @BeforeEach
    public void setUp() {
        adicionalMapper = new AdicionalMapperImpl();
    }

    @Test
    void toDtoMapsAdicionalToAdicionalDTO() {
        Adicional adicional = new Adicional();
        adicional.setId(1L);
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setId(2L);
        adicional.setDispositivos(Set.of(dispositivo));

        AdicionalDTO result = adicionalMapper.toDto(adicional);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertEquals(1, result.getDispositivos().size());
        assertEquals(Long.valueOf(2L), result.getDispositivos().iterator().next().getId());
    }

    @Test
    void toEntityMapsAdicionalDTOToAdicional() {
        AdicionalDTO adicionalDTO = new AdicionalDTO();
        adicionalDTO.setId(1L);

        Adicional result = adicionalMapper.toEntity(adicionalDTO);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
        assertTrue(result.getDispositivos().isEmpty());
    }

    @Test
    void toDtoDispositivoIdMapsDispositivoToDispositivoDTO() {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setId(1L);

        DispositivoDTO result = adicionalMapper.toDtoDispositivoId(dispositivo);

        assertNotNull(result);
        assertEquals(Long.valueOf(1L), result.getId());
    }

    @Test
    void toDtoDispositivoIdSetMapsDispositivoSetToDispositivoDTOSet() {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setId(1L);

        Set<DispositivoDTO> result = adicionalMapper.toDtoDispositivoIdSet(Set.of(dispositivo));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Long.valueOf(1L), result.iterator().next().getId());
    }
}
