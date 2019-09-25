package com.nguyenndd.hotel.manager.web.rest;

import com.nguyenndd.hotel.manager.MotelManagerApp;
import com.nguyenndd.hotel.manager.domain.Customer;
import com.nguyenndd.hotel.manager.repository.CustomerRepository;
import com.nguyenndd.hotel.manager.service.CustomerService;

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

import com.nguyenndd.hotel.manager.domain.enumeration.Gender;
import com.nguyenndd.hotel.manager.domain.enumeration.Nation;
/**
 * Integration tests for the {@link CustomerResource} REST controller.
 */
@SpringBootTest(classes = MotelManagerApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CustomerResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final String DEFAULT_BIRTHDAY = "AAAAAAAAAA";
    private static final String UPDATED_BIRTHDAY = "BBBBBBBBBB";

    private static final String DEFAULT_EXTRA_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EXTRA_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORIGIN_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ORIGIN_ADDRESS = "BBBBBBBBBB";

    private static final Nation DEFAULT_NATION = Nation.KINH;
    private static final Nation UPDATED_NATION = Nation.MUONG;

    private static final String DEFAULT_RELIGION = "AAAAAAAAAA";
    private static final String UPDATED_RELIGION = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONALITY = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITY = "BBBBBBBBBB";

    private static final String DEFAULT_INDENTITY = "AAAAAAAAAA";
    private static final String UPDATED_INDENTITY = "BBBBBBBBBB";

    private static final String DEFAULT_REGULARLY_ADRESS = "AAAAAAAAAA";
    private static final String UPDATED_REGULARLY_ADRESS = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_ACADEMIC_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_ACADEMIC_LEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_QUALIFICATION = "AAAAAAAAAA";
    private static final String UPDATED_QUALIFICATION = "BBBBBBBBBB";

    private static final String DEFAULT_FOREIGN_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_FOREIGN_LEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_JOB = "AAAAAAAAAA";
    private static final String UPDATED_JOB = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PASSSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_STATUS = false;
    private static final Boolean UPDATED_STATUS = true;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomerMockMvc;

    private Customer customer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createEntity(EntityManager em) {
        Customer customer = new Customer()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .gender(DEFAULT_GENDER)
            .birthday(DEFAULT_BIRTHDAY)
            .extraName(DEFAULT_EXTRA_NAME)
            .originAddress(DEFAULT_ORIGIN_ADDRESS)
            .nation(DEFAULT_NATION)
            .religion(DEFAULT_RELIGION)
            .nationality(DEFAULT_NATIONALITY)
            .indentity(DEFAULT_INDENTITY)
            .regularlyAdress(DEFAULT_REGULARLY_ADRESS)
            .address(DEFAULT_ADDRESS)
            .academicLevel(DEFAULT_ACADEMIC_LEVEL)
            .qualification(DEFAULT_QUALIFICATION)
            .foreignLevel(DEFAULT_FOREIGN_LEVEL)
            .job(DEFAULT_JOB)
            .email(DEFAULT_EMAIL)
            .passsword(DEFAULT_PASSSWORD)
            .phone(DEFAULT_PHONE)
            .status(DEFAULT_STATUS);
        return customer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createUpdatedEntity(EntityManager em) {
        Customer customer = new Customer()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .gender(UPDATED_GENDER)
            .birthday(UPDATED_BIRTHDAY)
            .extraName(UPDATED_EXTRA_NAME)
            .originAddress(UPDATED_ORIGIN_ADDRESS)
            .nation(UPDATED_NATION)
            .religion(UPDATED_RELIGION)
            .nationality(UPDATED_NATIONALITY)
            .indentity(UPDATED_INDENTITY)
            .regularlyAdress(UPDATED_REGULARLY_ADRESS)
            .address(UPDATED_ADDRESS)
            .academicLevel(UPDATED_ACADEMIC_LEVEL)
            .qualification(UPDATED_QUALIFICATION)
            .foreignLevel(UPDATED_FOREIGN_LEVEL)
            .job(UPDATED_JOB)
            .email(UPDATED_EMAIL)
            .passsword(UPDATED_PASSSWORD)
            .phone(UPDATED_PHONE)
            .status(UPDATED_STATUS);
        return customer;
    }

    @BeforeEach
    public void initTest() {
        customer = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomer() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();
        // Create the Customer
        restCustomerMockMvc.perform(post("/api/customers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isCreated());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate + 1);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testCustomer.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testCustomer.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testCustomer.getBirthday()).isEqualTo(DEFAULT_BIRTHDAY);
        assertThat(testCustomer.getExtraName()).isEqualTo(DEFAULT_EXTRA_NAME);
        assertThat(testCustomer.getOriginAddress()).isEqualTo(DEFAULT_ORIGIN_ADDRESS);
        assertThat(testCustomer.getNation()).isEqualTo(DEFAULT_NATION);
        assertThat(testCustomer.getReligion()).isEqualTo(DEFAULT_RELIGION);
        assertThat(testCustomer.getNationality()).isEqualTo(DEFAULT_NATIONALITY);
        assertThat(testCustomer.getIndentity()).isEqualTo(DEFAULT_INDENTITY);
        assertThat(testCustomer.getRegularlyAdress()).isEqualTo(DEFAULT_REGULARLY_ADRESS);
        assertThat(testCustomer.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testCustomer.getAcademicLevel()).isEqualTo(DEFAULT_ACADEMIC_LEVEL);
        assertThat(testCustomer.getQualification()).isEqualTo(DEFAULT_QUALIFICATION);
        assertThat(testCustomer.getForeignLevel()).isEqualTo(DEFAULT_FOREIGN_LEVEL);
        assertThat(testCustomer.getJob()).isEqualTo(DEFAULT_JOB);
        assertThat(testCustomer.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCustomer.getPasssword()).isEqualTo(DEFAULT_PASSSWORD);
        assertThat(testCustomer.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testCustomer.isStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCustomerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();

        // Create the Customer with an existing ID
        customer.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerMockMvc.perform(post("/api/customers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCustomers() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        // Get all the customerList
        restCustomerMockMvc.perform(get("/api/customers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].birthday").value(hasItem(DEFAULT_BIRTHDAY)))
            .andExpect(jsonPath("$.[*].extraName").value(hasItem(DEFAULT_EXTRA_NAME)))
            .andExpect(jsonPath("$.[*].originAddress").value(hasItem(DEFAULT_ORIGIN_ADDRESS)))
            .andExpect(jsonPath("$.[*].nation").value(hasItem(DEFAULT_NATION.toString())))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION)))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY)))
            .andExpect(jsonPath("$.[*].indentity").value(hasItem(DEFAULT_INDENTITY)))
            .andExpect(jsonPath("$.[*].regularlyAdress").value(hasItem(DEFAULT_REGULARLY_ADRESS)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].academicLevel").value(hasItem(DEFAULT_ACADEMIC_LEVEL)))
            .andExpect(jsonPath("$.[*].qualification").value(hasItem(DEFAULT_QUALIFICATION)))
            .andExpect(jsonPath("$.[*].foreignLevel").value(hasItem(DEFAULT_FOREIGN_LEVEL)))
            .andExpect(jsonPath("$.[*].job").value(hasItem(DEFAULT_JOB)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].passsword").value(hasItem(DEFAULT_PASSSWORD)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        // Get the customer
        restCustomerMockMvc.perform(get("/api/customers/{id}", customer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customer.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.birthday").value(DEFAULT_BIRTHDAY))
            .andExpect(jsonPath("$.extraName").value(DEFAULT_EXTRA_NAME))
            .andExpect(jsonPath("$.originAddress").value(DEFAULT_ORIGIN_ADDRESS))
            .andExpect(jsonPath("$.nation").value(DEFAULT_NATION.toString()))
            .andExpect(jsonPath("$.religion").value(DEFAULT_RELIGION))
            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY))
            .andExpect(jsonPath("$.indentity").value(DEFAULT_INDENTITY))
            .andExpect(jsonPath("$.regularlyAdress").value(DEFAULT_REGULARLY_ADRESS))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.academicLevel").value(DEFAULT_ACADEMIC_LEVEL))
            .andExpect(jsonPath("$.qualification").value(DEFAULT_QUALIFICATION))
            .andExpect(jsonPath("$.foreignLevel").value(DEFAULT_FOREIGN_LEVEL))
            .andExpect(jsonPath("$.job").value(DEFAULT_JOB))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.passsword").value(DEFAULT_PASSSWORD))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCustomer() throws Exception {
        // Get the customer
        restCustomerMockMvc.perform(get("/api/customers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomer() throws Exception {
        // Initialize the database
        customerService.save(customer);

        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Update the customer
        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        // Disconnect from session so that the updates on updatedCustomer are not directly saved in db
        em.detach(updatedCustomer);
        updatedCustomer
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .gender(UPDATED_GENDER)
            .birthday(UPDATED_BIRTHDAY)
            .extraName(UPDATED_EXTRA_NAME)
            .originAddress(UPDATED_ORIGIN_ADDRESS)
            .nation(UPDATED_NATION)
            .religion(UPDATED_RELIGION)
            .nationality(UPDATED_NATIONALITY)
            .indentity(UPDATED_INDENTITY)
            .regularlyAdress(UPDATED_REGULARLY_ADRESS)
            .address(UPDATED_ADDRESS)
            .academicLevel(UPDATED_ACADEMIC_LEVEL)
            .qualification(UPDATED_QUALIFICATION)
            .foreignLevel(UPDATED_FOREIGN_LEVEL)
            .job(UPDATED_JOB)
            .email(UPDATED_EMAIL)
            .passsword(UPDATED_PASSSWORD)
            .phone(UPDATED_PHONE)
            .status(UPDATED_STATUS);

        restCustomerMockMvc.perform(put("/api/customers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCustomer)))
            .andExpect(status().isOk());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customerList.get(customerList.size() - 1);
        assertThat(testCustomer.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testCustomer.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testCustomer.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testCustomer.getBirthday()).isEqualTo(UPDATED_BIRTHDAY);
        assertThat(testCustomer.getExtraName()).isEqualTo(UPDATED_EXTRA_NAME);
        assertThat(testCustomer.getOriginAddress()).isEqualTo(UPDATED_ORIGIN_ADDRESS);
        assertThat(testCustomer.getNation()).isEqualTo(UPDATED_NATION);
        assertThat(testCustomer.getReligion()).isEqualTo(UPDATED_RELIGION);
        assertThat(testCustomer.getNationality()).isEqualTo(UPDATED_NATIONALITY);
        assertThat(testCustomer.getIndentity()).isEqualTo(UPDATED_INDENTITY);
        assertThat(testCustomer.getRegularlyAdress()).isEqualTo(UPDATED_REGULARLY_ADRESS);
        assertThat(testCustomer.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testCustomer.getAcademicLevel()).isEqualTo(UPDATED_ACADEMIC_LEVEL);
        assertThat(testCustomer.getQualification()).isEqualTo(UPDATED_QUALIFICATION);
        assertThat(testCustomer.getForeignLevel()).isEqualTo(UPDATED_FOREIGN_LEVEL);
        assertThat(testCustomer.getJob()).isEqualTo(UPDATED_JOB);
        assertThat(testCustomer.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCustomer.getPasssword()).isEqualTo(UPDATED_PASSSWORD);
        assertThat(testCustomer.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testCustomer.isStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomer() throws Exception {
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerMockMvc.perform(put("/api/customers").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(customer)))
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCustomer() throws Exception {
        // Initialize the database
        customerService.save(customer);

        int databaseSizeBeforeDelete = customerRepository.findAll().size();

        // Delete the customer
        restCustomerMockMvc.perform(delete("/api/customers/{id}", customer.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Customer> customerList = customerRepository.findAll();
        assertThat(customerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
