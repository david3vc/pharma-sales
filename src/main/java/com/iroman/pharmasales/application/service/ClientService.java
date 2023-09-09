package com.iroman.pharmasales.application.service;

import com.iroman.pharmasales.application.dto.client.ClientDto;
import com.iroman.pharmasales.application.dto.client.ClientFilterDto;
import com.iroman.pharmasales.application.dto.client.ClientMediumDto;
import com.iroman.pharmasales.application.dto.client.ClientSaveDto;
import com.iroman.pharmasales.persistence.entity.Client;
import com.iroman.pharmasales.shared.exception.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientDto> findAll();
    ClientDto findById(Long id) throws DataNotFoundException;
    ClientDto create(ClientSaveDto clientBody) throws DataNotFoundException;
    ClientDto edit(Long id, ClientSaveDto clientBody) throws DataNotFoundException;
    ClientDto disable(Long id) throws DataNotFoundException;
    List<ClientMediumDto> search(String searchText);
    Page<ClientDto> paginationFilter(Pageable pageable, Optional<ClientFilterDto> filter);
}
