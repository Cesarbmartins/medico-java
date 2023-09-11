package med.voll.api.Domain.Consulta.Validacoes.cancelamento;

import med.voll.api.Domain.Consulta.DadosCancelamentoConsulta;

public interface IValidacaoCancelamento {
    void validar(DadosCancelamentoConsulta dados);
}
