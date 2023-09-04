package com.lgs.usermgmt.controller;

import com.lgs.usermgmt.model.ErrorResponse;
import com.lgs.usermgmt.model.UserRegistrationRequest;
import com.lgs.usermgmt.model.UserRegistrationResponse;
import com.lgs.usermgmt.service.UserManagementService;
import com.lgs.usermgmt.utils.AppConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

    @Operation(summary = "register new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registration is successful", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserRegistrationResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Validation errors", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserRegistrationResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
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
