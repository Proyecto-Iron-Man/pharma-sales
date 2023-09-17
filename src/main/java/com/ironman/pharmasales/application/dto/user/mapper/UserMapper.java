package com.ironman.pharmasales.application.dto.user.mapper;

import com.ironman.pharmasales.application.dto.user.UserCreateDto;
import com.ironman.pharmasales.application.dto.user.UserDto;
import com.ironman.pharmasales.application.dto.user.UserSecurityDto;
import com.ironman.pharmasales.persistence.entity.UserEntity;
import com.ironman.pharmasales.shared.state.mapper.StateMapper;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {StateMapper.class}
)
public interface UserMapper {

    // Dto from Entity Start
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "profileId", source = "profileId")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    UserDto toUserDto(UserEntity userEntity);

    @InheritConfiguration
    @Mapping(target = "security", ignore = true)
    UserSecurityDto toUserSecurityDto(UserEntity userEntity);
    // Dto from Entity End

    // Entity from Dto Start
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "profileId", source = "profileId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity toUserEntity(UserCreateDto userCreateDto);
    // Entity from Dto End
}
