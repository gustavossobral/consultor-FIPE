package com.nexuscar.system.nexuscarsystem.mapper;

import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.CarRegisterDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar.CarFilterDTO;
import com.nexuscar.system.nexuscarsystem.domain.entity.carro.CarEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    CarEntity registerToEntity (CarRegisterDTO dto);

    CarFilterDTO filterToDTO (CarEntity car);

}
