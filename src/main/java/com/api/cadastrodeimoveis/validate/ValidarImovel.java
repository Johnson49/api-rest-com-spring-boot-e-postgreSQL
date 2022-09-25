package com.api.cadastrodeimoveis.validate;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.api.cadastrodeimoveis.models.ProprietarioModel;
import com.api.cadastrodeimoveis.opcoes.SituacaoImovel;
import com.api.cadastrodeimoveis.opcoes.TipoImovel;

import lombok.Data;


@Data
public class ValidarImovel {

    @NotBlank(message = "Este campo é requerido")
    private String logradouro;

    @NotNull(message = "Este campo é requerido")
    private int numero;

    @NotBlank(message = "Este campo é requerido")
    private String complemento;

    @NotBlank(message = "Este campo é requerido")
    private String estado;

    @NotBlank(message = "Este campo é requerido")
    private String cidade;

    @NotBlank(message = "Este campo é requerido")
    private String bairro;

    @NotNull(message = "Este campo é requerido")
    private int cep;

    @NotNull(message = "Este campo é requerido")
    private ProprietarioModel proprietario; 
    
    @NotNull(message = "Este campo é requerido")
    private TipoImovel tipo_do_imovel;
    
    @NotNull(message = "Este campo é requerido")
    private SituacaoImovel situacao_do_imovel;

}
