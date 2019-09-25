package com.nguyenndd.hotel.manager.service;

import com.nguyenndd.hotel.manager.domain.Biography;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Biography}.
 */
public interface BiographyService {

    /**
     * Save a biography.
     *
     * @param biography the entity to save.
     * @return the persisted entity.
     */
    Biography save(Biography biography);

    /**
     * Get all the biographies.
     *
     * @return the list of entities.
     */
    List<Biography> findAll();


    /**
     * Get the "id" biography.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Biography> findOne(Long id);

    /**
     * Delete the "id" biography.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
