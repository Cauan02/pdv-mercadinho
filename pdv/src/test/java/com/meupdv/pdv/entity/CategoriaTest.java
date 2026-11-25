package com.meupdv.pdv.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoriaTest {


    @Test
    @DisplayName("1. idCategoria - Deve atribuir e retornar um ID válido com sucesso")
    void deveAtribuirERetornarIdCategoriaValido() {
        // Arrange
        Categoria categoria = new Categoria();
        Long idEsperado = 1L;

        // Act
        categoria.setIdCategoria(idEsperado);

        // Assert
        assertEquals(idEsperado, categoria.getIdCategoria());
    }

    @Test
    @DisplayName("2. idCategoria - Deve iniciar como nulo ao instanciar uma nova categoria")
    void deveIniciarIdCategoriaComoNulo() {
        // Arrange & Act
        Categoria categoria = new Categoria();

        // Assert
        assertNull(categoria.getIdCategoria());
    }

    @Test
    @DisplayName("3. idCategoria - Deve permitir atualizar o ID para um novo valor positivo")
    void deveAtualizarIdCategoriaParaNovoValor() {
        // Arrange
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1L);

        // Act
        categoria.setIdCategoria(2L);

        // Assert
        assertEquals(2L, categoria.getIdCategoria());
    }


    @Test
    @DisplayName("1. nome - Deve atribuir e retornar um nome válido com sucesso")
    void deveAtribuirERetornarNomeValido() {
        // Arrange
        Categoria categoria = new Categoria();
        String nomeEsperado = "Bebidas";

        // Act
        categoria.setNome(nomeEsperado);

        // Assert
        assertEquals(nomeEsperado, categoria.getNome());
    }

    @Test
    @DisplayName("2. nome - Deve lançar exceção ao tentar atribuir nome nulo")
    void deveLancarExcecaoQuandoNomeForNulo() {
        // Arrange
        Categoria categoria = new Categoria();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoria.setNome(null);
        });
    }

    @Test
    @DisplayName("3. nome - Deve lançar exceção ao tentar atribuir nome vazio ou apenas espaços")
    void deveLancarExcecaoQuandoNomeForVazio() {
        // Arrange
        Categoria categoria = new Categoria();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            categoria.setNome("   ");
        });
    }
}