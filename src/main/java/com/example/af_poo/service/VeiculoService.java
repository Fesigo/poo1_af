package com.example.af_poo.service;

import java.util.List;
import java.util.Optional;

import com.example.af_poo.dto.VeiculoDTO;
import com.example.af_poo.model.Veiculo;
import com.example.af_poo.repository.VeiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> getAllVeiculos(){
        return veiculoRepository.getAllVeiculos();
    }

    public Veiculo getVeiculoByCodigo(int codigo){
        Optional<Veiculo> op = veiculoRepository.getVeiculoByCodigo(codigo);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não cadastrado"));
    }

    public Veiculo salvar(Veiculo veiculo){
        return veiculoRepository.save(veiculo);
    }

    public void removeByCodigo(int codigo){
        veiculoRepository.remove(getVeiculoByCodigo(codigo));
    }

    public Veiculo update(Veiculo veiculo){
        getVeiculoByCodigo(veiculo.getCodigo());
        return veiculoRepository.update(veiculo);
    }

	public Veiculo fromDTO(VeiculoDTO dto) {
        
        Veiculo veiculo = new Veiculo();

        veiculo.setValorDiaria(dto.getValorDiaria());
        
        return veiculo;
	}
    
}
