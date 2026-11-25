package com.meupdv.pdv.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenda;

    private LocalDateTime dataHora;

    private BigDecimal total;

    private String formaPagamento;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itens = new ArrayList<>();

    public Venda() {
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        if (idVenda != null && idVenda <= 0) {
            throw new IllegalArgumentException("O ID da venda deve ser positivo.");
        }
        this.idVenda = idVenda;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        if (dataHora == null) {
            throw new IllegalArgumentException("A data e hora da venda não podem ser nulas.");
        }
        this.dataHora = dataHora;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        if (total == null || total.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O total da venda não pode ser nulo ou negativo.");
        }
        this.total = total;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        if (formaPagamento == null || formaPagamento.trim().isEmpty()) {
            throw new IllegalArgumentException("A forma de pagamento não pode ser vazia.");
        }
        this.formaPagamento = formaPagamento.trim();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        if (itens == null) {
            throw new IllegalArgumentException("A lista de itens da venda não pode ser nula.");
        }
        this.itens = itens;
    }

    public void adicionarItem(ItemVenda item) {
        if (item == null) {
            throw new IllegalArgumentException("O item a ser adicionado não pode ser nulo.");
        }
        item.setVenda(this);
        itens.add(item);
    }

    // Padrão de projeto BUILDER
    public static class VendaBuilder {
        private final Venda venda;

        public VendaBuilder() {
            this.venda = new Venda();
            this.venda.setDataHora(LocalDateTime.now());
            this.venda.setTotal(BigDecimal.ZERO);
        }

        public VendaBuilder comCliente(Cliente cliente) {
            venda.setCliente(cliente);
            return this;
        }

        public VendaBuilder comFormaPagamento(String formaPagamento) {
            venda.setFormaPagamento(formaPagamento);
            return this;
        }

        public VendaBuilder adicionarItem(ItemVenda item) {
            venda.adicionarItem(item);
            if (item.getSubtotal() != null) {
                venda.setTotal(venda.getTotal().add(item.getSubtotal()));
            }
            return this;
        }

        public Venda build() {
            return venda;
        }
    }

    public static VendaBuilder builder() {
        return new VendaBuilder();
    }
}