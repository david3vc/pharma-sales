package com.iroman.pharmasales.expose.web;

import com.iroman.pharmasales.application.dto.client.ClientDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.iroman.pharmasales.application.service.ClientService;
import com.iroman.pharmasales.shared.constant.StatusCode;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
