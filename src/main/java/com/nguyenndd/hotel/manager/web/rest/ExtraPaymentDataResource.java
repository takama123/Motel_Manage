package com.nguyenndd.hotel.manager.web.rest;

import com.nguyenndd.hotel.manager.domain.ExtraPaymentData;
import com.nguyenndd.hotel.manager.service.ExtraPaymentDataService;
import com.nguyenndd.hotel.manager.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.nguyenndd.hotel.manager.domain.ExtraPaymentData}.
 */
@RestController
@RequestMapping("/api")
public class ExtraPaymentDataResource {

    private final Logger log = LoggerFactory.getLogger(ExtraPaymentDataResource.class);

    private static final String ENTITY_NAME = "extraPaymentData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExtraPaymentDataService extraPaymentDataService;

    public ExtraPaymentDataResource(ExtraPaymentDataService extraPaymentDataService) {
        this.extraPaymentDataService = extraPaymentDataService;
    }

    /**
     * {@code POST  /extra-payment-data} : Create a new extraPaymentData.
     *
     * @param extraPaymentData the extraPaymentData to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new extraPaymentData, or with status {@code 400 (Bad Request)} if the extraPaymentData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/extra-payment-data")
    public ResponseEntity<ExtraPaymentData> createExtraPaymentData(@RequestBody ExtraPaymentData extraPaymentData) throws URISyntaxException {
        log.debug("REST request to save ExtraPaymentData : {}", extraPaymentData);
        if (extraPaymentData.getId() != null) {
            throw new BadRequestAlertException("A new extraPaymentData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExtraPaymentData result = extraPaymentDataService.save(extraPaymentData);
        return ResponseEntity.created(new URI("/api/extra-payment-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /extra-payment-data} : Updates an existing extraPaymentData.
     *
     * @param extraPaymentData the extraPaymentData to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated extraPaymentData,
     * or with status {@code 400 (Bad Request)} if the extraPaymentData is not valid,
     * or with status {@code 500 (Internal Server Error)} if the extraPaymentData couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/extra-payment-data")
    public ResponseEntity<ExtraPaymentData> updateExtraPaymentData(@RequestBody ExtraPaymentData extraPaymentData) throws URISyntaxException {
        log.debug("REST request to update ExtraPaymentData : {}", extraPaymentData);
        if (extraPaymentData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExtraPaymentData result = extraPaymentDataService.save(extraPaymentData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, extraPaymentData.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /extra-payment-data} : get all the extraPaymentData.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of extraPaymentData in body.
     */
    @GetMapping("/extra-payment-data")
    public List<ExtraPaymentData> getAllExtraPaymentData() {
        log.debug("REST request to get all ExtraPaymentData");
        return extraPaymentDataService.findAll();
    }

    /**
     * {@code GET  /extra-payment-data/:id} : get the "id" extraPaymentData.
     *
     * @param id the id of the extraPaymentData to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the extraPaymentData, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/extra-payment-data/{id}")
    public ResponseEntity<ExtraPaymentData> getExtraPaymentData(@PathVariable Long id) {
        log.debug("REST request to get ExtraPaymentData : {}", id);
        Optional<ExtraPaymentData> extraPaymentData = extraPaymentDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(extraPaymentData);
    }

    /**
     * {@code DELETE  /extra-payment-data/:id} : delete the "id" extraPaymentData.
     *
     * @param id the id of the extraPaymentData to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/extra-payment-data/{id}")
    public ResponseEntity<Void> deleteExtraPaymentData(@PathVariable Long id) {
        log.debug("REST request to delete ExtraPaymentData : {}", id);
        extraPaymentDataService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
