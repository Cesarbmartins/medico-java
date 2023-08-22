package med.voll.api.Domain.Paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.Domain.Endereco.DadosEndereco;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastradosPaciente(@NotBlank String nome,
                                       @NotBlank @Email String email,
                                       @NotBlank String telefone,
                                       @NotBlank @CPF String cpf,
                                       @NotNull @Valid DadosEndereco endereco) {
}
