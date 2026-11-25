package com.meupdv.pdv.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    @DisplayName("home - Deve retornar a view 'home'")
    void deveRetornarViewHome() {
        // Arrange
        HomeController controller = new HomeController();

        // Act
        String view = controller.home();

        // Assert
        assertEquals("home", view);
    }
}
