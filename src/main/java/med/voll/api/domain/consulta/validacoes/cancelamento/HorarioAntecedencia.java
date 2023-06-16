package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component("HorarioAntecedenciaCancelamento")
public class HorarioAntecedencia implements ValidadorCancelamentoDeConsulta{
    @Autowired
    private ConsultaRepository consultaRepository;
    @Override
    public void validar(DadosCancelamentoConsulta dados) {
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }

    }
}
