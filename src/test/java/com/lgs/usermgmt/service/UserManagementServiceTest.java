package com.lgs.usermgmt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lgs.usermgmt.entity.User;
import com.lgs.usermgmt.model.UserRegistrationRequest;
import com.lgs.usermgmt.model.UserRegistrationResponse;
import com.lgs.usermgmt.repository.UserRepository;
import com.lgs.usermgmt.service.validator.UserManagementServiceValidator;
import com.lgs.usermgmt.utils.AppConstants;

@ExtendWith(MockitoExtension.class)
public class UserManagementServiceTest {
    
    @InjectMocks
    private UserManagementServiceImpl userManagementServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserManagementServiceValidator userManagementServiceValidator;

    @Test
    public void testRegisterUserWhenValidationFailures(){
        UserRegistrationRequest userRegistrationRequest=getUserRegistrationRequest();
        userRegistrationRequest.setPassword(null);
        List<String> errorMessages=new ArrayList<String>();
        errorMessages.add("password cannot be null/empty");
        Mockito.when(userManagementServiceValidator.validateUserRegistrationRequest(userRegistrationRequest)).thenReturn(errorMessages);
        UserRegistrationResponse actualResponse = userManagementServiceImpl.registerUser(userRegistrationRequest);
        Assertions.assertEquals(AppConstants.USER_REGISTRATION_FAILURE_STATUS,actualResponse.getStatus());
    }

    @Test
    public void testRegisterUserWithValidData(){
        UserRegistrationRequest userRegistrationRequest=getUserRegistrationRequest();
        List<String> errorMessages=new ArrayList<String>();
        userRegistrationRequest.setCity("Montreal");
        Mockito.when(userManagementServiceValidator.validateUserRegistrationRequest(userRegistrationRequest)).thenReturn(errorMessages);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(getUser());
        UserRegistrationResponse actualResponse = userManagementServiceImpl.registerUser(userRegistrationRequest);
        Assertions.assertEquals(AppConstants.USER_REGISTRATION_SUCCESS_STATUS,actualResponse.getStatus());
        Assertions.assertNotNull(actualResponse.getUserId());
        Assertions.assertEquals("User registration is successful. Username: "+userRegistrationRequest.getUsername()+" and City: "+userRegistrationRequest.getCity(),actualResponse.getWelcomeMessage());
    }


    private UserRegistrationRequest getUserRegistrationRequest(){
        UserRegistrationRequest userRegistrationRequest=new UserRegistrationRequest();
        userRegistrationRequest.setUsername("test123");
        userRegistrationRequest.setPassword("Test_123");
        userRegistrationRequest.setIpAddress("24.48.0.1");
        return userRegistrationRequest;
    }

    private User getUser(){
        User user=new User();
        return user;
    }

}
