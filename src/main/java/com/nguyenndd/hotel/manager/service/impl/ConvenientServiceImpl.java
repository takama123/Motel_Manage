package com.nguyenndd.hotel.manager.service.impl;

import com.nguyenndd.hotel.manager.service.ConvenientService;
import com.nguyenndd.hotel.manager.domain.Convenient;
import com.nguyenndd.hotel.manager.repository.ConvenientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Convenient}.
 */
@Service
@Transactional
public class ConvenientServiceImpl implements ConvenientService {

    private final Logger log = LoggerFactory.getLogger(ConvenientServiceImpl.class);

    private final ConvenientRepository convenientRepository;

    public ConvenientServiceImpl(ConvenientRepository convenientRepository) {
        this.convenientRepository = convenientRepository;
    }

    @Override
    public Convenient save(Convenient convenient) {
        log.debug("Request to save Convenient : {}", convenient);
        return convenientRepository.save(convenient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Convenient> findAll() {
        log.debug("Request to get all Convenients");
        return convenientRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Convenient> findOne(Long id) {
        log.debug("Request to get Convenient : {}", id);
        return convenientRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Convenient : {}", id);
        convenientRepository.deleteById(id);
    }
}
