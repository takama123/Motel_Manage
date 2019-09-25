package com.nguyenndd.hotel.manager.repository;

import com.nguyenndd.hotel.manager.domain.Biography;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Biography entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BiographyRepository extends JpaRepository<Biography, Long> {
}
