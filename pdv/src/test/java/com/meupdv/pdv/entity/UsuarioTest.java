package com.meupdv.pdv.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
    }


    @Test
    @DisplayName("1. id - Deve atribuir e retornar um ID válido")
    void deveAtribuirERetornarIdValido() {
        // Arrange
        Long idEsperado = 1L;

        // Act
        usuario.setId(idEsperado);

        // Assert
        assertEquals(idEsperado, usuario.getId());
    }

    @Test
    @DisplayName("2. id - Deve iniciar como nulo ao instanciar usuário")
    void deveIniciarIdNulo() {
        // Arrange & Act
        Usuario novoUsuario = new Usuario();

        // Assert
        assertNull(novoUsuario.getId());
    }

    @Test
    @DisplayName("3. id - Deve lançar exceção para ID zero ou negativo")
    void deveLancarExcecaoParaIdInvalido() {
        // Arrange
        Long idInvalido = 0L;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> usuario.setId(idInvalido));
    }



    @Test
    @DisplayName("1. login - Deve atribuir e retornar um login válido")
    void deveAtribuirERetornarLoginValido() {
        // Arrange
        String loginEsperado = "admin";

        // Act
        usuario.setLogin(loginEsperado);

        // Assert
        assertEquals(loginEsperado, usuario.getLogin());
    }

    @Test
    @DisplayName("2. login - Deve lançar exceção quando login for nulo")
    void deveLancarExcecaoQuandoLoginForNulo() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> usuario.setLogin(null));
    }

    @Test
    @DisplayName("3. login - Deve lançar exceção quando login estiver em branco")
    void deveLancarExcecaoQuandoLoginForVazio() {
        // Arrange
        String loginVazio = "   ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> usuario.setLogin(loginVazio));
    }



    @Test
    @DisplayName("1. senha - Deve atribuir e retornar uma senha válida")
    void deveAtribuirERetornarSenhaValida() {
        // Arrange
        String senhaEsperada = "$2a$10$hashExemploBcrypt";

        // Act
        usuario.setSenha(senhaEsperada);

        // Assert
        assertEquals(senhaEsperada, usuario.getSenha());
    }

    @Test
    @DisplayName("2. senha - Deve lançar exceção quando senha for nula")
    void deveLancarExcecaoQuandoSenhaForNula() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> usuario.setSenha(null));
    }

    @Test
    @DisplayName("3. senha - Deve lançar exceção quando senha estiver em branco")
    void deveLancarExcecaoQuandoSenhaForVazia() {
        // Arrange
        String senhaVazia = "   ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> usuario.setSenha(senhaVazia));
    }



    @Test
    @DisplayName("1. perfil - Deve atribuir e retornar um perfil válido")
    void deveAtribuirERetornarPerfilValido() {
        // Arrange
        String perfilEsperado = "ADMIN";

        // Act
        usuario.setPerfil(perfilEsperado);

        // Assert
        assertEquals(perfilEsperado, usuario.getPerfil());
    }

    @Test
    @DisplayName("2. perfil - Deve lançar exceção quando perfil for nulo")
    void deveLancarExcecaoQuandoPerfilForNulo() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> usuario.setPerfil(null));
    }

    @Test
    @DisplayName("3. perfil - Deve lançar exceção quando perfil estiver em branco")
    void deveLancarExcecaoQuandoPerfilForVazio() {
        // Arrange
        String perfilVazio = "  ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> usuario.setPerfil(perfilVazio));
    }
}
