package com.meupdv.pdv.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
    }


    @Test
    @DisplayName("1. idCliente - Deve atribuir e retornar um ID válido")
    void deveAtribuirERetornarIdClienteValido() {
        // Arrange
        Long idEsperado = 1L;

        // Act
        cliente.setIdCliente(idEsperado);

        // Assert
        assertEquals(idEsperado, cliente.getIdCliente());
    }

    @Test
    @DisplayName("2. idCliente - Deve iniciar como nulo ao instanciar cliente")
    void deveIniciarIdClienteComoNulo() {
        // Arrange & Act
        Cliente novoCliente = new Cliente();

        // Assert
        assertNull(novoCliente.getIdCliente());
    }

    @Test
    @DisplayName("3. idCliente - Deve lançar exceção para ID zero ou negativo")
    void deveLancarExcecaoParaIdClienteInvalido() {
        // Arrange
        Long idInvalido = 0L;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cliente.setIdCliente(idInvalido));
    }


    @Test
    @DisplayName("1. nome - Deve atribuir e retornar um nome válido")
    void deveAtribuirERetornarNomeValido() {
        // Arrange
        String nomeEsperado = "Carlos Silva";

        // Act
        cliente.setNome(nomeEsperado);

        // Assert
        assertEquals(nomeEsperado, cliente.getNome());
    }

    @Test
    @DisplayName("2. nome - Deve lançar exceção quando nome for nulo")
    void deveLancarExcecaoQuandoNomeForNulo() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cliente.setNome(null));
    }

    @Test
    @DisplayName("3. nome - Deve lançar exceção quando nome estiver em branco")
    void deveLancarExcecaoQuandoNomeForVazio() {
        // Arrange
        String nomeVazio = "   ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cliente.setNome(nomeVazio));
    }



    @Test
    @DisplayName("1. telefone - Deve atribuir e retornar um telefone válido")
    void deveAtribuirERetornarTelefoneValido() {
        // Arrange
        String telefoneEsperado = "(11) 99999-8888";

        // Act
        cliente.setTelefone(telefoneEsperado);

        // Assert
        assertEquals(telefoneEsperado, cliente.getTelefone());
    }

    @Test
    @DisplayName("2. telefone - Deve lançar exceção quando telefone for nulo")
    void deveLancarExcecaoQuandoTelefoneForNulo() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cliente.setTelefone(null));
    }

    @Test
    @DisplayName("3. telefone - Deve lançar exceção quando telefone estiver em branco")
    void deveLancarExcecaoQuandoTelefoneForVazio() {
        // Arrange
        String telefoneVazio = "   ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cliente.setTelefone(telefoneVazio));
    }



    @Test
    @DisplayName("1. email - Deve atribuir e retornar um e-mail válido")
    void deveAtribuirERetornarEmailValido() {
        // Arrange
        String emailEsperado = "carlos@email.com";

        // Act
        cliente.setEmail(emailEsperado);

        // Assert
        assertEquals(emailEsperado, cliente.getEmail());
    }

    @Test
    @DisplayName("2. email - Deve lançar exceção quando e-mail for nulo")
    void deveLancarExcecaoQuandoEmailForNulo() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cliente.setEmail(null));
    }

    @Test
    @DisplayName("3. email - Deve lançar exceção quando e-mail não contiver '@'")
    void deveLancarExcecaoQuandoEmailForInvalido() {
        // Arrange
        String emailInvalido = "carlosemail.com";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cliente.setEmail(emailInvalido));
    }



    @Test
    @DisplayName("1. cpf - Deve atribuir e retornar um CPF válido com 11 dígitos")
    void deveAtribuirERetornarCpfValido() {
        // Arrange
        String cpfEsperado = "123.456.789-00";

        // Act
        cliente.setCpf(cpfEsperado);

        // Assert
        assertEquals(cpfEsperado, cliente.getCpf());
    }

    @Test
    @DisplayName("2. cpf - Deve lançar exceção quando CPF for nulo")
    void deveLancarExcecaoQuandoCpfForNulo() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cliente.setCpf(null));
    }

    @Test
    @DisplayName("3. cpf - Deve lançar exceção quando CPF tiver menos de 11 dígitos")
    void deveLancarExcecaoQuandoCpfForInvalido() {
        // Arrange
        String cpfCurto = "123.456";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> cliente.setCpf(cpfCurto));
    }
}
