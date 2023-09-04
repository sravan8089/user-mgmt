package com.lgs.usermgmt.integration;

import com.lgs.usermgmt.model.IpApiResponse;

public interface IpApiService {

    public IpApiResponse getIpLocationData(String ipAddress);
}
