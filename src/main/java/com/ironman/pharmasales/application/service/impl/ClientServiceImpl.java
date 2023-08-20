package com.ironman.pharmasales.application.service.impl;

import com.ironman.pharmasales.application.dto.client.ClientDto;
import com.ironman.pharmasales.application.dto.client.ClientFilterDto;
import com.ironman.pharmasales.application.dto.client.ClientMediumDto;
import com.ironman.pharmasales.application.dto.client.ClientSaveDto;
import com.ironman.pharmasales.application.dto.client.mapper.ClientMapper;
import com.ironman.pharmasales.application.service.ClientService;
import com.ironman.pharmasales.persistence.entity.Client;
import com.ironman.pharmasales.persistence.entity.DocumentType;
import com.ironman.pharmasales.persistence.repository.ClientRepository;
import com.ironman.pharmasales.persistence.repository.DocumentTypeRepository;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import com.ironman.pharmasales.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final DocumentTypeRepository documentTypeRepository;

    @Override
    public List<ClientDto> findAll() {
        List<Client> clients = (List<Client>) clientRepository.findAll();

        return clientMapper.toClientDtos(clients);
    }

    @Override
    public ClientDto findById(Long id) throws DataNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cliente no se encontro para el id: " + id));

        return clientMapper.toClientDto(client);
    }

    @Override
    public ClientDto create(ClientSaveDto clientBody) throws DataNotFoundException {
        DocumentType documentType = documentTypeRepository.findById(clientBody.getDocumentTypeId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Tipo de documento no encontrado para el id: " + clientBody.getDocumentTypeId())
                );

        Client clientSave = clientMapper.toClient(clientBody);
        clientSave.setDocumentType(documentType);
        clientSave.setState(State.ACTIVE.getValue());
        clientSave.setCreatedAt(LocalDateTime.now());

        Client client = clientRepository.save(clientSave);

        return clientMapper.toClientDto(client);
    }

    @Override
    public ClientDto edit(Long id, ClientSaveDto clientBody) throws DataNotFoundException {
        DocumentType documentType = documentTypeRepository.findById(clientBody.getDocumentTypeId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Tipo de documento no encontrado para el id: " + clientBody.getDocumentTypeId())
                );

        Client clientDb = clientRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cliente no se encontro para el id: " + id));

        clientMapper.updateClient(clientDb, clientBody);
        clientDb.setDocumentType(documentType);
        clientDb.setUpdatedAt(LocalDateTime.now());

        Client client = clientRepository.save(clientDb);

        return clientMapper.toClientDto(client);
    }

    @Override
    public ClientDto disabled(Long id) throws DataNotFoundException {
        Client clientDb = clientRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cliente no se encontro para el id: " + id));

        clientDb.setState(State.DISABLE.getValue());

        Client client = clientRepository.save(clientDb);

        return clientMapper.toClientDto(client);
    }

    @Override
    public Page<ClientDto> paginationFilter(Pageable pageable, Optional<ClientFilterDto> filter) {
        ClientFilterDto filterDto = filter.orElse(new ClientFilterDto());

        Client client = clientMapper.toClient(filterDto);

        Page<Client> clientPage = clientRepository.paginationFilter(pageable, client);

        return new PageImpl<>(
                clientMapper.toClientDtos(clientPage.getContent()),
                clientPage.getPageable(),
                clientPage.getTotalElements()
        );
    }

    @Override
    public List<ClientMediumDto> search(String searchText) {
        List<Client> clients = clientRepository.search(searchText);

        return clientMapper.toClientMediumDtos(clients);
    }
}
