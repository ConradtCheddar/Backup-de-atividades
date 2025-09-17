package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import produtos.Tela_Cadastro;
import produtos.Tela_Produtos;

public class TelaDeLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario, txtCPF;
	private JButton btnConfirma, btnSemCadastro;
	private JCheckBox checkAdmin;

	/**
	 * Create the panel.
	 */
	public TelaDeLogin() {
		setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuario.setBounds(48, 27, 116, 23);
		add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setToolTipText("");
		txtUsuario.setBounds(48, 61, 344, 23);
		add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(48, 108, 116, 23);
		add(lblNewLabel);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(48, 147, 344, 23);
		add(txtCPF);
		txtCPF.setColumns(10);
		
		JCheckBox checkAdmin = new JCheckBox("Admin?");
		checkAdmin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		checkAdmin.setBounds(48, 207, 97, 23);
		add(checkAdmin);
		
		JButton btnConfirma = new JButton("Confirmar");
		btnConfirma.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnConfirma.setBounds(205, 191, 160, 39);
		add(btnConfirma);	
		
		JButton btnSemCadastro = new JButton("Sem Cadastro?");
		btnSemCadastro.setBounds(335, 11, 105, 23);
		add(btnSemCadastro);
	}
	
	/**
	 * metodo responsavel por salvar o dado do campo "nome"
	 */
	public String getUsuario() {
		return this.txtUsuario.getText();
	}
	
	/**
	 * metodo responsavel por salvar o dado do campo "cpf"
	 */
	public String getCPF() {
		return this.txtCPF.getText();
	}
	
	/**
//	 * metodo responsavel por salvar o dado do campo "Admin?"
	 * @return 
	 */
	public JCheckBox getcheckAdmin() {
		return this.checkAdmin;
	}
	
	/**
	 * Metodo responsavel pelo funcionamento do botão "Confirmar"
	 */
	public void confirmar(ActionListener actionListener) {
		this.btnConfirma.addActionListener(actionListener);
	}
	
	/**
	 * Metodo responsavel pelo funcionamento do botão "Sem Cadastro?"
	 */
	public void SemCadastro(ActionListener actionListener) {
		this.btnSemCadastro.addActionListener(actionListener);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}