package com.api.cadastrodeimoveis.validate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class ValidarProprietario {

    @NotBlank(message = "Este campo é requerido")
    private String nome;

    @NotNull
    private long cpf;

    @NotNull
    private long rg;

    @NotNull
    private int cep;

    @NotNull
    private long telefone;

    @NotBlank(message = "Este campo é requerido")
    private String email;
    
    
}
