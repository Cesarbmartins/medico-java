package med.voll.api.Domain.Consulta.Validacoes.agendamento;

import med.voll.api.Domain.Consulta.ConsultaRepository;
import med.voll.api.Domain.Consulta.DadosAgendamentoConsulta;
import med.voll.api.Domain.Consulta.Validacoes.agendamento.IValidacaoAgendamentoConsultas;
import med.voll.api.Domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoDisponibilidadeMedicoNaData implements IValidacaoAgendamentoConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dados){
        var medicoPossuiConsultaHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());

        if(medicoPossuiConsultaHorario){
            throw new ValidacaoException("O médico já possui uma consulta nesse mesmo horário");
        }
    }
}
