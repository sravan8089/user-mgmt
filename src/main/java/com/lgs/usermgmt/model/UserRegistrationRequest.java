package com.lgs.usermgmt.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserRegistrationRequest {

    private String username;
    private String password;
    private String ipAddress;
    @JsonIgnore
    private String city;
}
