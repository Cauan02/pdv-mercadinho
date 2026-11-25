package com.meupdv.pdv.controller;

import com.meupdv.pdv.lanche.CatalogoLanches;
import com.meupdv.pdv.lanche.Lanche;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LancheControllerTest {

    @Mock
    private CatalogoLanches catalogo;

    @Mock
    private Model model;

    @InjectMocks
    private LancheController controller;

    @Test
    @DisplayName("tela - Deve carregar lanches e adicionais no model e retornar view 'lanches'")
    void deveExibirTelaDeLanches() {
        // Arrange
        when(catalogo.lanches()).thenReturn(Map.of("X-Salada", new BigDecimal("15.00")));
        when(catalogo.adicionais()).thenReturn(Map.of("Bacon", new BigDecimal("4.00")));

        // Act
        String view = controller.tela(model);

        // Assert
        assertEquals("lanches", view);
        verify(model, times(1)).addAttribute(eq("lanches"), any());
        verify(model, times(1)).addAttribute(eq("adicionais"), any());
    }

    @Test
    @DisplayName("calcular - Cenário Positivo: Deve montar lanche e retornar descrição e total na view")
    void deveCalcularLancheComSucesso() {
        // Arrange
        Lanche lancheMock = mock(Lanche.class);
        when(lancheMock.getDescricao()).thenReturn("X-Salada, Bacon");
        when(lancheMock.custo()).thenReturn(new BigDecimal("19.00"));
        when(catalogo.montar("X-Salada", List.of("Bacon"))).thenReturn(lancheMock);

        // Act
        String view = controller.calcular("X-Salada", List.of("Bacon"), model);

        // Assert
        assertEquals("lanche-resultado", view);
        verify(model, times(1)).addAttribute("descricao", "X-Salada, Bacon");
        verify(model, times(1)).addAttribute("total", new BigDecimal("19.00"));
    }

    @Test
    @DisplayName("calcular - Cenário Negativo: Deve capturar exceção e adicionar mensagem de erro no model")
    void deveTratarErroQuandoLancheForInvalido() {
        // Arrange
        when(catalogo.montar("Inexistente", List.of()))
                .thenThrow(new IllegalArgumentException("Lanche não encontrado"));

        // Act
        String view = controller.calcular("Inexistente", null, model);

        // Assert
        assertEquals("lanche-resultado", view);
        verify(model, times(1)).addAttribute("erro", "Lanche não encontrado");
    }
}