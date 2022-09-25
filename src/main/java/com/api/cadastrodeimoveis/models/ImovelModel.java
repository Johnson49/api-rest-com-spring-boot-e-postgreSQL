package com.api.cadastrodeimoveis.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.api.cadastrodeimoveis.opcoes.SituacaoImovel;
import com.api.cadastrodeimoveis.opcoes.TipoImovel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Imoveis")
public class ImovelModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String logradouro;
    
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private SituacaoImovel situacao_do_imovel;
    
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TipoImovel tipo_do_imovel;

    @Column(nullable = false, unique = true)
    private int numero;

    @Column(nullable = false, length = 70)
    private String complemento;

    @Column(nullable = false, length = 70)
    private String estado;

    @Column(nullable = false, length = 70)
    private String cidade;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false, length = 8)
    private int cep;

    @Column(nullable = false)
    private LocalDateTime registradoEm;

    @ManyToOne(fetch = FetchType.LAZY, optional = false )
    @JoinColumn(name = "id_proprietario", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ProprietarioModel proprietario; 



}