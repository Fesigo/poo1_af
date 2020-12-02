package com.example.af_poo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.af_poo.model.Cliente;
import com.example.af_poo.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteRepository {

    @Autowired
    private ReservaRepository reservaRepository;
    
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

    public Optional<Cliente> remove(Cliente cliente){

        Optional<List<Reserva>> reservas = reservaRepository.getReservasByCliente(cliente);

        if(reservas.equals(Optional.empty())){
            clientes.remove(cliente);
            return Optional.of(cliente);
        }
        return Optional.empty();

    }

    public Cliente update(Cliente cliente){
        Cliente aux = getClienteByCodigo(cliente.getCodigo()).get();

        if(aux != null){
            aux.setEndereco(cliente.getEndereco());
        }
        return aux;
    }
}
