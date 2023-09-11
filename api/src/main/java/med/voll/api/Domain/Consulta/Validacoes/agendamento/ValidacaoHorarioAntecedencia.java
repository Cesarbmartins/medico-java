package med.voll.api.Domain.Consulta.Validacoes.agendamento;

import med.voll.api.Domain.Consulta.DadosAgendamentoConsulta;
import med.voll.api.Domain.Consulta.Validacoes.agendamento.IValidacaoAgendamentoConsultas;
import med.voll.api.Domain.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacaoHorarioAntecedencia implements IValidacaoAgendamentoConsultas {
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30){
            throw new ValidacaoException("A consulta deve ser marcada com antecedência mínima de 30 minutos");
        }
    }
}
