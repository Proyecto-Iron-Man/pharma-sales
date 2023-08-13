package com.ironman.pharmasales.application.dto.client.mapper;

import com.ironman.pharmasales.application.dto.client.ClientMediumDto;
import com.ironman.pharmasales.application.dto.documenttype.mapper.DocumentTypeMapper;
import com.ironman.pharmasales.persistence.entity.Client;
import com.ironman.pharmasales.shared.state.mapper.StateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {StateMapper.class, DocumentTypeMapper.class}
)
public interface ClientMapper {

    // Dto from Entity Start
    @Mapping(target = "id", source = "id")
    @Mapping(target = "fullName", source = ".", qualifiedByName = "getFullName")
    @Mapping(target = "documentType", source = "documentType")
    @Mapping(target = "documentNumber", source = "documentNumber")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "address", source = "address")
    ClientMediumDto toClientMediumDto(Client client);

    @Named("getFullName")
    default String getFullName(Client client) {
        String fullName = client.getName() + " " + client.getLastName();

        return fullName.strip();
    }

    List<ClientMediumDto> toClientMediumDtos(List<Client> clients);
    // Dto from Entity End
}
