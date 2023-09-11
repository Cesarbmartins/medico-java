package med.voll.api.Domain.Consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime data);

    Boolean existsByPacienteIdAndDataBetween(Long aLong, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
