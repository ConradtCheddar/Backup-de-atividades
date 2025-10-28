package View;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Model.Produto;
import java.awt.event.ActionListener;

public class TelaDeProdutos extends JPanel {
    private static final long serialVersionUID = 1L;
    private JButton btnVisualizar, btnEditar, btnDeletar, btnVoltar, btnAdicionar;
    private JTable table;
    private DefaultTableModel model;

    /**
	 * Create the panel.
	 */
	public TelaDeProdutos() {
		setLayout(null);
		setPreferredSize(new Dimension(500, 400));
		
		JLabel lblTitulo = new JLabel("Visualização de produto");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(0, 3, 500, 31);
		add(lblTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 45, 333, 291);
		add(scrollPane);
		
		// Adiciona coluna de ID para corresponder aos dados adicionados no atualizarTable
		String colunas[]= {
			"ID", "Nome", "Categoria", "Preço", "Descrição", "Q_estoque" 
		};
		
		Object dados[][]= new Object[0][6];
		
		this.model = new DefaultTableModel(dados,colunas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column != 0; // ID column not editable
			}
		};
		// Cria a JTable com o model (antes a variável table era nula)
		this.table = new JTable(this.model);
		scrollPane.setViewportView(this.table);
		
		btnVisualizar = new JButton("Visualizar");
		btnVisualizar.setBounds(372, 75, 100, 23);
		add(btnVisualizar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(372, 122, 100, 23);
		add(btnEditar);
		
		btnDeletar = new JButton("Deletar");
		btnDeletar.setBounds(372, 170, 100, 23);
		add(btnDeletar);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(372, 220, 100, 23);
		add(btnAdicionar);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(386, 341, 89, 23);
		add(btnVoltar);

	}
	
	public void atualizarTable(ArrayList<Produto> lista) {
		if (lista == null) return; // Proteção caso a lista seja nula
		this.model.setRowCount(0); // Clear table before adding new rows
		for (Produto p : lista) {
			Object[] newRowData = {p.getId(), p.getNome_produto(), p.getCategoria(), p.getPreco(), p.getDescricao(), p.getQ_estoque()};
			this.model.addRow(newRowData);
		}
		System.out.println("[DEBUG] Atualizando tabela com " + lista.size() + " produtos");
			
	}
	
	/**
	 * funcionalidade do botao visualizar
	 */
	public void visualizar(ActionListener action) {
		this.btnVisualizar.addActionListener(action);
	}
	
	/**
	 * funcionalidade do botao voltar
	 */
	public void voltar(ActionListener action) {
		this.btnVoltar.addActionListener(action);
	}
	
	/**
	 * funcionalidade do botao editar
	 */
	
	public void editar(ActionListener action) {
		this.btnEditar.addActionListener(action);
	}
	
	/**
	 * funcionalidade do botao deletar
	 */
	public void deletar(ActionListener action) {
		this.btnDeletar.addActionListener(action);
	}
	
	/**
	 * funcionalidade do botao adicionar
	 */
	public void adicionar(ActionListener action) {
		this.btnAdicionar.addActionListener(action);
	}
	
	/**
	 * Get the JTable instance
	 */
	public JTable getTable() {
		return this.table;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	// Utility methods
	public Integer getSelectedId() {
		int row = table.getSelectedRow();
		if (row != -1) {
			return (Integer) table.getValueAt(row, 0);
		}
		return null;
	}
}