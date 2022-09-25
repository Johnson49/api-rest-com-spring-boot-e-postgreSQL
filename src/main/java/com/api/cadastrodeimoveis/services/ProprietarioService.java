package com.api.cadastrodeimoveis.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.cadastrodeimoveis.models.ProprietarioModel;
import com.api.cadastrodeimoveis.repositories.ProprietarioRepository;


@Service
public class ProprietarioService {
    final ProprietarioRepository proprietarioRepository;

    public ProprietarioService(ProprietarioRepository proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }

    @Transactional
    public ProprietarioModel save(ProprietarioModel proprietarioModel) {
        return proprietarioRepository.save(proprietarioModel);
    }

    public Page<ProprietarioModel> findAll(Pageable pageable) {
        return proprietarioRepository.findAll(pageable);
    }

    public boolean jaExisteEndereco(@NotNull(message = "Este campo é requerido") String email) {
        return proprietarioRepository.existsByEmail(email);
    }

    public Optional<ProprietarioModel> findById(UUID id) {
        return proprietarioRepository.findById(id);
    }

    @Transactional
    public void delete(ProprietarioModel proprietarioModel) {
         proprietarioRepository.delete(proprietarioModel);
    }
}

