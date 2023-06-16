package med.voll.api.service.paciente;

import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.domain.paciente.*;
import med.voll.api.domain.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public DadosDetalhesPaciente create(DadosCadastroPaciente dados) {
        var paciente = new Paciente(dados);
        return new DadosDetalhesPaciente(pacienteRepository.save(paciente));
    }

    public List<DadosListarPaciente> getAll() {
        return pacienteRepository.findAll().stream().map(DadosListarPaciente::new).toList();
    }

    public Page<DadosListarPaciente> getAllAtivos(Pageable pageable) {
        return pacienteRepository.findAllByAtivoTrue(pageable).map(DadosListarPaciente::new);
    }

    public DadosDetalhesPaciente update(DadosAtualizarPaciente dados) {
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarDados(dados);
        return new DadosDetalhesPaciente(paciente);
    }

    public void delete(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ValidacaoException("ID invalido!");
        }
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
    }

    public DadosDetalhesPaciente getById(Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        return new DadosDetalhesPaciente(paciente);
    }

    public boolean existsById(Long id) {
        return pacienteRepository.existsById(id);
    }

    public Paciente getPacienteById(Long id) {
        return pacienteRepository.getReferenceById(id);
    }
}
