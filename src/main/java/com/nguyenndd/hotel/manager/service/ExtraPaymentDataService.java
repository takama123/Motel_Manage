package com.nguyenndd.hotel.manager.service;

import com.nguyenndd.hotel.manager.domain.ExtraPaymentData;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ExtraPaymentData}.
 */
public interface ExtraPaymentDataService {

    /**
     * Save a extraPaymentData.
     *
     * @param extraPaymentData the entity to save.
     * @return the persisted entity.
     */
    ExtraPaymentData save(ExtraPaymentData extraPaymentData);

    /**
     * Get all the extraPaymentData.
     *
     * @return the list of entities.
     */
    List<ExtraPaymentData> findAll();


    /**
     * Get the "id" extraPaymentData.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExtraPaymentData> findOne(Long id);

    /**
     * Delete the "id" extraPaymentData.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
