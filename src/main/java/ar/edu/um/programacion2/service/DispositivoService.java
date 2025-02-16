package ar.edu.um.programacion2.service;

import ar.edu.um.programacion2.domain.Dispositivo;
import ar.edu.um.programacion2.repository.DispositivoRepository;
import ar.edu.um.programacion2.service.dto.DispositivoDTO;
import ar.edu.um.programacion2.service.mapper.DispositivoMapper;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Dispositivo}.
 */
@Service
@Transactional
public class DispositivoService {

    private final Logger log = LoggerFactory.getLogger(DispositivoService.class);

    private final DispositivoRepository dispositivoRepository;

    private final DispositivoMapper dispositivoMapper;

    public DispositivoService(DispositivoRepository dispositivoRepository, DispositivoMapper dispositivoMapper) {
        this.dispositivoRepository = dispositivoRepository;
        this.dispositivoMapper = dispositivoMapper;
    }

    /**
     * Save a dispositivo.
     *
     * @param dispositivoDTO the entity to save.
     * @return the persisted entity.
     */
    public DispositivoDTO save(DispositivoDTO dispositivoDTO) {
        log.debug("Request to save Dispositivo : {}", dispositivoDTO);
        Dispositivo dispositivo = dispositivoMapper.toEntity(dispositivoDTO);
        dispositivo = dispositivoRepository.save(dispositivo);
        return dispositivoMapper.toDto(dispositivo);
    }

    /**
     * Update a dispositivo.
     *
     * @param dispositivoDTO the entity to save.
     * @return the persisted entity.
     */
    public DispositivoDTO update(DispositivoDTO dispositivoDTO) {
        log.debug("Request to update Dispositivo : {}", dispositivoDTO);
        Dispositivo dispositivo = dispositivoMapper.toEntity(dispositivoDTO);
        dispositivo = dispositivoRepository.save(dispositivo);
        return dispositivoMapper.toDto(dispositivo);
    }

    /**
     * Partially update a dispositivo.
     *
     * @param dispositivoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DispositivoDTO> partialUpdate(DispositivoDTO dispositivoDTO) {
        log.debug("Request to partially update Dispositivo : {}", dispositivoDTO);

        return dispositivoRepository
            .findById(dispositivoDTO.getId())
            .map(existingDispositivo -> {
                dispositivoMapper.partialUpdate(existingDispositivo, dispositivoDTO);

                return existingDispositivo;
            })
            .map(dispositivoRepository::save)
            .map(dispositivoMapper::toDto);
    }

    /**
     * Get all the dispositivos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DispositivoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Dispositivos");
        return dispositivoRepository.findAll(pageable).map(dispositivoMapper::toDto);
    }

    /**
     * Get all the dispositivos without pagination.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DispositivoDTO> findAllNoPag() {
        log.debug("Request to get all Dispositivos without pagination");
        return dispositivoRepository.findAll().stream().map(dispositivoMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Get all the dispositivos with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<DispositivoDTO> findAllWithEagerRelationships(Pageable pageable) {
        return dispositivoRepository.findAllWithEagerRelationships(pageable).map(dispositivoMapper::toDto);
    }

    /**
     * Get one dispositivo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DispositivoDTO> findOne(Long id) {
        log.debug("Request to get Dispositivo : {}", id);
        return dispositivoRepository.findOneWithEagerRelationships(id).map(dispositivoMapper::toDto);
    }

    /**
     * Delete the dispositivo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Dispositivo : {}", id);
        dispositivoRepository.deleteById(id);
    }
}
