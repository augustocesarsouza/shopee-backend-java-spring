package com.backend.shopee.shopee_backend.application.services.interfaces;

import com.backend.shopee.shopee_backend.application.dto.ProductsOfferFlashDTO;
import com.backend.shopee.shopee_backend.application.dto.validations.ProductsOfferFlashValidator.ProductsOfferFlashDTOValidator;
import com.backend.shopee.shopee_backend.application.services.ResultService;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.UUID;

public interface IProductsOfferFlashService {
    ResultService<List<ProductsOfferFlashDTO>> GetAllProduct();
    ResultService<List<ProductsOfferFlashDTO>> GetAllByTagProduct(String hourFlashOffer, String tagProduct, int pageNumber, int pageSize);
    ResultService<ProductsOfferFlashDTO> CreateAsync(ProductsOfferFlashDTOValidator productsOfferFlashDTOValidator, BindingResult result);
    ResultService<ProductsOfferFlashDTO> Delete(String productsOfferFlashId);
}
