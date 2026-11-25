package com.meupdv.pdv.controller;

import com.meupdv.pdv.entity.Produto;
import com.meupdv.pdv.service.ProdutoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoRestControllerTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoRestController controller;

    @Test
    @DisplayName("GET /api/produtos - Deve retornar status 200 OK com a lista de produtos")
    void deveListarTodosComStatus200() {
        // Arrange
        Produto p1 = new Produto();
        p1.setIdProduto(1L);
        p1.setNome("Refrigerante 2L");
        when(produtoService.listarTodos()).thenReturn(List.of(p1));

        // Act
        ResponseEntity<List<Produto>> response = controller.listarTodos();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    @DisplayName("GET /api/produtos/{id} - Cenário Positivo: Deve retornar 200 OK quando ID existir")
    void deveBuscarPorIdComStatus200() {
        // Arrange
        Produto p = new Produto();
        p.setIdProduto(1L);
        p.setNome("Água 500ml");
        when(produtoService.buscarPorId(1L)).thenReturn(p);

        // Act
        ResponseEntity<Produto> response = controller.buscarPorId(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Água 500ml", response.getBody().getNome());
    }

    @Test
    @DisplayName("GET /api/produtos/{id} - Cenário Negativo: Deve retornar 404 Not Found quando ID não existir")
    void deveRetornar404QuandoProdutoNaoExistir() {
        // Arrange
        when(produtoService.buscarPorId(99L)).thenReturn(null);

        // Act
        ResponseEntity<Produto> response = controller.buscarPorId(99L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    @DisplayName("POST /api/produtos - Deve retornar status 201 Created ao cadastrar produto")
    void deveCriarProdutoComStatus201() {
        // Arrange
        Produto novo = new Produto();
        novo.setNome("Suco 1L");
        novo.setPrecoVenda(new BigDecimal("7.50"));

        Produto salvo = new Produto();
        salvo.setIdProduto(10L);
        salvo.setNome("Suco 1L");
        salvo.setPrecoVenda(new BigDecimal("7.50"));

        when(produtoService.salvar(novo)).thenReturn(salvo);

        // Act
        ResponseEntity<Produto> response = controller.criar(novo);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(10L, response.getBody().getIdProduto());
    }

    @Test
    @DisplayName("DELETE /api/produtos/{id} - Cenário Positivo: Deve retornar 204 No Content ao excluir")
    void deveDeletarComStatus204() {
        // Arrange
        Produto existente = new Produto();
        existente.setIdProduto(1L);
        when(produtoService.buscarPorId(1L)).thenReturn(existente);

        // Act
        ResponseEntity<Void> response = controller.deletar(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(produtoService, times(1)).deletar(1L);
    }

    @Test
    @DisplayName("DELETE /api/produtos/{id} - Cenário Negativo: Deve retornar 404 Not Found se ID não existir")
    void deveRetornar404AoDeletarProdutoInexistente() {
        // Arrange
        when(produtoService.buscarPorId(99L)).thenReturn(null);

        // Act
        ResponseEntity<Void> response = controller.deletar(99L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(produtoService, never()).deletar(any());
    }
}