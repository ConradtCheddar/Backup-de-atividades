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

public class TelaDeCadastroUsuarios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario, txtCPF;
	private JButton btnCadastrar;
	private JCheckBox checkAdmin;
	private JLabel Titulo;
	private JButton btnVoltar;

	/**
	 * Create the panel.
	 */
	public TelaDeCadastroUsuarios() {
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
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnCadastrar.setBounds(172, 305, 160, 39);
		add(btnCadastrar);
		
		Titulo = new JLabel("CADASTRO");
		Titulo.setHorizontalAlignment(SwingConstants.CENTER);
		Titulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Titulo.setBounds(133, 11, 214, 46);
		add(Titulo);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(29, 344, 89, 23);
		add(btnVoltar);
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
	 * Metodo responsavel pelo funcionamento do botão "Cadastrar"
	 */
	public void cadastrarUsuarios(ActionListener actionListener) {
		this.btnCadastrar.addActionListener(actionListener);
	}
	/**
	 * Metodo responsavel pelo funcionamento do botão "Voltar"
	 */
	public void voltar(ActionListener action) {
		this.btnVoltar.addActionListener(action);
	}
	
	
	
	
	
		
}