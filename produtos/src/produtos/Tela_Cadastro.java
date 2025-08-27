package produtos;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela_Cadastro extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtPreco;

	/**
	 * Create the panel.
	 */
	public Tela_Cadastro() {
		setLayout(null);
		
		JLabel lblNome = new JLabel("Nome do produto");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNome.setBounds(27, 88, 158, 24);
		add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(27, 123, 158, 24);
		add(txtNome);
		txtNome.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(222, 123, 158, 24);
		add(comboBox);
		comboBox.addItem("Açougue");
		comboBox.addItem("Hortifruti");
		comboBox.addItem("Padaria");
		comboBox.addItem("Higiene");
		comboBox.addItem("Pets");
		comboBox.addItem("Frios");
		comboBox.addItem("Bebidas");
		comboBox.addItem("Mercearia");
		
		JButton btnCadastro = new JButton("Cadastrar");
		btnCadastro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCadastro.setBounds(232, 158, 124, 33);
		add(btnCadastro);
		
		JLabel lblTitulo = new JLabel("Cadastro de produtos");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitulo.setBounds(10, 11, 430, 33);
		add(lblTitulo);
		
		JLabel lblPreco = new JLabel("Preço");
		lblPreco.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPreco.setBounds(27, 160, 158, 24);
		add(lblPreco);
		
		txtPreco = new JTextField();
		txtPreco.setColumns(10);
		txtPreco.setBounds(27, 195, 158, 24);
		add(txtPreco);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCategoria.setBounds(222, 88, 158, 24);
		add(lblCategoria);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBounds(232, 202, 124, 33);
		add(btnEditar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRemover.setBounds(232, 246, 124, 33);
		add(btnRemover);

	}
}
