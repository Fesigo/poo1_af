package com.example.af_poo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.af_poo.model.Reserva;

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
}
