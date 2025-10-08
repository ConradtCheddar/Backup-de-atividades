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
import javax.swing.JTable;

public class TelaDeCompra extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnCadastrar;
	private JComboBox cbCategoria;
	private JTable TabelaProdutos;

	/**
	 * Create the panel.
	 */
	public TelaDeCompra() {
		setLayout(null);
		setPreferredSize(new Dimension(500, 400));
		
		JLabel lblTitulo = new JLabel("Visualização de produto");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(0, 3, 500, 31);
		add(lblTitulo);
		
		TabelaProdutos = new JTable();
		TabelaProdutos.setBounds(26, 47, 166,307);
		add(TabelaProdutos);

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
