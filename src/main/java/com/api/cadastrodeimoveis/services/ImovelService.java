package com.api.cadastrodeimoveis.services;


import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.cadastrodeimoveis.models.ImovelModel;
import com.api.cadastrodeimoveis.repositories.ImovelRepository;


@Service
public class ImovelService {
    
    final ImovelRepository imovelRepository;

    public ImovelService(ImovelRepository imovelRepository) {
        this.imovelRepository = imovelRepository;
    }

    @Transactional
    public ImovelModel save(ImovelModel imovelModel) {
        return imovelRepository.save(imovelModel);
    }

    public boolean jaExisteEndereco(@NotNull(message = "Este campo é requerido") int numero) {
        return imovelRepository.existsByNumero(numero);
    }

    public Page<ImovelModel> findAll(Pageable pageable ){
        return imovelRepository.findAll(pageable);
    }

    public Optional<ImovelModel> findById(UUID id) {
        return imovelRepository.findById(id);
    }

    @Transactional
    public void delete(ImovelModel imovelModel) {
        imovelRepository.delete(imovelModel);
    }

    

}
