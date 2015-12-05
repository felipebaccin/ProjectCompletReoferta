package br.univel.Tela;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;

import br.univel.DAO.ClienteDAO;
import br.univel.Enum.Estado;
import br.univel.Enum.Genero;
import br.univel.entity.Cliente;
import br.univel.modelo.ModeloCliente;
import br.univel.modelo.ModeloProduto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class CadastroClienteDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtCidade;
	private JTextField txtEndereco;
	private JTextField txtEmail;
//	private JComboBox cbxEstado;
//	private JComboBox cbxGenero;
	private static ModeloCliente modelo;
	private static ClienteDAO clienteDao;
	private JTable table;
	private Cliente cliente = new Cliente();
	private List<Cliente> listaClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroClienteDialog dialog = new CadastroClienteDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroClienteDialog() {
//		carregaGenero();
//		carregaEstado();
		// atualizaTabela();

		ModeloCliente m = new ModeloCliente();
		
		setTitle("Cadastro de Cliente");

		setBounds(100, 100, 450, 426);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		contentPanel.add(lblNome, gbc_lblNome);

		txtNome = new JTextField();
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.insets = new Insets(0, 0, 5, 0);
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.gridx = 1;
		gbc_txtNome.gridy = 0;
		contentPanel.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone");
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.EAST;
		gbc_lblTelefone.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefone.gridx = 0;
		gbc_lblTelefone.gridy = 1;
		contentPanel.add(lblTelefone, gbc_lblTelefone);

		txtTelefone = new JTextField();
		GridBagConstraints gbc_txtTelefone = new GridBagConstraints();
		gbc_txtTelefone.anchor = GridBagConstraints.NORTH;
		gbc_txtTelefone.insets = new Insets(0, 0, 5, 0);
		gbc_txtTelefone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTelefone.gridx = 1;
		gbc_txtTelefone.gridy = 1;
		contentPanel.add(txtTelefone, gbc_txtTelefone);
		txtTelefone.setColumns(10);

		JLabel lblGenero = new JLabel("Genero");
		GridBagConstraints gbc_lblGenero = new GridBagConstraints();
		gbc_lblGenero.anchor = GridBagConstraints.EAST;
		gbc_lblGenero.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenero.gridx = 0;
		gbc_lblGenero.gridy = 2;
		contentPanel.add(lblGenero, gbc_lblGenero);

//		JComboBox cbxGenero = new JComboBox();
//		GridBagConstraints gbc_cbxGenero = new GridBagConstraints();
//		gbc_cbxGenero.insets = new Insets(0, 0, 5, 0);
//		gbc_cbxGenero.fill = GridBagConstraints.HORIZONTAL;
//		gbc_cbxGenero.gridx = 1;
//		gbc_cbxGenero.gridy = 2;
//		contentPanel.add(cbxGenero, gbc_cbxGenero);

		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 3;
		contentPanel.add(lblEmail, gbc_lblEmail);

		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.anchor = GridBagConstraints.NORTH;
		gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 1;
		gbc_txtEmail.gridy = 3;
		contentPanel.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);

		JLabel lblCidade = new JLabel("Cidade");
		GridBagConstraints gbc_lblCidade = new GridBagConstraints();
		gbc_lblCidade.anchor = GridBagConstraints.EAST;
		gbc_lblCidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblCidade.gridx = 0;
		gbc_lblCidade.gridy = 4;
		contentPanel.add(lblCidade, gbc_lblCidade);

		txtCidade = new JTextField();
		GridBagConstraints gbc_txtCidade = new GridBagConstraints();
		gbc_txtCidade.insets = new Insets(0, 0, 5, 0);
		gbc_txtCidade.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCidade.gridx = 1;
		gbc_txtCidade.gridy = 4;
		contentPanel.add(txtCidade, gbc_txtCidade);
		txtCidade.setColumns(10);

		JLabel lblEndereo = new JLabel("Endere\u00E7o");
		GridBagConstraints gbc_lblEndereo = new GridBagConstraints();
		gbc_lblEndereo.anchor = GridBagConstraints.EAST;
		gbc_lblEndereo.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndereo.gridx = 0;
		gbc_lblEndereo.gridy = 5;
		contentPanel.add(lblEndereo, gbc_lblEndereo);

		txtEndereco = new JTextField();
		GridBagConstraints gbc_txtEndereco = new GridBagConstraints();
		gbc_txtEndereco.insets = new Insets(0, 0, 5, 0);
		gbc_txtEndereco.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEndereco.gridx = 1;
		gbc_txtEndereco.gridy = 5;
		contentPanel.add(txtEndereco, gbc_txtEndereco);
		txtEndereco.setColumns(10);

//		JLabel lblEstado = new JLabel("Estado");
//		GridBagConstraints gbc_lblEstado = new GridBagConstraints();
//		gbc_lblEstado.anchor = GridBagConstraints.EAST;
//		gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
//		gbc_lblEstado.gridx = 0;
//		gbc_lblEstado.gridy = 6;
//		contentPanel.add(lblEstado, gbc_lblEstado);
//
//		JComboBox cbxEstado = new JComboBox();
//		GridBagConstraints gbc_cbxEstado = new GridBagConstraints();
//		gbc_cbxEstado.insets = new Insets(0, 0, 5, 0);
//		gbc_cbxEstado.fill = GridBagConstraints.HORIZONTAL;
//		gbc_cbxEstado.gridx = 1;
//		gbc_cbxEstado.gridy = 6;
//		contentPanel.add(cbxEstado, gbc_cbxEstado);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 2;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 7;
		contentPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		ModeloCliente modelo = new ModeloCliente();
		table.setModel(modelo);
		scrollPane.setViewportView(table);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridwidth = 2;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 8;
		contentPanel.add(separator, gbc_separator);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 9;
		contentPanel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.requestFocus();
				limpaCampos();
				txtNome.requestFocus();

			}
		});
		GridBagConstraints gbc_btnNovo = new GridBagConstraints();
		gbc_btnNovo.insets = new Insets(0, 0, 0, 5);
		gbc_btnNovo.gridx = 0;
		gbc_btnNovo.gridy = 0;
		panel_1.add(btnNovo, gbc_btnNovo);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.insets = new Insets(0, 0, 0, 5);
		gbc_btnSalvar.gridx = 1;
		gbc_btnSalvar.gridy = 0;
		panel_1.add(btnSalvar, gbc_btnSalvar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Excluir();

			}
		});
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.insets = new Insets(0, 0, 0, 5);
		gbc_btnExcluir.gridx = 2;
		gbc_btnExcluir.gridy = 0;
		panel_1.add(btnExcluir, gbc_btnExcluir);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroClienteDialog.this.dispose();
			
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 0;
		panel_1.add(btnCancelar, gbc_btnCancelar);

	}

//	 private void atualizaTabela() {
//	 table.setModel(new ModeloCliente(clienteDao.listaClientes()));
//	 }

	protected void Excluir() {
		

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int result = JOptionPane.showConfirmDialog(null,
					"Deseja excluir a pessoa " + cliente.getNome() + "?",
					"Deseja realizar essa opera��o?", dialogButton);

			if (result == JOptionPane.YES_OPTION) {
				ClienteDAO dao = new ClienteDAO();
				if (dao.deletar(cliente.getId()) == "NO") {
					JOptionPane.showMessageDialog(null,
							"N�o foi possivel excluir essa Pessoa",
							"Problemas ao Excluir", result);
					return;
				} else {

					JOptionPane.showMessageDialog(null,
							"Exclusao feita com sucesso");


					

				}

			}
		} 

	protected void salvar() {
		try {

			Cliente c = new Cliente();
			c.setNome(txtNome.getText());
			c.setTelefone(txtTelefone.getText());
			c.setEmail(txtEmail.getText());
			c.setEndereco(txtEndereco.getText());
			c.setCidade(txtCidade.getText());

//			c.setEstado((Estado) cbxEstado.getSelectedItem());
//			c.setGenero((Genero) cbxGenero.getSelectedItem());

			clienteDao.inserir(c);

			modelo.fireTableDataChanged();

			JOptionPane.showMessageDialog(null,
					"Categoria cadastrada com sucesso");
			limpaCampos();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar categoria");
		}
	}

//	private void carregaEstado() {
//		cbxEstado.setModel(new DefaultComboBoxModel(Genero.values()));
//		cbxEstado.addItem(null);
//		cbxEstado.setSelectedIndex(cbxGenero.getItemCount() - 1);
//	}
//
//	private void carregaGenero() {
//		cbxGenero.setModel(new DefaultComboBoxModel(Genero.values()));
//		cbxGenero.addItem(null);
//		cbxGenero.setSelectedIndex(cbxGenero.getItemCount() - 1);
//	}

	protected void limpaCampos() {
		txtNome.setText("");
		txtTelefone.setText("");
		txtCidade.setText("");
		txtEndereco.setText("");
		txtEmail.setText("");
//		cbxEstado.setSelectedIndex(cbxEstado.getItemCount() - 1);
//		cbxGenero.setSelectedIndex(cbxGenero.getItemCount() - 1);
		Cliente cliente = new Cliente();
	}

}
