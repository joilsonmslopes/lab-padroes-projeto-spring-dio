package com.joilson.gof.mapper;

import com.joilson.gof.dto.ClientResponseDTO;
import com.joilson.gof.model.Client;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {
    public ClientResponseDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }

        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setAddress(client.getAddress());
        return dto;
    }

    public Client toEntity(ClientResponseDTO dto) {
        if (dto == null) {
            return null;
        }

        Client client = new Client();
        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setAddress(dto.getAddress());
        return client;
    }

    public List<ClientResponseDTO> toDTOList(List<Client> clients) {
        if (clients == null) {
            return List.of();
        }

        return clients.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<Client> toEntityList(List<ClientResponseDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}