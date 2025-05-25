package br.com.fiap.monitoramentomottu.dto.Endereco;

public record EnderecoResponse(
     Integer numero,
     String cep,
     String estado,
     String cidade,
     String bairro,
     String logradouro) {
}
