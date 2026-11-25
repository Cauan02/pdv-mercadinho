package com.meupdv.pdv.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "item_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemVenda;

    private Integer quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal subtotal;


    private String descricao;


    @Transient
    private String lanche;

    @Transient
    private String adicionais;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    public ItemVenda() {
    }

    public Long getIdItemVenda() {
        return idItemVenda;
    }

    public void setIdItemVenda(Long idItemVenda) {
        if (idItemVenda != null && idItemVenda <= 0) {
            throw new IllegalArgumentException("O ID do item de venda deve ser positivo.");
        }
        this.idItemVenda = idItemVenda;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade do item deve ser maior que zero.");
        }
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        if (precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O preço unitário não pode ser nulo ou negativo.");
        }
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        if (subtotal == null || subtotal.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O subtotal não pode ser nulo ou negativo.");
        }
        this.subtotal = subtotal;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao != null && descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição, quando informada, não pode estar em branco.");
        }
        this.descricao = (descricao != null) ? descricao.trim() : null;
    }

    public String getLanche() {
        return lanche;
    }

    public void setLanche(String lanche) {
        if (lanche != null && lanche.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do lanche, quando informado, não pode estar em branco.");
        }
        this.lanche = (lanche != null) ? lanche.trim() : null;
    }

    public String getAdicionais() {
        return adicionais;
    }

    public void setAdicionais(String adicionais) {
        this.adicionais = (adicionais != null) ? adicionais.trim() : null;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        if (venda == null) {
            throw new IllegalArgumentException("A venda do item não pode ser nula.");
        }
        this.venda = venda;
    }
}