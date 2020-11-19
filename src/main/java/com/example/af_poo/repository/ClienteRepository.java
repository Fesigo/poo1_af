package com.example.af_poo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.af_poo.model.Cliente;

import org.springframework.stereotype.Component;

@Component
public class ClienteRepository {
    
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private int nextCode = 1;

    public List<Cliente> getAllClientes(){
        return clientes;
    }

    public Optional<Cliente> getClienteByCodigo(int codigo){

        for(Cliente c : clientes){
            if(c.getCodigo() == codigo){
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    public Cliente save(Cliente cliente){
        cliente.setCodigo(nextCode++);
        clientes.add(cliente);
        return cliente;
    }

    public void remove(Cliente cliente){
        clientes.remove(cliente);
    }

    public Cliente update(Cliente cliente){
        Cliente aux = getClienteByCodigo(cliente.getCodigo()).get();

        if(aux != null){
            aux.setEndereco(cliente.getEndereco());
        }
        return aux;
    }
}
