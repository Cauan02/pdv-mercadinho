package com.meupdv.pdv.service;

import com.meupdv.pdv.config.TentativasDeLogin;
import com.meupdv.pdv.entity.Usuario;
import com.meupdv.pdv.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutenticacaoServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TentativasDeLogin tentativas;

    @InjectMocks
    private AutenticacaoService autenticacaoService;



    @Test
    @DisplayName("Cenário Positivo - Deve carregar UserDetails com sucesso quando o usuário existir")
    void deveCarregarUsuarioQuandoLoginExistir() {
        // Arrange
        String login = "admin";
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setLogin(login);
        usuario.setSenha("$2a$10$hashSenhaExemplo");
        usuario.setPerfil("ADMIN");

        when(usuarioRepository.findByLogin(login)).thenReturn(Optional.of(usuario));
        when(tentativas.estaBloqueado(login)).thenReturn(false);

        // Act
        UserDetails userDetails = autenticacaoService.loadUserByUsername(login);

        // Assert
        assertNotNull(userDetails);
        assertEquals(login, userDetails.getUsername());
        assertEquals("$2a$10$hashSenhaExemplo", userDetails.getPassword());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
        verify(usuarioRepository, times(1)).findByLogin(login);
    }

    @Test
    @DisplayName("Cenário Negativo - Deve lançar UsernameNotFoundException quando o login não existir")
    void deveLancarExcecaoQuandoUsuarioNaoForEncontrado() {
        // Arrange
        String loginInexistente = "usuario_inexistente";
        when(usuarioRepository.findByLogin(loginInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> autenticacaoService.loadUserByUsername(loginInexistente)
        );

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(usuarioRepository, times(1)).findByLogin(loginInexistente);
        verifyNoInteractions(tentativas);
    }
}

