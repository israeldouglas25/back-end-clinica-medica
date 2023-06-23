package med.voll.api.controller;

import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.DadosDetalhesMedico;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.service.medico.IMedicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosCadastroMedicoJson;
    @Autowired
    private JacksonTester<DadosDetalhesMedico> dadosDetalhesMedicoJson;
    @MockBean
    private IMedicoService iMedicoService;

    @Test
    @DisplayName("Deve devolver 400 ao inserir informações invalidas")
    @WithMockUser
    void createCenario1() throws Exception {
        var responde = mvc.perform(post("/api/v1/medicos"))
                .andReturn().getResponse();

        assertThat(responde.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve devolver 200 ao inserir informações validas")
    @WithMockUser
    void createCenario2() throws Exception{
        var dados = new DadosCadastroMedico(
                "Medico",
                "medico@voll.med",
                "61999999999",
                "123456",
                Especialidade.CARDIOLOGIA,
                dadosEndereco()
        );

        var dadosDetalhamento = new DadosDetalhesMedico(
                null,
                dados.nome(),
                dados.email(),
                dados.crm(),
                dados.telefone(),
                dados.especialidade(),
                new Endereco(dadosEndereco())
        );

        when(iMedicoService.create(any())).thenReturn(dadosDetalhamento);

        var response = mvc
                .perform(post("/api/v1/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroMedicoJson.write(dados).getJson()))
                .andReturn().getResponse();

        var jsonEsperado = dadosDetalhesMedicoJson.write(dadosDetalhamento).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}