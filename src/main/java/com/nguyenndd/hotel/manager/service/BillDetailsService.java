package com.nguyenndd.hotel.manager.service;

import com.nguyenndd.hotel.manager.domain.BillDetails;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link BillDetails}.
 */
public interface BillDetailsService {

    /**
     * Save a billDetails.
     *
     * @param billDetails the entity to save.
     * @return the persisted entity.
     */
    BillDetails save(BillDetails billDetails);

    /**
     * Get all the billDetails.
     *
     * @return the list of entities.
     */
    List<BillDetails> findAll();


    /**
     * Get the "id" billDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BillDetails> findOne(Long id);

    /**
     * Delete the "id" billDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
