package com.nguyenndd.hotel.manager.service;

import com.nguyenndd.hotel.manager.domain.Convenient;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Convenient}.
 */
public interface ConvenientService {

    /**
     * Save a convenient.
     *
     * @param convenient the entity to save.
     * @return the persisted entity.
     */
    Convenient save(Convenient convenient);

    /**
     * Get all the convenients.
     *
     * @return the list of entities.
     */
    List<Convenient> findAll();


    /**
     * Get the "id" convenient.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Convenient> findOne(Long id);

    /**
     * Delete the "id" convenient.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
