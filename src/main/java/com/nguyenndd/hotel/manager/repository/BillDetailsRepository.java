package com.nguyenndd.hotel.manager.repository;

import com.nguyenndd.hotel.manager.domain.BillDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BillDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillDetailsRepository extends JpaRepository<BillDetails, Long> {
}
