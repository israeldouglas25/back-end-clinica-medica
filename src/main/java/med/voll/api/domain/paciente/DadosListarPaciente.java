package med.voll.api.domain.paciente;

public record DadosListarPaciente(Long id, String nome, String telefone, String email) {

    public DadosListarPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getTelefone(), paciente.getEmail());
    }
}
