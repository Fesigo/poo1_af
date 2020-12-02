package com.example.af_poo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.af_poo.dto.VeiculoDTO;
import com.example.af_poo.model.Veiculo;
import com.example.af_poo.service.VeiculoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    
    @Autowired
    private VeiculoService veiculoService;

    @GetMapping()
    public List<Veiculo> getVeiculos(){
        return veiculoService.getAllVeiculos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Veiculo> getVeiculoByCodigo(@PathVariable int codigo){
        Veiculo veiculo = veiculoService.getVeiculoByCodigo(codigo);

        return ResponseEntity.ok(veiculo);
    }

    @PostMapping()
    public ResponseEntity<Veiculo> salvar(@RequestBody @Valid Veiculo veiculo, HttpServletRequest request, UriComponentsBuilder builder){
        Veiculo novoVeiculo = veiculoService.salvar(veiculo);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + novoVeiculo.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable int codigo){
        veiculoService.removeByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Veiculo> atualizar(@RequestBody @Valid VeiculoDTO veiculoDTO, @PathVariable int codigo){
        Veiculo veiculo = veiculoService.fromDTO(veiculoDTO);

        veiculo.setCodigo(codigo);
        veiculo = veiculoService.update(veiculo);

        return ResponseEntity.ok(veiculo);
    }

}
