package com.backend.shopee.shopee_backend;

import com.backend.shopee.shopee_backend.applicationTest.userServiceTest.*;
import com.backend.shopee.shopee_backend.applicationTest.utilTest.BCryptPasswordEncoderUtilTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({AddressServiceTest.class, CategoriesServiceTest.class, CuponServiceTest.class,
        FlashSaleProductAllInfoServiceTest.class, ProductDiscoveriesOfDayServiceTest.class, ProductHighlightServiceTest.class,
        ProductOfferFlashTypeServiceTest.class, ProductsOfferFlashServiceTest.class, PromotionServiceTest.class, PromotionUserServiceTest.class,
        ShopeeUpdateServiceTest.class, ShopeeUpdateUserServiceTest.class, UserManagementServiceTest.class, UserAuthenticationServiceTest.class,
        BCryptPasswordEncoderUtilTest.class, UserSellerProductServiceTest.class, ProductOfferFlashSellerServiceTest.class,
        ProductOfferFlashDetailsServiceTest.class})
public class TestAllClass {
}
