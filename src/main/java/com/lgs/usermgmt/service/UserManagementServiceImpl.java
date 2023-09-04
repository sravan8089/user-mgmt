package com.lgs.usermgmt.service;

import com.lgs.usermgmt.entity.User;
import com.lgs.usermgmt.model.UserRegistrationRequest;
import com.lgs.usermgmt.model.UserRegistrationResponse;
import com.lgs.usermgmt.repository.UserRepository;
import com.lgs.usermgmt.service.validator.UserManagementServiceValidator;
import com.lgs.usermgmt.utils.AppConstants;
import com.lgs.usermgmt.utils.EncryptionDecryptionUtility;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManagementServiceValidator userManagementServiceValidator;

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) {
        List<String> errorMessages = userManagementServiceValidator
                .validateUserRegistrationRequest(userRegistrationRequest);
        if (errorMessages.isEmpty()) {
            log.info("UserRegistrationRequest validation successful. Proceeding with registration.");
            User user = User.builder().userId(UUID.randomUUID().toString())
                    .username(userRegistrationRequest.getUsername())
                    .password(EncryptionDecryptionUtility.encrypt(userRegistrationRequest.getPassword()))
                    .ipAddress(userRegistrationRequest.getIpAddress()).city(userRegistrationRequest.getCity()).build();
            userRepository.save(user);
            UserRegistrationResponse userRegistrationResponse = UserRegistrationResponse.builder()
                    .status(AppConstants.USER_REGISTRATION_SUCCESS_STATUS).userId(user.getUserId())
                    .welcomeMessage(this.generateWelcomeMessage(user.getUsername(), user.getCity())).build();
            return userRegistrationResponse;

        } else {
            log.info("UserRegistrationRequest is not valid. Returning failure response.");
            UserRegistrationResponse userRegistrationResponse = UserRegistrationResponse.builder()
                    .status(AppConstants.USER_REGISTRATION_FAILURE_STATUS).errorMessages(errorMessages).build();
            return userRegistrationResponse;
        }
    }

    private String generateWelcomeMessage(String username, String city) {
        StringBuilder sb = new StringBuilder();
        sb.append("User registration is successful.");
        sb.append(" Username: ");
        sb.append(username);
        sb.append(" and City: ");
        sb.append(city);
        return sb.toString();
    }
}
