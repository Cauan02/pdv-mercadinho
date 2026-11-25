package com.meupdv.pdv.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;

    private String nome;

    private String codigoBarras;

    private String descricao;

    private BigDecimal precoVenda;

    private BigDecimal precoCusto;

    private Integer quantidadeEstoque;

    private Integer estoqueMinimo;

    private LocalDate validade;

    private String status;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    public Produto() {
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        if (idProduto != null && idProduto <= 0) {
            throw new IllegalArgumentException("O ID do produto deve ser positivo.");
        }
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }
        this.nome = nome.trim();
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        if (codigoBarras == null || codigoBarras.trim().isEmpty()) {
            throw new IllegalArgumentException("O código de barras não pode ser vazio.");
        }
        this.codigoBarras = codigoBarras.trim();
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

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        if (precoVenda == null || precoVenda.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O preço de venda não pode ser nulo ou negativo.");
        }
        this.precoVenda = precoVenda;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        if (precoCusto == null || precoCusto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O preço de custo não pode ser nulo ou negativo.");
        }
        this.precoCusto = precoCusto;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        if (quantidadeEstoque == null || quantidadeEstoque < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser nula ou negativa.");
        }
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        if (estoqueMinimo == null || estoqueMinimo < 0) {
            throw new IllegalArgumentException("O estoque mínimo não pode ser nulo ou negativo.");
        }
        this.estoqueMinimo = estoqueMinimo;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("A categoria do produto não pode ser nula.");
        }
        this.categoria = categoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("O status do produto não pode ser vazio.");
        }
        this.status = status.trim();
    }
}
