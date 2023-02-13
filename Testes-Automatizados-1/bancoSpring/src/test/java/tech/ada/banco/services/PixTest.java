package tech.ada.banco.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tech.ada.banco.exceptions.ResourceNotFoundException;
import tech.ada.banco.exceptions.SaldoInsuficienteException;
import tech.ada.banco.model.Conta;
import tech.ada.banco.model.ModalidadeConta;
import tech.ada.banco.repository.ContaRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PixTest {
    private final ContaRepository repository = Mockito.mock(ContaRepository.class);
    private final Pix pix = new Pix(repository);

    @Test
    void testPixParcial() {
        Conta contaOrigem = criarConta(10, 100);
        Conta contaDestino = criarConta(10, 200);

        BigDecimal resp = pix.executar(100, 200, BigDecimal.ONE);

        verify(repository, times(1)).save(contaOrigem);
        assertEquals(BigDecimal.valueOf(9).setScale(2), resp.setScale(2));
        assertEquals(BigDecimal.valueOf(9).setScale(2), contaOrigem.getSaldo().setScale(2));

        verify(repository, times(1)).save(contaDestino);
        assertEquals(BigDecimal.valueOf(11).setScale(2), contaDestino.getSaldo().setScale(2));
    }

    @Test
    void testPixContaOrigemNaoEncontrada() {
        Conta contaOrigem = criarConta(10, 100);
        Conta contaDestino = criarConta(10, 200);

        try {
            pix.executar(101, 200, BigDecimal.ONE);
            fail("A conta não existe.");
        } catch (ResourceNotFoundException e) {

        }

        verify(repository, times(0)).save(any());

        assertEquals(BigDecimal.TEN.setScale(2), contaOrigem.getSaldo().setScale(2));
        assertEquals(BigDecimal.TEN.setScale(2), contaDestino.getSaldo().setScale(2));
    }

    @Test
    void testPixContaDestinoNaoEncontrada() {
        Conta contaOrigem = criarConta(10, 100);
        Conta contaDestino = criarConta(10, 200);

        try {
            pix.executar(100, 201, BigDecimal.ONE);
            fail("A conta não existe.");
        } catch (ResourceNotFoundException e) {

        }

        verify(repository, times(0)).save(any());

        assertEquals(BigDecimal.TEN.setScale(2), contaOrigem.getSaldo().setScale(2));
        assertEquals(BigDecimal.TEN.setScale(2), contaDestino.getSaldo().setScale(2));
    }

    @Test
    void testPixMaiorSaldo() {
        Conta contaOrigem = criarConta(5, 100);
        Conta contaDestino = criarConta(10, 200);

        assertThrows(SaldoInsuficienteException.class, () -> pix.executar(100, 200, BigDecimal.valueOf(6)));
        verify(repository, times(0)).save(any());
        assertEquals(BigDecimal.valueOf(5).setScale(2), contaOrigem.getSaldo().setScale(2));
        assertEquals(BigDecimal.valueOf(10).setScale(2), contaDestino.getSaldo().setScale(2));
    }

    @Test
    void testPixArredondamentoParaBaixo() { // erro corrigido
        Conta contaOrigem = criarConta(10, 100);
        Conta contaDestino = criarConta(0, 200);

        BigDecimal resp = pix.executar(100, 200, BigDecimal.valueOf(2.134956789));

        verify(repository, times(1)).save(contaOrigem);
        assertEquals(BigDecimal.valueOf(7.87), resp, "O valor subtraído foi arredondado para 2.13");
        assertEquals(BigDecimal.valueOf(7.87), contaOrigem.getSaldo());

        verify(repository, times(1)).save(contaDestino);
        assertEquals(BigDecimal.valueOf(2.13), contaDestino.getSaldo());
    }

    @Test
    void testPixArredondamentoParaCima() { // erro corrigido
        Conta contaOrigem = criarConta(10, 100);
        Conta contaDestino = criarConta(0, 200);

        BigDecimal resp = pix.executar(100, 200, BigDecimal.valueOf(2.135456789));

        verify(repository, times(1)).save(contaOrigem);
        assertEquals(BigDecimal.valueOf(7.86), resp, "O valor subtraído foi arredondado para 2.14");
        assertEquals(BigDecimal.valueOf(7.86), contaOrigem.getSaldo());

        verify(repository, times(1)).save(contaDestino);
        assertEquals(BigDecimal.valueOf(2.14), contaDestino.getSaldo());
    }

    private Conta criarConta(double valor, int numeroConta) {
        Conta conta = new Conta(ModalidadeConta.CC, null);
        if (valor == 0) {
            conta.setSaldo(BigDecimal.ZERO);
        } else {
            conta.deposito(BigDecimal.valueOf(valor));
        }
        when(repository.findContaByNumeroConta(numeroConta)).thenReturn(Optional.of(conta));
        assertEquals(BigDecimal.valueOf(valor), conta.getSaldo().setScale(1));
        return conta;
    }
}