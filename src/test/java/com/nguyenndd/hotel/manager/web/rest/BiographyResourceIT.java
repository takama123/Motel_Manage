package com.nguyenndd.hotel.manager.web.rest;

import com.nguyenndd.hotel.manager.MotelManagerApp;
import com.nguyenndd.hotel.manager.domain.Biography;
import com.nguyenndd.hotel.manager.repository.BiographyRepository;
import com.nguyenndd.hotel.manager.service.BiographyService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BiographyResource} REST controller.
 */
@SpringBootTest(classes = MotelManagerApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BiographyResourceIT {

    private static final LocalDate DEFAULT_FROM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FROM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TO_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TO_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_WORKING_DECRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_WORKING_DECRIPTION = "BBBBBBBBBB";

    @Autowired
    private BiographyRepository biographyRepository;

    @Autowired
    private BiographyService biographyService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBiographyMockMvc;

    private Biography biography;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Biography createEntity(EntityManager em) {
        Biography biography = new Biography()
            .fromDate(DEFAULT_FROM_DATE)
            .toDate(DEFAULT_TO_DATE)
            .address(DEFAULT_ADDRESS)
            .workingDecription(DEFAULT_WORKING_DECRIPTION);
        return biography;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Biography createUpdatedEntity(EntityManager em) {
        Biography biography = new Biography()
            .fromDate(UPDATED_FROM_DATE)
            .toDate(UPDATED_TO_DATE)
            .address(UPDATED_ADDRESS)
            .workingDecription(UPDATED_WORKING_DECRIPTION);
        return biography;
    }

    @BeforeEach
    public void initTest() {
        biography = createEntity(em);
    }

    @Test
    @Transactional
    public void createBiography() throws Exception {
        int databaseSizeBeforeCreate = biographyRepository.findAll().size();
        // Create the Biography
        restBiographyMockMvc.perform(post("/api/biographies").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(biography)))
            .andExpect(status().isCreated());

        // Validate the Biography in the database
        List<Biography> biographyList = biographyRepository.findAll();
        assertThat(biographyList).hasSize(databaseSizeBeforeCreate + 1);
        Biography testBiography = biographyList.get(biographyList.size() - 1);
        assertThat(testBiography.getFromDate()).isEqualTo(DEFAULT_FROM_DATE);
        assertThat(testBiography.getToDate()).isEqualTo(DEFAULT_TO_DATE);
        assertThat(testBiography.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testBiography.getWorkingDecription()).isEqualTo(DEFAULT_WORKING_DECRIPTION);
    }

    @Test
    @Transactional
    public void createBiographyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = biographyRepository.findAll().size();

        // Create the Biography with an existing ID
        biography.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBiographyMockMvc.perform(post("/api/biographies").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(biography)))
            .andExpect(status().isBadRequest());

        // Validate the Biography in the database
        List<Biography> biographyList = biographyRepository.findAll();
        assertThat(biographyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBiographies() throws Exception {
        // Initialize the database
        biographyRepository.saveAndFlush(biography);

        // Get all the biographyList
        restBiographyMockMvc.perform(get("/api/biographies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(biography.getId().intValue())))
            .andExpect(jsonPath("$.[*].fromDate").value(hasItem(DEFAULT_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].toDate").value(hasItem(DEFAULT_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].workingDecription").value(hasItem(DEFAULT_WORKING_DECRIPTION)));
    }
    
    @Test
    @Transactional
    public void getBiography() throws Exception {
        // Initialize the database
        biographyRepository.saveAndFlush(biography);

        // Get the biography
        restBiographyMockMvc.perform(get("/api/biographies/{id}", biography.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(biography.getId().intValue()))
            .andExpect(jsonPath("$.fromDate").value(DEFAULT_FROM_DATE.toString()))
            .andExpect(jsonPath("$.toDate").value(DEFAULT_TO_DATE.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.workingDecription").value(DEFAULT_WORKING_DECRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingBiography() throws Exception {
        // Get the biography
        restBiographyMockMvc.perform(get("/api/biographies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBiography() throws Exception {
        // Initialize the database
        biographyService.save(biography);

        int databaseSizeBeforeUpdate = biographyRepository.findAll().size();

        // Update the biography
        Biography updatedBiography = biographyRepository.findById(biography.getId()).get();
        // Disconnect from session so that the updates on updatedBiography are not directly saved in db
        em.detach(updatedBiography);
        updatedBiography
            .fromDate(UPDATED_FROM_DATE)
            .toDate(UPDATED_TO_DATE)
            .address(UPDATED_ADDRESS)
            .workingDecription(UPDATED_WORKING_DECRIPTION);

        restBiographyMockMvc.perform(put("/api/biographies").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBiography)))
            .andExpect(status().isOk());

        // Validate the Biography in the database
        List<Biography> biographyList = biographyRepository.findAll();
        assertThat(biographyList).hasSize(databaseSizeBeforeUpdate);
        Biography testBiography = biographyList.get(biographyList.size() - 1);
        assertThat(testBiography.getFromDate()).isEqualTo(UPDATED_FROM_DATE);
        assertThat(testBiography.getToDate()).isEqualTo(UPDATED_TO_DATE);
        assertThat(testBiography.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testBiography.getWorkingDecription()).isEqualTo(UPDATED_WORKING_DECRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingBiography() throws Exception {
        int databaseSizeBeforeUpdate = biographyRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBiographyMockMvc.perform(put("/api/biographies").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(biography)))
            .andExpect(status().isBadRequest());

        // Validate the Biography in the database
        List<Biography> biographyList = biographyRepository.findAll();
        assertThat(biographyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBiography() throws Exception {
        // Initialize the database
        biographyService.save(biography);

        int databaseSizeBeforeDelete = biographyRepository.findAll().size();

        // Delete the biography
        restBiographyMockMvc.perform(delete("/api/biographies/{id}", biography.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Biography> biographyList = biographyRepository.findAll();
        assertThat(biographyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
