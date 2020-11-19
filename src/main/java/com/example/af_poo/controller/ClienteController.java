package com.example.af_poo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.af_poo.dto.ClienteDTO;
import com.example.af_poo.model.Cliente;
import com.example.af_poo.service.ClienteService;

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
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping()
    public List<Cliente> getClientes(){
        return clienteService.getAllClientes();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Cliente> getClienteByCodigo(@PathVariable int codigo){
        Cliente cliente = clienteService.getClienteByCodigo(codigo);

        return ResponseEntity.ok(cliente);
    }

    @PostMapping()
    public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente, HttpServletRequest request, UriComponentsBuilder builder){
        Cliente novoCliente = clienteService.salvar(cliente);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + novoCliente.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable int codigo){
        clienteService.removeByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Cliente> atualizar(@RequestBody ClienteDTO clienteDTO, @PathVariable int codigo){
        Cliente cliente = clienteService.fromDTO(clienteDTO);

        cliente.setCodigo(codigo);
        cliente = clienteService.update(cliente);

        return ResponseEntity.ok(cliente);
    }
}
