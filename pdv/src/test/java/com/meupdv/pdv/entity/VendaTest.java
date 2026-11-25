package com.meupdv.pdv.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class VendaTest {

    private Venda venda;

    @BeforeEach
    void setUp() {
        venda = new Venda();
    }

    // ==========================================
    // 1. ATRIBUTO: idVenda (3 testes)
    // ==========================================

    @Test
    @DisplayName("1. idVenda - Deve atribuir e retornar um ID válido")
    void deveAtribuirERetornarIdVendaValido() {
        // Arrange
        Long idEsperado = 100L;

        // Act
        venda.setIdVenda(idEsperado);

        // Assert
        assertEquals(idEsperado, venda.getIdVenda());
    }

    @Test
    @DisplayName("2. idVenda - Deve iniciar como nulo ao instanciar venda")
    void deveIniciarIdVendaNulo() {
        // Arrange & Act
        Venda novaVenda = new Venda();

        // Assert
        assertNull(novaVenda.getIdVenda());
    }

    @Test
    @DisplayName("3. idVenda - Deve lançar exceção para ID zero ou negativo")
    void deveLancarExcecaoParaIdVendaInvalido() {
        // Arrange
        Long idInvalido = -1L;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> venda.setIdVenda(idInvalido));
    }

    // ==========================================
    // 2. ATRIBUTO: dataHora (3 testes)
    // ==========================================

    @Test
    @DisplayName("1. dataHora - Deve atribuir e retornar uma data e hora válida")
    void deveAtribuirERetornarDataHoraValida() {
        // Arrange
        LocalDateTime dataEsperada = LocalDateTime.of(2026, 11, 24, 18, 30);

        // Act
        venda.setDataHora(dataEsperada);

        // Assert
        assertEquals(dataEsperada, venda.getDataHora());
    }

    @Test
    @DisplayName("2. dataHora - Deve iniciar como nulo ao usar construtor padrão")
    void deveIniciarDataHoraNula() {
        // Arrange & Act
        Venda novaVenda = new Venda();

        // Assert
        assertNull(novaVenda.getDataHora());
    }

    @Test
    @DisplayName("3. dataHora - Deve lançar exceção quando dataHora for nula")
    void deveLancarExcecaoQuandoDataHoraForNula() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> venda.setDataHora(null));
    }

    // ==========================================
    // 3. ATRIBUTO: total (3 testes)
    // ==========================================

    @Test
    @DisplayName("1. total - Deve atribuir e retornar um total válido")
    void deveAtribuirERetornarTotalValido() {
        // Arrange
        BigDecimal totalEsperado = new BigDecimal("250.00");

        // Act
        venda.setTotal(totalEsperado);

        // Assert
        assertEquals(totalEsperado, venda.getTotal());
    }

    @Test
    @DisplayName("2. total - Deve permitir total igual a zero")
    void devePermitirTotalZero() {
        // Arrange
        BigDecimal totalZero = BigDecimal.ZERO;

        // Act
        venda.setTotal(totalZero);

        // Assert
        assertEquals(BigDecimal.ZERO, venda.getTotal());
    }

    @Test
    @DisplayName("3. total - Deve lançar exceção quando total for negativo ou nulo")
    void deveLancarExcecaoQuandoTotalForNegativo() {
        // Arrange
        BigDecimal totalNegativo = new BigDecimal("-15.00");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> venda.setTotal(totalNegativo));
    }

    // ==========================================
    // 4. ATRIBUTO: formaPagamento (3 testes)
    // ==========================================

    @Test
    @DisplayName("1. formaPagamento - Deve atribuir e retornar forma de pagamento válida")
    void deveAtribuirERetornarFormaPagamentoValida() {
        // Arrange
        String formaEsperada = "PIX";

        // Act
        venda.setFormaPagamento(formaEsperada);

        // Assert
        assertEquals(formaEsperada, venda.getFormaPagamento());
    }

    @Test
    @DisplayName("2. formaPagamento - Deve lançar exceção quando forma de pagamento for nula")
    void deveLancarExcecaoQuandoFormaPagamentoForNula() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> venda.setFormaPagamento(null));
    }

    @Test
    @DisplayName("3. formaPagamento - Deve lançar exceção quando forma de pagamento estiver em branco")
    void deveLancarExcecaoQuandoFormaPagamentoForVazia() {
        // Arrange
        String formaVazia = "   ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> venda.setFormaPagamento(formaVazia));
    }

    // ==========================================
    // 5. ATRIBUTO: cliente (3 testes)
    // ==========================================

    @Test
    @DisplayName("1. cliente - Deve vincular e retornar um cliente válido")
    void deveVincularERetornarClienteValido() {
        // Arrange
        Cliente clienteEsperado = new Cliente();

        // Act
        venda.setCliente(clienteEsperado);

        // Assert
        assertEquals(clienteEsperado, venda.getCliente());
    }

    @Test
    @DisplayName("2. cliente - Deve permitir cliente nulo (venda sem cadastro)")
    void devePermitirClienteNulo() {
        // Arrange & Act
        venda.setCliente(null);

        // Assert
        assertNull(venda.getCliente());
    }

    @Test
    @DisplayName("3. cliente - Deve substituir o cliente vinculado corretamente")
    void deveSubstituirClienteVinculado() {
        // Arrange
        Cliente cliente1 = new Cliente();
        Cliente cliente2 = new Cliente();
        venda.setCliente(cliente1);

        // Act
        venda.setCliente(cliente2);

        // Assert
        assertEquals(cliente2, venda.getCliente());
    }

    // ==========================================
    // 6. ATRIBUTO: itens (3 testes)
    // ==========================================

    @Test
    @DisplayName("1. itens - Deve atribuir e retornar uma lista de itens válida")
    void deveAtribuirERetornarListaItensValida() {
        // Arrange
        List<ItemVenda> listaEsperada = new ArrayList<>();
        listaEsperada.add(new ItemVenda());

        // Act
        venda.setItens(listaEsperada);

        // Assert
        assertEquals(1, venda.getItens().size());
    }

    @Test
    @DisplayName("2. itens - Deve iniciar com lista vazia ao instanciar venda")
    void deveIniciarItensComListaVazia() {
        // Arrange & Act
        Venda novaVenda = new Venda();

        // Assert
        assertNotNull(novaVenda.getItens());
        assertTrue(novaVenda.getItens().isEmpty());
    }

    @Test
    @DisplayName("3. itens - Deve lançar exceção ao tentar atribuir lista nula")
    void deveLancarExcecaoQuandoListaItensForNula() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> venda.setItens(null));
    }

    // ==========================================
    // 7. COMPORTAMENTOS: adicionarItem e Builder (3 testes)
    // ==========================================

    @Test
    @DisplayName("1. adicionarItem - Deve adicionar item na lista e vincular a venda nele")
    void deveAdicionarItemEVincularVenda() {
        // Arrange
        ItemVenda item = new ItemVenda();

        // Act
        venda.adicionarItem(item);

        // Assert
        assertEquals(1, venda.getItens().size());
        assertEquals(venda, item.getVenda());
    }

    @Test
    @DisplayName("2. adicionarItem - Deve lançar exceção ao tentar adicionar item nulo")
    void deveLancarExcecaoAoAdicionarItemNulo() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> venda.adicionarItem(null));
    }

    @Test
    @DisplayName("3. VendaBuilder - Deve construir venda completa somando subtotais dos itens")
    void deveConstruirVendaComBuilderESomarSubtotais() {
        // Arrange
        Cliente cliente = new Cliente();
        ItemVenda item = new ItemVenda();
        item.setSubtotal(new BigDecimal("45.00"));

        // Act
        Venda vendaConstruida = Venda.builder()
                .comCliente(cliente)
                .comFormaPagamento("CARTAO")
                .adicionarItem(item)
                .build();

        // Assert
        assertEquals(cliente, vendaConstruida.getCliente());
        assertEquals("CARTAO", vendaConstruida.getFormaPagamento());
        assertEquals(new BigDecimal("45.00"), vendaConstruida.getTotal());
        assertEquals(1, vendaConstruida.getItens().size());
    }
}
