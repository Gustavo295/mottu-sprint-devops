package br.com.fiap.monitoramentomottu.dto.Localizacao;

import java.time.LocalDateTime;

public record LocalizacaoResponse(
        Long id,

        LocalDateTime timestamp,
        Double xCoord,
        Double yCoord
) {
}
