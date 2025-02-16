package ar.edu.um.programacion2.service;

import ar.edu.um.programacion2.domain.VentaPersonalizacionOpcion;
import ar.edu.um.programacion2.repository.VentaPersonalizacionOpcionRepository;
import ar.edu.um.programacion2.service.dto.VentaPersonalizacionOpcionDTO;
import ar.edu.um.programacion2.service.mapper.VentaPersonalizacionOpcionMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link VentaPersonalizacionOpcion}.
 */
@Service
@Transactional
public class VentaPersonalizacionOpcionService {

    private final Logger log = LoggerFactory.getLogger(VentaPersonalizacionOpcionService.class);

    private final VentaPersonalizacionOpcionRepository ventaPersonalizacionOpcionRepository;

    private final VentaPersonalizacionOpcionMapper ventaPersonalizacionOpcionMapper;

    public VentaPersonalizacionOpcionService(
        VentaPersonalizacionOpcionRepository ventaPersonalizacionOpcionRepository,
        VentaPersonalizacionOpcionMapper ventaPersonalizacionOpcionMapper
    ) {
        this.ventaPersonalizacionOpcionRepository = ventaPersonalizacionOpcionRepository;
        this.ventaPersonalizacionOpcionMapper = ventaPersonalizacionOpcionMapper;
    }

    /**
     * Save a ventaPersonalizacionOpcion.
     *
     * @param ventaPersonalizacionOpcionDTO the entity to save.
     * @return the persisted entity.
     */
    public VentaPersonalizacionOpcionDTO save(VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO) {
        log.debug("Request to save VentaPersonalizacionOpcion : {}", ventaPersonalizacionOpcionDTO);
        VentaPersonalizacionOpcion ventaPersonalizacionOpcion = ventaPersonalizacionOpcionMapper.toEntity(ventaPersonalizacionOpcionDTO);
        ventaPersonalizacionOpcion = ventaPersonalizacionOpcionRepository.save(ventaPersonalizacionOpcion);
        return ventaPersonalizacionOpcionMapper.toDto(ventaPersonalizacionOpcion);
    }

    /**
     * Update a ventaPersonalizacionOpcion.
     *
     * @param ventaPersonalizacionOpcionDTO the entity to save.
     * @return the persisted entity.
     */
    public VentaPersonalizacionOpcionDTO update(VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO) {
        log.debug("Request to update VentaPersonalizacionOpcion : {}", ventaPersonalizacionOpcionDTO);
        VentaPersonalizacionOpcion ventaPersonalizacionOpcion = ventaPersonalizacionOpcionMapper.toEntity(ventaPersonalizacionOpcionDTO);
        ventaPersonalizacionOpcion = ventaPersonalizacionOpcionRepository.save(ventaPersonalizacionOpcion);
        return ventaPersonalizacionOpcionMapper.toDto(ventaPersonalizacionOpcion);
    }

    /**
     * Partially update a ventaPersonalizacionOpcion.
     *
     * @param ventaPersonalizacionOpcionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<VentaPersonalizacionOpcionDTO> partialUpdate(VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO) {
        log.debug("Request to partially update VentaPersonalizacionOpcion : {}", ventaPersonalizacionOpcionDTO);

        return ventaPersonalizacionOpcionRepository
            .findById(ventaPersonalizacionOpcionDTO.getId())
            .map(existingVentaPersonalizacionOpcion -> {
                ventaPersonalizacionOpcionMapper.partialUpdate(existingVentaPersonalizacionOpcion, ventaPersonalizacionOpcionDTO);

                return existingVentaPersonalizacionOpcion;
            })
            .map(ventaPersonalizacionOpcionRepository::save)
            .map(ventaPersonalizacionOpcionMapper::toDto);
    }

    /**
     * Get all the ventaPersonalizacionOpcions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<VentaPersonalizacionOpcionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VentaPersonalizacionOpcions");
        return ventaPersonalizacionOpcionRepository.findAll(pageable).map(ventaPersonalizacionOpcionMapper::toDto);
    }

    /**
     * Get one ventaPersonalizacionOpcion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<VentaPersonalizacionOpcionDTO> findOne(Long id) {
        log.debug("Request to get VentaPersonalizacionOpcion : {}", id);
        return ventaPersonalizacionOpcionRepository.findById(id).map(ventaPersonalizacionOpcionMapper::toDto);
    }

    /**
     * Delete the ventaPersonalizacionOpcion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete VentaPersonalizacionOpcion : {}", id);
        ventaPersonalizacionOpcionRepository.deleteById(id);
    }
}
