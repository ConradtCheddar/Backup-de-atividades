package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TelaDeLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario, txtCPF;
	private JButton btnConfirma, btnSemCadastro;
	private JCheckBox checkAdmin;
	private JLabel Titulo;

	/**
	 * Create the panel.
	 */
	public TelaDeLogin() {
		setLayout(null);
		setPreferredSize(new Dimension(500, 400));
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuario.setBounds(46, 68, 116, 23);
		add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setToolTipText("");
		txtUsuario.setBounds(46, 120, 403, 23);
		add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(46, 168, 116, 23);
		add(lblNewLabel);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(46, 222, 403, 23);
		add(txtCPF);
		txtCPF.setColumns(10);
		
		checkAdmin = new JCheckBox("Admin?");
		checkAdmin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		checkAdmin.setBounds(204, 264, 97, 23);
		add(checkAdmin);
		
		btnConfirma = new JButton("Confirmar");
		btnConfirma.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnConfirma.setBounds(172, 305, 160, 39);
		add(btnConfirma);	
		
		btnSemCadastro = new JButton("Sem Cadastro?");
		btnSemCadastro.setBounds(191, 366, 126, 23);
		add(btnSemCadastro);
		
		Titulo = new JLabel("LOGIN");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Titulo.setBounds(133, 11, 214, 46);
		add(Titulo);
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