package com.nguyenndd.hotel.manager.web.rest;

import com.nguyenndd.hotel.manager.domain.BillDetails;
import com.nguyenndd.hotel.manager.service.BillDetailsService;
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
 * REST controller for managing {@link com.nguyenndd.hotel.manager.domain.BillDetails}.
 */
@RestController
@RequestMapping("/api")
public class BillDetailsResource {

    private final Logger log = LoggerFactory.getLogger(BillDetailsResource.class);

    private static final String ENTITY_NAME = "billDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillDetailsService billDetailsService;

    public BillDetailsResource(BillDetailsService billDetailsService) {
        this.billDetailsService = billDetailsService;
    }

    /**
     * {@code POST  /bill-details} : Create a new billDetails.
     *
     * @param billDetails the billDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billDetails, or with status {@code 400 (Bad Request)} if the billDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bill-details")
    public ResponseEntity<BillDetails> createBillDetails(@RequestBody BillDetails billDetails) throws URISyntaxException {
        log.debug("REST request to save BillDetails : {}", billDetails);
        if (billDetails.getId() != null) {
            throw new BadRequestAlertException("A new billDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BillDetails result = billDetailsService.save(billDetails);
        return ResponseEntity.created(new URI("/api/bill-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bill-details} : Updates an existing billDetails.
     *
     * @param billDetails the billDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billDetails,
     * or with status {@code 400 (Bad Request)} if the billDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bill-details")
    public ResponseEntity<BillDetails> updateBillDetails(@RequestBody BillDetails billDetails) throws URISyntaxException {
        log.debug("REST request to update BillDetails : {}", billDetails);
        if (billDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BillDetails result = billDetailsService.save(billDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, billDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bill-details} : get all the billDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billDetails in body.
     */
    @GetMapping("/bill-details")
    public List<BillDetails> getAllBillDetails() {
        log.debug("REST request to get all BillDetails");
        return billDetailsService.findAll();
    }

    /**
     * {@code GET  /bill-details/:id} : get the "id" billDetails.
     *
     * @param id the id of the billDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bill-details/{id}")
    public ResponseEntity<BillDetails> getBillDetails(@PathVariable Long id) {
        log.debug("REST request to get BillDetails : {}", id);
        Optional<BillDetails> billDetails = billDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(billDetails);
    }

    /**
     * {@code DELETE  /bill-details/:id} : delete the "id" billDetails.
     *
     * @param id the id of the billDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bill-details/{id}")
    public ResponseEntity<Void> deleteBillDetails(@PathVariable Long id) {
        log.debug("REST request to delete BillDetails : {}", id);
        billDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
