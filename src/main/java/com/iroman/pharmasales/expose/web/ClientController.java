package com.iroman.pharmasales.expose.web;

import com.iroman.pharmasales.application.dto.client.ClientDto;
import com.iroman.pharmasales.application.dto.client.ClientMediumDto;
import com.iroman.pharmasales.application.dto.client.ClientSaveDto;
import com.iroman.pharmasales.application.service.ClientService;
import com.iroman.pharmasales.shared.constant.StatusCode;
import com.iroman.pharmasales.shared.exception.DataNotFoundException;
import com.iroman.pharmasales.shared.exception.entity.ArgumentNotValidError;
import com.iroman.pharmasales.shared.exception.entity.GeneralError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/clients")
@RequiredArgsConstructor
@RestController
public class ClientController {
    private final ClientService clientService;

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping
    public ResponseEntity<List<ClientDto>> findAll(){
        List<ClientDto> clientDtos = clientService.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(clientDtos);
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
    public ResponseEntity<ClientDto> findById(@PathVariable("id") Long id) throws DataNotFoundException {
        ClientDto clientDto = clientService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(clientDto);
    }

    @ApiResponse(responseCode = StatusCode.CREATED)
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @PostMapping
    public ResponseEntity<ClientDto> create(@Valid @RequestBody ClientSaveDto clientSaveDto) throws DataNotFoundException {
        ClientDto clientDto = clientService.create(clientSaveDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientDto);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> edit(@Valid @PathVariable("id") Long id, @RequestBody ClientSaveDto clientSaveDto) throws DataNotFoundException {
        ClientDto clientDto = clientService.edit(id, clientSaveDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(clientDto);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping("/search/{searchText}")
    public ResponseEntity<List<ClientMediumDto>> search(@PathVariable("searchText") String searchText) {
        List<ClientMediumDto> clientMediumDtoList = clientService.search(searchText);

        return ResponseEntity.status(HttpStatus.OK)
                .body(clientMediumDtoList);
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
    public ResponseEntity<ClientDto> disable(@PathVariable("id") Long id) throws DataNotFoundException {
        ClientDto clientDto = clientService.disable(id);

        return  ResponseEntity.status(HttpStatus.OK)
                .body(clientDto);
    }
}
