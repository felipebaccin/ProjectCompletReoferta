package br.univel.Tela;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.univel.DAO.ProdutoDAO;
import br.univel.Enum.Categoria;
import br.univel.Enum.Estado;
import br.univel.Enum.Genero;
import br.univel.Enum.Unidade;
import br.univel.entity.Produto;
import br.univel.entity.Produto;
import br.univel.modelo.ModeloProduto;
import br.univel.utils.Validador;

public class CadastroProdutoDialog extends JDialog {
	
	private static Produto produto = new Produto();
	private static List<Produto> listaProdutos;
	private static Categoria tipo;
	private static Unidade unidade;
	private JTextField txtDescricao;
	private JTextField txtCodigo;
	private JTextField txtCusto;
	private JTextField txtLucro;
	private JComboBox cbxUnidade;
	private JComboBox cbxTipo;
	private static ProdutoDAO produtoDao;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroProdutoDialog dialog = new CadastroProdutoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	ModeloProduto mp = new ModeloProduto();
	public CadastroProdutoDialog() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				txtCodigo.requestFocus();
				carregaCategoria();
				carregaUnidade();
			//	atualizaTabela();
				
				setTitle("Cadastro de Produto");
				
				carregaLista();
			}
		});
		setTitle("CADASTRO DE PRODUTO");
		setBounds(100, 100, 450, 524);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			JLabel lblDescricao = new JLabel("Descricao");
			GridBagConstraints gbc_lblDescricao = new GridBagConstraints();
			gbc_lblDescricao.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescricao.anchor = GridBagConstraints.EAST;
			gbc_lblDescricao.gridx = 0;
			gbc_lblDescricao.gridy = 0;
			getContentPane().add(lblDescricao, gbc_lblDescricao);
		}
		{
			txtDescricao = new JTextField();
			GridBagConstraints gbc_txtDescricao = new GridBagConstraints();
			gbc_txtDescricao.insets = new Insets(0, 0, 5, 0);
			gbc_txtDescricao.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtDescricao.gridx = 1;
			gbc_txtDescricao.gridy = 0;
			getContentPane().add(txtDescricao, gbc_txtDescricao);
			txtDescricao.setColumns(10);
		}
		{
			JLabel lblCodigoDeBarras = new JLabel("Codigo de Barras");
			GridBagConstraints gbc_lblCodigoDeBarras = new GridBagConstraints();
			gbc_lblCodigoDeBarras.anchor = GridBagConstraints.EAST;
			gbc_lblCodigoDeBarras.insets = new Insets(0, 0, 5, 5);
			gbc_lblCodigoDeBarras.gridx = 0;
			gbc_lblCodigoDeBarras.gridy = 1;
			getContentPane().add(lblCodigoDeBarras, gbc_lblCodigoDeBarras);
		}
		{
			txtCodigo = new JTextField();
			GridBagConstraints gbc_txtCodigo = new GridBagConstraints();
			gbc_txtCodigo.anchor = GridBagConstraints.NORTH;
			gbc_txtCodigo.insets = new Insets(0, 0, 5, 0);
			gbc_txtCodigo.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCodigo.gridx = 1;
			gbc_txtCodigo.gridy = 1;
			getContentPane().add(txtCodigo, gbc_txtCodigo);
			txtCodigo.setColumns(10);
		}
		{
			JLabel lblCusto = new JLabel("Custo");
			GridBagConstraints gbc_lblCusto = new GridBagConstraints();
			gbc_lblCusto.anchor = GridBagConstraints.EAST;
			gbc_lblCusto.insets = new Insets(0, 0, 5, 5);
			gbc_lblCusto.gridx = 0;
			gbc_lblCusto.gridy = 2;
			getContentPane().add(lblCusto, gbc_lblCusto);
		}
		{
			txtCusto = new JTextField();
			GridBagConstraints gbc_txtCusto = new GridBagConstraints();
			gbc_txtCusto.insets = new Insets(0, 0, 5, 0);
			gbc_txtCusto.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCusto.gridx = 1;
			gbc_txtCusto.gridy = 2;
			getContentPane().add(txtCusto, gbc_txtCusto);
			txtCusto.setColumns(10);
		}
		{
			JLabel lblMargemDeLucro = new JLabel("Margem de lucro");
			GridBagConstraints gbc_lblMargemDeLucro = new GridBagConstraints();
			gbc_lblMargemDeLucro.anchor = GridBagConstraints.EAST;
			gbc_lblMargemDeLucro.insets = new Insets(0, 0, 5, 5);
			gbc_lblMargemDeLucro.gridx = 0;
			gbc_lblMargemDeLucro.gridy = 3;
			getContentPane().add(lblMargemDeLucro, gbc_lblMargemDeLucro);
		}
		{
			txtLucro = new JTextField();
			GridBagConstraints gbc_txtLucro = new GridBagConstraints();
			gbc_txtLucro.insets = new Insets(0, 0, 5, 0);
			gbc_txtLucro.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtLucro.gridx = 1;
			gbc_txtLucro.gridy = 3;
			getContentPane().add(txtLucro, gbc_txtLucro);
			txtLucro.setColumns(10);
		}
		{
			JLabel lblUnidade = new JLabel("Unidade");
			GridBagConstraints gbc_lblUnidade = new GridBagConstraints();
			gbc_lblUnidade.anchor = GridBagConstraints.EAST;
			gbc_lblUnidade.insets = new Insets(0, 0, 5, 5);
			gbc_lblUnidade.gridx = 0;
			gbc_lblUnidade.gridy = 4;
			getContentPane().add(lblUnidade, gbc_lblUnidade);
		}
		{
			JComboBox cbxUnidade = new JComboBox();
			cbxUnidade.setName("UNIDADE");
			GridBagConstraints gbc_cbxUnidade = new GridBagConstraints();
			gbc_cbxUnidade.insets = new Insets(0, 0, 5, 0);
			gbc_cbxUnidade.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxUnidade.gridx = 1;
			gbc_cbxUnidade.gridy = 4;
			getContentPane().add(cbxUnidade, gbc_cbxUnidade);
		}
		{
			JLabel lblCategoria = new JLabel("Categoria");
			GridBagConstraints gbc_lblCategoria = new GridBagConstraints();
			gbc_lblCategoria.anchor = GridBagConstraints.EAST;
			gbc_lblCategoria.insets = new Insets(0, 0, 5, 5);
			gbc_lblCategoria.gridx = 0;
			gbc_lblCategoria.gridy = 5;
			getContentPane().add(lblCategoria, gbc_lblCategoria);
		}
		{
			JComboBox cbxCategoria = new JComboBox();
			cbxCategoria.setName("TIPO");
			GridBagConstraints gbc_cbxCategoria = new GridBagConstraints();
			gbc_cbxCategoria.insets = new Insets(0, 0, 5, 0);
			gbc_cbxCategoria.fill = GridBagConstraints.HORIZONTAL;
			gbc_cbxCategoria.gridx = 1;
			gbc_cbxCategoria.gridy = 5;
			getContentPane().add(cbxCategoria, gbc_cbxCategoria);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.gridwidth = 2;
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 6;
			getContentPane().add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JButton btnNovo = new JButton("NOVO");
				btnNovo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						txtDescricao.requestFocus();
						limpaCampos();
						txtDescricao.requestFocus();
					}
				});
				GridBagConstraints gbc_btnNovo = new GridBagConstraints();
				gbc_btnNovo.insets = new Insets(0, 0, 0, 5);
				gbc_btnNovo.gridx = 0;
				gbc_btnNovo.gridy = 0;
				panel.add(btnNovo, gbc_btnNovo);
			}
			{
				JButton button = new JButton("SALVAR");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						salvar();
					}
				});
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.insets = new Insets(0, 0, 0, 5);
				gbc_button.gridx = 1;
				gbc_button.gridy = 0;
				panel.add(button, gbc_button);
			}
			{
				JButton button = new JButton("EXCLUIR");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						excluir();
					}
				});
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.insets = new Insets(0, 0, 0, 5);
				gbc_button.gridx = 2;
				gbc_button.gridy = 0;
				panel.add(button, gbc_button);
			}
			{
				JButton button = new JButton("CANCELAR");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CadastroProdutoDialog.this.dispose();
					}
				});
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.insets = new Insets(0, 0, 0, 5);
				gbc_button.gridx = 3;
				gbc_button.gridy = 0;
				panel.add(button, gbc_button);
			}
			{
				JButton button = new JButton("SAIR");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				GridBagConstraints gbc_button = new GridBagConstraints();
				gbc_button.gridx = 4;
				gbc_button.gridy = 0;
				panel.add(button, gbc_button);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 2;
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 7;
			getContentPane().add(scrollPane, gbc_scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
			}
		}
	}
	protected void excluir() {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int result = JOptionPane.showConfirmDialog(null,
				"Deseja excluir a produto " + produto.getDescricao() + "?",
				"Deseja realizar essa opera��o?", dialogButton);

		if (result == JOptionPane.YES_OPTION) {
			ProdutoDAO dao = new ProdutoDAO();
			if (dao.deletar(produto.getId()) == "NO") {
				JOptionPane.showMessageDialog(null,
						"N�o foi possivel excluir essa Produto",
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

			Produto p = new Produto();
			p.setDescricao(txtDescricao.getText());
			p.setCodigoBarras(Integer.parseInt(txtCodigo.getText()));
			//p.setCusto(txtCusto.getText());
			//p.setMargemLucro(txtLucro.getText());


//			p.setUnidade((Unidade) cbxUnidade.getSelectedItem());
//			p.setCategoria((Categoria) cbxTipo.getSelectedItem());

			produtoDao.inserir(p);

			mp.fireTableDataChanged();

			JOptionPane.showMessageDialog(null,
					"Produto cadastrada com sucesso");
			limpaCampos();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar categoria");
		}
		
	}
	protected void carregaLista() {
		ProdutoDAO dao = new ProdutoDAO();
		mp.setProdutos(dao.listaprodutos());
		mp.fireTableDataChanged();
	}

	

	protected void carregaUnidade() {
		cbxUnidade.setModel(new DefaultComboBoxModel(Genero.values()));
		cbxUnidade.addItem(null);
		cbxUnidade.setSelectedIndex(cbxUnidade.getItemCount() - 1);
	
		
	}

	protected void carregaCategoria() {
		cbxTipo.setModel(new DefaultComboBoxModel(Genero.values()));
		cbxTipo.addItem(null);
		cbxTipo.setSelectedIndex(cbxTipo.getItemCount() - 1);
	
	}

	protected void limpaCampos() {
		txtDescricao.setText("");
		txtCusto.setText("");
		txtLucro.setText("");
		cbxUnidade.setSelectedIndex(cbxUnidade.getItemCount() - 1);
		cbxTipo.setSelectedIndex(cbxTipo.getItemCount() - 1);
		Produto p = new Produto();
		
	}

	

}