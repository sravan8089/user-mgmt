package com.lgs.usermgmt.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegistrationResponse {
    private String userId;
    private String status;
    private String welcomeMessage;
    private List<String> errorMessages;
}
