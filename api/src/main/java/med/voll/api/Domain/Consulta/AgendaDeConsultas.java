package med.voll.api.Domain.Consulta;

import med.voll.api.Domain.Consulta.Validacoes.agendamento.IValidacaoAgendamentoConsultas;
import med.voll.api.Domain.Medico.Medico;
import med.voll.api.Domain.Medico.MedicoRepository;
import med.voll.api.Domain.Paciente.PacienteRepository;
import med.voll.api.Domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    List<IValidacaoAgendamentoConsultas> validacoes;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("O id do paciente informado não existe");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("O id do médico informado não existe");
        }

        validacoes.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);
        
        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("A especialidade é obrigatória quando o médico não for escolhido");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        var consulta = consultaRepository.getReferenceById(dados.id());
        consulta.cancelar(dados.motivo());
    }
}
