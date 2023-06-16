package med.voll.api.service.consulta;

import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.service.medico.IMedicoService;
import med.voll.api.service.paciente.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas implements IAgendaDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private IMedicoService iMedicoService;
    @Autowired
    private IPacienteService iPacienteService;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!iPacienteService.existsById(dados.idPaciente())) {
            throw new ValidacaoException("ID do paciente não existe!");
        }
        if (dados.idMedico() != null && !iMedicoService.existsById(dados.idMedico())) {
            throw new ValidacaoException("ID do medico não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = iPacienteService.getPacienteById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe medico disponivel nesta data!");
        }
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("O ID da consulta não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

    public List<DadosDetalhamentoConsulta> getAll() {
        return consultaRepository.findAll().stream().map(DadosDetalhamentoConsulta::new).toList();
    }

    public Consulta getById(Long id){
        return consultaRepository.findById(id)
                .orElseThrow(() -> new ValidacaoException("ID da consulta invalido ou não existe"));
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return iMedicoService.getMedicoById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade não especificada!");
        }

        return iMedicoService.medicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
