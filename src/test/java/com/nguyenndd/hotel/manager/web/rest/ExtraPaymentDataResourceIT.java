package com.nguyenndd.hotel.manager.web.rest;

import com.nguyenndd.hotel.manager.MotelManagerApp;
import com.nguyenndd.hotel.manager.domain.ExtraPaymentData;
import com.nguyenndd.hotel.manager.repository.ExtraPaymentDataRepository;
import com.nguyenndd.hotel.manager.service.ExtraPaymentDataService;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ExtraPaymentDataResource} REST controller.
 */
@SpringBootTest(classes = MotelManagerApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ExtraPaymentDataResourceIT {

    private static final Double DEFAULT_COST = 1D;
    private static final Double UPDATED_COST = 2D;

    private static final String DEFAULT_DECRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DECRIPTION = "BBBBBBBBBB";

    @Autowired
    private ExtraPaymentDataRepository extraPaymentDataRepository;

    @Autowired
    private ExtraPaymentDataService extraPaymentDataService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExtraPaymentDataMockMvc;

    private ExtraPaymentData extraPaymentData;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExtraPaymentData createEntity(EntityManager em) {
        ExtraPaymentData extraPaymentData = new ExtraPaymentData()
            .cost(DEFAULT_COST)
            .decription(DEFAULT_DECRIPTION);
        return extraPaymentData;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExtraPaymentData createUpdatedEntity(EntityManager em) {
        ExtraPaymentData extraPaymentData = new ExtraPaymentData()
            .cost(UPDATED_COST)
            .decription(UPDATED_DECRIPTION);
        return extraPaymentData;
    }

    @BeforeEach
    public void initTest() {
        extraPaymentData = createEntity(em);
    }

    @Test
    @Transactional
    public void createExtraPaymentData() throws Exception {
        int databaseSizeBeforeCreate = extraPaymentDataRepository.findAll().size();
        // Create the ExtraPaymentData
        restExtraPaymentDataMockMvc.perform(post("/api/extra-payment-data").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(extraPaymentData)))
            .andExpect(status().isCreated());

        // Validate the ExtraPaymentData in the database
        List<ExtraPaymentData> extraPaymentDataList = extraPaymentDataRepository.findAll();
        assertThat(extraPaymentDataList).hasSize(databaseSizeBeforeCreate + 1);
        ExtraPaymentData testExtraPaymentData = extraPaymentDataList.get(extraPaymentDataList.size() - 1);
        assertThat(testExtraPaymentData.getCost()).isEqualTo(DEFAULT_COST);
        assertThat(testExtraPaymentData.getDecription()).isEqualTo(DEFAULT_DECRIPTION);
    }

    @Test
    @Transactional
    public void createExtraPaymentDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = extraPaymentDataRepository.findAll().size();

        // Create the ExtraPaymentData with an existing ID
        extraPaymentData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExtraPaymentDataMockMvc.perform(post("/api/extra-payment-data").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(extraPaymentData)))
            .andExpect(status().isBadRequest());

        // Validate the ExtraPaymentData in the database
        List<ExtraPaymentData> extraPaymentDataList = extraPaymentDataRepository.findAll();
        assertThat(extraPaymentDataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllExtraPaymentData() throws Exception {
        // Initialize the database
        extraPaymentDataRepository.saveAndFlush(extraPaymentData);

        // Get all the extraPaymentDataList
        restExtraPaymentDataMockMvc.perform(get("/api/extra-payment-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(extraPaymentData.getId().intValue())))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST.doubleValue())))
            .andExpect(jsonPath("$.[*].decription").value(hasItem(DEFAULT_DECRIPTION)));
    }
    
    @Test
    @Transactional
    public void getExtraPaymentData() throws Exception {
        // Initialize the database
        extraPaymentDataRepository.saveAndFlush(extraPaymentData);

        // Get the extraPaymentData
        restExtraPaymentDataMockMvc.perform(get("/api/extra-payment-data/{id}", extraPaymentData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(extraPaymentData.getId().intValue()))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST.doubleValue()))
            .andExpect(jsonPath("$.decription").value(DEFAULT_DECRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingExtraPaymentData() throws Exception {
        // Get the extraPaymentData
        restExtraPaymentDataMockMvc.perform(get("/api/extra-payment-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExtraPaymentData() throws Exception {
        // Initialize the database
        extraPaymentDataService.save(extraPaymentData);

        int databaseSizeBeforeUpdate = extraPaymentDataRepository.findAll().size();

        // Update the extraPaymentData
        ExtraPaymentData updatedExtraPaymentData = extraPaymentDataRepository.findById(extraPaymentData.getId()).get();
        // Disconnect from session so that the updates on updatedExtraPaymentData are not directly saved in db
        em.detach(updatedExtraPaymentData);
        updatedExtraPaymentData
            .cost(UPDATED_COST)
            .decription(UPDATED_DECRIPTION);

        restExtraPaymentDataMockMvc.perform(put("/api/extra-payment-data").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedExtraPaymentData)))
            .andExpect(status().isOk());

        // Validate the ExtraPaymentData in the database
        List<ExtraPaymentData> extraPaymentDataList = extraPaymentDataRepository.findAll();
        assertThat(extraPaymentDataList).hasSize(databaseSizeBeforeUpdate);
        ExtraPaymentData testExtraPaymentData = extraPaymentDataList.get(extraPaymentDataList.size() - 1);
        assertThat(testExtraPaymentData.getCost()).isEqualTo(UPDATED_COST);
        assertThat(testExtraPaymentData.getDecription()).isEqualTo(UPDATED_DECRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingExtraPaymentData() throws Exception {
        int databaseSizeBeforeUpdate = extraPaymentDataRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExtraPaymentDataMockMvc.perform(put("/api/extra-payment-data").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(extraPaymentData)))
            .andExpect(status().isBadRequest());

        // Validate the ExtraPaymentData in the database
        List<ExtraPaymentData> extraPaymentDataList = extraPaymentDataRepository.findAll();
        assertThat(extraPaymentDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExtraPaymentData() throws Exception {
        // Initialize the database
        extraPaymentDataService.save(extraPaymentData);

        int databaseSizeBeforeDelete = extraPaymentDataRepository.findAll().size();

        // Delete the extraPaymentData
        restExtraPaymentDataMockMvc.perform(delete("/api/extra-payment-data/{id}", extraPaymentData.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExtraPaymentData> extraPaymentDataList = extraPaymentDataRepository.findAll();
        assertThat(extraPaymentDataList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
