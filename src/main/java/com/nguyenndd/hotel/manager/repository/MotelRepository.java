package com.nguyenndd.hotel.manager.repository;

import com.nguyenndd.hotel.manager.domain.Motel;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Motel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MotelRepository extends JpaRepository<Motel, Long> {
}
