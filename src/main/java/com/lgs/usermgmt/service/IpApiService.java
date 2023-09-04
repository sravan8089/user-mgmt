package com.lgs.usermgmt.service;

import com.lgs.usermgmt.model.IpApiResponse;

public interface IpApiService {

    public IpApiResponse getIpLocationData(String ipAddress);
}
