package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.repository.MedicoRepository;

public class MedicoAtivo {

    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsulta dados){
        if (dados.idMedico() == null){
            return;
        }

        var medicoAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if (!medicoAtivo){
            throw new ValidacaoException("A consulta não pode ser agendada, medico não esta ativo.");
        }
    }
}
