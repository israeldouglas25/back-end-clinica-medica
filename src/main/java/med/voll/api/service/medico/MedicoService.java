package med.voll.api.service.medico;

import med.voll.api.domain.medico.*;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.domain.repository.UsuarioRepository;
import med.voll.api.domain.usuario.Role;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MedicoService implements IMedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DadosDetalhesMedico create(DadosCadastroMedico dados) {
        // Hash da senha
        String encoded = passwordEncoder.encode(dados.senha());
        usuarioRepository.save(new Usuario(null, dados.email(), encoded, Role.ROLE_MEDICO));
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
        if (!medicoRepository.existsById(id)) {
            throw new ValidacaoException("ID invalido!");
        }
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
    }

    public DadosDetalhesMedico getById(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return new DadosDetalhesMedico(medico);
    }

    public boolean existsById(Long id) {
        return medicoRepository.existsById(id);
    }

    public Medico getMedicoById(Long id) {
        return medicoRepository.getReferenceById(id);
    }

    public Medico medicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data) {
        return medicoRepository.medicoAleatorioLivreNaData(especialidade, data);
    }
}
