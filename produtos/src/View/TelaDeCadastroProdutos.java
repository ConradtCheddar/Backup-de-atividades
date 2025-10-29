package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class TelaDeCadastroProdutos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtPreco;
	private JButton btnCadastrar, btnVoltar;
	private JComboBox cbCategoria;
	private JTextArea txtDescricao;
	private JTextField txtEstoque;

	/**
	 * Create the panel.
	 */
	public TelaDeCadastroProdutos() {
		setLayout(null);
		setPreferredSize(new Dimension(500, 400));
		
		JLabel lblTitulo = new JLabel("Cadastro de produto");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(0, 3, 450, 31);
		add(lblTitulo);
		
		JLabel lblNomeProduto = new JLabel("Nome do produto");
		lblNomeProduto.setBounds(21, 45, 196, 14);
		add(lblNomeProduto);
		
		txtNome = new JTextField();
		txtNome.setBounds(21, 70, 196, 20);
		add(txtNome);
		txtNome.setColumns(10);
		
		txtPreco = new JTextField();
		txtPreco.setBounds(21, 126, 86, 20);
		add(txtPreco);
		txtPreco.setColumns(10);
		
		JLabel lblPreco = new JLabel("Preço");
		lblPreco.setBounds(21, 101, 86, 14);
		add(lblPreco);
		
		cbCategoria = new JComboBox();
		cbCategoria.setBounds(256, 69, 213, 22);
		add(cbCategoria);
		
		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(21, 157, 196, 14);
		add(lblDescricao);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnCadastrar.setBounds(280, 306, 173, 48);
		add(btnCadastrar);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(256, 45, 213, 14);
		add(lblCategoria);
		
		txtEstoque = new JTextField();
		txtEstoque.setBounds(131, 126, 86, 20);
		add(txtEstoque);
		txtEstoque.setColumns(10);
		
		JLabel lblEstoque = new JLabel("Quantidade");
		lblEstoque.setBounds(131, 101, 86, 14);
		add(lblEstoque);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(322, 272, 89, 23);
		add(btnVoltar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 182, 200, 200);
		add(scrollPane);
		
		txtDescricao = new JTextArea();
		scrollPane.setViewportView(txtDescricao);
		txtDescricao.setLineWrap(true);
		txtDescricao.setWrapStyleWord(true);

	}
	
	/**
	 * metodo responsavel por salvar o dado do campo "nome do produto"
	 */
	public String getNome_Produto() {
		return this.txtNome.getText();
	}
	
	/**
	 * metodo responsavel por salvar o dado do campo "preço"
	 */
	public String getPreco() {
		return this.txtPreco.getText();
	}
	
	/**
	 * metodo responsavel por salvar o dado do campo "categoria"
	 */
	public String getCategoria() {
		return this.cbCategoria.getSelectedItem().toString();
	}
	
	/**
	 * metodo responsavel por salvar o dado do campo "descrição"
	 */
	public String getDescricao() {
		return this.txtDescricao.getText();
	}
	
	/**
	 * metodo responsavel por salvar o dado do campo "estoque"
	 */
	public String getEstoque() {
		return this.txtEstoque.getText();
	}
	
	/**
	 * Limpa todos os campos do formulário
	 */
	public void clearFields() {
		this.txtNome.setText("");
		this.txtPreco.setText("");
		this.txtDescricao.setText("");
		this.txtEstoque.setText("");
		this.cbCategoria.setSelectedIndex(0);
	}
	
	/**
	 * Adiciona as categorias no ComboBox
	 */
	public void setItems() {
		this.cbCategoria.addItem("Hortifrúti");
		this.cbCategoria.addItem("Mercearia");
		this.cbCategoria.addItem("Padaria");
		this.cbCategoria.addItem("Açougue/Peixaria");
		this.cbCategoria.addItem("Frios e Laticínios");
		this.cbCategoria.addItem("Bebidas");
		this.cbCategoria.addItem("Higiene e Beleza");
		this.cbCategoria.addItem("Produtos de Limpeza");
	}
	
	/**
	 * funcionalidade do botao cadastrar
	 */
	public void cadastrarProdutos(ActionListener action) {
		this.btnCadastrar.addActionListener(action);
	}
	
	/**
	 * Metodo responsavel pelo funcionamento do botão "Voltar"
	 */
	public void voltar(ActionListener action) {
		btnVoltar.addActionListener(action);
	}
}