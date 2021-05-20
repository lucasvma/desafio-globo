package com.globo.challenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Cep Dto", description = "Object to transfer cep")
public class CepDto implements Serializable {

    @JsonProperty
    private String cep;

    @JsonProperty
    private String logradouro;

    @JsonProperty
    private String complemento;

    @JsonProperty
    private String bairro;

    @JsonProperty
    private String localidade;

    @JsonProperty
    private String uf;

    @JsonProperty
    private String ibge;

    @JsonProperty
    private String gia;

    @JsonProperty
    private String ddd;

    @JsonProperty
    private String siafi;

    @JsonProperty
    private boolean erro;

}
