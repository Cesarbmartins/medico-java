package med.voll.api.Domain.Paciente;

public record DadosListagemPaciente(String nome, String telefone) {
    public DadosListagemPaciente (Paciente paciente){
        this(paciente.getNome(), paciente.getTelefone());
    }
}
