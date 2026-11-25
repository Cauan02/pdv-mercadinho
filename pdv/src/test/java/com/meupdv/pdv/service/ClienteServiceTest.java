package com.meupdv.pdv.service;

import com.meupdv.pdv.entity.Cliente;
import com.meupdv.pdv.repository.ClienteRepository;
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
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;



    @Test
    @DisplayName("listarTodos - Cenário Positivo: Deve retornar lista de clientes cadastrados")
    void deveListarTodosOsClientesComSucesso() {
        // Arrange
        Cliente c1 = new Cliente();
        c1.setIdCliente(1L);
        c1.setNome("Carlos Silva");
        c1.setTelefone("(11) 99999-1111");
        c1.setEmail("carlos@email.com");
        c1.setCpf("111.222.333-44");

        Cliente c2 = new Cliente();
        c2.setIdCliente(2L);
        c2.setNome("Ana Souza");
        c2.setTelefone("(11) 98888-2222");
        c2.setEmail("ana@email.com");
        c2.setCpf("555.666.777-88");

        when(clienteRepository.findAll()).thenReturn(List.of(c1, c2));

        // Act
        List<Cliente> resultado = clienteService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Carlos Silva", resultado.get(0).getNome());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("listarTodos - Cenário Negativo: Deve retornar lista vazia quando não houver clientes")
    void deveRetornarListaVaziaQuandoNaoHouverClientes() {
        // Arrange
        when(clienteRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Cliente> resultado = clienteService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(clienteRepository, times(1)).findAll();
    }



    @Test
    @DisplayName("buscarPorId - Cenário Positivo: Deve retornar o cliente quando o ID existir")
    void deveBuscarClientePorIdComSucesso() {
        // Arrange
        Long id = 1L;
        Cliente cliente = new Cliente();
        cliente.setIdCliente(id);
        cliente.setNome("Carlos Silva");

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        // Act
        Cliente resultado = clienteService.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getIdCliente());
        assertEquals("Carlos Silva", resultado.getNome());
        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("buscarPorId - Cenário Negativo: Deve retornar null quando o ID não existir")
    void deveRetornarNullQuandoClienteNaoForEncontrado() {
        // Arrange
        Long idInexistente = 99L;
        when(clienteRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act
        Cliente resultado = clienteService.buscarPorId(idInexistente);

        // Assert
        assertNull(resultado);
        verify(clienteRepository, times(1)).findById(idInexistente);
    }



    @Test
    @DisplayName("salvar - Cenário Positivo: Deve salvar e retornar o cliente com sucesso")
    void deveSalvarClienteComSucesso() {
        // Arrange
        Cliente clienteParaSalvar = new Cliente();
        clienteParaSalvar.setNome("Mariana Lima");
        clienteParaSalvar.setTelefone("(11) 97777-3333");
        clienteParaSalvar.setEmail("mariana@email.com");
        clienteParaSalvar.setCpf("999.888.777-66");

        Cliente clienteSalvo = new Cliente();
        clienteSalvo.setIdCliente(1L);
        clienteSalvo.setNome("Mariana Lima");
        clienteSalvo.setTelefone("(11) 97777-3333");
        clienteSalvo.setEmail("mariana@email.com");
        clienteSalvo.setCpf("999.888.777-66");

        when(clienteRepository.save(clienteParaSalvar)).thenReturn(clienteSalvo);

        // Act
        Cliente resultado = clienteService.salvar(clienteParaSalvar);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdCliente());
        assertEquals("Mariana Lima", resultado.getNome());
        verify(clienteRepository, times(1)).save(clienteParaSalvar);
    }

    @Test
    @DisplayName("salvar - Cenário Negativo: Deve propagar exceção quando repositório falhar ao salvar")
    void deveLancarExcecaoQuandoFalharAoSalvarCliente() {
        // Arrange
        Cliente clienteInvalido = new Cliente();
        when(clienteRepository.save(clienteInvalido))
                .thenThrow(new IllegalArgumentException("Erro ao persistir cliente"));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clienteService.salvar(clienteInvalido));
        verify(clienteRepository, times(1)).save(clienteInvalido);
    }


    @Test
    @DisplayName("deletar - Cenário Positivo: Deve chamar deleteById com sucesso quando ID existir")
    void deveDeletarClienteComSucesso() {
        // Arrange
        Long id = 1L;
        doNothing().when(clienteRepository).deleteById(id);

        // Act
        clienteService.deletar(id);

        // Assert
        verify(clienteRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("deletar - Cenário Negativo: Deve lançar exceção ao tentar deletar ID inexistente")
    void deveLancarExcecaoQuandoFalharAoDeletarCliente() {
        // Arrange
        Long idInexistente = 999L;
        doThrow(new RuntimeException("Cliente não encontrado para exclusão"))
                .when(clienteRepository).deleteById(idInexistente);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> clienteService.deletar(idInexistente));
        verify(clienteRepository, times(1)).deleteById(idInexistente);
    }
}

