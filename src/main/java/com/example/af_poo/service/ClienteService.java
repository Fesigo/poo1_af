package com.example.af_poo.service;

import java.util.List;
import java.util.Optional;

import com.example.af_poo.dto.ClienteDTO;
import com.example.af_poo.model.Cliente;
import com.example.af_poo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes(){
        return clienteRepository.getAllClientes();
    }

    public Cliente getClienteByCodigo(int codigo){
        Optional<Cliente> op = clienteRepository.getClienteByCodigo(codigo);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não cadastrado"));
    }

    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void removeByCodigo(int codigo){
        Optional<Cliente> op = clienteRepository.remove(getClienteByCodigo(codigo));

        op.orElseThrow( () -> new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Esse cliente não pode ser deletado, pois ele possui reserva(s)"));
    }

    public Cliente update(Cliente cliente){
        getClienteByCodigo(cliente.getCodigo());
        return clienteRepository.update(cliente);
    }

	public Cliente fromDTO(ClienteDTO dto) {
        
        Cliente cliente = new Cliente();

        cliente.setEndereco(dto.getEndereco());
        
        return cliente;
	}
}
