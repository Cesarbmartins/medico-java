package med.voll.api.Domain.Consulta.Validacoes.agendamento;

import med.voll.api.Domain.Consulta.DadosAgendamentoConsulta;
import med.voll.api.Domain.Consulta.Validacoes.agendamento.IValidacaoAgendamentoConsultas;
import med.voll.api.Domain.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidacaoHorarioFuncionamentoClinica implements IValidacaoAgendamentoConsultas {
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        var antesAbrir = dataConsulta.getHour() < 7;
        var depoisFechar = dataConsulta.getHour() > 18;

        if(domingo || antesAbrir || depoisFechar){
            throw new ValidacaoException("Consulta marcada em data ou horário fora do funcionamento da clínica");
        }
    }
}
