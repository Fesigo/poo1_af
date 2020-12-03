package com.example.af_poo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.af_poo.dto.ReservaDTO;
import com.example.af_poo.model.Reserva;
import com.example.af_poo.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ReservaController {
    
    @Autowired
    ReservaService reservaService;

    @GetMapping("/reservas")
    public List<Reserva> getReservas(){
        return reservaService.getAllReservas();
    }

    @GetMapping("/reservas/{numero}")
    public ResponseEntity<Reserva> getReservaByNumero(@PathVariable int numero){
        Reserva reserva = reservaService.getReservaByNumero(numero);

        return ResponseEntity.ok(reserva);
    }

    @PostMapping("/reserva/cliente/{idCliente}/veiculo/{idVeiculo}")
    public ResponseEntity<Reserva> salvar(@PathVariable int idCliente, @PathVariable int idVeiculo, @RequestBody @Valid ReservaDTO reservaDTO, HttpServletRequest request, UriComponentsBuilder builder){
        Reserva reserva = reservaService.fromDTO(reservaDTO);
        reserva = reservaService.salvar(reserva, idCliente, idVeiculo);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + reserva.getNumero()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("/cliente/{idCliente}/reservas")
    public List<Reserva> getReservasByCliente(@PathVariable int idCliente){
        return reservaService.getReservasByCliente(idCliente);
    }

    @GetMapping("/veiculo/{idVeiculo}/reservas")
    public List<Reserva> getReservasByVeiculo(@PathVariable int idVeiculo){
        return reservaService.getReservasByVeiculo(idVeiculo);
    }

}
