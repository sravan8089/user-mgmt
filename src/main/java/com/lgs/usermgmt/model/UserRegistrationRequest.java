package com.lgs.usermgmt.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
public class UserRegistrationRequest {

    @Schema(description = "username of the user",maxLength = 10,minLength = 1,requiredMode = RequiredMode.REQUIRED)
    private String username;
    @Schema(description = "password. password should match below criteria.\nAtleast one upper case letter.\nAtleast one lower case letter. \nAtleast one digit. \n Atleast one special charater(_ # $ % .). \n Length of password should be 8 characters",maxLength = 10,minLength = 1,requiredMode = RequiredMode.REQUIRED)
    private String password;
    @Schema(description = "IP address of client. The request should come from canada only",maxLength = 15,requiredMode = RequiredMode.REQUIRED)
    private String ipAddress;
    @JsonIgnore
    private String city;
}
