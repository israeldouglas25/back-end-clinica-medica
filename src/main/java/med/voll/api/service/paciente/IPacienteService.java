package med.voll.api.service.paciente;

import med.voll.api.domain.paciente.DadosAtualizarPaciente;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.DadosDetalhesPaciente;
import med.voll.api.domain.paciente.DadosListarPaciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPacienteService {
    DadosDetalhesPaciente create(DadosCadastroPaciente dados);

    List<DadosListarPaciente> getAll();

    Page<DadosListarPaciente> getAllAtivos(Pageable pageable);

    DadosDetalhesPaciente update(DadosAtualizarPaciente dados);

    DadosDetalhesPaciente getById(Long id);

    void delete(Long id);
}
