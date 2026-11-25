package com.meupdv.pdv.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto();
    }


    @Test
    @DisplayName("1. idProduto - Deve atribuir e retornar um ID válido")
    void deveAtribuirERetornarIdProdutoValido() {
        // Arrange
        Long idEsperado = 1L;

        // Act
        produto.setIdProduto(idEsperado);

        // Assert
        assertEquals(idEsperado, produto.getIdProduto());
    }

    @Test
    @DisplayName("2. idProduto - Deve iniciar como nulo ao instanciar produto")
    void deveIniciarIdProdutoNulo() {
        // Arrange & Act
        Produto novoProduto = new Produto();

        // Assert
        assertNull(novoProduto.getIdProduto());
    }

    @Test
    @DisplayName("3. idProduto - Deve lançar exceção para ID zero ou negativo")
    void deveLancarExcecaoParaIdProdutoInvalido() {
        // Arrange
        Long idInvalido = 0L;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produto.setIdProduto(idInvalido));
    }


    @Test
    @DisplayName("1. nome - Deve atribuir e retornar um nome válido")
    void deveAtribuirERetornarNomeValido() {
        // Arrange
        String nomeEsperado = "Refrigerante Cola 2L";

        // Act
        produto.setNome(nomeEsperado);

        // Assert
        assertEquals(nomeEsperado, produto.getNome());
    }

    @Test
    @DisplayName("2. nome - Deve lançar exceção quando nome for nulo")
    void deveLancarExcecaoQuandoNomeForNulo() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produto.setNome(null));
    }

    @Test
    @DisplayName("3. nome - Deve lançar exceção quando nome estiver em branco")
    void deveLancarExcecaoQuandoNomeForVazio() {
        // Arrange
        String nomeVazio = "   ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produto.setNome(nomeVazio));
    }


    @Test
    @DisplayName("1. codigoBarras - Deve atribuir e retornar código de barras válido")
    void deveAtribuirERetornarCodigoBarrasValido() {
        // Arrange
        String codigoEsperado = "7894900011517";

        // Act
        produto.setCodigoBarras(codigoEsperado);

        // Assert
        assertEquals(codigoEsperado, produto.getCodigoBarras());
    }

    @Test
    @DisplayName("2. codigoBarras - Deve lançar exceção quando código de barras for nulo")
    void deveLancarExcecaoQuandoCodigoBarrasForNulo() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produto.setCodigoBarras(null));
    }

    @Test
    @DisplayName("3. codigoBarras - Deve lançar exceção quando código de barras estiver em branco")
    void deveLancarExcecaoQuandoCodigoBarrasForVazio() {
        // Arrange
        String codigoVazio = "  ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produto.setCodigoBarras(codigoVazio));
    }


    @Test
    @DisplayName("1. descricao - Deve atribuir e retornar uma descrição válida")
    void deveAtribuirERetornarDescricaoValida() {
        // Arrange
        String descricaoEsperada = "Garrafa PET 2 Litros";

        // Act
        produto.setDescricao(descricaoEsperada);

        // Assert
        assertEquals(descricaoEsperada, produto.getDescricao());
    }

    @Test
    @DisplayName("2. descricao - Deve permitir descrição nula")
    void devePermitirDescricaoNula() {
        // Arrange & Act
        produto.setDescricao(null);

        // Assert
        assertNull(produto.getDescricao());
    }

    @Test
    @DisplayName("3. descricao - Deve lançar exceção quando descrição tiver apenas espaços")
    void deveLancarExcecaoQuandoDescricaoForVazia() {
        // Arrange
        String descricaoVazia = "   ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produto.setDescricao(descricaoVazia));
    }



    @Test
    @DisplayName("1. precoVenda - Deve atribuir e retornar preço de venda válido")
    void deveAtribuirERetornarPrecoVendaValido() {
        // Arrange
        BigDecimal precoEsperado = new BigDecimal("9.99");

        // Act
        produto.setPrecoVenda(precoEsperado);

        // Assert
        assertEquals(precoEsperado, produto.getPrecoVenda());
    }

    @Test
    @DisplayName("2. precoVenda - Deve permitir preço de venda igual a zero")
    void devePermitirPrecoVendaZero() {
        // Arrange
        BigDecimal precoZero = BigDecimal.ZERO;

        // Act
        produto.setPrecoVenda(precoZero);

        // Assert
        assertEquals(BigDecimal.ZERO, produto.getPrecoVenda());
    }

    @Test
    @DisplayName("3. precoVenda - Deve lançar exceção quando preço de venda for negativo ou nulo")
    void deveLancarExcecaoQuandoPrecoVendaForNegativo() {
        // Arrange
        BigDecimal precoNegativo = new BigDecimal("-1.00");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produto.setPrecoVenda(precoNegativo));
    }


    @Test
    @DisplayName("1. precoCusto - Deve atribuir e retornar preço de custo válido")
    void deveAtribuirERetornarPrecoCustoValido() {
        // Arrange
        BigDecimal custoEsperado = new BigDecimal("6.50");

        // Act
        produto.setPrecoCusto(custoEsperado);

        // Assert
        assertEquals(custoEsperado, produto.getPrecoCusto());
    }

    @Test
    @DisplayName("2. precoCusto - Deve permitir preço de custo igual a zero")
    void devePermitirPrecoCustoZero() {
        // Arrange
        BigDecimal custoZero = BigDecimal.ZERO;

        // Act
        produto.setPrecoCusto(custoZero);

        // Assert
        assertEquals(BigDecimal.ZERO, produto.getPrecoCusto());
    }

    @Test
    @DisplayName("3. precoCusto - Deve lançar exceção quando preço de custo for negativo ou nulo")
    void deveLancarExcecaoQuandoPrecoCustoForNegativo() {
        // Arrange
        BigDecimal custoNegativo = new BigDecimal("-2.50");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produto.setPrecoCusto(custoNegativo));
    }


    @Test
    @DisplayName("1. quantidadeEstoque - Deve atribuir e retornar quantidade em estoque válida")
    void deveAtribuirERetornarQuantidadeEstoqueValida() {
        // Arrange
        Integer estoqueEsperado = 50;

        // Act
        produto.setQuantidadeEstoque(estoqueEsperado);

        // Assert
        assertEquals(estoqueEsperado, produto.getQuantidadeEstoque());
    }

    @Test
    @DisplayName("2. quantidadeEstoque - Deve permitir estoque zerado")
    void devePermitirQuantidadeEstoqueZero() {
        // Arrange
        Integer estoqueZero = 0;

        // Act
        produto.setQuantidadeEstoque(estoqueZero);

        // Assert
        assertEquals(0, produto.getQuantidadeEstoque());
    }

    @Test
    @DisplayName("3. quantidadeEstoque - Deve lançar exceção quando estoque for negativo ou nulo")
    void deveLancarExcecaoQuandoQuantidadeEstoqueForNegativa() {
        // Arrange
        Integer estoqueNegativo = -1;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produto.setQuantidadeEstoque(estoqueNegativo));
    }


    @Test
    @DisplayName("1. estoqueMinimo - Deve atribuir e retornar estoque mínimo válido")
    void deveAtribuirERetornarEstoqueMinimoValido() {
        // Arrange
        Integer minimoEsperado = 10;

        // Act
        produto.setEstoqueMinimo(minimoEsperado);

        // Assert
        assertEquals(minimoEsperado, produto.getEstoqueMinimo());
    }

    @Test
    @DisplayName("2. estoqueMinimo - Deve permitir estoque mínimo igual a zero")
    void devePermitirEstoqueMinimoZero() {
        // Arrange
        Integer minimoZero = 0;

        // Act
        produto.setEstoqueMinimo(minimoZero);

        // Assert
        assertEquals(0, produto.getEstoqueMinimo());
    }

    @Test
    @DisplayName("3. estoqueMinimo - Deve lançar exceção quando estoque mínimo for negativo ou nulo")
    void deveLancarExcecaoQuandoEstoqueMinimoForNegativo() {
        // Arrange
        Integer minimoNegativo = -3;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produto.setEstoqueMinimo(minimoNegativo));
    }



    @Test
    @DisplayName("1. validade - Deve atribuir e retornar data de validade")
    void deveAtribuirERetornarValidadeValida() {
        // Arrange
        LocalDate validadeEsperada = LocalDate.of(2027, 5, 20);

        // Act
        produto.setValidade(validadeEsperada);

        // Assert
        assertEquals(validadeEsperada, produto.getValidade());
    }

    @Test
    @DisplayName("2. validade - Deve permitir validade nula (produtos não perecíveis)")
    void devePermitirValidadeNula() {
        // Arrange & Act
        produto.setValidade(null);

        // Assert
        assertNull(produto.getValidade());
    }

    @Test
    @DisplayName("3. validade - Deve atualizar a data de validade corretamente")
    void deveAtualizarValidadeCorretamente() {
        // Arrange
        produto.setValidade(LocalDate.of(2026, 12, 1));
        LocalDate novaValidade = LocalDate.of(2027, 6, 1);

        // Act
        produto.setValidade(novaValidade);

        // Assert
        assertEquals(novaValidade, produto.getValidade());
    }



    @Test
    @DisplayName("1. status - Deve atribuir e retornar status válido")
    void deveAtribuirERetornarStatusValido() {
        // Arrange
        String statusEsperado = "ATIVO";

        // Act
        produto.setStatus(statusEsperado);

        // Assert
        assertEquals(statusEsperado, produto.getStatus());
    }

    @Test
    @DisplayName("2. status - Deve lançar exceção quando status for nulo")
    void deveLancarExcecaoQuandoStatusForNulo() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produto.setStatus(null));
    }

    @Test
    @DisplayName("3. status - Deve lançar exceção quando status estiver em branco")
    void deveLancarExcecaoQuandoStatusForVazio() {
        // Arrange
        String statusVazio = "   ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produto.setStatus(statusVazio));
    }



    @Test
    @DisplayName("1. categoria - Deve vincular e retornar uma categoria válida")
    void deveVincularERetornarCategoriaValida() {
        // Arrange
        Categoria categoriaEsperada = new Categoria();

        // Act
        produto.setCategoria(categoriaEsperada);

        // Assert
        assertEquals(categoriaEsperada, produto.getCategoria());
    }

    @Test
    @DisplayName("2. categoria - Deve iniciar como nulo ao instanciar produto")
    void deveIniciarCategoriaNula() {
        // Arrange & Act
        Produto novoProduto = new Produto();

        // Assert
        assertNull(novoProduto.getCategoria());
    }

    @Test
    @DisplayName("3. categoria - Deve lançar exceção ao tentar vincular categoria nula")
    void deveLancarExcecaoQuandoCategoriaForNula() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produto.setCategoria(null));
    }
}
