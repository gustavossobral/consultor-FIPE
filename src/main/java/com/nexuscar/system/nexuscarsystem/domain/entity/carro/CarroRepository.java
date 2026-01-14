package com.nexuscar.system.nexuscarsystem.domain.entity.carro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CarroRepository extends JpaRepository<CarroEntity, Long> {

    List<CarroEntity> findByPrecoBetweenAndStatus(BigDecimal min, BigDecimal max, Status status);

    List<CarroEntity> findByQuilometragemBetweenAndStatus(int min, int max, Status status);

    List<CarroEntity> findByStatus(Status status);
}
