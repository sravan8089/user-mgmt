package com.lgs.usermgmt.service.validator;

import com.lgs.usermgmt.integration.IpApiService;
import com.lgs.usermgmt.model.IpApiResponse;
import com.lgs.usermgmt.model.UserRegistrationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserManagementServiceValidator {

    @Autowired
    private IpApiService ipApiService;

    public List<String> validateUserRegistrationRequest(UserRegistrationRequest userRegistrationRequest) {
        List<String> errorMessages = new ArrayList<>();
        //username validation
        if (!StringUtils.hasText(userRegistrationRequest.getUsername())) {
            log.error("username is null/empty");
            errorMessages.add("username cannot be null");
        }else if (userRegistrationRequest.getUsername().length()>10){
            log.error("username length is greater than 10 characters");
            errorMessages.add("username length should be less than 10");
        }
        //password validation
        if (!StringUtils.hasText(userRegistrationRequest.getPassword())) {
            log.error("password is null/empty");
            errorMessages.add("password cannot be null");
        } else if (!isValidPassword(userRegistrationRequest.getPassword())) {
            log.error("password not matching the criteria");
            errorMessages.add("password not matching the criteria");
        } else if (userRegistrationRequest.getPassword().length()>25){
            log.error("password length is greater than 25 characters");
            errorMessages.add("password length should be less than 25");
        }

        //IP address validation
        if (!StringUtils.hasText(userRegistrationRequest.getIpAddress())) {
            log.error("ipAddress is null/empty");
            errorMessages.add("ipAddress cannot be null");
        } else if (errorMessages.isEmpty()) {// tracing ip address is all the validations are successful
            IpApiResponse ipApiResponse = ipApiService.getIpLocationData(userRegistrationRequest.getIpAddress());
            if (ipApiResponse.getCountry().equals("Canada")) {
                log.info("valid IP address");
                // setting city field to use it for welcome message
                userRegistrationRequest.setCity(ipApiResponse.getCity());
            } else {
                log.error("IP address is not from Canada");
                errorMessages.add("IP address is not from Canada");
            }
        }
        return errorMessages;
    }

    private Boolean isValidPassword(String password) {
        String passwordRegexPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[_#$%.])[A-Za-z\\d_#$%.]{8,}$";
        if (password.matches(passwordRegexPattern))
            return true;
        return false;
    }

    private Boolean isValidIpAddress(String ipAddress) {
        IpApiResponse ipApiResponse = ipApiService.getIpLocationData(ipAddress);
        if (!ipApiResponse.getCountry().equals("CANADA")) {
            log.error("request is not from Canada");
            return false;
        }
        return true;
    }

}
