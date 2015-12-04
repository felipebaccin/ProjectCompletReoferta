package br.univel.Tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastros = new JMenu("Cadastros");
		menuBar.add(mnCadastros);
		
		JMenuItem mntmCadastroDeCliente = new JMenuItem("Cadastro de cliente");
		mntmCadastroDeCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroClienteDialog ccd = new CadastroClienteDialog();
				ccd.setVisible(true);
				
			}
		});
		mnCadastros.add(mntmCadastroDeCliente);
		
		JMenuItem mntmCadastroDe = new JMenuItem("Cadastro de Produto");
		mntmCadastroDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProdutoDialog cpd = new CadastroProdutoDialog();
				cpd.setVisible(true);
			
			}
		});
		mnCadastros.add(mntmCadastroDe);
		
		JMenu mnVenda = new JMenu("Venda");
		menuBar.add(mnVenda);
		
		JMenuItem mntmVendaDeProduto = new JMenuItem("Venda de produto ");
		mntmVendaDeProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaVenda vd = new TelaVenda();
				setVisible(true);
			}
		});
		mnVenda.add(mntmVendaDeProduto);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
