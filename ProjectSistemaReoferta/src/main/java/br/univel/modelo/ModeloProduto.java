package br.univel.modelo;

import javax.swing.table.AbstractTableModel;

import java.util.List;

import br.univel.entity.*;

public class ModeloProduto extends AbstractTableModel {

	List<Produto> produtos;
	
	public void ModelCliente(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return produtos.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Produto produto = produtos.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return produto.getId();
		case 1:
			return produto.getDescricao();
		default:
			return "Erro";
		}
	}
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "ID";
		case 1:
			return "Descrição";
		default:
			return "Erro";
		}

	};

}
