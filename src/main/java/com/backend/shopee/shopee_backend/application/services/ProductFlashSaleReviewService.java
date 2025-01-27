package com.backend.shopee.shopee_backend.application.services;

import com.backend.shopee.shopee_backend.application.dto.ProductFlashSaleReviewDTO;
import com.backend.shopee.shopee_backend.application.dto.validateErrosDTOs.IValidateErrorsDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductFlashSaleReviewValidationDTOs.ProductFlashSaleReviewCreateDTOValidator;
import com.backend.shopee.shopee_backend.application.services.interfaces.IProductFlashSaleReviewService;
import com.backend.shopee.shopee_backend.data.utilityExternal.Interface.ICloudinaryUti;
import com.backend.shopee.shopee_backend.domain.entities.ProductFlashSaleReview;
import com.backend.shopee.shopee_backend.domain.repositories.IProductFlashSaleReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class ProductFlashSaleReviewService implements IProductFlashSaleReviewService {
    private final IProductFlashSaleReviewRepository productFlashSaleReviewRepository;
    private final IValidateErrorsDTO validateErrorsDTO;
    private final ModelMapper modelMapper;
    private final ICloudinaryUti cloudinaryUti;

    @Autowired
    public ProductFlashSaleReviewService(IProductFlashSaleReviewRepository productFlashSaleReviewRepository, IValidateErrorsDTO validateErrorsDTO,
                                         ModelMapper modelMapper, ICloudinaryUti cloudinaryUti) {
        this.productFlashSaleReviewRepository = productFlashSaleReviewRepository;
        this.validateErrorsDTO = validateErrorsDTO;
        this.modelMapper = modelMapper;
        this.cloudinaryUti = cloudinaryUti;
    }

    @Override
    public ResultService<List<ProductFlashSaleReviewDTO>> GetAllProductFlashSaleReviewsByProductsOfferFlashId(UUID productsOfferFlashId) {
        try {
            var entityDTO = productFlashSaleReviewRepository.GetAllProductFlashSaleReviewsByProductsOfferFlashId(productsOfferFlashId);

            if(entityDTO == null)
                return ResultService.Fail("not found ProductFlashSaleReview");

//            List<ProductFlashSaleReviewDTO> elements = new ArrayList<>();

            entityDTO.forEach((el) -> {
                el.setCreationDate(el.convertCreationDateDateFromUtcToLocal());
            });

//            entityDTO.setLastLogin(userSellerProductDTO.convertLastLoginFromUtcToLocal());
            return ResultService.Ok(entityDTO);
        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductFlashSaleReviewDTO> CreateAsync(ProductFlashSaleReviewCreateDTOValidator productFlashSaleReviewCreateDTOValidator,
                                                                BindingResult result) {
        if(productFlashSaleReviewCreateDTOValidator == null)
            return ResultService.Fail("error DTO Is Null");

        if(result.hasErrors()){
            var errorsDTO = result.getAllErrors();
            var errors = validateErrorsDTO.ValidateDTO(errorsDTO);

            return ResultService.RequestError("error validate DTO", errors);
        }

        try {
            List<String> listElementImgVideo = productFlashSaleReviewCreateDTOValidator.getImgAndVideoReviewsProduct();
            List<String> listImgVideo = new ArrayList<>();

            if(listElementImgVideo != null && !listElementImgVideo.isEmpty()){
                for (String el : listElementImgVideo) {
                    boolean isImage = el.startsWith("data:image");
                    boolean isVideo = el.startsWith("data:video");

                    if(isImage){
//                        String base64String = el.substring(el.indexOf(",") + 1);
                        String base64String = el.split(",")[1];

                        int width = 10;
                        int height = 10;

                        try {
                            byte[] imageBytes = Base64.getDecoder().decode(base64String);

                            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
                            BufferedImage image = ImageIO.read(inputStream);

                            if (image != null) {
                                width = image.getWidth();
                                height = image.getHeight();
                            } else {
                                return ResultService.Fail("Unable to read image data.");
                            }
                        } catch (IOException e) {
                            return ResultService.Fail("Error processing image. " + e.getMessage());
                        }

                        var resultCreate = cloudinaryUti.CreateMedia(el,"reviews-product-flash-sale-img-and-video-java", width, height);

                        if (resultCreate.getImgUrl() == null || resultCreate.getPublicId() == null)
                            return ResultService.Fail("error when create Img ProductFlashSaleReview");

                        listImgVideo.add(resultCreate.getImgUrl());
                    }else if (isVideo){
                        var resultCreate = cloudinaryUti.CreateMedia(el, "reviews-product-flash-sale-img-and-video-java", 517, 919);

                        if (resultCreate.getImgUrl() == null || resultCreate.getPublicId() == null) {
                            return ResultService.Fail("Error when creating video.");
                        }

                        listImgVideo.add(resultCreate.getImgUrl());
                    }
                };
            }

            UUID entityId = UUID.randomUUID();
            LocalDateTime DateNow = LocalDateTime.now();

            ZonedDateTime DateNowUtc = DateNow.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
            // talvez tenha que conver de "ZonedDateTime" para "LocalDateTime"

            //ZonedDateTime birthDateUtc = birthDate.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));

            var entityProduct = new ProductFlashSaleReview(entityId, productFlashSaleReviewCreateDTOValidator.getMessage(),
                    DateNowUtc, productFlashSaleReviewCreateDTOValidator.getCostBenefit(), productFlashSaleReviewCreateDTOValidator.getSimilarToAd(),
                    productFlashSaleReviewCreateDTOValidator.getStarQuantity(), UUID.fromString(productFlashSaleReviewCreateDTOValidator.getProductsOfferFlashId()),
                    UUID.fromString(productFlashSaleReviewCreateDTOValidator.getUserId()), null, listImgVideo,
                    productFlashSaleReviewCreateDTOValidator.getVariation());
//            ProductFlashSaleReview(UUID id, String message, ZonedDateTime creationDate, String costBenefit,
//                    String similarToAd, Integer starQuantity, UUID productsOfferFlashId, UUID userId,
//                    User user, List<String> imgAndVideoReviewsProduct, String variation

            var entityCreatedProduct = productFlashSaleReviewRepository.create(entityProduct);

            var entityDTOCreateMap = modelMapper.map(entityCreatedProduct, ProductFlashSaleReviewDTO.class);

            return ResultService.Ok(entityDTOCreateMap);

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }

    @Override
    public ResultService<ProductFlashSaleReviewDTO> Delete(UUID productFlashSaleReviewId) {
        try {
            var entityDelete = productFlashSaleReviewRepository.GetByProductFlashSaleReviewIdToDelete(productFlashSaleReviewId);

            if(entityDelete == null)
                return ResultService.Fail("ProductFlashSaleReviewDTO not found");

            var listImg = entityDelete.getImgAndVideoReviewsProduct();

            if(listImg != null && !listImg.isEmpty()){
                for (String el : listImg) {
                    var deleteFound = cloudinaryUti.DeleteFileCloudinaryExtractingPublicIdFromUrlList(el);

                    if(!deleteFound.getDeleteSuccessfully())
                        return ResultService.Fail(deleteFound.getMessage());
                }
            }

            var entityDeleteSuccessfully = productFlashSaleReviewRepository.delete(entityDelete.getId());

            return ResultService.Ok(modelMapper.map(entityDeleteSuccessfully, ProductFlashSaleReviewDTO.class));

        }catch (Exception ex){
            return ResultService.Fail(ex.getMessage());
        }
    }
}
