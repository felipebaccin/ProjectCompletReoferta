package br.univel.modelo;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.utils.TransacaoVendaItem;

public class TableModelVendaItem extends AbstractTableModel{

private static final long serialVersionUID = 1L;
	
	List<TransacaoVendaItem> listaVendaItens;
		
	public TableModelVendaItem(List<TransacaoVendaItem> listaItens) {	
		this.listaVendaItens = listaItens;
	}

	public int getColumnCount() {		
		return 4;
	}

	public int getRowCount() {		
		return listaVendaItens.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		TransacaoVendaItem vendaitem = listaVendaItens.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return vendaitem.getId();
		case 1:
			return vendaitem.getDescricaoProduto();
		case 2:
			return vendaitem.getQuantidade();
		case 3:
			return vendaitem.getVlProduto();
		default:
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "CÓDIGO";
		case 1:
			return "DESCRIÇÃO";
		case 2:
			return "QUANTIDADE";
		case 3:
			return "VALOR";
		default:
			return null;
		}
	}

}
