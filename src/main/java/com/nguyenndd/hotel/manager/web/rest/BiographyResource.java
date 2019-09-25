package com.nguyenndd.hotel.manager.web.rest;

import com.nguyenndd.hotel.manager.domain.Biography;
import com.nguyenndd.hotel.manager.service.BiographyService;
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
 * REST controller for managing {@link com.nguyenndd.hotel.manager.domain.Biography}.
 */
@RestController
@RequestMapping("/api")
public class BiographyResource {

    private final Logger log = LoggerFactory.getLogger(BiographyResource.class);

    private static final String ENTITY_NAME = "biography";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BiographyService biographyService;

    public BiographyResource(BiographyService biographyService) {
        this.biographyService = biographyService;
    }

    /**
     * {@code POST  /biographies} : Create a new biography.
     *
     * @param biography the biography to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new biography, or with status {@code 400 (Bad Request)} if the biography has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/biographies")
    public ResponseEntity<Biography> createBiography(@RequestBody Biography biography) throws URISyntaxException {
        log.debug("REST request to save Biography : {}", biography);
        if (biography.getId() != null) {
            throw new BadRequestAlertException("A new biography cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Biography result = biographyService.save(biography);
        return ResponseEntity.created(new URI("/api/biographies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /biographies} : Updates an existing biography.
     *
     * @param biography the biography to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated biography,
     * or with status {@code 400 (Bad Request)} if the biography is not valid,
     * or with status {@code 500 (Internal Server Error)} if the biography couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/biographies")
    public ResponseEntity<Biography> updateBiography(@RequestBody Biography biography) throws URISyntaxException {
        log.debug("REST request to update Biography : {}", biography);
        if (biography.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Biography result = biographyService.save(biography);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, biography.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /biographies} : get all the biographies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of biographies in body.
     */
    @GetMapping("/biographies")
    public List<Biography> getAllBiographies() {
        log.debug("REST request to get all Biographies");
        return biographyService.findAll();
    }

    /**
     * {@code GET  /biographies/:id} : get the "id" biography.
     *
     * @param id the id of the biography to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the biography, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/biographies/{id}")
    public ResponseEntity<Biography> getBiography(@PathVariable Long id) {
        log.debug("REST request to get Biography : {}", id);
        Optional<Biography> biography = biographyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(biography);
    }

    /**
     * {@code DELETE  /biographies/:id} : delete the "id" biography.
     *
     * @param id the id of the biography to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/biographies/{id}")
    public ResponseEntity<Void> deleteBiography(@PathVariable Long id) {
        log.debug("REST request to delete Biography : {}", id);
        biographyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
