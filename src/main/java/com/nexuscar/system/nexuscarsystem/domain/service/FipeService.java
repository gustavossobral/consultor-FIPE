package com.nexuscar.system.nexuscarsystem.domain.service;

import com.nexuscar.system.nexuscarsystem.domain.DTO.fipe.*;
import com.nexuscar.system.nexuscarsystem.domain.client.FipeOnlineClient;
import com.nexuscar.system.nexuscarsystem.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FipeService {

    @Autowired
    private FipeOnlineClient consultFipe;

    public Page<ConsultBrandsDTO> searchBrands(Pageable pageable){

        List<ConsultBrandsDTO> brands = consultFipe.getBrandsCode();

        return PaginationUtils.paginateList(brands, pageable);
    }

    public Page<ModelsPerBrandDTO> searchModelsPerBrand(String brandId,Pageable pageable){

        List<ModelsPerBrandDTO> allModels = consultFipe.getModelsPerBrand(brandId);

        return PaginationUtils.paginateList(allModels, pageable);
    }

    public Page<YearsPerModelDTO> getYearsByModel(String brandId, String modelId, Pageable pageable){

        List<YearsPerModelDTO> years = consultFipe.getYearsPerModel(brandId,modelId);

        return PaginationUtils.paginateList(years, pageable);
    }

    public DetailModelDTO detailModel(String brandId, String modelId, String yearId){

        DetailModelDTO model = consultFipe.detailModel(brandId, modelId, yearId) ;

        return model;
    }
}

