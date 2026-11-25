package com.meupdv.pdv.controller;

import com.meupdv.pdv.entity.Produto;
import com.meupdv.pdv.service.CategoriaService;
import com.meupdv.pdv.service.ProdutoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    @Mock
    private CategoriaService categoriaService;

    @Mock
    private Model model;

    @InjectMocks
    private ProdutoController controller;

    @Test
    @DisplayName("listar - Deve listar produtos no model e retornar view 'produtos'")
    void deveListarProdutosComSucesso() {
        // Arrange
        when(produtoService.listarTodos()).thenReturn(List.of(new Produto()));

        // Act
        String view = controller.listar(model);

        // Assert
        assertEquals("produtos", view);
        verify(model, times(1)).addAttribute(eq("produtos"), any());
    }

    @Test
    @DisplayName("novo - Deve preparar produto e categorias no model e retornar 'produto-form'")
    void deveAbrirFormularioNovoProduto() {
        // Arrange
        when(categoriaService.listarTodos()).thenReturn(List.of());

        // Act
        String view = controller.novo(model);

        // Assert
        assertEquals("produto-form", view);
        verify(model, times(1)).addAttribute(eq("produto"), any(Produto.class));
        verify(model, times(1)).addAttribute(eq("categorias"), any());
    }

    @Test
    @DisplayName("editar - Deve buscar produto por ID e retornar 'produto-form'")
    void deveAbrirFormularioEdicaoProduto() {
        // Arrange
        Produto produto = new Produto();
        produto.setIdProduto(1L);
        when(produtoService.buscarPorId(1L)).thenReturn(produto);

        // Act
        String view = controller.editar(1L, model);

        // Assert
        assertEquals("produto-form", view);
        verify(model, times(1)).addAttribute("produto", produto);
    }

    @Test
    @DisplayName("salvar - Deve salvar produto e redirecionar para '/produtos'")
    void deveSalvarProdutoERedirecionar() {
        // Arrange
        Produto produto = new Produto();

        // Act
        String redirect = controller.salvar(produto);

        // Assert
        assertEquals("redirect:/produtos", redirect);
        verify(produtoService, times(1)).salvar(produto);
    }

    @Test
    @DisplayName("excluir - Deve excluir produto e redirecionar para '/produtos'")
    void deveExcluirProdutoERedirecionar() {
        // Arrange
        Long id = 1L;

        // Act
        String redirect = controller.excluir(id);

        // Assert
        assertEquals("redirect:/produtos", redirect);
        verify(produtoService, times(1)).deletar(id);
    }

    @Test
    @DisplayName("buscarPorId - Deve buscar por ID quando informado e retornar view 'Produtos'")
    void deveBuscarProdutoPorIdNaTela() {
        // Arrange
        Produto produto = new Produto();
        produto.setIdProduto(1L);
        when(produtoService.buscarPorId(1L)).thenReturn(produto);

        // Act
        String view = controller.buscarPorId(1L, model);

        // Assert
        assertEquals("produtos", view);
        verify(model, times(1)).addAttribute("produtos", List.of(produto));
    }

    @Test
    @DisplayName("buscarPorId - Deve listar todos quando ID for nulo")
    void deveListarTodosQuandoIdNaBuscaForNulo() {
        // Arrange
        when(produtoService.listarTodos()).thenReturn(List.of());

        // Act
        String view = controller.buscarPorId(null, model);

        // Assert
        assertEquals("produtos", view);
        verify(produtoService, times(1)).listarTodos();
    }
}