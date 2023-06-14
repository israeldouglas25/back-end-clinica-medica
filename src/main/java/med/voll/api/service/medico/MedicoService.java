package med.voll.api.service.medico;

import med.voll.api.domain.medico.*;
import med.voll.api.domain.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService implements IMedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    public DadosDetalhesMedico create(DadosCadastroMedico dados) {
        var medico = new Medico(dados);
        return new DadosDetalhesMedico(medicoRepository.save(medico));
    }

    public Page<DadosListarMedico> getAll(Pageable pageable) {
        return medicoRepository.findAllByAtivoTrue(pageable).map(DadosListarMedico::new);
    }

    public DadosDetalhesMedico update(DadosAtualizarMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarDados(dados);
        return new DadosDetalhesMedico(medico);
    }

    public void delete(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
    }

    public DadosDetalhesMedico getById(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return new DadosDetalhesMedico(medico);
    }
}