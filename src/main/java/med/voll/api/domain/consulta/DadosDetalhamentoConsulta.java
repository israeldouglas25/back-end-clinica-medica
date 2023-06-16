package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, String medico, Especialidade especialidade, String paciente, LocalDateTime data) {
    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getNome(), consulta.getMedico().getEspecialidade(), consulta.getPaciente().getNome(), consulta.getData());
    }
}
