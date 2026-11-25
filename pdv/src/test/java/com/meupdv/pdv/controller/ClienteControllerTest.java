package com.meupdv.pdv.controller;

import com.meupdv.pdv.entity.Cliente;
import com.meupdv.pdv.service.ClienteService;
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
class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @Mock
    private Model model;

    @InjectMocks
    private ClienteController controller;

    @Test
    @DisplayName("listar - Deve adicionar lista de clientes no model e retornar view 'clientes'")
    void deveListarClientesComSucesso() {
        // Arrange
        when(clienteService.listarTodos()).thenReturn(List.of(new Cliente()));

        // Act
        String view = controller.listar(model);

        // Assert
        assertEquals("clientes", view);
        verify(model, times(1)).addAttribute(eq("clientes"), any());
    }

    @Test
    @DisplayName("novo - Deve adicionar novo Cliente no model e retornar view 'cliente-form'")
    void deveAbrirFormularioNovoCliente() {
        // Arrange & Act
        String view = controller.novo(model);

        // Assert
        assertEquals("cliente-form", view);
        verify(model, times(1)).addAttribute(eq("cliente"), any(Cliente.class));
    }

    @Test
    @DisplayName("editar - Deve buscar cliente por ID e retornar view 'cliente-form'")
    void deveAbrirFormularioEdicaoCliente() {
        // Arrange
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        when(clienteService.buscarPorId(1L)).thenReturn(cliente);

        // Act
        String view = controller.editar(1L, model);

        // Assert
        assertEquals("cliente-form", view);
        verify(model, times(1)).addAttribute("cliente", cliente);
    }

    @Test
    @DisplayName("salvar - Deve salvar cliente e redirecionar para '/clientes'")
    void deveSalvarClienteERedirecionar() {
        // Arrange
        Cliente cliente = new Cliente();

        // Act
        String redirect = controller.salvar(cliente);

        // Assert
        assertEquals("redirect:/clientes", redirect);
        verify(clienteService, times(1)).salvar(cliente);
    }

    @Test
    @DisplayName("excluir - Deve deletar cliente por ID e redirecionar para '/clientes'")
    void deveExcluirClienteERedirecionar() {
        // Arrange
        Long id = 1L;

        // Act
        String redirect = controller.excluir(id);

        // Assert
        assertEquals("redirect:/clientes", redirect);
        verify(clienteService, times(1)).deletar(id);
    }
}
