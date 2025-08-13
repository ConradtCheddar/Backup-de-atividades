package produtos;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class Tela_Cadastro extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Tela_Cadastro() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("cadastro");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 62));
		lblNewLabel.setBounds(10, 106, 417, 89);
		add(lblNewLabel);

	}

}
