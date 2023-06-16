package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsulta dados){
        var pacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if (!pacienteAtivo){
            throw new ValidacaoException("A consulta não pode ser agendada, paciente não esta ativo.");
        }
    }
}
