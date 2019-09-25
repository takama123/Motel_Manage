package com.nguyenndd.hotel.manager.service.impl;

import com.nguyenndd.hotel.manager.service.ExtraPaymentDataService;
import com.nguyenndd.hotel.manager.domain.ExtraPaymentData;
import com.nguyenndd.hotel.manager.repository.ExtraPaymentDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ExtraPaymentData}.
 */
@Service
@Transactional
public class ExtraPaymentDataServiceImpl implements ExtraPaymentDataService {

    private final Logger log = LoggerFactory.getLogger(ExtraPaymentDataServiceImpl.class);

    private final ExtraPaymentDataRepository extraPaymentDataRepository;

    public ExtraPaymentDataServiceImpl(ExtraPaymentDataRepository extraPaymentDataRepository) {
        this.extraPaymentDataRepository = extraPaymentDataRepository;
    }

    @Override
    public ExtraPaymentData save(ExtraPaymentData extraPaymentData) {
        log.debug("Request to save ExtraPaymentData : {}", extraPaymentData);
        return extraPaymentDataRepository.save(extraPaymentData);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExtraPaymentData> findAll() {
        log.debug("Request to get all ExtraPaymentData");
        return extraPaymentDataRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ExtraPaymentData> findOne(Long id) {
        log.debug("Request to get ExtraPaymentData : {}", id);
        return extraPaymentDataRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ExtraPaymentData : {}", id);
        extraPaymentDataRepository.deleteById(id);
    }
}
