package com.meupdv.pdv.service;

import com.meupdv.pdv.entity.Produto;
import com.meupdv.pdv.repository.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;


    @Test
    @DisplayName("listarTodos - Cenário Positivo: Deve retornar lista de produtos cadastrados")
    void deveListarTodosOsProdutosComSucesso() {
        // Arrange
        Produto p1 = new Produto();
        p1.setIdProduto(1L);
        p1.setNome("Refrigerante Cola 2L");
        p1.setPrecoVenda(new BigDecimal("9.50"));

        Produto p2 = new Produto();
        p2.setIdProduto(2L);
        p2.setNome("Suco de Laranja 1L");
        p2.setPrecoVenda(new BigDecimal("7.00"));

        when(produtoRepository.findAll()).thenReturn(List.of(p1, p2));

        // Act
        List<Produto> resultado = produtoService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Refrigerante Cola 2L", resultado.get(0).getNome());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("listarTodos - Cenário Negativo: Deve retornar lista vazia quando não houver produtos")
    void deveRetornarListaVaziaQuandoNaoHouverProdutos() {
        // Arrange
        when(produtoRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Produto> resultado = produtoService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(produtoRepository, times(1)).findAll();
    }



    @Test
    @DisplayName("buscarPorId - Cenário Positivo: Deve retornar o produto quando o ID existir")
    void deveBuscarProdutoPorIdComSucesso() {
        // Arrange
        Long id = 1L;
        Produto produto = new Produto();
        produto.setIdProduto(id);
        produto.setNome("Água Mineral 500ml");

        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));

        // Act
        Produto resultado = produtoService.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getIdProduto());
        assertEquals("Água Mineral 500ml", resultado.getNome());
        verify(produtoRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("buscarPorId - Cenário Negativo: Deve retornar null quando o ID não existir")
    void deveRetornarNullQuandoProdutoNaoForEncontrado() {
        // Arrange
        Long idInexistente = 99L;
        when(produtoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act
        Produto resultado = produtoService.buscarPorId(idInexistente);

        // Assert
        assertNull(resultado);
        verify(produtoRepository, times(1)).findById(idInexistente);
    }



    @Test
    @DisplayName("salvar - Cenário Positivo: Deve salvar e retornar o produto com sucesso")
    void deveSalvarProdutoComSucesso() {
        // Arrange
        Produto produtoParaSalvar = new Produto();
        produtoParaSalvar.setNome("Biscoito Recheado");
        produtoParaSalvar.setCodigoBarras("7891000100103");
        produtoParaSalvar.setPrecoVenda(new BigDecimal("3.50"));

        Produto produtoSalvo = new Produto();
        produtoSalvo.setIdProduto(1L);
        produtoSalvo.setNome("Biscoito Recheado");
        produtoSalvo.setCodigoBarras("7891000100103");
        produtoSalvo.setPrecoVenda(new BigDecimal("3.50"));

        when(produtoRepository.save(produtoParaSalvar)).thenReturn(produtoSalvo);

        // Act
        Produto resultado = produtoService.salvar(produtoParaSalvar);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdProduto());
        assertEquals("Biscoito Recheado", resultado.getNome());
        verify(produtoRepository, times(1)).save(produtoParaSalvar);
    }

    @Test
    @DisplayName("salvar - Cenário Negativo: Deve propagar exceção quando repositório falhar ao salvar")
    void deveLancarExcecaoQuandoFalharAoSalvarProduto() {
        // Arrange
        Produto produtoInvalido = new Produto();
        when(produtoRepository.save(produtoInvalido))
                .thenThrow(new IllegalArgumentException("Erro ao persistir produto"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> produtoService.salvar(produtoInvalido));
        verify(produtoRepository, times(1)).save(produtoInvalido);
    }


    @Test
    @DisplayName("deletar - Cenário Positivo: Deve chamar deleteById com sucesso quando ID existir")
    void deveDeletarProdutoComSucesso() {
        // Arrange
        Long id = 1L;
        doNothing().when(produtoRepository).deleteById(id);

        // Act
        produtoService.deletar(id);

        // Assert
        verify(produtoRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("deletar - Cenário Negativo: Deve lançar exceção ao tentar deletar ID inexistente")
    void deveLancarExcecaoQuandoFalharAoDeletarProduto() {
        // Arrange
        Long idInexistente = 999L;
        doThrow(new RuntimeException("Produto não encontrado para exclusão"))
                .when(produtoRepository).deleteById(idInexistente);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> produtoService.deletar(idInexistente));
        verify(produtoRepository, times(1)).deleteById(idInexistente);
    }
}
