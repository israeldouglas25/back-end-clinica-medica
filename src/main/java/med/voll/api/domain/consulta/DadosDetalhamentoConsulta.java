package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, String paciente, String medico, Especialidade especialidade, LocalDateTime data) {
    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getPaciente().getNome(), consulta.getMedico().getNome(), consulta.getMedico().getEspecialidade(), consulta.getData());
    }
}
