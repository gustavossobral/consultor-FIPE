package com.nexuscar.system.nexuscarsystem.domain.entity.carro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CarroRepository extends JpaRepository<CarroEntity, Long> {

    List<CarroEntity> findByPrecoBetween(BigDecimal min, BigDecimal max);

    List<CarroEntity> findByQuilometragemBetween(int min, int max);
}
