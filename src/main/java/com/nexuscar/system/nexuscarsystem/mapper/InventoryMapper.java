package com.nexuscar.system.nexuscarsystem.mapper;

import com.nexuscar.system.nexuscarsystem.dto.car.CarRegisterDTO;
import com.nexuscar.system.nexuscarsystem.dto.car.CarFilterDTO;
import com.nexuscar.system.nexuscarsystem.model.CarEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    CarEntity registerToEntity (CarRegisterDTO dto);

    CarFilterDTO filterToDTO (CarEntity car);

}
