package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import med.voll.api.service.medico.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/v1/medicos")
public class MedicoController {

    @Autowired
    private IMedicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = service.create(dados);
        var uri = uriBuilder.path("api/v1/medicos/{id}").buildAndExpand(medico.id()).toUri();
        return ResponseEntity.created(uri).body(medico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListarMedico>> getAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DadosAtualizarMedico dados) {
        return ResponseEntity.ok(service.update(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
}
