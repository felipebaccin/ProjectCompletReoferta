package br.univel.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.univel.Enum.Estado;
import br.univel.Enum.Genero;

public class Cliente {

	private int id;
	private String nome;
	private String telefone;
	private String endereco;
	private String cidade;
	private String email;
	
	//enum
	@Enumerated(EnumType.STRING)
	Estado estado;
	@Enumerated(EnumType.STRING)
	Genero genero;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	
	
}
