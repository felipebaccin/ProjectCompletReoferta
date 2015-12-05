package br.univel.Tela;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.univel.DAO.ClienteDAO;
import br.univel.DAO.ProdutoDAO;
import br.univel.entity.Cliente;
import br.univel.entity.Produto;
import br.univel.modelo.TableModelVendaItem;
import br.univel.utils.TransacaoVenda;
import br.univel.utils.TransacaoVendaItem;

public class TelaVenda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static TelaVenda INSTANCIA;
	private JTextField txtNumeroTransacao;
	private JTextField txtDataHoraFinalizacao;
	private JTextField txtAndamento;
	private static JTable table;
	private static JComboBox comboProduto;
	private static JComboBox comboCliente;
	private static TransacaoVenda venda = new TransacaoVenda();
	private static List<TransacaoVendaItem> listaItens = new ArrayList<TransacaoVendaItem>();
	private static ProdutoDAO daoProduto;
	private static JTextField txtQuantidade;
	private JTextField txtValorPagamento;
	private JTextField textField;
	private JTextField txtValorTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaVenda dialog = new TelaVenda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaVenda() {
		setTitle("TRANSA\u00C7\u00C3O DE VENDA");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				carregaCliente();
				carregaProdutos();
				
			}
		});
		setBounds(100, 100, 661, 633);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{424, 0};
		gbl_contentPanel.rowHeights = new int[]{128, 0, 0, 14, 34, 92, 35, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.anchor = GridBagConstraints.NORTH;
			gbc_panel.fill = GridBagConstraints.HORIZONTAL;
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{158, 0, 0};
			gbl_panel.rowHeights = new int[]{14, 14, 14, 14, 14, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblNmeroTransao = new JLabel("N\u00DAMERO");
				GridBagConstraints gbc_lblNmeroTransao = new GridBagConstraints();
				gbc_lblNmeroTransao.anchor = GridBagConstraints.WEST;
				gbc_lblNmeroTransao.insets = new Insets(0, 0, 5, 5);
				gbc_lblNmeroTransao.gridx = 0;
				gbc_lblNmeroTransao.gridy = 0;
				panel.add(lblNmeroTransao, gbc_lblNmeroTransao);
			}
			{
				txtNumeroTransacao = new JTextField();
				GridBagConstraints gbc_txtNumeroTransacao = new GridBagConstraints();
				gbc_txtNumeroTransacao.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtNumeroTransacao.insets = new Insets(0, 0, 5, 0);
				gbc_txtNumeroTransacao.gridx = 1;
				gbc_txtNumeroTransacao.gridy = 0;
				panel.add(txtNumeroTransacao, gbc_txtNumeroTransacao);
				txtNumeroTransacao.setColumns(10);
			}
			{
				JLabel lblNewLabel = new JLabel("STATUS");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 0;
				gbc_lblNewLabel.gridy = 1;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				txtAndamento = new JTextField();
				txtAndamento.setText("ANDAMENTO");
				txtAndamento.setEnabled(false);
				GridBagConstraints gbc_txtAndamento = new GridBagConstraints();
				gbc_txtAndamento.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtAndamento.insets = new Insets(0, 0, 5, 0);
				gbc_txtAndamento.gridx = 1;
				gbc_txtAndamento.gridy = 1;
				panel.add(txtAndamento, gbc_txtAndamento);
				txtAndamento.setColumns(10);
			}
			{
				JLabel lblDataHora = new JLabel("DATA / HORA FINALIZA\u00C7\u00C3O");
				GridBagConstraints gbc_lblDataHora = new GridBagConstraints();
				gbc_lblDataHora.anchor = GridBagConstraints.WEST;
				gbc_lblDataHora.insets = new Insets(0, 0, 5, 5);
				gbc_lblDataHora.gridx = 0;
				gbc_lblDataHora.gridy = 2;
				panel.add(lblDataHora, gbc_lblDataHora);
			}
			{
				txtDataHoraFinalizacao = new JTextField();
				txtDataHoraFinalizacao.setEnabled(false);
				GridBagConstraints gbc_txtDataHoraFinalizacao = new GridBagConstraints();
				gbc_txtDataHoraFinalizacao.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtDataHoraFinalizacao.insets = new Insets(0, 0, 5, 0);
				gbc_txtDataHoraFinalizacao.gridx = 1;
				gbc_txtDataHoraFinalizacao.gridy = 2;
				panel.add(txtDataHoraFinalizacao, gbc_txtDataHoraFinalizacao);
				txtDataHoraFinalizacao.setColumns(10);
			}
			{
				JLabel lblCdigoCliente = new JLabel("C\u00D3DIGO CLIENTE");
				GridBagConstraints gbc_lblCdigoCliente = new GridBagConstraints();
				gbc_lblCdigoCliente.anchor = GridBagConstraints.WEST;
				gbc_lblCdigoCliente.insets = new Insets(0, 0, 5, 5);
				gbc_lblCdigoCliente.gridx = 0;
				gbc_lblCdigoCliente.gridy = 3;
				panel.add(lblCdigoCliente, gbc_lblCdigoCliente);
			}
			{
				comboCliente = new JComboBox();
				GridBagConstraints gbc_comboCliente = new GridBagConstraints();
				gbc_comboCliente.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboCliente.insets = new Insets(0, 0, 5, 0);
				gbc_comboCliente.gridx = 1;
				gbc_comboCliente.gridy = 3;
				panel.add(comboCliente, gbc_comboCliente);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("PRODUTO");
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
				gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
				gbc_lblNewLabel_1.gridx = 0;
				gbc_lblNewLabel_1.gridy = 4;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				comboProduto = new JComboBox();
				GridBagConstraints gbc_comboProduto = new GridBagConstraints();
				gbc_comboProduto.anchor = GridBagConstraints.WEST;
				gbc_comboProduto.gridx = 1;
				gbc_comboProduto.gridy = 4;
				panel.add(comboProduto, gbc_comboProduto);
			}
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Quantidade");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 1;
			contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		}
		{
			txtQuantidade = new JTextField();
			txtQuantidade.setText("");
			GridBagConstraints gbc_txtQuantidade = new GridBagConstraints();
			gbc_txtQuantidade.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtQuantidade.insets = new Insets(0, 0, 5, 0);
			gbc_txtQuantidade.gridx = 0;
			gbc_txtQuantidade.gridy = 2;
			contentPanel.add(txtQuantidade, gbc_txtQuantidade);
			txtQuantidade.setColumns(10);
		}
		{
			JLabel lblItens = new JLabel("ITENS");
			GridBagConstraints gbc_lblItens = new GridBagConstraints();
			gbc_lblItens.anchor = GridBagConstraints.WEST;
			gbc_lblItens.insets = new Insets(0, 0, 5, 0);
			gbc_lblItens.gridx = 0;
			gbc_lblItens.gridy = 3;
			contentPanel.add(lblItens, gbc_lblItens);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 4;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0};
			gbl_panel.rowHeights = new int[]{273, 0};
			gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 0;
				gbc_scrollPane.gridy = 0;
				panel.add(scrollPane, gbc_scrollPane);
				{
					table = new JTable();
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 5;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblValorTotal = new JLabel("VALOR TOTAL");
				GridBagConstraints gbc_lblValorTotal = new GridBagConstraints();
				gbc_lblValorTotal.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblValorTotal.insets = new Insets(0, 0, 5, 5);
				gbc_lblValorTotal.gridx = 0;
				gbc_lblValorTotal.gridy = 0;
				panel.add(lblValorTotal, gbc_lblValorTotal);
			}
			{
				txtValorTotal = new JTextField();
				txtValorTotal.setEnabled(false);
				GridBagConstraints gbc_txtValorTotal = new GridBagConstraints();
				gbc_txtValorTotal.insets = new Insets(0, 0, 5, 0);
				gbc_txtValorTotal.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtValorTotal.gridx = 1;
				gbc_txtValorTotal.gridy = 0;
				panel.add(txtValorTotal, gbc_txtValorTotal);
				txtValorTotal.setColumns(10);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("VALOR PAGAMENTO");
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_3.gridx = 0;
				gbc_lblNewLabel_3.gridy = 1;
				panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
			}
			{
				txtValorPagamento = new JTextField();
				GridBagConstraints gbc_txtValorPagamento = new GridBagConstraints();
				gbc_txtValorPagamento.insets = new Insets(0, 0, 5, 0);
				gbc_txtValorPagamento.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtValorPagamento.gridx = 1;
				gbc_txtValorPagamento.gridy = 1;
				panel.add(txtValorPagamento, gbc_txtValorPagamento);
				txtValorPagamento.setColumns(10);
			}
			{
				JLabel lblValorTroco = new JLabel("VALOR TROCO");
				GridBagConstraints gbc_lblValorTroco = new GridBagConstraints();
				gbc_lblValorTroco.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblValorTroco.insets = new Insets(0, 0, 0, 5);
				gbc_lblValorTroco.gridx = 0;
				gbc_lblValorTroco.gridy = 2;
				panel.add(lblValorTroco, gbc_lblValorTroco);
			}
			{
				textField = new JTextField();
				textField.setEnabled(false);
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 1;
				gbc_textField.gridy = 2;
				panel.add(textField, gbc_textField);
				textField.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 6;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JButton btnFinalizar = new JButton("Fechar");
				btnFinalizar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						criarTransacao();
					}
				});
				GridBagConstraints gbc_btnFinalizar = new GridBagConstraints();
				gbc_btnFinalizar.gridx = 1;
				gbc_btnFinalizar.gridy = 0;
				panel.add(btnFinalizar, gbc_btnFinalizar);
			}
		}
	}
	
	protected void criarTransacao() {
		
		venda = new TransacaoVenda();
		
		
	}

	public synchronized static TelaVenda getInstancia() {
		if(INSTANCIA == null) {
			INSTANCIA = new TelaVenda();
		}
		return INSTANCIA;
	}
	
	private static void carregaProdutos(){
		comboProduto.removeAllItems();
		comboProduto.addItem("");
		
		for (Produto produto : ProdutoDAO.listaprodutos()) {
			comboProduto.addItem(produto.getDescricao());
		}
	}
	
	private static void carregaCliente(){
		comboCliente.removeAllItems();
		comboCliente.addItem("");
		
		for (Cliente cliente : ClienteDAO.listaClientes()) {
			comboCliente.addItem(cliente.getNome());
		}
	}
	
	private static void adicionalistaItens(){
		
		Produto produto = daoProduto.getProduto(comboProduto.getSelectedItem().toString());
		TransacaoVendaItem item = new TransacaoVendaItem();
		
		item.setIdProduto(produto.getId());
		item.setDescricaoProduto(produto.getDescricao());
		item.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
		item.setVlProduto(produto.getMargemLucro());
		
		listaItens.add(item);
		atualizaLista();
		
		
	}
	
	public static void atualizaLista(){		
		table.setModel(new TableModelVendaItem(listaItens));				
	}

}
