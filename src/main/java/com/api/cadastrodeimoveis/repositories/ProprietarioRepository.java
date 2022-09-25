package com.api.cadastrodeimoveis.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.cadastrodeimoveis.models.ProprietarioModel;

@Repository
public interface ProprietarioRepository extends JpaRepository<ProprietarioModel, UUID> {
    boolean existsByEmail(String email);
}
