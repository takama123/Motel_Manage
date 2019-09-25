package com.nguyenndd.hotel.manager.service.impl;

import com.nguyenndd.hotel.manager.service.MotelService;
import com.nguyenndd.hotel.manager.domain.Motel;
import com.nguyenndd.hotel.manager.repository.MotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Motel}.
 */
@Service
@Transactional
public class MotelServiceImpl implements MotelService {

    private final Logger log = LoggerFactory.getLogger(MotelServiceImpl.class);

    private final MotelRepository motelRepository;

    public MotelServiceImpl(MotelRepository motelRepository) {
        this.motelRepository = motelRepository;
    }

    @Override
    public Motel save(Motel motel) {
        log.debug("Request to save Motel : {}", motel);
        return motelRepository.save(motel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Motel> findAll() {
        log.debug("Request to get all Motels");
        return motelRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Motel> findOne(Long id) {
        log.debug("Request to get Motel : {}", id);
        return motelRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Motel : {}", id);
        motelRepository.deleteById(id);
    }
}
