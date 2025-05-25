package br.com.fiap.monitoramentomottu.dto.Patio;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record PatioRequest(
        @PositiveOrZero int qtdMoto,
        @Positive int area,
        @Positive int capacidadeMax,
        @Positive Long filialId,
        List<Long> motosId
        ) {
}
