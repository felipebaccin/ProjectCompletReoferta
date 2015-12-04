package br.univel.modelo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.univel.entity.Cliente;

@Entity
public class TransacaoVenda {
	
	@Id
	@GeneratedValue
	int id;
	@OneToOne
	Cliente cliente;

	@OneToMany(mappedBy = "transacaovenda", cascade = CascadeType.ALL)
	private List<TransacaoVendaItem> transacaoVendaItemList;
	
	BigDecimal valorTotal;
	BigDecimal valorPagamento;
	BigDecimal valorTroco;
	
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	Timestamp dataHoraFinalizacao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	public BigDecimal getValorTroco() {
		return valorTroco;
	}
	public void setValorTroco(BigDecimal valorTroco) {
		this.valorTroco = valorTroco;
	}
	public Timestamp getDataHoraFinalizacao() {
		return dataHoraFinalizacao;
	}
	public void setDataHoraFinalizacao(Timestamp dataHoraFinalizacao) {
		this.dataHoraFinalizacao = dataHoraFinalizacao;
	}
	public List<TransacaoVendaItem> getTransacaoVendaItemList() {
		return transacaoVendaItemList;
	}
	public void setTransacaoVendaItemList(
			List<TransacaoVendaItem> transacaoVendaItemList) {
		this.transacaoVendaItemList = transacaoVendaItemList;
	}
	
	

	
	

}
