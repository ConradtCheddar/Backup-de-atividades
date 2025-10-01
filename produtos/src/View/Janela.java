package View;

import java.awt.CardLayout;
import java.awt.Dimension;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 400, 300);

		this.cardLayout = new CardLayout();

		this.contentPane = new JPanel(this.cardLayout);
		this.contentPane.setPreferredSize(new Dimension(500, 400)); 
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
