package com.backend.shopee.shopee_backend;

import com.backend.shopee.shopee_backend.applicationTest.userServiceTest.UserAuthenticationServiceTest;
import com.backend.shopee.shopee_backend.applicationTest.userServiceTest.UserManagementServiceTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({UserManagementServiceTest.class, UserAuthenticationServiceTest.class})
public class TestAllClass {
}
