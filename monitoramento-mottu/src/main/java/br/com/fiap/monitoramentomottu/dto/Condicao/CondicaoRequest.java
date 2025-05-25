package br.com.fiap.monitoramentomottu.dto.Condicao;

import jakarta.validation.constraints.NotBlank;

public record CondicaoRequest(
        @NotBlank String nome,
        @NotBlank String cor) {
}
