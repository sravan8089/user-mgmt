package com.lgs.usermgmt.controller;

import com.lgs.usermgmt.model.UserRegistrationRequest;
import com.lgs.usermgmt.model.UserRegistrationResponse;
import com.lgs.usermgmt.service.UserManagementService;
import com.lgs.usermgmt.utils.AppConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Validated
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @PostMapping
    public ResponseEntity<UserRegistrationResponse> registerUser(
            @RequestBody UserRegistrationRequest userRegistrationRequest) {
        UserRegistrationResponse userRegistrationResponse = userManagementService.registerUser(userRegistrationRequest);
        if (userRegistrationResponse.getStatus().equals(AppConstants.USER_REGISTRATION_SUCCESS_STATUS)) {
            return new ResponseEntity<>(userRegistrationResponse, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(userRegistrationResponse, HttpStatus.BAD_REQUEST);
        }

    }
}
