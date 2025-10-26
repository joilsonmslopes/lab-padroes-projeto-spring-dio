package com.joilson.gof.service;

import com.joilson.gof.dto.ClientRequestDTO;
import com.joilson.gof.dto.ClientResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClientService {
    List<ClientResponseDTO> findAll();
    ClientResponseDTO findById(Long id) throws Exception;
    ClientResponseDTO save(ClientRequestDTO dto);
    ClientResponseDTO update(Long id, ClientRequestDTO dto) throws Exception;
    void deleteById(Long id) throws Exception;
}
