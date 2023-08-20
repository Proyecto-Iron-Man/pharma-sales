package com.ironman.pharmasales.expose.web;

import com.ironman.pharmasales.application.dto.client.ClientDto;
import com.ironman.pharmasales.application.dto.client.ClientFilterDto;
import com.ironman.pharmasales.application.dto.client.ClientMediumDto;
import com.ironman.pharmasales.application.dto.client.ClientSaveDto;
import com.ironman.pharmasales.application.service.ClientService;
import com.ironman.pharmasales.shared.constant.StatusCode;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import com.ironman.pharmasales.shared.exception.entity.ArgumentNotValidError;
import com.ironman.pharmasales.shared.exception.entity.GeneralError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping
    public ResponseEntity<List<ClientDto>> findAll() {
        List<ClientDto> clients = clientService.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(clients);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable("id") Long id)
            throws DataNotFoundException {
        ClientDto client = clientService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(client);
    }

    @ApiResponse(responseCode = StatusCode.CREATED)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PostMapping
    public ResponseEntity<ClientDto> create(@Valid @RequestBody ClientSaveDto clientBody)
            throws DataNotFoundException {
        ClientDto client = clientService.create(clientBody);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(client);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> edit(@PathVariable("id") Long id, @Valid @RequestBody ClientSaveDto clientBody)
            throws DataNotFoundException {
        ClientDto client = clientService.edit(id, clientBody);

        return ResponseEntity.status(HttpStatus.OK)
                .body(client);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ClientDto> disabled(@PathVariable("id") Long id) throws DataNotFoundException {
        ClientDto client = clientService.disabled(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(client);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping("/pagination-filter")
    public ResponseEntity<Page<ClientDto>> paginationFilter(Pageable pageable, Optional<ClientFilterDto> filter) {
        Page<ClientDto> clientDtoPage = clientService.paginationFilter(pageable, filter);

        return ResponseEntity.status(HttpStatus.OK)
                .body(clientDtoPage);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping("/search/{searchText}")
    public ResponseEntity<List<ClientMediumDto>> search(@PathVariable("searchText") String searchText){
        List<ClientMediumDto> clients = clientService.search(searchText);

        return ResponseEntity.status(HttpStatus.OK)
                .body(clients);
    }
}
