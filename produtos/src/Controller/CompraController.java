package Controller;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.ProdutoDAO;
import Model.UsuarioDAO;
import View.TelaDeCompra;

public class CompraController {
	private final ProdutoDAO model;
	private final Navegador navegador;
	private final TelaDeCompra view;
	
	
	public CompraController(TelaDeCompra view, ProdutoDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		
		
//		this.view.comprar(e ->{
//			JTable table = view.getTable();
//			DefaultTableModel modelTable = (DefaultTableModel) table.getModel();
//
//			int selectedRow = table.getSelectedRow();
//			if (selectedRow >= 0) {
//				// Pega o ID da linha selecionada (coluna 0, que é a coluna oculta)
//				Object idValue = table.getValueAt(selectedRow, 0);
//				if (idValue != null) {
//					int id = (int) idValue;
//
//					if (this.model.buscarServicoPorId(id)) {
//						JOptionPane.showMessageDialog(null, "Imposivel deletar trabalhos aceitos", "Erro",
//							JOptionPane.ERROR_MESSAGE);
//					} else {
//						// Confirmar exclusão
//						int option = JOptionPane.showConfirmDialog(null,
//								"Tem certeza que deseja deletar este trabalho?", "Confirmar Exclusão",
//								JOptionPane.YES_NO_OPTION);
//
//						if (option == JOptionPane.YES_OPTION) {
//							// Deletar a linha do banco de dados e da JTable
//							this.model.deletarServico(id);
//							modelTable.removeRow(selectedRow); // Remove da tabela visual
//						}
//					}
//				}
//			} else {
//				JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir.");
//			}
//		});
		
		this.view.voltar(e ->{
			navegador.navegarPara("LOGIN");
		});
		
		
		
	}

}
