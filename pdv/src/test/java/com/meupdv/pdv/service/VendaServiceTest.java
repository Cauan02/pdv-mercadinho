package com.meupdv.pdv.service;

import com.meupdv.pdv.entity.*;
import com.meupdv.pdv.historico.HistoricoVendas;
import com.meupdv.pdv.lanche.CatalogoLanches;
import com.meupdv.pdv.lanche.Lanche;
import com.meupdv.pdv.repository.CupomRepository;
import com.meupdv.pdv.repository.ProdutoRepository;
import com.meupdv.pdv.repository.VendaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VendaServiceTest {

    @Mock
    private VendaRepository vendaRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private CupomRepository cupomRepository;

    @Mock
    private HistoricoVendas historicoVendas;

    @Mock
    private CatalogoLanches catalogo;

    @InjectMocks
    private VendaService vendaService;



    @Test
    @DisplayName("listarTodos - Cenário Positivo: Deve retornar lista de vendas realizadas")
    void deveListarTodasAsVendasComSucesso() {
        // Arrange
        Venda v1 = new Venda();
        v1.setIdVenda(1L);
        v1.setFormaPagamento("PIX");

        Venda v2 = new Venda();
        v2.setIdVenda(2L);
        v2.setFormaPagamento("DINHEIRO");

        when(vendaRepository.findAll()).thenReturn(List.of(v1, v2));

        // Act
        List<Venda> resultado = vendaService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(vendaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("listarTodos - Cenário Negativo: Deve retornar lista vazia quando não houver vendas")
    void deveRetornarListaVaziaQuandoNaoHouverVendas() {
        // Arrange
        when(vendaRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Venda> resultado = vendaService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(vendaRepository, times(1)).findAll();
    }


    @Test
    @DisplayName("buscarPorId - Cenário Positivo: Deve retornar a venda quando o ID existir")
    void deveBuscarVendaPorIdComSucesso() {
        // Arrange
        Long id = 1L;
        Venda venda = new Venda();
        venda.setIdVenda(id);
        venda.setFormaPagamento("CARTAO_DEBITO");

        when(vendaRepository.findById(id)).thenReturn(Optional.of(venda));

        // Act
        Venda resultado = vendaService.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getIdVenda());
        verify(vendaRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("buscarPorId - Cenário Negativo: Deve retornar null quando a venda não existir")
    void deveRetornarNullQuandoVendaNaoForEncontrada() {
        // Arrange
        Long idInexistente = 99L;
        when(vendaRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act
        Venda resultado = vendaService.buscarPorId(idInexistente);

        // Assert
        assertNull(resultado);
        verify(vendaRepository, times(1)).findById(idInexistente);
    }


    @Test
    @DisplayName("finalizarVenda - Cenário Positivo (Produto): Deve finalizar venda, baixar estoque e gerar cupom")
    void deveFinalizarVendaDeProdutoComSucesso() {
        // Arrange
        Produto produto = new Produto();
        produto.setIdProduto(1L);
        produto.setNome("Refrigerante 2L");
        produto.setPrecoVenda(new BigDecimal("10.00"));
        produto.setQuantidadeEstoque(20);

        ItemVenda itemRecebido = new ItemVenda();
        itemRecebido.setProduto(produto);
        itemRecebido.setQuantidade(2);

        Venda vendaRecebida = new Venda();
        vendaRecebida.setFormaPagamento("PIX");
        vendaRecebida.setItens(List.of(itemRecebido));

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(vendaRepository.save(any(Venda.class))).thenAnswer(invocation -> {
            Venda v = invocation.getArgument(0);
            v.setIdVenda(10L);
            return v;
        });

        // Act
        Venda vendaSalva = vendaService.finalizarVenda(vendaRecebida);

        // Assert
        assertNotNull(vendaSalva);
        assertEquals(10L, vendaSalva.getIdVenda());
        assertEquals(18, produto.getQuantidadeEstoque()); // Baixou de 20 para 18
        verify(produtoRepository, times(1)).save(produto);
        verify(cupomRepository, times(1)).save(any(Cupom.class));
        verify(historicoVendas, times(1)).registrar(vendaSalva);
    }

    @Test
    @DisplayName("finalizarVenda - Cenário Positivo (Lanche Decorator): Deve montar lanche pelo catálogo e finalizar venda")
    void deveFinalizarVendaDeLancheComSucesso() {
        // Arrange
        Lanche lancheMock = mock(Lanche.class);
        when(lancheMock.getDescricao()).thenReturn("X-Salada + Bacon");
        when(lancheMock.custo()).thenReturn(new BigDecimal("25.00"));

        when(catalogo.montar(eq("X-Salada"), eq(List.of("Bacon")))).thenReturn(lancheMock);
        when(vendaRepository.save(any(Venda.class))).thenAnswer(invocation -> {
            Venda v = invocation.getArgument(0);
            v.setIdVenda(11L);
            return v;
        });

        ItemVenda itemLanche = new ItemVenda();
        itemLanche.setLanche("X-Salada");
        itemLanche.setAdicionais("Bacon");
        itemLanche.setQuantidade(1);

        Venda vendaRecebida = new Venda();
        vendaRecebida.setFormaPagamento("DINHEIRO");
        vendaRecebida.setItens(List.of(itemLanche));

        // Act
        Venda vendaSalva = vendaService.finalizarVenda(vendaRecebida);

        // Assert
        assertNotNull(vendaSalva);
        assertEquals(11L, vendaSalva.getIdVenda());
        assertEquals("X-Salada + Bacon", vendaSalva.getItens().get(0).getDescricao());
        verify(cupomRepository, times(1)).save(any(Cupom.class));
        verify(historicoVendas, times(1)).registrar(vendaSalva);
    }

    @Test
    @DisplayName("finalizarVenda - Cenário Negativo: Deve lançar exceção quando estoque for insuficiente")
    void deveLancarExcecaoQuandoEstoqueForInsuficiente() {
        // Arrange
        Produto produto = new Produto();
        produto.setIdProduto(1L);
        produto.setNome("Suco Natural");
        produto.setPrecoVenda(new BigDecimal("8.00"));
        produto.setQuantidadeEstoque(1);

        ItemVenda itemRecebido = new ItemVenda();
        itemRecebido.setProduto(produto);
        itemRecebido.setQuantidade(5); // Pede 5, mas só tem 1

        Venda vendaRecebida = new Venda();
        vendaRecebida.setFormaPagamento("PIX");
        vendaRecebida.setItens(List.of(itemRecebido));

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        // Act & Assert
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> vendaService.finalizarVenda(vendaRecebida)
        );

        assertTrue(ex.getMessage().contains("Estoque insuficiente"));
        verify(vendaRepository, never()).save(any());
        verify(cupomRepository, never()).save(any());
    }

    @Test
    @DisplayName("finalizarVenda - Cenário Negativo: Deve lançar exceção quando produto não for encontrado")
    void deveLancarExcecaoQuandoProdutoNaoExistirNaVenda() {
        // Arrange
        Produto produtoInexistente = new Produto();
        produtoInexistente.setIdProduto(999L);

        ItemVenda itemRecebido = new ItemVenda();
        itemRecebido.setProduto(produtoInexistente);
        itemRecebido.setQuantidade(1);

        Venda vendaRecebida = new Venda();
        vendaRecebida.setFormaPagamento("PIX");
        vendaRecebida.setItens(List.of(itemRecebido));

        when(produtoRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> vendaService.finalizarVenda(vendaRecebida)
        );

        assertEquals("Produto não encontrado", ex.getMessage());
        verify(vendaRepository, never()).save(any());
    }



    @Test
    @DisplayName("cancelarVenda - Cenário Positivo: Deve devolver estoque, remover cupom e excluir venda")
    void deveCancelarVendaComSucesso() {
        // Arrange
        Long idVenda = 1L;
        Produto produto = new Produto();
        produto.setIdProduto(10L);
        produto.setQuantidadeEstoque(5);

        ItemVenda item = new ItemVenda();
        item.setProduto(produto);
        item.setQuantidade(3);

        Venda venda = new Venda();
        venda.setIdVenda(idVenda);
        venda.setItens(List.of(item));

        Cupom cupom = new Cupom();
        cupom.setIdCupom(50L);

        when(vendaRepository.findById(idVenda)).thenReturn(Optional.of(venda));
        when(cupomRepository.findByVenda(venda)).thenReturn(cupom);

        // Act
        vendaService.cancelarVenda(idVenda);

        // Assert
        assertEquals(8, produto.getQuantidadeEstoque()); // Devolveu 3 para o estoque de 5
        verify(produtoRepository, times(1)).save(produto);
        verify(cupomRepository, times(1)).delete(cupom);
        verify(historicoVendas, times(1)).remover(idVenda);
        verify(vendaRepository, times(1)).delete(venda);
    }

    @Test
    @DisplayName("cancelarVenda - Cenário Negativo: Deve lançar exceção ao tentar cancelar venda inexistente")
    void deveLancarExcecaoAoCancelarVendaInexistente() {
        // Arrange
        Long idInexistente = 404L;
        when(vendaRepository.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> vendaService.cancelarVenda(idInexistente)
        );

        assertEquals("Venda não encontrada", ex.getMessage());
        verify(vendaRepository, never()).delete(any());
    }



    @Test
    @DisplayName("deletar - Cenário Positivo: Deve excluir venda por ID com sucesso")
    void deveDeletarVendaPorIdComSucesso() {
        // Arrange
        Long id = 1L;
        doNothing().when(vendaRepository).deleteById(id);

        // Act
        vendaService.deletar(id);

        // Assert
        verify(vendaRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("deletar - Cenário Negativo: Deve lançar exceção quando repositório falhar ao deletar")
    void deveLancarExcecaoQuandoFalharAoDeletarVenda() {
        // Arrange
        Long idInexistente = 999L;
        doThrow(new RuntimeException("Erro ao deletar venda"))
                .when(vendaRepository).deleteById(idInexistente);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> vendaService.deletar(idInexistente));
        verify(vendaRepository, times(1)).deleteById(idInexistente);
    }
}