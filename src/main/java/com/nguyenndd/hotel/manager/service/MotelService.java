package com.nguyenndd.hotel.manager.service;

import com.nguyenndd.hotel.manager.domain.Motel;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Motel}.
 */
public interface MotelService {

    /**
     * Save a motel.
     *
     * @param motel the entity to save.
     * @return the persisted entity.
     */
    Motel save(Motel motel);

    /**
     * Get all the motels.
     *
     * @return the list of entities.
     */
    List<Motel> findAll();


    /**
     * Get the "id" motel.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Motel> findOne(Long id);

    /**
     * Delete the "id" motel.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
