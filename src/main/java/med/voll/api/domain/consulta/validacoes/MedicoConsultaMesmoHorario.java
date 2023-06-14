package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConsultaMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DadosAgendamentoConsulta dados){
        var consultaMesmoHorario = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (consultaMesmoHorario){
            throw new ValidacaoException("Medico já possui consulta agendada neste horario");
        }
    }
}
