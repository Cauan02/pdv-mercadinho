package com.meupdv.pdv.controller;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private HttpSession sessao;

    @Mock
    private Model model;

    private final LoginController controller = new LoginController();

    @Test
    @DisplayName("login - Deve retornar view 'login' e exibir tentativas restantes quando houver")
    void deveExibirLoginComTentativasRestantes() {
        // Arrange
        when(sessao.getAttribute("loginRestantes")).thenReturn(2);
        when(sessao.getAttribute("loginBloqueadoAte")).thenReturn(null);

        // Act
        String view = controller.login(sessao, model);

        // Assert
        assertEquals("login", view);
        verify(sessao, times(1)).removeAttribute("loginRestantes");
        verify(model, times(1)).addAttribute("restantes", 2);
    }

    @Test
    @DisplayName("login - Deve manter bloqueio ativo quando tempo futuro ainda não expirou")
    void deveManterBloqueioQuandoAindaEstiverNoPrazo() {
        // Arrange
        long tempoFuturo = System.currentTimeMillis() + 60_000L;
        when(sessao.getAttribute("loginRestantes")).thenReturn(null);
        when(sessao.getAttribute("loginBloqueadoAte")).thenReturn(tempoFuturo);

        // Act
        String view = controller.login(sessao, model);

        // Assert
        assertEquals("login", view);
        verify(model, times(1)).addAttribute("bloqueadoAte", tempoFuturo);
        verify(sessao, never()).removeAttribute("loginBloqueadoAte");
    }
}
