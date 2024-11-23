package br.com.vulner.beans;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PessoaCliente.class, name = "pessoaCliente"),
        @JsonSubTypes.Type(value = EmpresaCliente.class, name = "empresaCliente")
})
public abstract class Cliente {
}
