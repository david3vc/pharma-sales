package com.iroman.pharmasales.application.service.impl;

import com.iroman.pharmasales.application.dto.client.ClientDto;
import com.iroman.pharmasales.application.dto.client.ClientFilterDto;
import com.iroman.pharmasales.application.dto.client.ClientMediumDto;
import com.iroman.pharmasales.application.dto.client.ClientSaveDto;
import com.iroman.pharmasales.application.dto.client.mapper.ClientMapper;
import com.iroman.pharmasales.application.dto.subcategory.SubcategorySaveDto;
import com.iroman.pharmasales.application.service.ClientService;
import com.iroman.pharmasales.persistence.entity.Client;
import com.iroman.pharmasales.persistence.entity.DocumentType;
import com.iroman.pharmasales.persistence.repository.ClientRepository;
import com.iroman.pharmasales.persistence.repository.DocumentTypeRepository;
import com.iroman.pharmasales.shared.exception.DataNotFoundException;
import com.iroman.pharmasales.shared.state.enums.State;
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
        List<Client> clients = (List<Client>)clientRepository.findAll();

        return clientMapper.toClientDtos(clients);
    }

    @Override
    public ClientDto findById(Long id) throws DataNotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> clientNotFoundException(id));

        return clientMapper.toClientDto(client);
    }

    @Override
    public ClientDto create(ClientSaveDto clientBody) throws DataNotFoundException {
        DocumentType documentType = documentTypeRepository.findById(clientBody.getDocumentTypeId())
                .orElseThrow(() -> documentTypeNotFoundException(clientBody));

        Client clientSave = clientMapper.toClient((clientBody));

        clientSave.setDocumentType(documentType);
        clientSave.setState(State.ACTIVE.getValue());
        clientSave.setCreatedAt(LocalDateTime.now());

        Client client = clientRepository.save(clientSave);

        return clientMapper.toClientDto(client);
    }

    @Override
    public ClientDto edit(Long id, ClientSaveDto clientBody) throws DataNotFoundException {
        Client clientDb = clientRepository.findById(id)
                .orElseThrow(() -> clientNotFoundException(id));

        DocumentType documentType = documentTypeRepository.findById(clientBody.getDocumentTypeId())
                .orElseThrow(() -> documentTypeNotFoundException(clientBody));

        clientMapper.updateClient(clientDb, clientBody);

        clientDb.setDocumentType(documentType);
        clientDb.setUpdatedAt(LocalDateTime.now());

        Client client = clientRepository.save(clientDb);

        return clientMapper.toClientDto(client);
    }

    @Override
    public ClientDto disable(Long id) throws DataNotFoundException {
        Client clientDb = clientRepository.findById(id)
                .orElseThrow(() -> clientNotFoundException(id));

        clientDb.setState(State.DISABLE.getValue());

        Client client = clientRepository.save(clientDb);

        return clientMapper.toClientDto(client);
    }

    @Override
    public List<ClientMediumDto> search(String searchText) {
        List<Client> clients = clientRepository.search(searchText);

        return clientMapper.toClientMediumDtos(clients);
    }

    @Override
    public Page<ClientDto> paginationFilter(Pageable pageable, Optional<ClientFilterDto> filter) {
        ClientFilterDto filterDto = filter.orElse(new ClientFilterDto());

        Client client = clientMapper.toClient(filterDto);

        Page<Client> clientPage = clientRepository.paginationFilter(pageable, client);

        List<ClientDto> clientDtos = clientMapper.toClientDtos(clientPage.getContent());

        Page<ClientDto> clientDtoPage = new PageImpl<>(clientDtos, clientPage.getPageable(), clientPage.getTotalElements());

        return clientDtoPage;
    }

    private static DataNotFoundException clientNotFoundException(Long id) {
        return new DataNotFoundException("Cliente no encontrado para el id: " + id);
    }

    private static DataNotFoundException documentTypeNotFoundException(ClientSaveDto clientBody) {
        return new DataNotFoundException("Cliente no encontrado para el id: " + clientBody.getDocumentTypeId());
    }
}
