package com.meupdv.pdv.service;

import com.meupdv.pdv.entity.Categoria;
import com.meupdv.pdv.repository.CategoriaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;


    @Test
    @DisplayName("listarTodos - Cenário Positivo: Deve retornar lista de categorias cadastradas")
    void deveListarTodasAsCategoriasComSucesso() {
        // Arrange
        Categoria cat1 = new Categoria();
        cat1.setIdCategoria(1L);
        cat1.setNome("Bebidas");

        Categoria cat2 = new Categoria();
        cat2.setIdCategoria(2L);
        cat2.setNome("Lanches");

        when(categoriaRepository.findAll()).thenReturn(List.of(cat1, cat2));

        // Act
        List<Categoria> resultado = categoriaService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Bebidas", resultado.get(0).getNome());
        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("listarTodos - Cenário Negativo: Deve retornar lista vazia quando não houver categorias")
    void deveRetornarListaVaziaQuandoNaoHouverCategorias() {
        // Arrange
        when(categoriaRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Categoria> resultado = categoriaService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(categoriaRepository, times(1)).findAll();
    }



    @Test
    @DisplayName("buscarPorId - Cenário Positivo: Deve retornar a categoria quando o ID existir")
    void deveBuscarCategoriaPorIdComSucesso() {
        // Arrange
        Long id = 1L;
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(id);
        categoria.setNome("Sobremesas");

        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoria));

        // Act
        Categoria resultado = categoriaService.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getIdCategoria());
        assertEquals("Sobremesas", resultado.getNome());
        verify(categoriaRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("buscarPorId - Cenário Negativo: Deve retornar null quando o ID não existir")
    void deveRetornarNullQuandoCategoriaNaoForEncontrada() {
        // Arrange
        Long idInexistente = 99L;
        when(categoriaRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act
        Categoria resultado = categoriaService.buscarPorId(idInexistente);

        // Assert
        assertNull(resultado);
        verify(categoriaRepository, times(1)).findById(idInexistente);
    }



    @Test
    @DisplayName("salvar - Cenário Positivo: Deve salvar e retornar a categoria com sucesso")
    void deveSalvarCategoriaComSucesso() {
        // Arrange
        Categoria categoriaParaSalvar = new Categoria();
        categoriaParaSalvar.setNome("Salgados");

        Categoria categoriaSalva = new Categoria();
        categoriaSalva.setIdCategoria(1L);
        categoriaSalva.setNome("Salgados");

        when(categoriaRepository.save(categoriaParaSalvar)).thenReturn(categoriaSalva);

        // Act
        Categoria resultado = categoriaService.salvar(categoriaParaSalvar);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdCategoria());
        assertEquals("Salgados", resultado.getNome());
        verify(categoriaRepository, times(1)).save(categoriaParaSalvar);
    }

    @Test
    @DisplayName("salvar - Cenário Negativo: Deve propagar exceção quando repositório falhar ao salvar")
    void deveLancarExcecaoQuandoFalharAoSalvarCategoria() {
        // Arrange
        Categoria categoriaInvalida = new Categoria();
        when(categoriaRepository.save(categoriaInvalida))
                .thenThrow(new IllegalArgumentException("Erro ao persistir categoria"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> categoriaService.salvar(categoriaInvalida));
        verify(categoriaRepository, times(1)).save(categoriaInvalida);
    }



    @Test
    @DisplayName("deletar - Cenário Positivo: Deve chamar deleteById com sucesso quando ID existir")
    void deveDeletarCategoriaComSucesso() {
        // Arrange
        Long id = 1L;
        doNothing().when(categoriaRepository).deleteById(id);

        // Act
        categoriaService.deletar(id);

        // Assert
        verify(categoriaRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("deletar - Cenário Negativo: Deve lançar exceção ao tentar deletar ID inválido ou inexistente")
    void deveLancarExcecaoQuandoFalharAoDeletarCategoria() {
        // Arrange
        Long idInexistente = 999L;
        doThrow(new RuntimeException("Categoria não encontrada para exclusão"))
                .when(categoriaRepository).deleteById(idInexistente);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> categoriaService.deletar(idInexistente));
        verify(categoriaRepository, times(1)).deleteById(idInexistente);
    }
}
