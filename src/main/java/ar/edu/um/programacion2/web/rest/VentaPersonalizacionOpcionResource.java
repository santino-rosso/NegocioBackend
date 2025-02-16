package ar.edu.um.programacion2.web.rest;

import ar.edu.um.programacion2.repository.VentaPersonalizacionOpcionRepository;
import ar.edu.um.programacion2.service.VentaPersonalizacionOpcionService;
import ar.edu.um.programacion2.service.dto.VentaPersonalizacionOpcionDTO;
import ar.edu.um.programacion2.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ar.edu.um.programacion2.domain.VentaPersonalizacionOpcion}.
 */
@RestController
@RequestMapping("/api")
public class VentaPersonalizacionOpcionResource {

    private final Logger log = LoggerFactory.getLogger(VentaPersonalizacionOpcionResource.class);

    private static final String ENTITY_NAME = "ventaPersonalizacionOpcion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VentaPersonalizacionOpcionService ventaPersonalizacionOpcionService;

    private final VentaPersonalizacionOpcionRepository ventaPersonalizacionOpcionRepository;

    public VentaPersonalizacionOpcionResource(
        VentaPersonalizacionOpcionService ventaPersonalizacionOpcionService,
        VentaPersonalizacionOpcionRepository ventaPersonalizacionOpcionRepository
    ) {
        this.ventaPersonalizacionOpcionService = ventaPersonalizacionOpcionService;
        this.ventaPersonalizacionOpcionRepository = ventaPersonalizacionOpcionRepository;
    }

    /**
     * {@code POST  /venta-personalizacion-opcions} : Create a new ventaPersonalizacionOpcion.
     *
     * @param ventaPersonalizacionOpcionDTO the ventaPersonalizacionOpcionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ventaPersonalizacionOpcionDTO, or with status {@code 400 (Bad Request)} if the ventaPersonalizacionOpcion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/venta-personalizacion-opcions")
    public ResponseEntity<VentaPersonalizacionOpcionDTO> createVentaPersonalizacionOpcion(
        @RequestBody VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO
    ) throws URISyntaxException {
        log.debug("REST request to save VentaPersonalizacionOpcion : {}", ventaPersonalizacionOpcionDTO);
        if (ventaPersonalizacionOpcionDTO.getId() != null) {
            throw new BadRequestAlertException("A new ventaPersonalizacionOpcion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VentaPersonalizacionOpcionDTO result = ventaPersonalizacionOpcionService.save(ventaPersonalizacionOpcionDTO);
        return ResponseEntity
            .created(new URI("/api/venta-personalizacion-opcions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /venta-personalizacion-opcions/:id} : Updates an existing ventaPersonalizacionOpcion.
     *
     * @param id the id of the ventaPersonalizacionOpcionDTO to save.
     * @param ventaPersonalizacionOpcionDTO the ventaPersonalizacionOpcionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ventaPersonalizacionOpcionDTO,
     * or with status {@code 400 (Bad Request)} if the ventaPersonalizacionOpcionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ventaPersonalizacionOpcionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/venta-personalizacion-opcions/{id}")
    public ResponseEntity<VentaPersonalizacionOpcionDTO> updateVentaPersonalizacionOpcion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update VentaPersonalizacionOpcion : {}, {}", id, ventaPersonalizacionOpcionDTO);
        if (ventaPersonalizacionOpcionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ventaPersonalizacionOpcionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ventaPersonalizacionOpcionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VentaPersonalizacionOpcionDTO result = ventaPersonalizacionOpcionService.update(ventaPersonalizacionOpcionDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ventaPersonalizacionOpcionDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /venta-personalizacion-opcions/:id} : Partial updates given fields of an existing ventaPersonalizacionOpcion, field will ignore if it is null
     *
     * @param id the id of the ventaPersonalizacionOpcionDTO to save.
     * @param ventaPersonalizacionOpcionDTO the ventaPersonalizacionOpcionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ventaPersonalizacionOpcionDTO,
     * or with status {@code 400 (Bad Request)} if the ventaPersonalizacionOpcionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ventaPersonalizacionOpcionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ventaPersonalizacionOpcionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/venta-personalizacion-opcions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VentaPersonalizacionOpcionDTO> partialUpdateVentaPersonalizacionOpcion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update VentaPersonalizacionOpcion partially : {}, {}", id, ventaPersonalizacionOpcionDTO);
        if (ventaPersonalizacionOpcionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ventaPersonalizacionOpcionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ventaPersonalizacionOpcionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VentaPersonalizacionOpcionDTO> result = ventaPersonalizacionOpcionService.partialUpdate(ventaPersonalizacionOpcionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ventaPersonalizacionOpcionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /venta-personalizacion-opcions} : get all the ventaPersonalizacionOpcions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ventaPersonalizacionOpcions in body.
     */
    @GetMapping("/venta-personalizacion-opcions")
    public ResponseEntity<List<VentaPersonalizacionOpcionDTO>> getAllVentaPersonalizacionOpcions(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of VentaPersonalizacionOpcions");
        Page<VentaPersonalizacionOpcionDTO> page = ventaPersonalizacionOpcionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /venta-personalizacion-opcions/:id} : get the "id" ventaPersonalizacionOpcion.
     *
     * @param id the id of the ventaPersonalizacionOpcionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ventaPersonalizacionOpcionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/venta-personalizacion-opcions/{id}")
    public ResponseEntity<VentaPersonalizacionOpcionDTO> getVentaPersonalizacionOpcion(@PathVariable Long id) {
        log.debug("REST request to get VentaPersonalizacionOpcion : {}", id);
        Optional<VentaPersonalizacionOpcionDTO> ventaPersonalizacionOpcionDTO = ventaPersonalizacionOpcionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ventaPersonalizacionOpcionDTO);
    }

    /**
     * {@code DELETE  /venta-personalizacion-opcions/:id} : delete the "id" ventaPersonalizacionOpcion.
     *
     * @param id the id of the ventaPersonalizacionOpcionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/venta-personalizacion-opcions/{id}")
    public ResponseEntity<Void> deleteVentaPersonalizacionOpcion(@PathVariable Long id) {
        log.debug("REST request to delete VentaPersonalizacionOpcion : {}", id);
        ventaPersonalizacionOpcionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
