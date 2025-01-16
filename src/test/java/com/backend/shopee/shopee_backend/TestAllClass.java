package com.backend.shopee.shopee_backend;

import com.backend.shopee.shopee_backend.applicationTest.userServiceTest.*;
import com.backend.shopee.shopee_backend.applicationTest.utilTest.BCryptPasswordEncoderUtilTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({UserManagementServiceTest.class, UserAuthenticationServiceTest.class, AddressServiceTest.class,
        CuponServiceTest.class, ProductsOfferFlashServiceTest.class, PromotionServiceTest.class, PromotionUserServiceTest.class,
        ShopeeUpdateServiceTest.class, ShopeeUpdateUserServiceTest.class, BCryptPasswordEncoderUtilTest.class,
        CategoriesServiceTest.class})
public class TestAllClass {
}
