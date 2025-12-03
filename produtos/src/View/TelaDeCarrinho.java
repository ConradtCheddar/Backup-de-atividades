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

public class TelaDeCarrinho extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnVoltar, btnComprar;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public TelaDeCarrinho() {
		setLayout(null);
		setPreferredSize(new Dimension(500, 400));
		
		JLabel lblTitulo = new JLabel("Visualização de produtos");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(0, 3, 500, 31);
		add(lblTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 333, 291);
		add(scrollPane);
		
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
		this.table = new JTable(this.model);
		scrollPane.setViewportView(this.table);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(386, 341, 89, 23);
		add(btnVoltar);
		
		btnComprar = new JButton("Comprar");
		btnComprar.setBounds(386, 71, 89, 23);
		add(btnComprar);

	}
	
	public void atualizarTable(ArrayList<Produto> lista) {
		if (lista == null) return;
		this.model.setRowCount(0);
		for (Produto p : lista) {
			Object[] newRowData = {p.getId(), p.getNome_produto(), p.getCategoria(), p.getPreco(), p.getDescricao(), p.getQ_estoque()};
			this.model.addRow(newRowData);
		}
			
	}
	
	
	
	/**
	 * funcionalidade do botao voltar
	 */
	public void voltar(ActionListener action) {
		this.btnVoltar.addActionListener(action);
	}
	
	/**
	 * funcionalidade do botao comprar
	 */
	public void comprar(ActionListener action) {
		this.btnComprar.addActionListener(action);
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

    public JButton getBtnVoltar() {
        return btnVoltar;
    }

    public void setBtnVoltar(JButton btnVoltar) {
        this.btnVoltar = btnVoltar;
    }

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
            return (Integer) table.getValueAt(row, 5);
        }
        return null;
    }

    public String getSelectedProduct() {
        int row = table.getSelectedRow();
        if (row != -1) {
            return (String) table.getValueAt(row, 1);
        }
        return null;
    }

    public Double getSelectedPrice() {
        int row = table.getSelectedRow();
        if (row != -1) {
            Object value = table.getValueAt(row, 3);
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