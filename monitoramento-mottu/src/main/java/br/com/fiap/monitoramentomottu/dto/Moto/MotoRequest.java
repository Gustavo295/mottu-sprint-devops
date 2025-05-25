package br.com.fiap.monitoramentomottu.dto.Moto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record MotoRequest(
        @NotBlank
        @Pattern(
                regexp = "^[A-Z]{3}\\d[A-Z0-9]\\d{2}$|^[A-Z]{3}-\\d{4}$",
                message = "Placa deve estar no formato ABC1D23 ou AAA-1234"
        )
        String placa,

        @NotBlank(message = "Voce deve colocar um modelo")
        String modelo,
        @NotNull(message = "A moto precisa de uma condição")
        @Positive(message = "O valor não pode ser negativo" )
        Long condicaoId,
        @Positive(message = "O valor não pode ser negativo" )
        Long patioId
) {}
