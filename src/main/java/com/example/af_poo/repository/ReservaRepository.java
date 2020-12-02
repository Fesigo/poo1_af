package com.example.af_poo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.af_poo.model.Cliente;
import com.example.af_poo.model.Reserva;
import com.example.af_poo.model.Veiculo;

import org.springframework.stereotype.Component;

@Component
public class ReservaRepository {
    
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private int nextCode = 1;

    public List<Reserva> getAllReservas(){
        return reservas;
    }

    public Optional<Reserva> getReservaByNumero(int numero){

        for(Reserva r : reservas){
            if(r.getNumero() == numero){
                return Optional.of(r);
            }
        }
        return Optional.empty();
    }

    public Reserva save(Reserva reserva){
        reserva.setNumero(nextCode++);
        reservas.add(reserva);
        return reserva;
    }

	public Optional<List<Reserva>> getReservasByCliente(Cliente cliente) {

        List<Reserva> reservas = getAllReservas();
        ArrayList<Reserva> reservasCliente = new ArrayList<Reserva>();

        for(Reserva aux : reservas){
            if(cliente == aux.getCliente()){
                reservasCliente.add(aux);
            }
        }
        
        if(reservasCliente.size() > 0){
            return Optional.of(reservasCliente);
        }
        else{
            return Optional.empty();
        }
	}

	public Optional<List<Reserva>> getReservasByVeiculo(Veiculo veiculo) {

        List<Reserva> reservas = getAllReservas();
        ArrayList<Reserva> reservasVeiculo = new ArrayList<Reserva>();

        for(Reserva aux : reservas){
            if(veiculo == aux.getVeiculo()){
                reservasVeiculo.add(aux);
            }
        }

        if(reservasVeiculo.size() > 0){
            return Optional.of(reservasVeiculo);
        }
        else{
            return Optional.empty();
        }

	}


}
