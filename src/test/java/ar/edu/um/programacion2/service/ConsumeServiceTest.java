package ar.edu.um.programacion2.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import ar.edu.um.programacion2.service.dto.DispositivoDTO;
import ar.edu.um.programacion2.service.dto.TokenResponseDTO;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class ConsumeServiceTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private DispositivoService dispositivoService;

    @InjectMocks
    private ConsumeService consumeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.header(anyString(), anyString())).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.bodyValue(anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    }

    @Test
    void fetchTokenReturnsValidToken() {
        String token = consumeService.fetchToken();
        assertNotNull(token);
        verify(webClient.post(), times(1));
    }

    @Test
    void fetchAndUpdateDispositivosFetchesAndSavesDispositivos() {
        DispositivoDTO dispositivo = new DispositivoDTO();
        dispositivo.setId(1L);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.header(anyString(), anyString())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToFlux(DispositivoDTO.class)).thenReturn(Flux.just(dispositivo));
        when(dispositivoService.findAllNoPag()).thenReturn(Collections.emptyList());

        consumeService.fetchAndUpdateDispositivos();

        verify(dispositivoService, times(2)).save(any(DispositivoDTO.class));
    }

    @Test
    void saveOrUpdateCambiosUpdatesExistingDispositivo() {
        DispositivoDTO existingDispositivo = new DispositivoDTO();
        existingDispositivo.setId(1L);
        existingDispositivo.setNombre("Nombre");

        DispositivoDTO newDispositivo = new DispositivoDTO();
        newDispositivo.setId(1L);
        newDispositivo.setNombre("Nuevo Nombre");

        when(dispositivoService.findAllNoPag()).thenReturn(Collections.singletonList(existingDispositivo));

        consumeService.saveOrUpdateCambios(Collections.singletonList(newDispositivo));

        verify(dispositivoService, times(1)).update(newDispositivo);
        verify(dispositivoService, never()).save(newDispositivo);
    }

    @Test
    void saveOrUpdateCambiosSavesNewDispositivo() {
        DispositivoDTO newDispositivo = new DispositivoDTO();
        newDispositivo.setId(1L);
        newDispositivo.setNombre("Nombre");

        when(dispositivoService.findAllNoPag()).thenReturn(Collections.emptyList());

        consumeService.saveOrUpdateCambios(Collections.singletonList(newDispositivo));

        verify(dispositivoService, times(1)).save(newDispositivo);
        verify(dispositivoService, never()).update(newDispositivo);
    }

    @Test
    void saveOrUpdateCambiosDoesNotUpdateUnchangedDispositivo() {
        DispositivoDTO existingDispositivo = new DispositivoDTO();
        existingDispositivo.setId(1L);
        existingDispositivo.setNombre("Nombre");

        DispositivoDTO newDispositivo = new DispositivoDTO();
        newDispositivo.setId(1L);
        newDispositivo.setNombre("Nombre");

        when(dispositivoService.findAllNoPag()).thenReturn(Collections.singletonList(existingDispositivo));

        consumeService.saveOrUpdateCambios(Collections.singletonList(newDispositivo));

        verify(dispositivoService, never()).update(newDispositivo);
        verify(dispositivoService, never()).save(newDispositivo);
    }
}
