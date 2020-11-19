package com.example.af_poo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.af_poo.model.Veiculo;

import org.springframework.stereotype.Component;

@Component
public class VeiculoRepository {
    
    private ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
    private int nextCode = 1;

    public List<Veiculo> getAllVeiculos(){
        return veiculos;
    }

    public Optional<Veiculo> getVeiculoByCodigo(int codigo){

        for(Veiculo v : veiculos){
            if(v.getCodigo() == codigo){
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }

    public Veiculo save(Veiculo veiculo){
        veiculo.setCodigo(nextCode++);
        veiculos.add(veiculo);
        return veiculo;
    }

    public void remove(Veiculo veiculo){
        veiculos.remove(veiculo);
    }

    public Veiculo update(Veiculo veiculo){
        Veiculo aux = getVeiculoByCodigo(veiculo.getCodigo()).get();

        if(aux != null){
            aux.setValorDiaria(veiculo.getValorDiaria());
        }
        return aux;
    }

}
