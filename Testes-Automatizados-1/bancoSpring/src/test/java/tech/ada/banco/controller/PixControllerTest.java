package tech.ada.banco.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import tech.ada.banco.model.Conta;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PixControllerTest extends BaseContaTest {

    private final String baseUri = "/pix";

    @Test
    void testPixSemSaldo() throws Exception {
        Conta contaBase = criarConta(BigDecimal.ZERO);
        Conta contaDestino = criarConta(BigDecimal.TEN);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("destino", String.valueOf(contaDestino.getNumeroConta()));
        params.add("valor", "10");

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .params(params)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andReturn().getResponse().getErrorMessage();

        assertEquals("Limite acima do saldo disponível!", response);
        assertEquals(BigDecimal.ZERO, contaBase.getSaldo());
        assertEquals(BigDecimal.TEN, contaDestino.getSaldo());
    }

    @Test
    void testPixSaldoTotal() throws Exception {
        Conta contaBase = criarConta(BigDecimal.TEN);
        Conta contaDestino = criarConta(BigDecimal.TEN);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("destino", String.valueOf(contaDestino.getNumeroConta()));
        params.add("valor", "10");

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .params(params)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        contaBase = obtemContaDoBanco(contaBase);
        assertEquals("0.00", response);
        assertEquals(BigDecimal.ZERO.setScale(2), contaBase.getSaldo());

        contaDestino = obtemContaDoBanco(contaDestino);
        assertEquals(BigDecimal.valueOf(20).setScale(2), contaDestino.getSaldo());
    }

    @Test
    void testPixSaldoInsuficiente() throws Exception {
        Conta contaBase = criarConta(BigDecimal.ONE);
        Conta contaDestino = criarConta(BigDecimal.TEN);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("destino", String.valueOf(contaDestino.getNumeroConta()));
        params.add("valor", "10");

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .params(params)
                                .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isBadRequest())
                    .andReturn().getResponse().getErrorMessage();

        assertEquals("Limite acima do saldo disponível!", response);
        assertEquals(BigDecimal.ONE, contaBase.getSaldo());
        assertEquals(BigDecimal.TEN, contaDestino.getSaldo());
    }

    @Test
    void testPixParcial() throws Exception {
        Conta contaBase = criarConta(BigDecimal.TEN);
        Conta contaDestino = criarConta(BigDecimal.ZERO);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("destino", String.valueOf(contaDestino.getNumeroConta()));
        params.add("valor", "7");

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .params(params)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        contaBase = obtemContaDoBanco(contaBase);
        assertEquals("3.00", response);
        assertEquals(BigDecimal.valueOf(3).setScale(2), contaBase.getSaldo());

        contaDestino = obtemContaDoBanco(contaDestino);
        assertEquals(BigDecimal.valueOf(7).setScale(2), contaDestino.getSaldo());
    }

    @Test
    void testPixParcialQuebrado() throws Exception {
        Conta contaBase = criarConta(BigDecimal.TEN);
        Conta contaDestino = criarConta(BigDecimal.ZERO);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("destino", String.valueOf(contaDestino.getNumeroConta()));
        params.add("valor", "7.8");

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .params(params)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        contaBase = obtemContaDoBanco(contaBase);
        assertEquals("2.20", response);
        assertEquals(BigDecimal.valueOf(2.2).setScale(2), contaBase.getSaldo());

        contaDestino = obtemContaDoBanco(contaDestino);
        assertEquals(BigDecimal.valueOf(7.8).setScale(2), contaDestino.getSaldo());
    }

    @Test
    void testPixContaOrigemInvalida() throws Exception {
        Conta contaBase = criarConta(BigDecimal.TEN);
        Conta contaDestino = criarConta(BigDecimal.TEN);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("destino", String.valueOf(contaDestino.getNumeroConta()));
        params.add("valor", "5");

        Optional<Conta> contaInexistente = repository.findContaByNumeroConta(50000);
        assertTrue(contaInexistente.isEmpty());

        String response =
                mvc.perform(post(baseUri + "/50000")
                                .params(params)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isNotFound())
                        .andReturn().getResponse().getErrorMessage();

        contaBase = obtemContaDoBanco(contaBase);
        assertEquals("Recurso não encontrado.", response);
        assertEquals(BigDecimal.TEN, contaBase.getSaldo());

        contaDestino = obtemContaDoBanco(contaDestino);
        assertEquals(BigDecimal.TEN, contaDestino.getSaldo());
    }

    @Test
    void testPixContaDestinoInvalida() throws Exception {
        Conta contaBase = criarConta(BigDecimal.TEN);
        Conta contaDestino = criarConta(BigDecimal.TEN);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("destino", "50000");
        params.add("valor", "5");

        Optional<Conta> contaInexistente = repository.findContaByNumeroConta(50000);
        assertTrue(contaInexistente.isEmpty());

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .params(params)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isNotFound())
                        .andReturn().getResponse().getErrorMessage();

        contaBase = obtemContaDoBanco(contaBase);
        assertEquals(BigDecimal.TEN, contaBase.getSaldo());

        contaDestino = obtemContaDoBanco(contaDestino);
        assertEquals("Recurso não encontrado.", response);
        assertEquals(BigDecimal.TEN, contaDestino.getSaldo());
    }

    @Test
    void testPixNegativo() throws Exception {
        Conta contaBase = criarConta(BigDecimal.TEN);
        Conta contaDestino = criarConta(BigDecimal.TEN);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("destino", String.valueOf(contaDestino.getNumeroConta()));
        params.add("valor", "-10");

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .params(params)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andReturn().getResponse().getErrorMessage();

        contaBase = obtemContaDoBanco(contaBase);
        assertEquals("Valor informado está inválido.", response);
        assertEquals(BigDecimal.TEN, contaBase.getSaldo());

        contaDestino = obtemContaDoBanco(contaDestino);
        assertEquals(BigDecimal.TEN, contaDestino.getSaldo());
    }

    @Test
    void testPixZerado() throws Exception {
        Conta contaBase = criarConta(BigDecimal.TEN);
        Conta contaDestino = criarConta(BigDecimal.TEN);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("destino", String.valueOf(contaDestino.getNumeroConta()));
        params.add("valor", "0");

        String response =
                mvc.perform(post(baseUri + "/" + contaBase.getNumeroConta())
                                .params(params)
                                .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
                        .andReturn().getResponse().getErrorMessage();

        contaBase = obtemContaDoBanco(contaBase);
        assertEquals("Valor informado está inválido.", response);
        assertEquals(BigDecimal.TEN, contaBase.getSaldo());

        contaDestino = obtemContaDoBanco(contaDestino);
        assertEquals(BigDecimal.TEN, contaDestino.getSaldo());
    }
}