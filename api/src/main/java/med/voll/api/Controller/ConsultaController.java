package med.voll.api.Controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.Domain.Consulta.AgendaDeConsultas;
import med.voll.api.Domain.Consulta.DadosAgendamentoConsulta;
import med.voll.api.Domain.Consulta.DadosCancelamentoConsulta;
import med.voll.api.Domain.Consulta.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agendaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendar (@RequestBody @Valid DadosAgendamentoConsulta dados){
        return ResponseEntity.ok(agendaService.agendar(dados));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarConsulta( @RequestBody DadosCancelamentoConsulta dados){
        agendaService.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
