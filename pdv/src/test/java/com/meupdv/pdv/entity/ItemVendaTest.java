package com.meupdv.pdv.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ItemVendaTest {

    private ItemVenda itemVenda;

    @BeforeEach
    void setUp() {
        itemVenda = new ItemVenda();
    }


    @Test
    @DisplayName("1. idItemVenda - Deve atribuir e retornar um ID válido")
    void deveAtribuirERetornarIdItemVendaValido() {
        // Arrange
        Long idEsperado = 1L;

        // Act
        itemVenda.setIdItemVenda(idEsperado);

        // Assert
        assertEquals(idEsperado, itemVenda.getIdItemVenda());
    }

    @Test
    @DisplayName("2. idItemVenda - Deve iniciar como nulo ao instanciar item")
    void deveIniciarIdItemVendaNulo() {
        // Arrange & Act
        ItemVenda novoItem = new ItemVenda();

        // Assert
        assertNull(novoItem.getIdItemVenda());
    }

    @Test
    @DisplayName("3. idItemVenda - Deve lançar exceção para ID zero ou negativo")
    void deveLancarExcecaoParaIdItemVendaInvalido() {
        // Arrange
        Long idInvalido = 0L;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> itemVenda.setIdItemVenda(idInvalido));
    }



    @Test
    @DisplayName("1. quantidade - Deve atribuir e retornar uma quantidade positiva")
    void deveAtribuirERetornarQuantidadeValida() {
        // Arrange
        Integer qtdEsperada = 3;

        // Act
        itemVenda.setQuantidade(qtdEsperada);

        // Assert
        assertEquals(qtdEsperada, itemVenda.getQuantidade());
    }

    @Test
    @DisplayName("2. quantidade - Deve lançar exceção quando quantidade for zero ou negativa")
    void deveLancarExcecaoQuandoQuantidadeForZeroOuNegativa() {
        // Arrange
        Integer qtdZero = 0;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> itemVenda.setQuantidade(qtdZero));
    }

    @Test
    @DisplayName("3. quantidade - Deve lançar exceção quando quantidade for nula")
    void deveLancarExcecaoQuandoQuantidadeForNula() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> itemVenda.setQuantidade(null));
    }


    @Test
    @DisplayName("1. precoUnitario - Deve atribuir e retornar um preço unitário válido")
    void deveAtribuirERetornarPrecoUnitarioValido() {
        // Arrange
        BigDecimal precoEsperado = new BigDecimal("12.50");

        // Act
        itemVenda.setPrecoUnitario(precoEsperado);

        // Assert
        assertEquals(precoEsperado, itemVenda.getPrecoUnitario());
    }

    @Test
    @DisplayName("2. precoUnitario - Deve permitir preço unitário igual a zero")
    void devePermitirPrecoUnitarioZero() {
        // Arrange
        BigDecimal precoZero = BigDecimal.ZERO;

        // Act
        itemVenda.setPrecoUnitario(precoZero);

        // Assert
        assertEquals(BigDecimal.ZERO, itemVenda.getPrecoUnitario());
    }

    @Test
    @DisplayName("3. precoUnitario - Deve lançar exceção quando preço unitário for negativo ou nulo")
    void deveLancarExcecaoQuandoPrecoUnitarioForNegativo() {
        // Arrange
        BigDecimal precoNegativo = new BigDecimal("-5.00");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> itemVenda.setPrecoUnitario(precoNegativo));
    }


    @Test
    @DisplayName("1. subtotal - Deve atribuir e retornar um subtotal válido")
    void deveAtribuirERetornarSubtotalValido() {
        // Arrange
        BigDecimal subtotalEsperado = new BigDecimal("37.50");

        // Act
        itemVenda.setSubtotal(subtotalEsperado);

        // Assert
        assertEquals(subtotalEsperado, itemVenda.getSubtotal());
    }

    @Test
    @DisplayName("2. subtotal - Deve permitir subtotal igual a zero")
    void devePermitirSubtotalZero() {
        // Arrange
        BigDecimal subtotalZero = BigDecimal.ZERO;

        // Act
        itemVenda.setSubtotal(subtotalZero);

        // Assert
        assertEquals(BigDecimal.ZERO, itemVenda.getSubtotal());
    }

    @Test
    @DisplayName("3. subtotal - Deve lançar exceção quando subtotal for negativo ou nulo")
    void deveLancarExcecaoQuandoSubtotalForNegativo() {
        // Arrange
        BigDecimal subtotalNegativo = new BigDecimal("-1.00");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> itemVenda.setSubtotal(subtotalNegativo));
    }



    @Test
    @DisplayName("1. descricao - Deve atribuir e retornar uma descrição válida")
    void deveAtribuirERetornarDescricaoValida() {
        // Arrange
        String descricaoEsperada = "X-Salada com Bacon";

        // Act
        itemVenda.setDescricao(descricaoEsperada);

        // Assert
        assertEquals(descricaoEsperada, itemVenda.getDescricao());
    }

    @Test
    @DisplayName("2. descricao - Deve permitir descrição nula (quando item é produto do estoque)")
    void devePermitirDescricaoNula() {
        // Arrange & Act
        itemVenda.setDescricao(null);

        // Assert
        assertNull(itemVenda.getDescricao());
    }

    @Test
    @DisplayName("3. descricao - Deve lançar exceção quando descrição tiver apenas espaços")
    void deveLancarExcecaoQuandoDescricaoForVazia() {
        // Arrange
        String descricaoVazia = "   ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> itemVenda.setDescricao(descricaoVazia));
    }



    @Test
    @DisplayName("1. lanche - Deve atribuir e retornar o nome do lanche")
    void deveAtribuirERetornarLancheValido() {
        // Arrange
        String lancheEsperado = "X-Burger";

        // Act
        itemVenda.setLanche(lancheEsperado);

        // Assert
        assertEquals(lancheEsperado, itemVenda.getLanche());
    }

    @Test
    @DisplayName("2. lanche - Deve permitir lanche nulo")
    void devePermitirLancheNulo() {
        // Arrange & Act
        itemVenda.setLanche(null);

        // Assert
        assertNull(itemVenda.getLanche());
    }

    @Test
    @DisplayName("3. lanche - Deve lançar exceção quando lanche tiver apenas espaços")
    void deveLancarExcecaoQuandoLancheForVazio() {
        // Arrange
        String lancheVazio = "  ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> itemVenda.setLanche(lancheVazio));
    }



    @Test
    @DisplayName("1. adicionais - Deve atribuir e retornar adicionais válidos")
    void deveAtribuirERetornarAdicionaisValidos() {
        // Arrange
        String adicionaisEsperados = "Queijo Extra, Ovo";

        // Act
        itemVenda.setAdicionais(adicionaisEsperados);

        // Assert
        assertEquals(adicionaisEsperados, itemVenda.getAdicionais());
    }

    @Test
    @DisplayName("2. adicionais - Deve permitir adicionais nulos")
    void devePermitirAdicionaisNulos() {
        // Arrange & Act
        itemVenda.setAdicionais(null);

        // Assert
        assertNull(itemVenda.getAdicionais());
    }

    @Test
    @DisplayName("3. adicionais - Deve remover espaços extras das extremidades")
    void deveLimparEspacosExtrasDeAdicionais() {
        // Arrange
        String adicionaisComEspacos = "  Bacon  ";

        // Act
        itemVenda.setAdicionais(adicionaisComEspacos);

        // Assert
        assertEquals("Bacon", itemVenda.getAdicionais());
    }


    @Test
    @DisplayName("1. produto - Deve vincular e retornar um produto válido")
    void deveVincularERetornarProdutoValido() {
        // Arrange
        Produto produtoEsperado = new Produto();

        // Act
        itemVenda.setProduto(produtoEsperado);

        // Assert
        assertEquals(produtoEsperado, itemVenda.getProduto());
    }

    @Test
    @DisplayName("2. produto - Deve permitir produto nulo (quando o item é um lanche)")
    void devePermitirProdutoNulo() {
        // Arrange & Act
        itemVenda.setProduto(null);

        // Assert
        assertNull(itemVenda.getProduto());
    }

    @Test
    @DisplayName("3. produto - Deve permitir substituir o produto vinculado")
    void deveSubstituirProdutoVinculado() {
        // Arrange
        Produto produto1 = new Produto();
        Produto produto2 = new Produto();
        itemVenda.setProduto(produto1);

        // Act
        itemVenda.setProduto(produto2);

        // Assert
        assertEquals(produto2, itemVenda.getProduto());
    }



    @Test
    @DisplayName("1. venda - Deve vincular e retornar uma venda válida")
    void deveVincularERetornarVendaValida() {
        // Arrange
        Venda vendaEsperada = new Venda();

        // Act
        itemVenda.setVenda(vendaEsperada);

        // Assert
        assertEquals(vendaEsperada, itemVenda.getVenda());
    }

    @Test
    @DisplayName("2. venda - Deve iniciar como nulo ao instanciar item")
    void deveIniciarVendaNula() {
        // Arrange & Act
        ItemVenda novoItem = new ItemVenda();

        // Assert
        assertNull(novoItem.getVenda());
    }

    @Test
    @DisplayName("3. venda - Deve lançar exceção ao tentar vincular venda nula")
    void deveLancarExcecaoQuandoVendaForNula() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> itemVenda.setVenda(null));
    }
}
