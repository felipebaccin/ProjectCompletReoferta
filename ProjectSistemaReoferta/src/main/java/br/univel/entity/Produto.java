package br.univel.entity;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.univel.Enum.Categoria;
import br.univel.Enum.Genero;
import br.univel.Enum.Unidade;

public class Produto {

	private int id; 
	private int codigoBarras;
	private String descricao;
	private int custo;
	private int margemLucro;
	@Enumerated(EnumType.STRING)
	Categoria categoria;
	@Enumerated(EnumType.STRING)
	Unidade unidade;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(int codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getCusto() {
		return custo;
	}
	public void setCusto(int custo) {
		this.custo = custo;
	}
	public int getMargemLucro() {
		return margemLucro;
	}
	public void setMargemLucro(int margemLucro) {
		this.margemLucro = margemLucro;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Unidade getUnidade() {
		return unidade;
	}
	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}
}
