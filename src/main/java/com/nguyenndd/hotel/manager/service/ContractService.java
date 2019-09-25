package com.nguyenndd.hotel.manager.service;

import com.nguyenndd.hotel.manager.domain.Contract;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Contract}.
 */
public interface ContractService {

    /**
     * Save a contract.
     *
     * @param contract the entity to save.
     * @return the persisted entity.
     */
    Contract save(Contract contract);

    /**
     * Get all the contracts.
     *
     * @return the list of entities.
     */
    List<Contract> findAll();


    /**
     * Get the "id" contract.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Contract> findOne(Long id);

    /**
     * Delete the "id" contract.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
