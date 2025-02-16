package ar.edu.um.programacion2.web.rest;

import ar.edu.um.programacion2.repository.CaracteristicaRepository;
import ar.edu.um.programacion2.service.CaracteristicaService;
import ar.edu.um.programacion2.service.dto.CaracteristicaDTO;
import ar.edu.um.programacion2.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ar.edu.um.programacion2.domain.Caracteristica}.
 */
@RestController
@RequestMapping("/api")
public class CaracteristicaResource {

    private final Logger log = LoggerFactory.getLogger(CaracteristicaResource.class);

    private static final String ENTITY_NAME = "caracteristica";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CaracteristicaService caracteristicaService;

    private final CaracteristicaRepository caracteristicaRepository;

    public CaracteristicaResource(CaracteristicaService caracteristicaService, CaracteristicaRepository caracteristicaRepository) {
        this.caracteristicaService = caracteristicaService;
        this.caracteristicaRepository = caracteristicaRepository;
    }

    /**
     * {@code POST  /caracteristicas} : Create a new caracteristica.
     *
     * @param caracteristicaDTO the caracteristicaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new caracteristicaDTO, or with status {@code 400 (Bad Request)} if the caracteristica has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/caracteristicas")
    public ResponseEntity<CaracteristicaDTO> createCaracteristica(@Valid @RequestBody CaracteristicaDTO caracteristicaDTO)
        throws URISyntaxException {
        log.debug("REST request to save Caracteristica : {}", caracteristicaDTO);
        if (caracteristicaDTO.getId() != null) {
            throw new BadRequestAlertException("A new caracteristica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CaracteristicaDTO result = caracteristicaService.save(caracteristicaDTO);
        return ResponseEntity
            .created(new URI("/api/caracteristicas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /caracteristicas/:id} : Updates an existing caracteristica.
     *
     * @param id the id of the caracteristicaDTO to save.
     * @param caracteristicaDTO the caracteristicaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caracteristicaDTO,
     * or with status {@code 400 (Bad Request)} if the caracteristicaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the caracteristicaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/caracteristicas/{id}")
    public ResponseEntity<CaracteristicaDTO> updateCaracteristica(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CaracteristicaDTO caracteristicaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Caracteristica : {}, {}", id, caracteristicaDTO);
        if (caracteristicaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, caracteristicaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!caracteristicaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CaracteristicaDTO result = caracteristicaService.update(caracteristicaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caracteristicaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /caracteristicas/:id} : Partial updates given fields of an existing caracteristica, field will ignore if it is null
     *
     * @param id the id of the caracteristicaDTO to save.
     * @param caracteristicaDTO the caracteristicaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated caracteristicaDTO,
     * or with status {@code 400 (Bad Request)} if the caracteristicaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the caracteristicaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the caracteristicaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/caracteristicas/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CaracteristicaDTO> partialUpdateCaracteristica(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CaracteristicaDTO caracteristicaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Caracteristica partially : {}, {}", id, caracteristicaDTO);
        if (caracteristicaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, caracteristicaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!caracteristicaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CaracteristicaDTO> result = caracteristicaService.partialUpdate(caracteristicaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, caracteristicaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /caracteristicas} : get all the caracteristicas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of caracteristicas in body.
     */
    @GetMapping("/caracteristicas")
    public List<CaracteristicaDTO> getAllCaracteristicas() {
        log.debug("REST request to get all Caracteristicas");
        return caracteristicaService.findAll();
    }

    /**
     * {@code GET  /caracteristicas/:id} : get the "id" caracteristica.
     *
     * @param id the id of the caracteristicaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the caracteristicaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/caracteristicas/{id}")
    public ResponseEntity<CaracteristicaDTO> getCaracteristica(@PathVariable Long id) {
        log.debug("REST request to get Caracteristica : {}", id);
        Optional<CaracteristicaDTO> caracteristicaDTO = caracteristicaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(caracteristicaDTO);
    }

    /**
     * {@code DELETE  /caracteristicas/:id} : delete the "id" caracteristica.
     *
     * @param id the id of the caracteristicaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/caracteristicas/{id}")
    public ResponseEntity<Void> deleteCaracteristica(@PathVariable Long id) {
        log.debug("REST request to delete Caracteristica : {}", id);
        caracteristicaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
