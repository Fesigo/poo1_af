package com.example.af_poo.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.af_poo.dto.ReservaDTO;
import com.example.af_poo.model.Cliente;
import com.example.af_poo.model.Reserva;
import com.example.af_poo.model.Veiculo;
import com.example.af_poo.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VeiculoService veiculoService;

    public List<Reserva> getAllReservas(){
        return reservaRepository.getAllReservas();
    }

    public Reserva getReservaByNumero(int numero){
        Optional<Reserva> op = reservaRepository.getReservaByNumero(numero);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva inexistente"));
    }

    public Reserva salvar(Reserva reserva, int idCliente, int idVeiculo){

        Cliente cliente = clienteService.getClienteByCodigo(idCliente);
        Veiculo veiculo = veiculoService.getVeiculoByCodigo(idVeiculo);
        Optional<Reserva> op;

        if(reserva.getDataInicio().isBefore(LocalDateTime.now()) || reserva.getDataInicio().getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            op = Optional.empty();
            return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Data de início inválida"));
        }

        if(reserva.getDataFim().isBefore(reserva.getDataInicio()) || reserva.getDataFim().getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            op = Optional.empty();
            return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Data de fim inválida"));
        }

        List<Reserva> reservas = getAllReservas();
        for(Reserva aux : reservas){
            if(aux.getVeiculo() == veiculo){
                if(reserva.getDataInicio().isAfter(aux.getDataInicio()) && reserva.getDataInicio().isBefore(aux.getDataFim()) || reserva.getDataInicio().isEqual(aux.getDataInicio())){
                    op = Optional.empty();
                    return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Já há uma reserva para esse período"));
                }
            }
        }

        for(Reserva aux : reservas){
            if(aux.getVeiculo() == veiculo){
                if(reserva.getDataFim().isAfter(aux.getDataInicio()) && reserva.getDataFim().isBefore(aux.getDataFim()) || reserva.getDataFim().isEqual(aux.getDataFim())){
                    op = Optional.empty();
                    return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Já há uma reserva para esse período"));
                }
                
            }
        }

        reserva.setCliente(cliente);
        reserva.setVeiculo(veiculo);

        reserva.setTotalReserva(reserva.calculaTotal(reserva.getDataInicio(), reserva.getDataFim(), veiculo.getValorDiaria()));

        return reservaRepository.save(reserva);
    }

    public Reserva fromDTO(ReservaDTO dto){
        Reserva reserva = new Reserva();

        reserva.setDataInicio(dto.getDataInicio());
        reserva.setDataFim(dto.getDataFim());

        return reserva;
    }

	public List<Reserva> getReservasByCliente(int idCliente) {

        Cliente cliente = clienteService.getClienteByCodigo(idCliente);
        
        Optional<List<Reserva>> op = reservaRepository.getReservasByCliente(cliente);

        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse cliente não possui reservas"));

	}

	public List<Reserva> getReservasByVeiculo(int idVeiculo) {

        Veiculo veiculo = veiculoService.getVeiculoByCodigo(idVeiculo);
        
        Optional<List<Reserva>> op = reservaRepository.getReservasByVeiculo(veiculo);

        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse veículo não possui reservas"));

	}
}
