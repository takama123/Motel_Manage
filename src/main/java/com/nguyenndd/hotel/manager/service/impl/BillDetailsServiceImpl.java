package com.nguyenndd.hotel.manager.service.impl;

import com.nguyenndd.hotel.manager.service.BillDetailsService;
import com.nguyenndd.hotel.manager.domain.BillDetails;
import com.nguyenndd.hotel.manager.repository.BillDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link BillDetails}.
 */
@Service
@Transactional
public class BillDetailsServiceImpl implements BillDetailsService {

    private final Logger log = LoggerFactory.getLogger(BillDetailsServiceImpl.class);

    private final BillDetailsRepository billDetailsRepository;

    public BillDetailsServiceImpl(BillDetailsRepository billDetailsRepository) {
        this.billDetailsRepository = billDetailsRepository;
    }

    @Override
    public BillDetails save(BillDetails billDetails) {
        log.debug("Request to save BillDetails : {}", billDetails);
        return billDetailsRepository.save(billDetails);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BillDetails> findAll() {
        log.debug("Request to get all BillDetails");
        return billDetailsRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<BillDetails> findOne(Long id) {
        log.debug("Request to get BillDetails : {}", id);
        return billDetailsRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BillDetails : {}", id);
        billDetailsRepository.deleteById(id);
    }
}
