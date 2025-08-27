package produtos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela_Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtCPF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Login frame = new Tela_Login();
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
	String usuarioA = "henrique";
	String usuarioN = "gustavo";
	String cpfA = "321";
	String cpfN = "123";
	String Emessage = "Usuario ou CPF incorretos";
	public Tela_Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuario.setBounds(48, 27, 116, 23);
		contentPane.add(lblUsuario);
		
		JLabel lblCPF = new JLabel("CPF");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCPF.setBounds(48, 108, 116, 23);
		contentPane.add(lblCPF);
		
		JCheckBox checkAdmin = new JCheckBox("Admin?");
		checkAdmin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		checkAdmin.setBounds(48, 207, 97, 23);
		contentPane.add(checkAdmin);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(48, 61, 344, 23);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(48, 147, 344, 23);
		contentPane.add(txtCPF);
		txtCPF.setColumns(10);
		
		JButton buttonConfirma = new JButton("Confirmar");
		buttonConfirma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkAdmin.isSelected()) {
					if(txtUsuario.getText().equals(usuarioA)&&txtCPF.getText().equals(cpfA)) {
						Tela_Cadastro cadastro = new Tela_Cadastro();
						setContentPane(cadastro);
						revalidate();
					}else {
						JOptionPane.showMessageDialog(null, Emessage, "Erro!!!",JOptionPane.ERROR_MESSAGE);
					}
				}else if (!checkAdmin.isSelected()) {
					if(txtUsuario.getText().equals(usuarioN)&&txtCPF.getText().equals(cpfN)) {
						Tela_Produtos produtos = new Tela_Produtos();
						setContentPane(produtos);
						revalidate();
					}else {
						JOptionPane.showMessageDialog(null, Emessage, "Erro!!!",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		buttonConfirma.setFont(new Font("Tahoma", Font.PLAIN, 25));
		buttonConfirma.setBounds(205, 191, 160, 39);
		contentPane.add(buttonConfirma);
		
		
	}
}
