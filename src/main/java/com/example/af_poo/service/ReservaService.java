package com.example.af_poo.service;

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

        reserva.setCliente(cliente);
        reserva.setVeiculo(veiculo);
        reserva.setTotalReserva(reserva.getDataFim().compareTo(reserva.getDataInicio()) * veiculo.getValorDiaria());

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

        List<Reserva> reservas = reservaRepository.getReservasByCliente(cliente);

//        op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse cliente não possui reservas"));

		return reservas;
	}

	public List<Reserva> getReservasByVeiculo(int idVeiculo) {

        Veiculo veiculo = veiculoService.getVeiculoByCodigo(idVeiculo);

        List<Reserva> reservas = reservaRepository.getReservasByVeiculo(veiculo);

		return reservas;
	}
}
