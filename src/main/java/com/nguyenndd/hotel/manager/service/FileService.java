package com.nguyenndd.hotel.manager.service;

import com.nguyenndd.hotel.manager.domain.File;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link File}.
 */
public interface FileService {

    /**
     * Save a file.
     *
     * @param file the entity to save.
     * @return the persisted entity.
     */
    File save(File file);

    /**
     * Get all the files.
     *
     * @return the list of entities.
     */
    List<File> findAll();


    /**
     * Get the "id" file.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<File> findOne(Long id);

    /**
     * Delete the "id" file.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
