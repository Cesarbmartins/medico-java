package med.voll.api.Domain.Consulta.Validacoes.agendamento;

import med.voll.api.Domain.Consulta.DadosAgendamentoConsulta;
import med.voll.api.Domain.Consulta.Validacoes.agendamento.IValidacaoAgendamentoConsultas;
import med.voll.api.Domain.Medico.MedicoRepository;
import med.voll.api.Domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoAtivo implements IValidacaoAgendamentoConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados){
       if(dados.idMedico() == null){
           return;
       }

       var medicoAtivo = medicoRepository.findAtivoById(dados.idMedico());

       if(!medicoAtivo){
           throw new ValidacaoException("A consulta só pode ser realizada com um médico ativo");
       }
    }


}
