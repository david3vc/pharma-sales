package com.iroman.pharmasales.application.service.impl;

import com.iroman.pharmasales.application.dto.client.ClientDto;
import com.iroman.pharmasales.application.dto.client.ClientFilterDto;
import com.iroman.pharmasales.application.dto.client.ClientSaveDto;
import com.iroman.pharmasales.application.dto.client.mapper.ClientMapper;
import com.iroman.pharmasales.application.service.ClientService;
import com.iroman.pharmasales.persistence.entity.Client;
import com.iroman.pharmasales.persistence.repository.ClientRepository;
import com.iroman.pharmasales.persistence.repository.DocumentTypeRepository;
import com.iroman.pharmasales.shared.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        List<Client> clients = (List<Client>)clientRepository.findAll();

        return clientMapper.toClientDtos(clients);
    }

    @Override
    public ClientDto findById(Long id) throws DataNotFoundException {
        return null;
    }

    @Override
    public ClientDto create(ClientSaveDto clientBody) throws DataNotFoundException {
        return null;
    }

    @Override
    public ClientDto edit(Long id, ClientSaveDto clientBody) throws DataNotFoundException {
        return null;
    }

    @Override
    public ClientDto disable(Long id) throws DataNotFoundException {
        return null;
    }

    @Override
    public Page<ClientDto> paginationFilter(Pageable pageable, Optional<ClientFilterDto> filter) {
        return null;
    }
}
