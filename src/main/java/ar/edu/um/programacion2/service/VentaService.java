package ar.edu.um.programacion2.service;

import ar.edu.um.programacion2.domain.*;
import ar.edu.um.programacion2.repository.*;
import ar.edu.um.programacion2.service.dto.*;
import ar.edu.um.programacion2.service.mapper.VentaMapper;
import java.util.*;
import javax.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link Venta}.
 */
@Service
@Transactional
public class VentaService {

    private final Logger log = LoggerFactory.getLogger(VentaService.class);

    private final VentaRepository ventaRepository;

    private final UserRepository userRepository;

    private final AdicionalRepository adicionalRepository;

    private final PersonalizacionRepository personalizacionRepository;

    private final OpcionRepository opcionRepository;

    private final VentaMapper ventaMapper;

    private final CaracteristicaRepository caracteristicaRepository;

    private final VentaPersonalizacionOpcionRepository ventaPersonalizacionOpcionRepository;

    private final WebClient webClient;

    private static final String token =
        "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYW50aW5vcm9zc28iLCJleHAiOjE3NDEzODkxNDYsImF1dGgiOiJST0xFX1VTRVIiLCJpYXQiOjE3MzI3NDkxNDZ9.kn8JN-eTS8rn3DbNaoRkJtbVJFls7KJd6v69swgfpVjSkXUbWbu8jtWrY457P3bEhQlkHg3cn_Ug3DTAg7q7Aw";

    public VentaService(
        VentaRepository ventaRepository,
        VentaMapper ventaMapper,
        UserRepository userRepository,
        AdicionalRepository adicionalRepository,
        CaracteristicaRepository caracteristicaRepository,
        PersonalizacionRepository personalizacionRepository,
        OpcionRepository opcionRepository,
        VentaPersonalizacionOpcionRepository ventaPersonalizacionOpcionRepository
    ) {
        this.ventaRepository = ventaRepository;
        this.ventaMapper = ventaMapper;
        this.userRepository = userRepository;
        this.adicionalRepository = adicionalRepository;
        this.caracteristicaRepository = caracteristicaRepository;
        this.personalizacionRepository = personalizacionRepository;
        this.opcionRepository = opcionRepository;
        this.ventaPersonalizacionOpcionRepository = ventaPersonalizacionOpcionRepository;
        this.webClient = WebClient.create();
    }

    /**
     * Save a venta.
     *
     * @param ventaDTO the entity to save.
     * @return the persisted entity.
     */
    public VentaDTO save(VentaDTO ventaDTO) {
        log.debug("Request to save Venta : {}", ventaDTO);
        Venta venta = prepararVenta(ventaDTO);
        venta = ventaRepository.save(venta);
        VentaServicioDTO ventaServicioDTO = realizarVentaServicio(ventaDTO);
        notificarServicio(ventaServicioDTO)
            .doOnSuccess(response -> log.info("POST exitoso. Respuesta: {}", response))
            .doOnError(error -> log.error("Error al realizar el POST: {}", error.getMessage()))
            .doFinally(signal -> log.info("Finalizó el intento de POST con estado: {}", signal))
            .subscribe();
        return ventaMapper.toDto(venta);
    }

    /**
     * Update a venta.
     *
     * @param ventaDTO the entity to save.
     * @return the persisted entity.
     */
    public VentaDTO update(VentaDTO ventaDTO) {
        log.debug("Request to update Venta : {}", ventaDTO);
        Venta venta = prepararVenta(ventaDTO);
        venta = ventaRepository.save(venta);
        return ventaMapper.toDto(venta);
    }

    /**
     * Partially update a venta.
     *
     * @param ventaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<VentaDTO> partialUpdate(VentaDTO ventaDTO) {
        log.debug("Request to partially update Venta : {}", ventaDTO);

        return ventaRepository
            .findById(ventaDTO.getId())
            .map(existingVenta -> {
                ventaMapper.partialUpdate(existingVenta, ventaDTO);

                return existingVenta;
            })
            .map(ventaRepository::save)
            .map(ventaMapper::toDto);
    }

    /**
     * Get all the ventas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<VentaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Ventas");
        return ventaRepository.findAll(pageable).map(ventaMapper::toDto);
    }

    /**
     * Get all the ventas with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<VentaDTO> findAllWithEagerRelationships(Pageable pageable) {
        return ventaRepository.findAllWithEagerRelationships(pageable).map(ventaMapper::toDto);
    }

    /**
     * Get all the ventas by user.
     *
     * @param userId the user id.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<VentaDTO> findAllByUser(Long userId, Pageable pageable) {
        return ventaRepository.findAllByUserId(userId, pageable).map(ventaMapper::toDto);
    }

    /**
     * Get one venta by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<VentaDTO> findOne(Long id) {
        log.debug("Request to get Venta : {}", id);
        return ventaRepository.findOneWithEagerRelationships(id).map(ventaMapper::toDto);
    }

    /**
     * Delete the venta by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Venta : {}", id);
        ventaRepository.deleteById(id);
    }

    public Venta prepararVenta(VentaDTO ventaDTO) {
        User user = findUserById(ventaDTO.getUser().getId());
        Set<Adicional> adicionales = findAdicionales(ventaDTO.getAdicionales());
        Set<Caracteristica> caracteristicas = findCaracteristicas(ventaDTO.getCaracteristicas());
        Set<VentaPersonalizacionOpcion> personalizacionesOpciones = findPersonalizacionesOpciones(ventaDTO.getPersonalizaciones());

        Venta venta = ventaMapper.toEntity(ventaDTO);
        venta.setUser(user);
        venta.setAdicionales(adicionales);
        venta.setCaracteristicas(caracteristicas);
        venta.setPersonalizaciones(personalizacionesOpciones);

        return venta;
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public Set<Adicional> findAdicionales(Set<AdicionalVentaDTO> adicionalDTOs) {
        Set<Adicional> adicionales = new HashSet<>();
        for (AdicionalVentaDTO adicionalDTO : adicionalDTOs) {
            Adicional adicional = adicionalRepository
                .findById(adicionalDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Adicional not found"));
            adicionales.add(adicional);
        }
        return adicionales;
    }

    public Set<Caracteristica> findCaracteristicas(Set<CaracteristicaVentaDTO> caracteristicaDTOs) {
        Set<Caracteristica> caracteristicas = new HashSet<>();
        for (CaracteristicaVentaDTO caracteristicaDTO : caracteristicaDTOs) {
            Caracteristica caracteristica = caracteristicaRepository
                .findById(caracteristicaDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Caracteristica not found"));
            caracteristicas.add(caracteristica);
        }
        return caracteristicas;
    }

    public Set<VentaPersonalizacionOpcion> findPersonalizacionesOpciones(Set<VentaPersonalizacionOpcionDTO> personalizacionDTOs) {
        Set<VentaPersonalizacionOpcion> personalizacionesOpciones = new HashSet<>();
        for (VentaPersonalizacionOpcionDTO personalizacionDTO : personalizacionDTOs) {
            VentaPersonalizacionOpcion personalizacionOpcion = personalizacionDTO.getId() != null
                ? ventaPersonalizacionOpcionRepository
                    .findById(personalizacionDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("VentaPersonalizacionOpcion not found"))
                : new VentaPersonalizacionOpcion();
            Personalizacion personalizacion = personalizacionRepository
                .findById(personalizacionDTO.getPersonalizacionId())
                .orElseThrow(() -> new EntityNotFoundException("Personalizacion not found"));
            Opcion opcion = opcionRepository
                .findById(personalizacionDTO.getOpcion().getId())
                .orElseThrow(() -> new EntityNotFoundException("Opcion not found"));
            personalizacionOpcion.setPersonalizacion(personalizacion);
            personalizacionOpcion.setOpcion(opcion);
            personalizacionesOpciones.add(personalizacionOpcion);
        }
        return personalizacionesOpciones;
    }

    public VentaServicioDTO realizarVentaServicio(VentaDTO ventaDTO) {
        Set<AdicionalServicioDTO> adicionalesServicio = new HashSet<>();
        for (AdicionalVentaDTO adicionalVentaDTO : ventaDTO.getAdicionales()) {
            AdicionalServicioDTO adicionalServicioDTO = new AdicionalServicioDTO();
            adicionalServicioDTO.setId(adicionalVentaDTO.getId());
            adicionalServicioDTO.setPrecio(adicionalVentaDTO.getPrecio());
            adicionalesServicio.add(adicionalServicioDTO);
        }

        Set<PersonalizacionServicioDTO> personalizacionesServicio = new HashSet<>();
        for (VentaPersonalizacionOpcionDTO personalizacionOpcionDTO : ventaDTO.getPersonalizaciones()) {
            OpcionServicioDTO opcionServicioDTO = new OpcionServicioDTO();
            opcionServicioDTO.setId(personalizacionOpcionDTO.getOpcion().getId());
            PersonalizacionServicioDTO personalizacionServicioDTO = new PersonalizacionServicioDTO();
            personalizacionServicioDTO.setId(personalizacionOpcionDTO.getPersonalizacionId());
            personalizacionServicioDTO.setPrecio(personalizacionOpcionDTO.getOpcion().getPrecioAdicional());
            personalizacionServicioDTO.setOpcion(opcionServicioDTO);
            personalizacionesServicio.add(personalizacionServicioDTO);
        }

        VentaServicioDTO ventaServicioDTO = new VentaServicioDTO();
        ventaServicioDTO.setIdDispositivo(ventaDTO.getIdDispositivo());
        ventaServicioDTO.setPersonalizaciones(personalizacionesServicio);
        ventaServicioDTO.setAdicionales(adicionalesServicio);
        ventaServicioDTO.setPrecioFinal(ventaDTO.getPrecioFinal());
        ventaServicioDTO.setFechaVenta(ventaDTO.getFechaVenta());

        return ventaServicioDTO;
    }

    public Mono<String> notificarServicio(VentaServicioDTO ventaServicioDTO) {
        return webClient
            .post()
            .uri("http://10.145.1.1:8080/api/catedra/vender")
            .header("Authorization", "Bearer " + token)
            .bodyValue(ventaServicioDTO)
            .retrieve()
            .bodyToMono(String.class)
            .onErrorResume(
                WebClientResponseException.class,
                e -> Mono.error(new RuntimeException("Error al realizar la solicitud: " + e.getMessage()))
            );
    }
}
