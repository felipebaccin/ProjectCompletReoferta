package br.univel.modelo;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Validador {
	
	public static boolean verificarCampos(JPanel painel) {

		Component[] componentes = painel.getComponents();
		boolean valida = true;

		for (int i = 0; i < componentes.length; i++) {
			if (componentes[i] instanceof JTextField) {
				JTextField texto = (JTextField) componentes[i];
				if (texto.getName() != "CÓDIGO"){
				if (texto.isEditable()) {
					if (texto.getText().trim().equals("")
							|| texto.getText() == null) {
						JOptionPane.showMessageDialog(null, "Preencha o campo "
								+ texto.getName() + "!");
						texto.requestFocus();
						valida = false;
						break;
					}
				}
				}
			}

			if (componentes[i] instanceof JPasswordField) {
				JPasswordField textoPass = (JPasswordField) componentes[i];
				if (textoPass.getPassword().toString().trim().equals("")
						|| textoPass.getPassword().toString() == null) {
					JOptionPane.showMessageDialog(null, "Preencha o campo "
							+ textoPass.getName() + "!");
					textoPass.requestFocus();
					valida = false;
					break;
				}
			}

			if (componentes[i] instanceof JComboBox<?>) {
				JComboBox<?> combo = (JComboBox<?>) componentes[i];
				if (combo.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Selecione o campo "
							+ combo.getName() + "!");
					combo.requestFocus();
					valida = false;
					break;
				}
			}
			
			if(componentes[i] instanceof JFormattedTextField) {
				JFormattedTextField textoFormatado = (JFormattedTextField) componentes[i];
				JTextField teste = textoFormatado;
				if(teste.getText().toString().trim().equals("")
						|| teste.getText().toString() == null) {
					JOptionPane.showMessageDialog(null, "Preencha o campo "
							+ textoFormatado.getName() + "!");
					textoFormatado.requestFocus();
					valida = false;
					break;
				}
			}

		}
		return valida;
	}
	
	public static void limparCampos(JPanel painel) {

		Component[] componentes = painel.getComponents();

		for (int i = 0; i < componentes.length; i++) {
			if (componentes[i] instanceof JTextField) {
				JTextField texto = (JTextField) componentes[i];
				texto.setText("");
			}

			if (componentes[i] instanceof JPasswordField) {
				JPasswordField textoPass = (JPasswordField) componentes[i];
				textoPass.setText("");
			}

			if (componentes[i] instanceof JComboBox<?>) {
				JComboBox<?> combo = (JComboBox<?>) componentes[i];
				combo.setSelectedIndex(0);
			}
			
			if(componentes[i] instanceof JFormattedTextField) {
				JFormattedTextField textoFormatado = (JFormattedTextField) componentes[i];
				textoFormatado.setText("");
			}

		}
	}

}
