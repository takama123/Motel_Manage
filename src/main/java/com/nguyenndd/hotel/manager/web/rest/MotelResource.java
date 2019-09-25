package com.nguyenndd.hotel.manager.web.rest;

import com.nguyenndd.hotel.manager.domain.Motel;
import com.nguyenndd.hotel.manager.service.MotelService;
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
 * REST controller for managing {@link com.nguyenndd.hotel.manager.domain.Motel}.
 */
@RestController
@RequestMapping("/api")
public class MotelResource {

    private final Logger log = LoggerFactory.getLogger(MotelResource.class);

    private static final String ENTITY_NAME = "motel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MotelService motelService;

    public MotelResource(MotelService motelService) {
        this.motelService = motelService;
    }

    /**
     * {@code POST  /motels} : Create a new motel.
     *
     * @param motel the motel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new motel, or with status {@code 400 (Bad Request)} if the motel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/motels")
    public ResponseEntity<Motel> createMotel(@RequestBody Motel motel) throws URISyntaxException {
        log.debug("REST request to save Motel : {}", motel);
        if (motel.getId() != null) {
            throw new BadRequestAlertException("A new motel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Motel result = motelService.save(motel);
        return ResponseEntity.created(new URI("/api/motels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /motels} : Updates an existing motel.
     *
     * @param motel the motel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated motel,
     * or with status {@code 400 (Bad Request)} if the motel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the motel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/motels")
    public ResponseEntity<Motel> updateMotel(@RequestBody Motel motel) throws URISyntaxException {
        log.debug("REST request to update Motel : {}", motel);
        if (motel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Motel result = motelService.save(motel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, motel.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /motels} : get all the motels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of motels in body.
     */
    @GetMapping("/motels")
    public List<Motel> getAllMotels() {
        log.debug("REST request to get all Motels");
        return motelService.findAll();
    }

    /**
     * {@code GET  /motels/:id} : get the "id" motel.
     *
     * @param id the id of the motel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the motel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/motels/{id}")
    public ResponseEntity<Motel> getMotel(@PathVariable Long id) {
        log.debug("REST request to get Motel : {}", id);
        Optional<Motel> motel = motelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(motel);
    }

    /**
     * {@code DELETE  /motels/:id} : delete the "id" motel.
     *
     * @param id the id of the motel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/motels/{id}")
    public ResponseEntity<Void> deleteMotel(@PathVariable Long id) {
        log.debug("REST request to delete Motel : {}", id);
        motelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
