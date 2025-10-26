package com.joilson.gof.service.impl;

import com.joilson.gof.dto.ClientRequestDTO;
import com.joilson.gof.dto.ClientResponseDTO;
import com.joilson.gof.mapper.ClientMapper;
import com.joilson.gof.model.Address;
import com.joilson.gof.model.Client;
import com.joilson.gof.repository.AddressRepository;
import com.joilson.gof.repository.ClientRepository;
import com.joilson.gof.service.ClientService;
import com.joilson.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ViaCepService viaCepService;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<ClientResponseDTO> findAll() {
        Iterable<Client> iterableClients = clientRepository.findAll();
        List<Client> clientList = StreamSupport.stream(iterableClients.spliterator(), false)
                .toList();
        return clientMapper.toDTOList(clientList);
    }

    @Override
    public ClientResponseDTO findById(Long id) throws Exception {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new Exception("Cliente não encontrado com o ID: " + id));
        return clientMapper.toDTO(client);
    }

    @Override
    public ClientResponseDTO save(ClientRequestDTO dto) {
        String cep = dto.getCep();
        Address address = getAddress(cep);
        Client client = new Client();
        client.setName(dto.getName());
        client.setAddress(address);

        Client newClient = clientRepository.save(client);
        return clientMapper.toDTO(newClient);
    }

    @Override
    public ClientResponseDTO update(Long id, ClientRequestDTO dto) throws Exception {
        Client clientToUpdate = clientRepository.findById(id).orElseThrow(() -> new Exception("Cliente não encontrado com o ID: " + id));
        String cep = dto.getCep();
        Address addressFound = getAddress(cep);
        clientToUpdate.setName(dto.getName());
        clientToUpdate.setAddress(addressFound);

        clientRepository.save(clientToUpdate);
        return clientMapper.toDTO(clientToUpdate);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        Client clientFound = clientRepository.findById(id)
                .orElseThrow(() -> new Exception("Cliente não encontrado com o ID: " + id));

        clientRepository.delete(clientFound);
    }

    private Address getAddress(String cep) {
        return addressRepository.findById(cep).orElseGet(() -> {
            Address newAddress = null;
            try {
                newAddress = viaCepService.findById(cep).orElseThrow(() -> new Exception("Cep não encontrado na api ViaCep"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            addressRepository.save(newAddress);
            return newAddress;
        });
    }
}
