package com.ironman.pharmasales.expose.web;

import com.ironman.pharmasales.application.dto.client.ClientMediumDto;
import com.ironman.pharmasales.application.service.ClientService;
import com.ironman.pharmasales.shared.constant.StatusCode;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping("/search/{searchText}")
    public ResponseEntity<List<ClientMediumDto>> search(@PathVariable("searchText") String searchText){
        List<ClientMediumDto> clients = clientService.search(searchText);

        return ResponseEntity.status(HttpStatus.OK)
                .body(clients);
    }
}
