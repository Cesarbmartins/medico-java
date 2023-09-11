package med.voll.api.Domain.Consulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        @NotNull
        Long id,
        MotivoCancelamento motivo) {
}
