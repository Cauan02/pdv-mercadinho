package com.meupdv.pdv.controller;

import com.meupdv.pdv.entity.Categoria;
import com.meupdv.pdv.service.CategoriaService;
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
class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    @Mock
    private Model model;

    @InjectMocks
    private CategoriaController controller;

    @Test
    @DisplayName("listar - Deve adicionar lista de categorias no model e retornar view 'categorias'")
    void deveListarCategoriasComSucesso() {
        // Arrange
        when(categoriaService.listarTodos()).thenReturn(List.of(new Categoria()));

        // Act
        String view = controller.listar(model);

        // Assert
        assertEquals("categorias", view);
        verify(model, times(1)).addAttribute(eq("categorias"), any());
        verify(categoriaService, times(1)).listarTodos();
    }

    @Test
    @DisplayName("novo - Deve adicionar nova Categoria no model e retornar view 'categoria-form'")
    void deveAbrirFormularioDeNovaCategoria() {
        // Arrange & Act
        String view = controller.novo(model);

        // Assert
        assertEquals("categoria-form", view);
        verify(model, times(1)).addAttribute(eq("categoria"), any(Categoria.class));
    }

    @Test
    @DisplayName("salvar - Deve salvar categoria e redirecionar para '/categorias'")
    void deveSalvarCategoriaERedirecionar() {
        // Arrange
        Categoria categoria = new Categoria();
        categoria.setNome("Bebidas");

        // Act
        String redirect = controller.salvar(categoria);

        // Assert
        assertEquals("redirect:/categorias", redirect);
        verify(categoriaService, times(1)).salvar(categoria);
    }
}