package com.nguyenndd.hotel.manager.web.rest;

import com.nguyenndd.hotel.manager.MotelManagerApp;
import com.nguyenndd.hotel.manager.domain.Convenient;
import com.nguyenndd.hotel.manager.repository.ConvenientRepository;
import com.nguyenndd.hotel.manager.service.ConvenientService;

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
 * Integration tests for the {@link ConvenientResource} REST controller.
 */
@SpringBootTest(classes = MotelManagerApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ConvenientResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DECRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DECRIPTION = "BBBBBBBBBB";

    @Autowired
    private ConvenientRepository convenientRepository;

    @Autowired
    private ConvenientService convenientService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restConvenientMockMvc;

    private Convenient convenient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Convenient createEntity(EntityManager em) {
        Convenient convenient = new Convenient()
            .title(DEFAULT_TITLE)
            .decription(DEFAULT_DECRIPTION);
        return convenient;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Convenient createUpdatedEntity(EntityManager em) {
        Convenient convenient = new Convenient()
            .title(UPDATED_TITLE)
            .decription(UPDATED_DECRIPTION);
        return convenient;
    }

    @BeforeEach
    public void initTest() {
        convenient = createEntity(em);
    }

    @Test
    @Transactional
    public void createConvenient() throws Exception {
        int databaseSizeBeforeCreate = convenientRepository.findAll().size();
        // Create the Convenient
        restConvenientMockMvc.perform(post("/api/convenients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(convenient)))
            .andExpect(status().isCreated());

        // Validate the Convenient in the database
        List<Convenient> convenientList = convenientRepository.findAll();
        assertThat(convenientList).hasSize(databaseSizeBeforeCreate + 1);
        Convenient testConvenient = convenientList.get(convenientList.size() - 1);
        assertThat(testConvenient.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testConvenient.getDecription()).isEqualTo(DEFAULT_DECRIPTION);
    }

    @Test
    @Transactional
    public void createConvenientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = convenientRepository.findAll().size();

        // Create the Convenient with an existing ID
        convenient.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConvenientMockMvc.perform(post("/api/convenients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(convenient)))
            .andExpect(status().isBadRequest());

        // Validate the Convenient in the database
        List<Convenient> convenientList = convenientRepository.findAll();
        assertThat(convenientList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllConvenients() throws Exception {
        // Initialize the database
        convenientRepository.saveAndFlush(convenient);

        // Get all the convenientList
        restConvenientMockMvc.perform(get("/api/convenients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(convenient.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].decription").value(hasItem(DEFAULT_DECRIPTION)));
    }
    
    @Test
    @Transactional
    public void getConvenient() throws Exception {
        // Initialize the database
        convenientRepository.saveAndFlush(convenient);

        // Get the convenient
        restConvenientMockMvc.perform(get("/api/convenients/{id}", convenient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(convenient.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.decription").value(DEFAULT_DECRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingConvenient() throws Exception {
        // Get the convenient
        restConvenientMockMvc.perform(get("/api/convenients/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConvenient() throws Exception {
        // Initialize the database
        convenientService.save(convenient);

        int databaseSizeBeforeUpdate = convenientRepository.findAll().size();

        // Update the convenient
        Convenient updatedConvenient = convenientRepository.findById(convenient.getId()).get();
        // Disconnect from session so that the updates on updatedConvenient are not directly saved in db
        em.detach(updatedConvenient);
        updatedConvenient
            .title(UPDATED_TITLE)
            .decription(UPDATED_DECRIPTION);

        restConvenientMockMvc.perform(put("/api/convenients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedConvenient)))
            .andExpect(status().isOk());

        // Validate the Convenient in the database
        List<Convenient> convenientList = convenientRepository.findAll();
        assertThat(convenientList).hasSize(databaseSizeBeforeUpdate);
        Convenient testConvenient = convenientList.get(convenientList.size() - 1);
        assertThat(testConvenient.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testConvenient.getDecription()).isEqualTo(UPDATED_DECRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingConvenient() throws Exception {
        int databaseSizeBeforeUpdate = convenientRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConvenientMockMvc.perform(put("/api/convenients").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(convenient)))
            .andExpect(status().isBadRequest());

        // Validate the Convenient in the database
        List<Convenient> convenientList = convenientRepository.findAll();
        assertThat(convenientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConvenient() throws Exception {
        // Initialize the database
        convenientService.save(convenient);

        int databaseSizeBeforeDelete = convenientRepository.findAll().size();

        // Delete the convenient
        restConvenientMockMvc.perform(delete("/api/convenients/{id}", convenient.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Convenient> convenientList = convenientRepository.findAll();
        assertThat(convenientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
