package tech.ada.banco.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.banco.exceptions.ResourceNotFoundException;
import tech.ada.banco.model.Conta;
import tech.ada.banco.model.ModalidadeConta;
import tech.ada.banco.repository.ContaRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepositoTest {
    private final ContaRepository repository = Mockito.mock(ContaRepository.class);
    private final Deposito deposito = new Deposito(repository);

    @Test
    void testeExecutarDepositoComum() {
        Conta conta = criarConta(100);

        BigDecimal resp = deposito.executar(100, BigDecimal.TEN);

        verify(repository, times(1)).save(conta);

        assertEquals(BigDecimal.valueOf(10).setScale(2), resp);
        assertEquals(BigDecimal.valueOf(10).setScale(2), conta.getSaldo());
    }
    @Test
    void testeExecutarDepositoContaInexistente() {
        Conta conta = criarConta(100);

        try {
            deposito.executar(1, BigDecimal.ONE);
            fail("A conta não existe.");
        } catch (ResourceNotFoundException e) {

        }

        verify(repository, times(0)).save(conta);

        assertEquals(BigDecimal.valueOf(0), conta.getSaldo());
    }

    @Test
    void testDepositoArredondamentoParaBaixo() {
        Conta conta = criarConta(100);

        BigDecimal resp = deposito.executar(100, BigDecimal.valueOf(10.134956789));

        verify(repository, times(1)).save(conta);

        assertEquals(BigDecimal.valueOf(10.13), resp, "O valor do deposito foi arredondado para 10.13");
        assertEquals(BigDecimal.valueOf(10.13), conta.getSaldo());
    }

    @Test
    void testDepositoArredondamentoParaCima() {
        Conta conta = criarConta(100);

        BigDecimal resp = deposito.executar(100, BigDecimal.valueOf(10.135456789));

        verify(repository, times(1)).save(conta);
        assertEquals(BigDecimal.valueOf(10.14), resp, "O valor do deposito foi arredondado para 10.14");
        assertEquals(BigDecimal.valueOf(10.14), conta.getSaldo());
    }

    private Conta criarConta(int numeroConta) {
        Conta conta = new Conta(ModalidadeConta.CC, null);
        when(repository.findContaByNumeroConta(numeroConta)).thenReturn(Optional.of(conta));
        return conta;
    }

}