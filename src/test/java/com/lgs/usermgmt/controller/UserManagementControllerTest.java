package com.lgs.usermgmt.controller;

import com.lgs.usermgmt.model.UserRegistrationRequest;
import com.lgs.usermgmt.model.UserRegistrationResponse;
import com.lgs.usermgmt.service.UserManagementService;
import com.lgs.usermgmt.utils.AppConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class UserManagementControllerTest {

    @InjectMocks
    private UserManagementController userManagementController;

    @Mock
    private UserManagementService userManagementService;

    @Mock
    private UserRegistrationRequest userRegistrationRequest;


    @Test
    public void testRegisterUserWhenRegistrationIsSuccessful(){
        UserRegistrationResponse userRegistrationResponse=new UserRegistrationResponse();
        userRegistrationResponse.setStatus(AppConstants.USER_REGISTRATION_SUCCESS_STATUS);
        Mockito.when(userManagementService.registerUser(userRegistrationRequest)).thenReturn(userRegistrationResponse);
        ResponseEntity<UserRegistrationResponse> responseEntity = userManagementController.registerUser(userRegistrationRequest);
        Assertions.assertEquals(HttpStatus.CREATED.value(),responseEntity.getStatusCode().value());
    }

   @Test
    public void testRegisterUserWhenRegistrationIsFailed(){
        UserRegistrationResponse userRegistrationResponse=new UserRegistrationResponse();
        userRegistrationResponse.setStatus(AppConstants.USER_REGISTRATION_FAILURE_STATUS);
        Mockito.when(userManagementService.registerUser(userRegistrationRequest)).thenReturn(userRegistrationResponse);
        ResponseEntity<UserRegistrationResponse> responseEntity = userManagementController.registerUser(userRegistrationRequest);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),responseEntity.getStatusCode().value());
    }
}
