package ar.edu.um.programacion2.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.edu.um.programacion2.IntegrationTest;
import ar.edu.um.programacion2.domain.VentaPersonalizacionOpcion;
import ar.edu.um.programacion2.repository.VentaPersonalizacionOpcionRepository;
import ar.edu.um.programacion2.service.dto.VentaPersonalizacionOpcionDTO;
import ar.edu.um.programacion2.service.mapper.VentaPersonalizacionOpcionMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link VentaPersonalizacionOpcionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VentaPersonalizacionOpcionResourceIT {

    private static final String ENTITY_API_URL = "/api/venta-personalizacion-opcions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VentaPersonalizacionOpcionRepository ventaPersonalizacionOpcionRepository;

    @Autowired
    private VentaPersonalizacionOpcionMapper ventaPersonalizacionOpcionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVentaPersonalizacionOpcionMockMvc;

    private VentaPersonalizacionOpcion ventaPersonalizacionOpcion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VentaPersonalizacionOpcion createEntity(EntityManager em) {
        VentaPersonalizacionOpcion ventaPersonalizacionOpcion = new VentaPersonalizacionOpcion();
        return ventaPersonalizacionOpcion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VentaPersonalizacionOpcion createUpdatedEntity(EntityManager em) {
        VentaPersonalizacionOpcion ventaPersonalizacionOpcion = new VentaPersonalizacionOpcion();
        return ventaPersonalizacionOpcion;
    }

    @BeforeEach
    public void initTest() {
        ventaPersonalizacionOpcion = createEntity(em);
    }

    @Test
    @Transactional
    void createVentaPersonalizacionOpcion() throws Exception {
        int databaseSizeBeforeCreate = ventaPersonalizacionOpcionRepository.findAll().size();
        // Create the VentaPersonalizacionOpcion
        VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO = ventaPersonalizacionOpcionMapper.toDto(ventaPersonalizacionOpcion);
        restVentaPersonalizacionOpcionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ventaPersonalizacionOpcionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the VentaPersonalizacionOpcion in the database
        List<VentaPersonalizacionOpcion> ventaPersonalizacionOpcionList = ventaPersonalizacionOpcionRepository.findAll();
        assertThat(ventaPersonalizacionOpcionList).hasSize(databaseSizeBeforeCreate + 1);
        VentaPersonalizacionOpcion testVentaPersonalizacionOpcion = ventaPersonalizacionOpcionList.get(
            ventaPersonalizacionOpcionList.size() - 1
        );
    }

    @Test
    @Transactional
    void createVentaPersonalizacionOpcionWithExistingId() throws Exception {
        // Create the VentaPersonalizacionOpcion with an existing ID
        ventaPersonalizacionOpcion.setId(1L);
        VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO = ventaPersonalizacionOpcionMapper.toDto(ventaPersonalizacionOpcion);

        int databaseSizeBeforeCreate = ventaPersonalizacionOpcionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVentaPersonalizacionOpcionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ventaPersonalizacionOpcionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VentaPersonalizacionOpcion in the database
        List<VentaPersonalizacionOpcion> ventaPersonalizacionOpcionList = ventaPersonalizacionOpcionRepository.findAll();
        assertThat(ventaPersonalizacionOpcionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllVentaPersonalizacionOpcions() throws Exception {
        // Initialize the database
        ventaPersonalizacionOpcionRepository.saveAndFlush(ventaPersonalizacionOpcion);

        // Get all the ventaPersonalizacionOpcionList
        restVentaPersonalizacionOpcionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ventaPersonalizacionOpcion.getId().intValue())));
    }

    @Test
    @Transactional
    void getVentaPersonalizacionOpcion() throws Exception {
        // Initialize the database
        ventaPersonalizacionOpcionRepository.saveAndFlush(ventaPersonalizacionOpcion);

        // Get the ventaPersonalizacionOpcion
        restVentaPersonalizacionOpcionMockMvc
            .perform(get(ENTITY_API_URL_ID, ventaPersonalizacionOpcion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ventaPersonalizacionOpcion.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingVentaPersonalizacionOpcion() throws Exception {
        // Get the ventaPersonalizacionOpcion
        restVentaPersonalizacionOpcionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingVentaPersonalizacionOpcion() throws Exception {
        // Initialize the database
        ventaPersonalizacionOpcionRepository.saveAndFlush(ventaPersonalizacionOpcion);

        int databaseSizeBeforeUpdate = ventaPersonalizacionOpcionRepository.findAll().size();

        // Update the ventaPersonalizacionOpcion
        VentaPersonalizacionOpcion updatedVentaPersonalizacionOpcion = ventaPersonalizacionOpcionRepository
            .findById(ventaPersonalizacionOpcion.getId())
            .get();
        // Disconnect from session so that the updates on updatedVentaPersonalizacionOpcion are not directly saved in db
        em.detach(updatedVentaPersonalizacionOpcion);
        VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO = ventaPersonalizacionOpcionMapper.toDto(
            updatedVentaPersonalizacionOpcion
        );

        restVentaPersonalizacionOpcionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ventaPersonalizacionOpcionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ventaPersonalizacionOpcionDTO))
            )
            .andExpect(status().isOk());

        // Validate the VentaPersonalizacionOpcion in the database
        List<VentaPersonalizacionOpcion> ventaPersonalizacionOpcionList = ventaPersonalizacionOpcionRepository.findAll();
        assertThat(ventaPersonalizacionOpcionList).hasSize(databaseSizeBeforeUpdate);
        VentaPersonalizacionOpcion testVentaPersonalizacionOpcion = ventaPersonalizacionOpcionList.get(
            ventaPersonalizacionOpcionList.size() - 1
        );
    }

    @Test
    @Transactional
    void putNonExistingVentaPersonalizacionOpcion() throws Exception {
        int databaseSizeBeforeUpdate = ventaPersonalizacionOpcionRepository.findAll().size();
        ventaPersonalizacionOpcion.setId(count.incrementAndGet());

        // Create the VentaPersonalizacionOpcion
        VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO = ventaPersonalizacionOpcionMapper.toDto(ventaPersonalizacionOpcion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVentaPersonalizacionOpcionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ventaPersonalizacionOpcionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ventaPersonalizacionOpcionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VentaPersonalizacionOpcion in the database
        List<VentaPersonalizacionOpcion> ventaPersonalizacionOpcionList = ventaPersonalizacionOpcionRepository.findAll();
        assertThat(ventaPersonalizacionOpcionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVentaPersonalizacionOpcion() throws Exception {
        int databaseSizeBeforeUpdate = ventaPersonalizacionOpcionRepository.findAll().size();
        ventaPersonalizacionOpcion.setId(count.incrementAndGet());

        // Create the VentaPersonalizacionOpcion
        VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO = ventaPersonalizacionOpcionMapper.toDto(ventaPersonalizacionOpcion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVentaPersonalizacionOpcionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ventaPersonalizacionOpcionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VentaPersonalizacionOpcion in the database
        List<VentaPersonalizacionOpcion> ventaPersonalizacionOpcionList = ventaPersonalizacionOpcionRepository.findAll();
        assertThat(ventaPersonalizacionOpcionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVentaPersonalizacionOpcion() throws Exception {
        int databaseSizeBeforeUpdate = ventaPersonalizacionOpcionRepository.findAll().size();
        ventaPersonalizacionOpcion.setId(count.incrementAndGet());

        // Create the VentaPersonalizacionOpcion
        VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO = ventaPersonalizacionOpcionMapper.toDto(ventaPersonalizacionOpcion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVentaPersonalizacionOpcionMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ventaPersonalizacionOpcionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VentaPersonalizacionOpcion in the database
        List<VentaPersonalizacionOpcion> ventaPersonalizacionOpcionList = ventaPersonalizacionOpcionRepository.findAll();
        assertThat(ventaPersonalizacionOpcionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVentaPersonalizacionOpcionWithPatch() throws Exception {
        // Initialize the database
        ventaPersonalizacionOpcionRepository.saveAndFlush(ventaPersonalizacionOpcion);

        int databaseSizeBeforeUpdate = ventaPersonalizacionOpcionRepository.findAll().size();

        // Update the ventaPersonalizacionOpcion using partial update
        VentaPersonalizacionOpcion partialUpdatedVentaPersonalizacionOpcion = new VentaPersonalizacionOpcion();
        partialUpdatedVentaPersonalizacionOpcion.setId(ventaPersonalizacionOpcion.getId());

        restVentaPersonalizacionOpcionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVentaPersonalizacionOpcion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVentaPersonalizacionOpcion))
            )
            .andExpect(status().isOk());

        // Validate the VentaPersonalizacionOpcion in the database
        List<VentaPersonalizacionOpcion> ventaPersonalizacionOpcionList = ventaPersonalizacionOpcionRepository.findAll();
        assertThat(ventaPersonalizacionOpcionList).hasSize(databaseSizeBeforeUpdate);
        VentaPersonalizacionOpcion testVentaPersonalizacionOpcion = ventaPersonalizacionOpcionList.get(
            ventaPersonalizacionOpcionList.size() - 1
        );
    }

    @Test
    @Transactional
    void fullUpdateVentaPersonalizacionOpcionWithPatch() throws Exception {
        // Initialize the database
        ventaPersonalizacionOpcionRepository.saveAndFlush(ventaPersonalizacionOpcion);

        int databaseSizeBeforeUpdate = ventaPersonalizacionOpcionRepository.findAll().size();

        // Update the ventaPersonalizacionOpcion using partial update
        VentaPersonalizacionOpcion partialUpdatedVentaPersonalizacionOpcion = new VentaPersonalizacionOpcion();
        partialUpdatedVentaPersonalizacionOpcion.setId(ventaPersonalizacionOpcion.getId());

        restVentaPersonalizacionOpcionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVentaPersonalizacionOpcion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedVentaPersonalizacionOpcion))
            )
            .andExpect(status().isOk());

        // Validate the VentaPersonalizacionOpcion in the database
        List<VentaPersonalizacionOpcion> ventaPersonalizacionOpcionList = ventaPersonalizacionOpcionRepository.findAll();
        assertThat(ventaPersonalizacionOpcionList).hasSize(databaseSizeBeforeUpdate);
        VentaPersonalizacionOpcion testVentaPersonalizacionOpcion = ventaPersonalizacionOpcionList.get(
            ventaPersonalizacionOpcionList.size() - 1
        );
    }

    @Test
    @Transactional
    void patchNonExistingVentaPersonalizacionOpcion() throws Exception {
        int databaseSizeBeforeUpdate = ventaPersonalizacionOpcionRepository.findAll().size();
        ventaPersonalizacionOpcion.setId(count.incrementAndGet());

        // Create the VentaPersonalizacionOpcion
        VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO = ventaPersonalizacionOpcionMapper.toDto(ventaPersonalizacionOpcion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVentaPersonalizacionOpcionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ventaPersonalizacionOpcionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ventaPersonalizacionOpcionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VentaPersonalizacionOpcion in the database
        List<VentaPersonalizacionOpcion> ventaPersonalizacionOpcionList = ventaPersonalizacionOpcionRepository.findAll();
        assertThat(ventaPersonalizacionOpcionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVentaPersonalizacionOpcion() throws Exception {
        int databaseSizeBeforeUpdate = ventaPersonalizacionOpcionRepository.findAll().size();
        ventaPersonalizacionOpcion.setId(count.incrementAndGet());

        // Create the VentaPersonalizacionOpcion
        VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO = ventaPersonalizacionOpcionMapper.toDto(ventaPersonalizacionOpcion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVentaPersonalizacionOpcionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ventaPersonalizacionOpcionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the VentaPersonalizacionOpcion in the database
        List<VentaPersonalizacionOpcion> ventaPersonalizacionOpcionList = ventaPersonalizacionOpcionRepository.findAll();
        assertThat(ventaPersonalizacionOpcionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVentaPersonalizacionOpcion() throws Exception {
        int databaseSizeBeforeUpdate = ventaPersonalizacionOpcionRepository.findAll().size();
        ventaPersonalizacionOpcion.setId(count.incrementAndGet());

        // Create the VentaPersonalizacionOpcion
        VentaPersonalizacionOpcionDTO ventaPersonalizacionOpcionDTO = ventaPersonalizacionOpcionMapper.toDto(ventaPersonalizacionOpcion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVentaPersonalizacionOpcionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ventaPersonalizacionOpcionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the VentaPersonalizacionOpcion in the database
        List<VentaPersonalizacionOpcion> ventaPersonalizacionOpcionList = ventaPersonalizacionOpcionRepository.findAll();
        assertThat(ventaPersonalizacionOpcionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVentaPersonalizacionOpcion() throws Exception {
        // Initialize the database
        ventaPersonalizacionOpcionRepository.saveAndFlush(ventaPersonalizacionOpcion);

        int databaseSizeBeforeDelete = ventaPersonalizacionOpcionRepository.findAll().size();

        // Delete the ventaPersonalizacionOpcion
        restVentaPersonalizacionOpcionMockMvc
            .perform(delete(ENTITY_API_URL_ID, ventaPersonalizacionOpcion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VentaPersonalizacionOpcion> ventaPersonalizacionOpcionList = ventaPersonalizacionOpcionRepository.findAll();
        assertThat(ventaPersonalizacionOpcionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
