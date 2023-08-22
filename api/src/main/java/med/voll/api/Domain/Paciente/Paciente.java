package med.voll.api.Domain.Paciente;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.Domain.Endereco.Endereco;


@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String cpf;

    private String telefone;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;
    public Paciente(DadosCadastradosPaciente dado){
        this.ativo = true;
        this.nome = dado.nome();
        this.email= dado.email();
        this.cpf = dado.cpf();
        this.telefone = dado.telefone();
        this.endereco = new Endereco(dado.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void excluir(){
        this.ativo = false;
    }
}
