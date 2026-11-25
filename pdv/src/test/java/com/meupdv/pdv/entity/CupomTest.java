package com.meupdv.pdv.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class CupomTest {

    private Cupom cupom;

    @BeforeEach
    void setUp() {
        cupom = new Cupom();
    }


    @Test
    @DisplayName("1. idCupom - Deve atribuir e retornar um ID válido")
    void deveAtribuirERetornarIdCupomValido() {
        // Arrange
        Long idEsperado = 10L;

        // Act
        cupom.setIdCupom(idEsperado);

        // Assert
        assertEquals(idEsperado, cupom.getIdCupom());
    }

    @Test
    @DisplayName("2. idCupom - Deve iniciar como nulo ao instanciar cupom")
    void deveIniciarIdCupomComoNulo() {
        // Arrange & Act
        Cupom novoCupom = new Cupom();

        // Assert
        assertNull(novoCupom.getIdCupom());
    }

    @Test
    @DisplayName("3. idCupom - Deve lançar exceção para ID zero ou negativo")
    void deveLancarExcecaoParaIdCupomInvalido() {
        // Arrange
        Long idInvalido = -5L;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cupom.setIdCupom(idInvalido));
    }



    @Test
    @DisplayName("1. numeroCupom - Deve atribuir e retornar um número de cupom válido")
    void deveAtribuirERetornarNumeroCupomValido() {
        // Arrange
        String numeroEsperado = "CUPOM-2026-001";

        // Act
        cupom.setNumeroCupom(numeroEsperado);

        // Assert
        assertEquals(numeroEsperado, cupom.getNumeroCupom());
    }

    @Test
    @DisplayName("2. numeroCupom - Deve lançar exceção quando número do cupom for nulo")
    void deveLancarExcecaoQuandoNumeroCupomForNulo() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cupom.setNumeroCupom(null));
    }

    @Test
    @DisplayName("3. numeroCupom - Deve lançar exceção quando número do cupom estiver em branco")
    void deveLancarExcecaoQuandoNumeroCupomForVazio() {
        // Arrange
        String numeroVazio = "   ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cupom.setNumeroCupom(numeroVazio));
    }


    @Test
    @DisplayName("1. dataEmissao - Deve atribuir e retornar uma data de emissão válida")
    void deveAtribuirERetornarDataEmissaoValida() {
        // Arrange
        LocalDateTime dataEsperada = LocalDateTime.of(2026, 11, 24, 17, 50);

        // Act
        cupom.setDataEmissao(dataEsperada);

        // Assert
        assertEquals(dataEsperada, cupom.getDataEmissao());
    }

    @Test
    @DisplayName("2. dataEmissao - Deve iniciar como nulo ao instanciar cupom")
    void deveIniciarDataEmissaoComoNula() {
        // Arrange & Act
        Cupom novoCupom = new Cupom();

        // Assert
        assertNull(novoCupom.getDataEmissao());
    }

    @Test
    @DisplayName("3. dataEmissao - Deve lançar exceção quando data de emissão for nula")
    void deveLancarExcecaoQuandoDataEmissaoForNula() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cupom.setDataEmissao(null));
    }


    @Test
    @DisplayName("1. valorTotal - Deve atribuir e retornar um valor total válido")
    void deveAtribuirERetornarValorTotalValido() {
        // Arrange
        BigDecimal valorEsperado = new BigDecimal("149.90");

        // Act
        cupom.setValorTotal(valorEsperado);

        // Assert
        assertEquals(valorEsperado, cupom.getValorTotal());
    }

    @Test
    @DisplayName("2. valorTotal - Deve permitir valor total igual a zero")
    void devePermitirValorTotalZero() {
        // Arrange
        BigDecimal valorZero = BigDecimal.ZERO;

        // Act
        cupom.setValorTotal(valorZero);

        // Assert
        assertEquals(BigDecimal.ZERO, cupom.getValorTotal());
    }

    @Test
    @DisplayName("3. valorTotal - Deve lançar exceção quando valor total for negativo ou nulo")
    void deveLancarExcecaoQuandoValorTotalForNegativo() {
        // Arrange
        BigDecimal valorNegativo = new BigDecimal("-10.00");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cupom.setValorTotal(valorNegativo));
    }



    @Test
    @DisplayName("1. venda - Deve vincular e retornar uma venda válida")
    void deveVincularERetornarVendaValida() {
        // Arrange
        Venda vendaEsperada = new Venda();

        // Act
        cupom.setVenda(vendaEsperada);

        // Assert
        assertEquals(vendaEsperada, cupom.getVenda());
    }

    @Test
    @DisplayName("2. venda - Deve iniciar como nulo antes de vincular venda")
    void deveIniciarVendaComoNula() {
        // Arrange & Act
        Cupom novoCupom = new Cupom();

        // Assert
        assertNull(novoCupom.getVenda());
    }

    @Test
    @DisplayName("3. venda - Deve lançar exceção ao tentar vincular venda nula")
    void deveLancarExcecaoQuandoVendaForNula() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cupom.setVenda(null));
    }
}
