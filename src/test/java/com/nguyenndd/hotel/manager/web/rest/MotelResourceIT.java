package com.nguyenndd.hotel.manager.web.rest;

import com.nguyenndd.hotel.manager.MotelManagerApp;
import com.nguyenndd.hotel.manager.domain.Motel;
import com.nguyenndd.hotel.manager.repository.MotelRepository;
import com.nguyenndd.hotel.manager.service.MotelService;

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
 * Integration tests for the {@link MotelResource} REST controller.
 */
@SpringBootTest(classes = MotelManagerApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MotelResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_DECRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DECRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_ELECTRICITY_PRICE = 1D;
    private static final Double UPDATED_ELECTRICITY_PRICE = 2D;

    private static final Double DEFAULT_WATER_PRICE = 1D;
    private static final Double UPDATED_WATER_PRICE = 2D;

    @Autowired
    private MotelRepository motelRepository;

    @Autowired
    private MotelService motelService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMotelMockMvc;

    private Motel motel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Motel createEntity(EntityManager em) {
        Motel motel = new Motel()
            .title(DEFAULT_TITLE)
            .address(DEFAULT_ADDRESS)
            .phone(DEFAULT_PHONE)
            .decription(DEFAULT_DECRIPTION)
            .electricityPrice(DEFAULT_ELECTRICITY_PRICE)
            .waterPrice(DEFAULT_WATER_PRICE);
        return motel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Motel createUpdatedEntity(EntityManager em) {
        Motel motel = new Motel()
            .title(UPDATED_TITLE)
            .address(UPDATED_ADDRESS)
            .phone(UPDATED_PHONE)
            .decription(UPDATED_DECRIPTION)
            .electricityPrice(UPDATED_ELECTRICITY_PRICE)
            .waterPrice(UPDATED_WATER_PRICE);
        return motel;
    }

    @BeforeEach
    public void initTest() {
        motel = createEntity(em);
    }

    @Test
    @Transactional
    public void createMotel() throws Exception {
        int databaseSizeBeforeCreate = motelRepository.findAll().size();
        // Create the Motel
        restMotelMockMvc.perform(post("/api/motels").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(motel)))
            .andExpect(status().isCreated());

        // Validate the Motel in the database
        List<Motel> motelList = motelRepository.findAll();
        assertThat(motelList).hasSize(databaseSizeBeforeCreate + 1);
        Motel testMotel = motelList.get(motelList.size() - 1);
        assertThat(testMotel.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMotel.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testMotel.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testMotel.getDecription()).isEqualTo(DEFAULT_DECRIPTION);
        assertThat(testMotel.getElectricityPrice()).isEqualTo(DEFAULT_ELECTRICITY_PRICE);
        assertThat(testMotel.getWaterPrice()).isEqualTo(DEFAULT_WATER_PRICE);
    }

    @Test
    @Transactional
    public void createMotelWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = motelRepository.findAll().size();

        // Create the Motel with an existing ID
        motel.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMotelMockMvc.perform(post("/api/motels").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(motel)))
            .andExpect(status().isBadRequest());

        // Validate the Motel in the database
        List<Motel> motelList = motelRepository.findAll();
        assertThat(motelList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMotels() throws Exception {
        // Initialize the database
        motelRepository.saveAndFlush(motel);

        // Get all the motelList
        restMotelMockMvc.perform(get("/api/motels?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(motel.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].decription").value(hasItem(DEFAULT_DECRIPTION)))
            .andExpect(jsonPath("$.[*].electricityPrice").value(hasItem(DEFAULT_ELECTRICITY_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].waterPrice").value(hasItem(DEFAULT_WATER_PRICE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getMotel() throws Exception {
        // Initialize the database
        motelRepository.saveAndFlush(motel);

        // Get the motel
        restMotelMockMvc.perform(get("/api/motels/{id}", motel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(motel.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.decription").value(DEFAULT_DECRIPTION))
            .andExpect(jsonPath("$.electricityPrice").value(DEFAULT_ELECTRICITY_PRICE.doubleValue()))
            .andExpect(jsonPath("$.waterPrice").value(DEFAULT_WATER_PRICE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMotel() throws Exception {
        // Get the motel
        restMotelMockMvc.perform(get("/api/motels/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMotel() throws Exception {
        // Initialize the database
        motelService.save(motel);

        int databaseSizeBeforeUpdate = motelRepository.findAll().size();

        // Update the motel
        Motel updatedMotel = motelRepository.findById(motel.getId()).get();
        // Disconnect from session so that the updates on updatedMotel are not directly saved in db
        em.detach(updatedMotel);
        updatedMotel
            .title(UPDATED_TITLE)
            .address(UPDATED_ADDRESS)
            .phone(UPDATED_PHONE)
            .decription(UPDATED_DECRIPTION)
            .electricityPrice(UPDATED_ELECTRICITY_PRICE)
            .waterPrice(UPDATED_WATER_PRICE);

        restMotelMockMvc.perform(put("/api/motels").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMotel)))
            .andExpect(status().isOk());

        // Validate the Motel in the database
        List<Motel> motelList = motelRepository.findAll();
        assertThat(motelList).hasSize(databaseSizeBeforeUpdate);
        Motel testMotel = motelList.get(motelList.size() - 1);
        assertThat(testMotel.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMotel.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testMotel.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testMotel.getDecription()).isEqualTo(UPDATED_DECRIPTION);
        assertThat(testMotel.getElectricityPrice()).isEqualTo(UPDATED_ELECTRICITY_PRICE);
        assertThat(testMotel.getWaterPrice()).isEqualTo(UPDATED_WATER_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingMotel() throws Exception {
        int databaseSizeBeforeUpdate = motelRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMotelMockMvc.perform(put("/api/motels").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(motel)))
            .andExpect(status().isBadRequest());

        // Validate the Motel in the database
        List<Motel> motelList = motelRepository.findAll();
        assertThat(motelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMotel() throws Exception {
        // Initialize the database
        motelService.save(motel);

        int databaseSizeBeforeDelete = motelRepository.findAll().size();

        // Delete the motel
        restMotelMockMvc.perform(delete("/api/motels/{id}", motel.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Motel> motelList = motelRepository.findAll();
        assertThat(motelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
