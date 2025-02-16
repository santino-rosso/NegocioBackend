package ar.edu.um.programacion2.service;

import ar.edu.um.programacion2.service.dto.*;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@Transactional
public class ConsumeService {

    private final Logger log = LoggerFactory.getLogger(ConsumeService.class);
    private final WebClient webClient;
    private final DispositivoService dispositivoService;
    private String token;

    public ConsumeService(DispositivoService dispositivoService) {
        this.webClient = WebClient.create();
        this.dispositivoService = dispositivoService;
        this.token = fetchToken();
    }

    @Scheduled(initialDelay = 0, fixedRate = 28800000)
    public void programarActualizacion() {
        fetchAndUpdateDispositivos();
    }

    public String fetchToken() {
        try {
            TokenResponseDTO tokenResponse = webClient
                .post()
                .uri("http://10.145.1.1:8080/api/authenticate")
                .header("Content-Type", "application/json")
                .bodyValue("{\"username\": \"santinorosso\", \"password\": \"santino24\", \"rememberMe\": false}")
                .retrieve()
                .bodyToMono(TokenResponseDTO.class)
                .block();

            if (tokenResponse == null || tokenResponse.getId_token() == null) {
                log.warn("El token es nulo");
                return null;
            }
            log.debug("Token obtenido");
            return tokenResponse.getId_token();
        } catch (Exception e) {
            log.error("Error al obtener el token", e);
            return null;
        }
    }

    public void fetchAndUpdateDispositivos() {
        try {
            List<DispositivoDTO> dispositivos = webClient
                .get()
                .uri("http://10.145.1.1:8080/api/catedra/dispositivos")
                .header("Authorization", "Bearer " + this.token)
                .retrieve()
                .bodyToFlux(DispositivoDTO.class)
                .collectList()
                .block();
            if (dispositivos != null) {
                saveOrUpdateCambios(dispositivos);
            } else {
                log.warn("La lista de dispositivos es nula");
            }
        } catch (WebClientResponseException.Unauthorized e) {
            log.warn("Token expirado, obteniendo un nuevo token");
            this.token = fetchToken();
            fetchAndUpdateDispositivos();
        } catch (Exception e) {
            log.error("Error al obtener y actualizar dispositivos", e);
        }
    }

    public void saveOrUpdateCambios(List<DispositivoDTO> nuevosDispositivos) {
        List<DispositivoDTO> existentesDispositivos = dispositivoService.findAllNoPag();
        for (DispositivoDTO nuevoDispositivo : nuevosDispositivos) {
            Optional<DispositivoDTO> existenteDispositivo = existentesDispositivos
                .stream()
                .filter(d -> d.getId().equals(nuevoDispositivo.getId()))
                .findFirst();
            if (existenteDispositivo.isPresent()) {
                DispositivoDTO dispositivoExistente = existenteDispositivo.get();
                if (!dispositivoExistente.equals(nuevoDispositivo)) {
                    dispositivoService.update(nuevoDispositivo);
                    log.debug("Dispositivo actualizado: {}", nuevoDispositivo);
                } else {
                    log.debug("Dispositivo sin cambios: {}", nuevoDispositivo);
                }
            } else {
                dispositivoService.save(nuevoDispositivo);
                log.debug("Dispositivo guardado: {}", nuevoDispositivo);
            }
        }
    }
}
