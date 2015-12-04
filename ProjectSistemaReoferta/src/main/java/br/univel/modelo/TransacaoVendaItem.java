package br.univel.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TransacaoVendaItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int id;
	
	@ManyToOne
	@JoinColumn(name = "transacaovenda_id")
	private TransacaoVenda transacaovenda;

	private int idProduto;
	private String descricaoProduto;
	private BigDecimal vlProduto;
	private int quantidade;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TransacaoVenda getTransacaovenda() {
		return transacaovenda;
	}
	public void setTransacaovenda(TransacaoVenda transacaovenda) {
		this.transacaovenda = transacaovenda;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	public BigDecimal getVlProduto() {
		return vlProduto;
	}
	public void setVlProduto(BigDecimal vlProduto) {
		this.vlProduto = vlProduto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
