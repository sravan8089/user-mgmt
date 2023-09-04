package com.lgs.usermgmt.integration;

import com.lgs.usermgmt.model.IpApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class IpApiServiceImpl implements IpApiService{
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public IpApiResponse getIpLocationData(String ipAddress) {
        String ipApiUrl="http://ip-api.com/json/{ipAddress}";
        String url=ipApiUrl.replace("{ipAddress}",ipAddress);
        ResponseEntity<IpApiResponse> response = restTemplate.getForEntity(url, IpApiResponse.class);
        log.debug("IP API response:: {}",response.getBody());
        return response.getBody();
    }
}
