package com.ironman.pharmasales.application.dto.client.mapper;

import com.ironman.pharmasales.application.dto.client.ClientDto;
import com.ironman.pharmasales.application.dto.client.ClientFilterDto;
import com.ironman.pharmasales.application.dto.client.ClientMediumDto;
import com.ironman.pharmasales.application.dto.client.ClientSaveDto;
import com.ironman.pharmasales.application.dto.documenttype.mapper.DocumentTypeMapper;
import com.ironman.pharmasales.persistence.entity.Client;
import com.ironman.pharmasales.shared.state.mapper.StateMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {StateMapper.class, DocumentTypeMapper.class}
)
public interface ClientMapper {

    // Dto from Entity Start
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "documentType", source = "documentType")
    @Mapping(target = "documentNumber", source = "documentNumber")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    ClientDto toClientDto(Client client);

    List<ClientDto> toClientDtos(List<Client> clients);

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

    // Entity from Dto Start
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "documentTypeId", source = "documentTypeId")
    @Mapping(target = "documentNumber", source = "documentNumber")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Client toClient(ClientSaveDto clientSaveDto);

    @InheritConfiguration
    void updateClient(@MappingTarget Client client, ClientSaveDto clientSaveDto);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "documentTypeId", source = "documentTypeId")
    @Mapping(target = "documentNumber", source = "documentNumber")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Client toClient(ClientFilterDto clientFilterDto);
    // Entity from Dto End
}
