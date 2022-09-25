package com.api.cadastrodeimoveis.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "Proprietario")
public class ProprietarioModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private long cpf;

    @Column(nullable = false)
    private long rg;

    @Column(nullable = false)
    private int cep;

    @Column(nullable = false)
    private long telefone;

    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private LocalDateTime registradoEm;

    @OneToMany(mappedBy = "proprietario", cascade = {CascadeType.ALL})
    private List<ImovelModel> imoveis;
}

