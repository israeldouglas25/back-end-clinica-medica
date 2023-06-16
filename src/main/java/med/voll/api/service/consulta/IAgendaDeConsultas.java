package med.voll.api.service.consulta;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;

import java.util.List;

public interface IAgendaDeConsultas {
    DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados);

    void cancelar(DadosCancelamentoConsulta dados);

    List<DadosDetalhamentoConsulta> getAll();
}
