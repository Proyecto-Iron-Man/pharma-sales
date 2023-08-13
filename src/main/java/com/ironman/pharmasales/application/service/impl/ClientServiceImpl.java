package com.ironman.pharmasales.application.service.impl;

import com.ironman.pharmasales.application.dto.client.ClientMediumDto;
import com.ironman.pharmasales.application.dto.client.mapper.ClientMapper;
import com.ironman.pharmasales.application.service.ClientService;
import com.ironman.pharmasales.persistence.entity.Client;
import com.ironman.pharmasales.persistence.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientMediumDto> search(String searchText) {
        List<Client> clients = clientRepository.search(searchText);

        return clientMapper.toClientMediumDtos(clients);
    }
}
