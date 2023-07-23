package com.ironman.pharmasales.shared.state.mapper;

import com.ironman.pharmasales.shared.state.enums.State;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StateMapper {
    @ValueMapping(source = "A", target = "ACTIVE")
    @ValueMapping(source = "E", target = "DISABLE")
    State toState(String state);
}
