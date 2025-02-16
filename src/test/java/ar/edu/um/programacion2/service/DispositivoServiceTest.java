package ar.edu.um.programacion2.service;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import ar.edu.um.programacion2.domain.Dispositivo;
import ar.edu.um.programacion2.repository.DispositivoRepository;
import ar.edu.um.programacion2.service.DispositivoService;
import ar.edu.um.programacion2.service.dto.DispositivoDTO;
import ar.edu.um.programacion2.service.mapper.DispositivoMapper;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DispositivoServiceTest {

    @Mock
    private DispositivoRepository dispositivoRepository;

    @Mock
    private DispositivoMapper dispositivoMapper;

    @InjectMocks
    private DispositivoService dispositivoService;

    @Test
    void findAllNoPagReturnsAllDispositivos() {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setId(1L);
        DispositivoDTO dispositivoDTO = new DispositivoDTO();
        dispositivoDTO.setId(1L);

        when(dispositivoRepository.findAll()).thenReturn(Collections.singletonList(dispositivo));
        when(dispositivoMapper.toDto(dispositivo)).thenReturn(dispositivoDTO);

        List<DispositivoDTO> result = dispositivoService.findAllNoPag();

        assertEquals(1, result.size());
        assertEquals(dispositivoDTO, result.get(0));
    }

    @Test
    void findAllNoPagReturnsEmptyListWhenNoDispositivos() {
        when(dispositivoRepository.findAll()).thenReturn(Collections.emptyList());

        List<DispositivoDTO> result = dispositivoService.findAllNoPag();

        assertTrue(result.isEmpty());
    }

    @Test
    void findAllNoPagHandlesNullDispositivoList() {
        when(dispositivoRepository.findAll()).thenReturn(Collections.emptyList());

        List<DispositivoDTO> result = dispositivoService.findAllNoPag();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
