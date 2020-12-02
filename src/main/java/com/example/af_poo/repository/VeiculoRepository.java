package com.example.af_poo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.af_poo.model.Reserva;
import com.example.af_poo.model.Veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VeiculoRepository {

    @Autowired
    private ReservaRepository reservaRepository;
    
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

    public Optional<Veiculo> remove(Veiculo veiculo){

        Optional<List<Reserva>> reservas = reservaRepository.getReservasByVeiculo(veiculo);

        if(reservas.equals(Optional.empty())){
            veiculos.remove(veiculo);
            return Optional.of(veiculo);
        }
        return Optional.empty();
        
    }

    public Veiculo update(Veiculo veiculo){
        Veiculo aux = getVeiculoByCodigo(veiculo.getCodigo()).get();

        if(aux != null){
            aux.setValorDiaria(veiculo.getValorDiaria());
        }
        return aux;
    }

}
