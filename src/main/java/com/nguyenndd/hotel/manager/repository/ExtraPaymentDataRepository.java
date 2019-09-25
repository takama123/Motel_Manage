package com.nguyenndd.hotel.manager.repository;

import com.nguyenndd.hotel.manager.domain.ExtraPaymentData;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ExtraPaymentData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExtraPaymentDataRepository extends JpaRepository<ExtraPaymentData, Long> {
}
