package com.nguyenndd.hotel.manager.service.impl;

import com.nguyenndd.hotel.manager.service.BiographyService;
import com.nguyenndd.hotel.manager.domain.Biography;
import com.nguyenndd.hotel.manager.repository.BiographyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Biography}.
 */
@Service
@Transactional
public class BiographyServiceImpl implements BiographyService {

    private final Logger log = LoggerFactory.getLogger(BiographyServiceImpl.class);

    private final BiographyRepository biographyRepository;

    public BiographyServiceImpl(BiographyRepository biographyRepository) {
        this.biographyRepository = biographyRepository;
    }

    @Override
    public Biography save(Biography biography) {
        log.debug("Request to save Biography : {}", biography);
        return biographyRepository.save(biography);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Biography> findAll() {
        log.debug("Request to get all Biographies");
        return biographyRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Biography> findOne(Long id) {
        log.debug("Request to get Biography : {}", id);
        return biographyRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Biography : {}", id);
        biographyRepository.deleteById(id);
    }
}
