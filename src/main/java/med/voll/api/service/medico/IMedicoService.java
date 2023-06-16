package med.voll.api.service.medico;

import med.voll.api.domain.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface IMedicoService {
    DadosDetalhesMedico create(DadosCadastroMedico dados);

    Page<DadosListarMedico> getAll(Pageable pageable);

    DadosDetalhesMedico update(DadosAtualizarMedico dados);

    DadosDetalhesMedico getById(Long id);

    void delete(Long id);

    boolean existsById(Long id);

    Medico getMedicoById(Long id);

    Medico medicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
}
