package com.meupdv.pdv.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cupom")
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCupom;

    private String numeroCupom;

    private LocalDateTime dataEmissao;

    private BigDecimal valorTotal;

    @OneToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    public Cupom() {
    }

    public Long getIdCupom() {
        return idCupom;
    }

    public void setIdCupom(Long idCupom) {
        if (idCupom != null && idCupom <= 0) {
            throw new IllegalArgumentException("O ID do cupom deve ser positivo.");
        }
        this.idCupom = idCupom;
    }

    public String getNumeroCupom() {
        return numeroCupom;
    }

    public void setNumeroCupom(String numeroCupom) {
        if (numeroCupom == null || numeroCupom.trim().isEmpty()) {
            throw new IllegalArgumentException("O número do cupom não pode ser vazio.");
        }
        this.numeroCupom = numeroCupom.trim();
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDateTime dataEmissao) {
        if (dataEmissao == null) {
            throw new IllegalArgumentException("A data de emissão do cupom não pode ser nula.");
        }
        this.dataEmissao = dataEmissao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        if (valorTotal == null || valorTotal.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor total do cupom não pode ser nulo ou negativo.");
        }
        this.valorTotal = valorTotal;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        if (venda == null) {
            throw new IllegalArgumentException("A venda associada ao cupom não pode ser nula.");
        }
        this.venda = venda;
    }
}