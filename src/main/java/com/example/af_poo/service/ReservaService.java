package com.example.af_poo.service;

import java.util.List;
import java.util.Optional;

import com.example.af_poo.model.Reserva;
import com.example.af_poo.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> getAllReservas(){
        return reservaRepository.getAllReservas();
    }

    public Reserva getReservaByNumero(int numero){
        Optional<Reserva> op = reservaRepository.getReservaByNumero(numero);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva inexistente"));
    }

    public Reserva salvar(Reserva reserva){
        return reservaRepository.save(reserva);
    }


/* Talvez não use
	public Reserva fromDTO(ReservaDTO dto) {
        
        Reserva reserva = new Reserva();

        reserva.setDataFim(dto.getDataFim());
        
        return reserva;
	}*/
}
