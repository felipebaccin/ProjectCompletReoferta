package br.univel.modelo;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.entity.Cliente;

public class ModeloCliente extends AbstractTableModel {

	List<Cliente> listaClientes;

	public void ModelCliente(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return listaClientes.size();
	}

	public Object getValueAt(int arg0, int arg1) {
		Cliente cliente = listaClientes.get(arg0);

		switch (arg1) {
		case 0:
			return cliente.getId();
		case 1:
			return cliente.getNome();
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
			return "Nome";
		default:
			return "Erro";
		}

	};

}
