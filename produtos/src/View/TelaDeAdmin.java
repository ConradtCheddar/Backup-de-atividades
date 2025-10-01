package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TelaDeAdmin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtPreco;
	private JButton btnCadastrar;
	private JComboBox cbCategoria;

	/**
	 * Create the panel.
	 */
	public TelaDeAdmin() {
		setLayout(null);
		setPreferredSize(new Dimension(500, 400));
		
		JLabel lblTitulo = new JLabel("Cadastro de produto");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(0, 3, 450, 31);
		add(lblTitulo);
		
		JLabel lblNomeProduto = new JLabel("Nome do produto");
		lblNomeProduto.setBounds(21, 45, 86, 14);
		add(lblNomeProduto);
		
		txtNome = new JTextField();
		txtNome.setBounds(21, 70, 196, 20);
		add(txtNome);
		txtNome.setColumns(10);
		
		txtPreco = new JTextField();
		txtPreco.setBounds(21, 126, 196, 20);
		add(txtPreco);
		txtPreco.setColumns(10);
		
		JLabel lblPreco = new JLabel("Preço");
		lblPreco.setBounds(21, 101, 86, 14);
		add(lblPreco);
		
		JComboBox cbCategoria = new JComboBox();
		cbCategoria.setBounds(256, 69, 213, 22);
		add(cbCategoria);
		
		JTextArea txtDescricao = new JTextArea();
		txtDescricao.setBounds(21, 182, 196, 193);
		add(txtDescricao);
		
		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(21, 157, 86, 14);
		add(lblDescricao);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnCadastrar.setBounds(280, 306, 173, 48);
		add(btnCadastrar);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(256, 45, 86, 14);
		add(lblCategoria);

	}
	
	/**
	 * metodo responsavel por salvar o dado do campo "nome"
	 */
	public String getUsuario() {
		return this.txtNome.getText();
	}
	
	/**
	 * metodo responsavel por salvar o dado do campo "preço"
	 */
	public String getCPF() {
		return this.txtPreco.getText();
	}
	
	
	/**
	 * Metodo responsavel pelo funcionamento do botão "Confirmar"
	 */
	public void cadastrar(ActionListener actionListener) {
		this.btnCadastrar.addActionListener(actionListener);
	}
	
	/**
	 * Metodo responsavel pelo funcionamento do combobox "categoria"
	 * @return 
	 */
	public String categoria() {
		return cbCategoria.getSelectedItem().toString();
	}
	
	
}
