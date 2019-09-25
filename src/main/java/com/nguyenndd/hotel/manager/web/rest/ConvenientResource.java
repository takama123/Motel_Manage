package com.nguyenndd.hotel.manager.web.rest;

import com.nguyenndd.hotel.manager.domain.Convenient;
import com.nguyenndd.hotel.manager.service.ConvenientService;
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
 * REST controller for managing {@link com.nguyenndd.hotel.manager.domain.Convenient}.
 */
@RestController
@RequestMapping("/api")
public class ConvenientResource {

    private final Logger log = LoggerFactory.getLogger(ConvenientResource.class);

    private static final String ENTITY_NAME = "convenient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConvenientService convenientService;

    public ConvenientResource(ConvenientService convenientService) {
        this.convenientService = convenientService;
    }

    /**
     * {@code POST  /convenients} : Create a new convenient.
     *
     * @param convenient the convenient to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new convenient, or with status {@code 400 (Bad Request)} if the convenient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/convenients")
    public ResponseEntity<Convenient> createConvenient(@RequestBody Convenient convenient) throws URISyntaxException {
        log.debug("REST request to save Convenient : {}", convenient);
        if (convenient.getId() != null) {
            throw new BadRequestAlertException("A new convenient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Convenient result = convenientService.save(convenient);
        return ResponseEntity.created(new URI("/api/convenients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /convenients} : Updates an existing convenient.
     *
     * @param convenient the convenient to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated convenient,
     * or with status {@code 400 (Bad Request)} if the convenient is not valid,
     * or with status {@code 500 (Internal Server Error)} if the convenient couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/convenients")
    public ResponseEntity<Convenient> updateConvenient(@RequestBody Convenient convenient) throws URISyntaxException {
        log.debug("REST request to update Convenient : {}", convenient);
        if (convenient.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Convenient result = convenientService.save(convenient);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, convenient.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /convenients} : get all the convenients.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of convenients in body.
     */
    @GetMapping("/convenients")
    public List<Convenient> getAllConvenients() {
        log.debug("REST request to get all Convenients");
        return convenientService.findAll();
    }

    /**
     * {@code GET  /convenients/:id} : get the "id" convenient.
     *
     * @param id the id of the convenient to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the convenient, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/convenients/{id}")
    public ResponseEntity<Convenient> getConvenient(@PathVariable Long id) {
        log.debug("REST request to get Convenient : {}", id);
        Optional<Convenient> convenient = convenientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(convenient);
    }

    /**
     * {@code DELETE  /convenients/:id} : delete the "id" convenient.
     *
     * @param id the id of the convenient to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/convenients/{id}")
    public ResponseEntity<Void> deleteConvenient(@PathVariable Long id) {
        log.debug("REST request to delete Convenient : {}", id);
        convenientService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
