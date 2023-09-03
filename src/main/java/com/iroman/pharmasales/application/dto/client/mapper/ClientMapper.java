package com.iroman.pharmasales.application.dto.client.mapper;

import com.iroman.pharmasales.application.dto.client.ClientDto;
import com.iroman.pharmasales.application.dto.client.ClientFilterDto;
import com.iroman.pharmasales.application.dto.client.ClientSaveDto;
import com.iroman.pharmasales.application.dto.documenttype.mapper.DocumentTypeMapper;
import com.iroman.pharmasales.persistence.entity.Client;
import com.iroman.pharmasales.shared.state.mapper.StateMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {StateMapper.class, DocumentTypeMapper.class})
public interface ClientMapper {
    // Dto from Entity start
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "documentNumber", source = "documentNumber")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "documentType", source = "documentType")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    ClientDto toClientDto(Client client);
    List<ClientDto> toClientDtos(List<Client> clients);
    // Dto from Entity end

    // Entity from dto start
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "documentNumber", source = "documentNumber")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "documentTypeId", source = "documentTypeId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Client toClient(ClientSaveDto clientSaveDto);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "documentTypeId", source = "documentTypeId")
    @Mapping(target = "documentNumber", source = "documentNumber")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Client toClient(ClientFilterDto clientFilterDto);
    // Entity from dto end
}
