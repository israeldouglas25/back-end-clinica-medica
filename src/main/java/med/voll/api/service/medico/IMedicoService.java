package med.voll.api.service.medico;

import med.voll.api.domain.medico.DadosAtualizarMedico;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.DadosDetalhesMedico;
import med.voll.api.domain.medico.DadosListarMedico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMedicoService {
    DadosDetalhesMedico create(DadosCadastroMedico dados);

    Page<DadosListarMedico> getAll(Pageable pageable);

    DadosDetalhesMedico update(DadosAtualizarMedico dados);

    DadosDetalhesMedico getById(Long id);

    void delete(Long id);
}
