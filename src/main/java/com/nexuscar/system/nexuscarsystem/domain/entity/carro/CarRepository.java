package com.nexuscar.system.nexuscarsystem.domain.entity.carro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CarRepository extends JpaRepository<CarEntity, Long> {

    Page<CarEntity> findByPrecoBetweenAndStatus(BigDecimal min, BigDecimal max, Status status, Pageable pageable);

    Page<CarEntity> findByQuilometragemBetweenAndStatus(int min, int max, Status status, Pageable pageable);

    Page<CarEntity> findByStatus(Status status, Pageable pageable);

    List<CarEntity> findByStatus(Status status);
}
