package tech.ada.banco.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import tech.ada.banco.model.Conta;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DepositoControllerTest extends BaseContaTest {

    private final String baseUri = "/deposito";

    @Test
    void testDepositoSemSaldo() throws Exception {
        Conta contaBase = criarConta(BigDecimal.ZERO);

        String response = mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                      .param("valor", "10")
                                      .contentType(MediaType.APPLICATION_JSON))
                             .andDo(print())
                             .andExpect(status().isOk())
                             .andReturn().getResponse().getContentAsString();
        assertEquals("10.00", response);
    }

    @Test
    void testDepositoComSucessoComum() throws Exception {
        Conta contaBase = criarConta(BigDecimal.TEN);

        String response = mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                     .param("valor", "10")
                                     .contentType(MediaType.APPLICATION_JSON))
                             .andDo(print())
                             .andExpect(status().isOk())
                             .andReturn().getResponse().getContentAsString();
        assertEquals("20.00", response);
    }

    @Test
    void testDepositoComSucessoQuebrado() throws Exception {
        Conta contaBase = criarConta(BigDecimal.TEN);

        String response = mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                     .param("valor", "12.5")
                                     .contentType(MediaType.APPLICATION_JSON))
                             .andDo(print())
                             .andExpect(status().isOk())
                             .andReturn().getResponse().getContentAsString();
        assertEquals("22.50", response);
    }

    @Test
    void testDepositoZero() throws Exception { // erro corrigido
        Conta contaBase = criarConta(BigDecimal.TEN);

        String response = mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                     .param("valor", "0")
                                     .contentType(MediaType.APPLICATION_JSON))
                             .andDo(print())
                             .andExpect(status().isBadRequest())
                             .andReturn().getResponse().getErrorMessage();
        assertEquals("Valor informado está inválido.", response);
    }

    @Test
    void testDepositoValorNegativo() throws Exception {
        Conta contaBase = criarConta(BigDecimal.TEN);

        String response = mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                     .param("valor", "-1")
                                     .contentType(MediaType.APPLICATION_JSON))
                             .andDo(print())
                             .andExpect(status().isBadRequest())
                             .andReturn().getResponse().getErrorMessage();
        assertEquals("Valor informado está inválido.", response);
    }

    @Test
    void testDepositoContaInvalida() throws Exception {
        Conta contaBase = criarConta(BigDecimal.TEN);
        Optional<Conta> contaInexistente = repository.findContaByNumeroConta(50000);
        assertTrue(contaInexistente.isEmpty());

        String response = mvc.perform(post(baseUri + "/50000")
                                     .param("valor", "10")
                                     .contentType(MediaType.APPLICATION_JSON))
                             .andDo(print())
                             .andExpect(status().isNotFound())
                             .andReturn().getResponse().getErrorMessage();

        contaBase = obtemContaDoBanco(contaBase);
        assertEquals(BigDecimal.valueOf(10), contaBase.getSaldo());
        assertEquals("Recurso não encontrado.", response);
    }
}