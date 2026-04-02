package com.nexuscar.system.nexuscarsystem.domain.service;

import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.CarRegisterDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar.CarFilterDTO;
import com.nexuscar.system.nexuscarsystem.domain.entity.carro.CarEntity;
import com.nexuscar.system.nexuscarsystem.domain.entity.carro.CarRepository;
import com.nexuscar.system.nexuscarsystem.domain.entity.carro.Status;
import com.nexuscar.system.nexuscarsystem.mapper.InventoryMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private CarRepository repository;

    @Autowired
    private InventoryMapper mapper;

    public CarEntity findCar(Long carId){
        return repository.findById(carId).orElseThrow(() -> new EntityNotFoundException("Carro com o id " + carId + " não encontrado."));
    }

    @Transactional
    public void register(CarRegisterDTO dto){

        CarEntity car = mapper.registerToEntity(dto);

        repository.save(car);
    }

    @Transactional
    public void reserve(Long carId){

        CarEntity car = findCar(carId);

        if(car.getStatus() == Status.DISPONIVEL) {
            LocalDateTime today = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
            car.setInicioReserva(today);
            car.setStatus(Status.RESERVADO);
            repository.save(car);
        } else {
            throw new RuntimeException("Erro na reserva do veículo.");
        }
    }

    @Transactional
    public void sell(Long carId){

        CarEntity car = findCar(carId);

        if(car.getStatus() == Status.RESERVADO) {
            car.setInicioReserva(null);
            car.setStatus(Status.VENDIDO);
            repository.save(car);
        } else {
            throw new RuntimeException("Erro na venda do veículo");
        }
    }

    public Page<CarFilterDTO> filterByPrice(BigDecimal min, BigDecimal max, Pageable pageable){

        Page<CarEntity> filteredCars = repository.findByPrecoBetweenAndStatus(min, max, Status.DISPONIVEL, pageable);

        return filteredCars.map(mapper::filterToDTO);
    }

    public Page<CarFilterDTO> filterByMileage(int min, int max, Pageable pageable){

        Page<CarEntity> filteredCars = repository.findByQuilometragemBetweenAndStatus(min, max, Status.DISPONIVEL, pageable);

        return filteredCars.map(mapper::filterToDTO);
    }

    public Page<CarFilterDTO> filterByAvailable(Pageable pageable){

        Page<CarEntity> availables = repository.findByStatus(Status.DISPONIVEL, pageable);

        return availables.map(mapper::filterToDTO);
    }

    @Scheduled(fixedRate = 900000)
    @Transactional
    public void reservationCleaningRoutine(){

        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        List<CarEntity> reserved = repository.findByStatus(Status.RESERVADO);

        for (CarEntity car : reserved) {
            if (car.getInicioReserva() != null && car.getInicioReserva().plusHours(24).isBefore(now)){
                car.setStatus(Status.DISPONIVEL);
                car.setInicioReserva(null);
            }
        }
    }

}
