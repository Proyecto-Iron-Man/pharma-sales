package com.ironman.pharmasales.application.dto.user;

import com.ironman.pharmasales.shared.security.entity.SecurityEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserSecurityDto extends UserDto {
    SecurityEntity security;
}
