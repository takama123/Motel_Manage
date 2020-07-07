package com.nguyenndd.hotel.manager.repository;

import com.nguyenndd.hotel.manager.domain.Services;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Services entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {
}
