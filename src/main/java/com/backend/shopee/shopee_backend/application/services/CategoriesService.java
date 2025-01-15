package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.CategoriesDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.CategoriesValidationDTOs.CategoriesCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.ICategoriesService;
import com.backend.shopee.shopee_backend.data.cloudinaryUtil.CloudinaryCreate;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.Categories;
import com.backend.shopee.shopee_backend.domain.repositories.ICategoriesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriesService implements ICategoriesService {
    private final ICategoriesRepository categoriesRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;
    private final ICloudinaryUti cloudinaryUti;

    @Autowired
    public CategoriesService(ICategoriesRepository categoriesRepository, IValidateErrorsDTO validateErrorsDTO,
                             ModelMapper modelMapper, ICloudinaryUti cloudinaryUti) {
        this.categoriesRepository = categoriesRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
        this.cloudinaryUti = cloudinaryUti;
    }

    @Override
    public ResultService<CategoriesDTO> GetCategoriesById(UUID categoriesId) {
        try {
            var categoriesDTO = categoriesRepository.GetCategoriesById(categoriesId);

            if(categoriesDTO == null)
                return ResultService.Fail("not found promotion");

            return ResultService.Ok(categoriesDTO);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<List<CategoriesDTO>> GetAllCategories() {
        try {
            var categoriesDTOs = categoriesRepository.GetAllCategories();

            if(categoriesDTOs == null)
                return ResultService.Fail("not found promotion");

            return ResultService.Ok(categoriesDTOs);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<CategoriesDTO> CreateAsync(CategoriesCreateDTOValidator categoriesCreateDTOValidator, BindingResult result) {
        if(categoriesCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            UUID categoryId = UUID.randomUUID();

            var resultCreate = new CloudinaryCreate();

            resultCreate = cloudinaryUti.CreateMedia(categoriesCreateDTOValidator.getImgCategory(), "category-all-java", 360, 360);

            if (resultCreate.getImgUrl() == null || resultCreate.getPublicId() == null)
                return ResultService.Fail("error when create Img Promotion");

            var categories = new Categories(categoryId, resultCreate.getImgUrl(),
                    categoriesCreateDTOValidator.getAltValue(), categoriesCreateDTOValidator.getTitle());

            var createCategories = categoriesRepository.create(categories);

            var categoriesDTOCreateMap = modelMapper.map(createCategories, CategoriesDTO.class);

            return ResultService.Ok(categoriesDTOCreateMap);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<CategoriesDTO> Delete(UUID categoriesId) {
        try {
            var categoriesDelete = categoriesRepository.GetCategoriesToDelete(categoriesId);

            if(categoriesDelete == null)
                return ResultService.Fail("Category not found");

            if(categoriesDelete.getImgCategory() != null){
                var deleteFound = cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(categoriesDelete.getImgCategory());

                if(!deleteFound.getDeleteSuccessfully())
                    return ResultService.Fail(deleteFound.getMessage());
            }

            var categoriesDeleteSuccessfully = categoriesRepository.delete(categoriesDelete.getId());

            return ResultService.Ok(modelMapper.map(categoriesDeleteSuccessfully, CategoriesDTO.class));

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
