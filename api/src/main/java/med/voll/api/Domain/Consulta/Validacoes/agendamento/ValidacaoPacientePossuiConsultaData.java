package med.voll.api.Domain.Consulta.Validacoes.agendamento;

import med.voll.api.Domain.Consulta.ConsultaRepository;
import med.voll.api.Domain.Consulta.DadosAgendamentoConsulta;
import med.voll.api.Domain.Consulta.Validacoes.agendamento.IValidacaoAgendamentoConsultas;
import med.voll.api.Domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacientePossuiConsultaData implements IValidacaoAgendamentoConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados){
       var primeiroHorario = dados.data().withHour(7);
       var ultimoHorario = dados.data().withHour(18);
       var pacientePossuiConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
       if(pacientePossuiConsultaNoDia){
           throw new ValidacaoException("O paciente já possui consulta nesse dia");
       }
    }
}
