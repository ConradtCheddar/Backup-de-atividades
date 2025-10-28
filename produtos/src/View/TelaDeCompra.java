package View;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Model.Produto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaDeCompra extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnComprar, btnVoltar, btnVisualizar;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public TelaDeCompra() {
		setLayout(null);
		setPreferredSize(new Dimension(500, 400));
		
		JLabel lblTitulo = new JLabel("Visualização de produtos");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(0, 3, 500, 31);
		add(lblTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 45, 333, 291);
		add(scrollPane);
		
		// Adiciona coluna de ID para corresponder aos dados adicionados no atualizarTable
		String colunas[]= {
			"ID", "Nome", "Categoria", "preço", "Descrição", "Q_estoque" 
		};
		
		Object dados[][]= new Object[0][6];
		
		this.model = new DefaultTableModel(dados,colunas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		// Cria a JTable com o model (antes a variável table era nula)
		this.table = new JTable(this.model);
		scrollPane.setViewportView(this.table);
		
		btnComprar = new JButton("Comprar");
		btnComprar.setBounds(386, 75, 100, 23);
		add(btnComprar);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(386, 341, 89, 23);
		add(btnVoltar);
		
		btnVisualizar = new JButton("Visualizar");
		btnVisualizar.setBounds(386, 123, 100, 23);
		add(btnVisualizar);

	}
	
	public void atualizarTable(ArrayList<Produto> lista) {
		if (lista == null) return; // Proteção caso a lista seja nula
		this.model.setRowCount(0); // Clear table before adding new rows
		for (Produto p : lista) {
			Object[] newRowData = {p.getId(), p.getNome_produto(), p.getCategoria(), p.getPreco(), p.getDescricao(), p.getQ_estoque()};
			this.model.addRow(newRowData);
		}
			
	}
	
	/**
	 * funcionalidade do botao comprar
	 */
	
	public void comprar(ActionListener action) {
		this.btnComprar.addActionListener(action);
	}
	
	/**
	 * funcionalidade do botao voltar
	 */
	public void voltar(ActionListener action) {
		this.btnVoltar.addActionListener(action);
	}
	
	/**
	 * funcionalidade do botao voltar
	 */
	public void visualizar(ActionListener action) {
		this.btnVisualizar.addActionListener(action);
	}
	
	
	/**
	 * getters e setters
	 */
	public JTable getTable() {
        return this.table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public JButton getBtnComprar() {
        return btnComprar;
    }

    public void setBtnComprar(JButton btnComprar) {
        this.btnComprar = btnComprar;
    }

    public JButton getBtnVoltar() {
        return btnVoltar;
    }

    public void setBtnVoltar(JButton btnVoltar) {
        this.btnVoltar = btnVoltar;
    }

    // Utility methods
    public Integer getSelectedId() {
        int row = table.getSelectedRow();
        if (row != -1) {
            return (Integer) table.getValueAt(row, 0);
        }
        return null;
    }

    public Integer getSelectedQuantity() {
        int row = table.getSelectedRow();
        if (row != -1) {
            return (Integer) table.getValueAt(row, 5); // Q_estoque column
        }
        return null;
    }

    public String getSelectedProduct() {
        int row = table.getSelectedRow();
        if (row != -1) {
            return (String) table.getValueAt(row, 1); // Nome column
        }
        return null;
    }

    public Double getSelectedPrice() {
        int row = table.getSelectedRow();
        if (row != -1) {
            Object value = table.getValueAt(row, 3); // Preço column
            if (value instanceof Double) {
                return (Double) value;
            } else if (value instanceof String) {
                return Double.parseDouble(((String) value).replace(",", "."));
            }
        }
        return null;
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}