package com.lgs.usermgmt.model;

import lombok.Data;

@Data
public class IpApiResponse {
    private String status;
    private String message;
    private String country;
    private String city;
}
