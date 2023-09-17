package com.ironman.pharmasales.shared.security.entity;

import lombok.Data;
import java.util.Date;

@Data
public class SecurityEntity {
    private String tokenType;
    private String accessToken;
    private Date expiresOn;
}
