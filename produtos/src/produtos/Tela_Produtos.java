package produtos;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class Tela_Produtos extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Tela_Produtos() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("produtos");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 42));
		lblNewLabel.setBounds(24, 117, 416, 75);
		add(lblNewLabel);

	}
}
