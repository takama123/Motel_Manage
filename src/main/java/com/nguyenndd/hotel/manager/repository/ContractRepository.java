package com.nguyenndd.hotel.manager.repository;

import com.nguyenndd.hotel.manager.domain.Contract;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Contract entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
}
