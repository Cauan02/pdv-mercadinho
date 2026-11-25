package com.meupdv.pdv.controller;

import com.meupdv.pdv.entity.Cliente;
import com.meupdv.pdv.entity.Venda;
import com.meupdv.pdv.service.ClienteService;
import com.meupdv.pdv.service.ProdutoService;
import com.meupdv.pdv.service.VendaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VendaControllerTest {

    @Mock
    private VendaService vendaService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private VendaController controller;

    @Test
    @DisplayName("nova - Deve carregar clientes e produtos no model e retornar view 'pdv'")
    void deveAbrirTelaDeNovaVenda() {
        // Arrange
        when(clienteService.listarTodos()).thenReturn(List.of());
        when(produtoService.listarTodos()).thenReturn(List.of());

        // Act
        String view = controller.nova(model);

        // Assert
        assertEquals("pdv", view);
        verify(model, times(1)).addAttribute(eq("clientes"), any());
        verify(model, times(1)).addAttribute(eq("produtos"), any());
    }

    @Test
    @DisplayName("finalizar - Cenário Positivo: Deve finalizar venda e redirecionar para cupom")
    void deveFinalizarVendaComSucesso() {
        // Arrange
        Venda vendaRecebida = new Venda();
        Cliente clienteSemId = new Cliente();
        vendaRecebida.setCliente(clienteSemId);

        Venda vendaSalva = new Venda();
        vendaSalva.setIdVenda(10L);

        when(vendaService.finalizarVenda(vendaRecebida)).thenReturn(vendaSalva);

        // Act
        String redirect = controller.finalizar(vendaRecebida, redirectAttributes);

        // Assert
        assertEquals("redirect:/vendas/10/cupom", redirect);
        assertNull(vendaRecebida.getCliente());
        verify(vendaService, times(1)).finalizarVenda(vendaRecebida);
    }

    @Test
    @DisplayName("finalizar - Cenário Negativo: Deve redirecionar para '/vendas/nova' com erro se falhar")
    void deveRedirecionarComErroQuandoFinalizarFalhar() {
        // Arrange
        Venda vendaRecebida = new Venda();
        when(vendaService.finalizarVenda(vendaRecebida))
                .thenThrow(new RuntimeException("Estoque insuficiente"));

        // Act
        String redirect = controller.finalizar(vendaRecebida, redirectAttributes);

        // Assert
        assertEquals("redirect:/vendas/nova", redirect);
        verify(redirectAttributes, times(1)).addFlashAttribute("erro", "Estoque insuficiente");
    }

    @Test
    @DisplayName("cupom - Deve buscar venda por ID e retornar view 'cupom'")
    void deveExibirCupomDaVenda() {
        // Arrange
        Venda venda = new Venda();
        venda.setIdVenda(5L);
        when(vendaService.buscarPorId(5L)).thenReturn(venda);

        // Act
        String view = controller.cupom(5L, model);

        // Assert
        assertEquals("cupom", view);
        verify(model, times(1)).addAttribute("venda", venda);
    }

    @Test
    @DisplayName("listar - Deve listar vendas no model e retornar view 'vendas'")
    void deveListarTodasAsVendas() {
        // Arrange
        when(vendaService.listarTodos()).thenReturn(List.of());

        // Act
        String view = controller.listar(model);

        // Assert
        assertEquals("vendas", view);
        verify(model, times(1)).addAttribute(eq("vendas"), any());
    }

    @Test
    @DisplayName("cancelar - Cenário Positivo: Deve cancelar venda e redirecionar com mensagem de sucesso")
    void deveCancelarVendaComSucesso() {
        // Arrange
        Long id = 1L;

        // Act
        String redirect = controller.cancelar(id, redirectAttributes);

        // Assert
        assertEquals("redirect:/vendas", redirect);
        verify(vendaService, times(1)).cancelarVenda(id);
        verify(redirectAttributes, times(1)).addFlashAttribute("sucesso", "Venda cancelada e estoque devolvido.");
    }

    @Test
    @DisplayName("cancelar - Cenário Negativo: Deve capturar erro e adicionar flash attribute ao falhar")
    void deveTratarErroAoCancelarVenda() {
        // Arrange
        Long id = 99L;
        doThrow(new RuntimeException("Venda não encontrada")).when(vendaService).cancelarVenda(id);

        // Act
        String redirect = controller.cancelar(id, redirectAttributes);

        // Assert
        assertEquals("redirect:/vendas", redirect);
        verify(redirectAttributes, times(1)).addFlashAttribute("erro", "Venda não encontrada");
    }
}