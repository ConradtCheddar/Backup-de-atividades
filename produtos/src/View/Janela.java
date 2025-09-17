package View;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Janela extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static CardLayout cardLayout;

	/**
	 * Create the frame.
	 */

	public Janela() {

		this.cardLayout = new CardLayout();

		this.contentPane = new JPanel(cardLayout);
		this.contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(this.contentPane);

	}

	public void mostrarTela(String panelName) {
		this.cardLayout.show(this.contentPane, panelName);
		this.pack();
	}

	public void adicionarTela(String panelName, JPanel tela) {
		this.contentPane.add(tela, panelName);
	}
}
