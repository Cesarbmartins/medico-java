package med.voll.api.Domain.Consulta.Validacoes.cancelamento;

import med.voll.api.Domain.Consulta.ConsultaRepository;
import med.voll.api.Domain.Consulta.DadosCancelamentoConsulta;
import med.voll.api.Domain.Consulta.Validacoes.cancelamento.IValidacaoCancelamento;
import med.voll.api.Domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacaoHoraAntecedenciaCancelamento implements IValidacaoCancelamento {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dados) {
       var consulta = consultaRepository.getReferenceById(dados.id());
       var agora = LocalDateTime.now();
       var diferencaHoras = Duration.between(agora, consulta.getData()).toHours();
       if(diferencaHoras < 24){
           throw new ValidacaoException("O cancelamento de uma consulta só pode sr feito 24 horas antes da mesma");
       }

    }
}
