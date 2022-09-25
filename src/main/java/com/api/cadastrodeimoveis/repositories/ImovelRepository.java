package com.api.cadastrodeimoveis.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.cadastrodeimoveis.models.ImovelModel;

@Repository
public interface ImovelRepository extends JpaRepository<ImovelModel, UUID> {

    boolean existsByNumero(int numero);
    
}