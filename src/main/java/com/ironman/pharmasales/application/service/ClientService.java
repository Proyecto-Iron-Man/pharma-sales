package com.ironman.pharmasales.application.service;

import com.ironman.pharmasales.application.dto.client.ClientMediumDto;

import java.util.List;

public interface ClientService {
    List<ClientMediumDto> search(String searchText);
}
