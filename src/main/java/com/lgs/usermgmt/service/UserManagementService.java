package com.lgs.usermgmt.service;

import com.lgs.usermgmt.model.UserRegistrationRequest;
import com.lgs.usermgmt.model.UserRegistrationResponse;

public interface UserManagementService {

    public UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest);
}
