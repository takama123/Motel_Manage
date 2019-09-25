package com.nguyenndd.hotel.manager.web.rest;

import com.nguyenndd.hotel.manager.MotelManagerApp;
import com.nguyenndd.hotel.manager.domain.BillDetails;
import com.nguyenndd.hotel.manager.repository.BillDetailsRepository;
import com.nguyenndd.hotel.manager.service.BillDetailsService;

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
 * Integration tests for the {@link BillDetailsResource} REST controller.
 */
@SpringBootTest(classes = MotelManagerApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BillDetailsResourceIT {

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_OLD_KWH = 1D;
    private static final Double UPDATED_OLD_KWH = 2D;

    private static final Double DEFAULT_OLD_WATER = 1D;
    private static final Double UPDATED_OLD_WATER = 2D;

    private static final Double DEFAULT_NEW_KWH = 1D;
    private static final Double UPDATED_NEW_KWH = 2D;

    private static final Double DEFAULT_NEW_WATER = 1D;
    private static final Double UPDATED_NEW_WATER = 2D;

    private static final Double DEFAULT_ROOM_PRICE = 1D;
    private static final Double UPDATED_ROOM_PRICE = 2D;

    private static final Double DEFAULT_ELECTRICITY_PRICE = 1D;
    private static final Double UPDATED_ELECTRICITY_PRICE = 2D;

    private static final Double DEFAULT_WATER_PRICE = 1D;
    private static final Double UPDATED_WATER_PRICE = 2D;

    @Autowired
    private BillDetailsRepository billDetailsRepository;

    @Autowired
    private BillDetailsService billDetailsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillDetailsMockMvc;

    private BillDetails billDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillDetails createEntity(EntityManager em) {
        BillDetails billDetails = new BillDetails()
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .oldKwh(DEFAULT_OLD_KWH)
            .oldWater(DEFAULT_OLD_WATER)
            .newKwh(DEFAULT_NEW_KWH)
            .newWater(DEFAULT_NEW_WATER)
            .roomPrice(DEFAULT_ROOM_PRICE)
            .electricityPrice(DEFAULT_ELECTRICITY_PRICE)
            .waterPrice(DEFAULT_WATER_PRICE);
        return billDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BillDetails createUpdatedEntity(EntityManager em) {
        BillDetails billDetails = new BillDetails()
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .oldKwh(UPDATED_OLD_KWH)
            .oldWater(UPDATED_OLD_WATER)
            .newKwh(UPDATED_NEW_KWH)
            .newWater(UPDATED_NEW_WATER)
            .roomPrice(UPDATED_ROOM_PRICE)
            .electricityPrice(UPDATED_ELECTRICITY_PRICE)
            .waterPrice(UPDATED_WATER_PRICE);
        return billDetails;
    }

    @BeforeEach
    public void initTest() {
        billDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createBillDetails() throws Exception {
        int databaseSizeBeforeCreate = billDetailsRepository.findAll().size();
        // Create the BillDetails
        restBillDetailsMockMvc.perform(post("/api/bill-details").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billDetails)))
            .andExpect(status().isCreated());

        // Validate the BillDetails in the database
        List<BillDetails> billDetailsList = billDetailsRepository.findAll();
        assertThat(billDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        BillDetails testBillDetails = billDetailsList.get(billDetailsList.size() - 1);
        assertThat(testBillDetails.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testBillDetails.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testBillDetails.getOldKwh()).isEqualTo(DEFAULT_OLD_KWH);
        assertThat(testBillDetails.getOldWater()).isEqualTo(DEFAULT_OLD_WATER);
        assertThat(testBillDetails.getNewKwh()).isEqualTo(DEFAULT_NEW_KWH);
        assertThat(testBillDetails.getNewWater()).isEqualTo(DEFAULT_NEW_WATER);
        assertThat(testBillDetails.getRoomPrice()).isEqualTo(DEFAULT_ROOM_PRICE);
        assertThat(testBillDetails.getElectricityPrice()).isEqualTo(DEFAULT_ELECTRICITY_PRICE);
        assertThat(testBillDetails.getWaterPrice()).isEqualTo(DEFAULT_WATER_PRICE);
    }

    @Test
    @Transactional
    public void createBillDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = billDetailsRepository.findAll().size();

        // Create the BillDetails with an existing ID
        billDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillDetailsMockMvc.perform(post("/api/bill-details").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billDetails)))
            .andExpect(status().isBadRequest());

        // Validate the BillDetails in the database
        List<BillDetails> billDetailsList = billDetailsRepository.findAll();
        assertThat(billDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBillDetails() throws Exception {
        // Initialize the database
        billDetailsRepository.saveAndFlush(billDetails);

        // Get all the billDetailsList
        restBillDetailsMockMvc.perform(get("/api/bill-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(billDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].oldKwh").value(hasItem(DEFAULT_OLD_KWH.doubleValue())))
            .andExpect(jsonPath("$.[*].oldWater").value(hasItem(DEFAULT_OLD_WATER.doubleValue())))
            .andExpect(jsonPath("$.[*].newKwh").value(hasItem(DEFAULT_NEW_KWH.doubleValue())))
            .andExpect(jsonPath("$.[*].newWater").value(hasItem(DEFAULT_NEW_WATER.doubleValue())))
            .andExpect(jsonPath("$.[*].roomPrice").value(hasItem(DEFAULT_ROOM_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].electricityPrice").value(hasItem(DEFAULT_ELECTRICITY_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].waterPrice").value(hasItem(DEFAULT_WATER_PRICE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getBillDetails() throws Exception {
        // Initialize the database
        billDetailsRepository.saveAndFlush(billDetails);

        // Get the billDetails
        restBillDetailsMockMvc.perform(get("/api/bill-details/{id}", billDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(billDetails.getId().intValue()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.oldKwh").value(DEFAULT_OLD_KWH.doubleValue()))
            .andExpect(jsonPath("$.oldWater").value(DEFAULT_OLD_WATER.doubleValue()))
            .andExpect(jsonPath("$.newKwh").value(DEFAULT_NEW_KWH.doubleValue()))
            .andExpect(jsonPath("$.newWater").value(DEFAULT_NEW_WATER.doubleValue()))
            .andExpect(jsonPath("$.roomPrice").value(DEFAULT_ROOM_PRICE.doubleValue()))
            .andExpect(jsonPath("$.electricityPrice").value(DEFAULT_ELECTRICITY_PRICE.doubleValue()))
            .andExpect(jsonPath("$.waterPrice").value(DEFAULT_WATER_PRICE.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingBillDetails() throws Exception {
        // Get the billDetails
        restBillDetailsMockMvc.perform(get("/api/bill-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBillDetails() throws Exception {
        // Initialize the database
        billDetailsService.save(billDetails);

        int databaseSizeBeforeUpdate = billDetailsRepository.findAll().size();

        // Update the billDetails
        BillDetails updatedBillDetails = billDetailsRepository.findById(billDetails.getId()).get();
        // Disconnect from session so that the updates on updatedBillDetails are not directly saved in db
        em.detach(updatedBillDetails);
        updatedBillDetails
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .oldKwh(UPDATED_OLD_KWH)
            .oldWater(UPDATED_OLD_WATER)
            .newKwh(UPDATED_NEW_KWH)
            .newWater(UPDATED_NEW_WATER)
            .roomPrice(UPDATED_ROOM_PRICE)
            .electricityPrice(UPDATED_ELECTRICITY_PRICE)
            .waterPrice(UPDATED_WATER_PRICE);

        restBillDetailsMockMvc.perform(put("/api/bill-details").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedBillDetails)))
            .andExpect(status().isOk());

        // Validate the BillDetails in the database
        List<BillDetails> billDetailsList = billDetailsRepository.findAll();
        assertThat(billDetailsList).hasSize(databaseSizeBeforeUpdate);
        BillDetails testBillDetails = billDetailsList.get(billDetailsList.size() - 1);
        assertThat(testBillDetails.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testBillDetails.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testBillDetails.getOldKwh()).isEqualTo(UPDATED_OLD_KWH);
        assertThat(testBillDetails.getOldWater()).isEqualTo(UPDATED_OLD_WATER);
        assertThat(testBillDetails.getNewKwh()).isEqualTo(UPDATED_NEW_KWH);
        assertThat(testBillDetails.getNewWater()).isEqualTo(UPDATED_NEW_WATER);
        assertThat(testBillDetails.getRoomPrice()).isEqualTo(UPDATED_ROOM_PRICE);
        assertThat(testBillDetails.getElectricityPrice()).isEqualTo(UPDATED_ELECTRICITY_PRICE);
        assertThat(testBillDetails.getWaterPrice()).isEqualTo(UPDATED_WATER_PRICE);
    }

    @Test
    @Transactional
    public void updateNonExistingBillDetails() throws Exception {
        int databaseSizeBeforeUpdate = billDetailsRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillDetailsMockMvc.perform(put("/api/bill-details").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(billDetails)))
            .andExpect(status().isBadRequest());

        // Validate the BillDetails in the database
        List<BillDetails> billDetailsList = billDetailsRepository.findAll();
        assertThat(billDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBillDetails() throws Exception {
        // Initialize the database
        billDetailsService.save(billDetails);

        int databaseSizeBeforeDelete = billDetailsRepository.findAll().size();

        // Delete the billDetails
        restBillDetailsMockMvc.perform(delete("/api/bill-details/{id}", billDetails.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BillDetails> billDetailsList = billDetailsRepository.findAll();
        assertThat(billDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
