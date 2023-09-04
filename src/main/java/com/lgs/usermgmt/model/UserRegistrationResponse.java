package com.lgs.usermgmt.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegistrationResponse {
    @Schema(description = "UserId in UUID format")
    private String userId;
    @Schema(description = "this field value will be SUCCESS or FAILURE. SUCCESS if the user is registered successfully. FAILURE incase of any failures.")
    private String status;
    @Schema(description = "This field contains welcome message if user registration is successful")
    private String welcomeMessage;
    @Schema(description = "This field contains array of error messages if user registration is failed")
    private List<String> errorMessages;
}
