package com.ironman.pharmasales.application.service;

import com.ironman.pharmasales.application.dto.user.AuthDto;
import com.ironman.pharmasales.application.dto.user.UserCreateDto;
import com.ironman.pharmasales.application.dto.user.UserDto;
import com.ironman.pharmasales.application.dto.user.UserSecurityDto;
import com.ironman.pharmasales.persistence.entity.UserEntity;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;

public interface UserService {

    UserDto create(UserCreateDto userCreateDto) throws DataNotFoundException;

    UserSecurityDto login(AuthDto authDto) throws DataNotFoundException;
}
