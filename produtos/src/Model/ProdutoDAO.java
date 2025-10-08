package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Controller.Navegador;

public class ProdutoDAO {
	
	private final Navegador n;
	
	public ProdutoDAO(Navegador n) {
		this.n = n;
	}
	
	
	public void cadastrarProdutos(Produto p) throws ClassNotFoundException {

			if (p.getNome_produto().isEmpty() || p.getCategoria().isEmpty() || p.getPreco() == 0 || p.getDescricao().isEmpty() 
					|| p.getQ_estoque() == 0) {

				JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);

			} else {

					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection conn = DriverManager.getConnection(
								"jdbc:mysql://localhost:3306/MercadoBD",
							    "mercado",
							    "1234"
							);

						String sql = "INSERT INTO Produtos (nome_produto, categoria, preco, descrição, q_estoque) VALUES (?, ?, ?, ?, ?)";
						var stmt = conn.prepareStatement(sql);
						
						stmt.setString(1, p.getNome_produto()); // Nome
						stmt.setString(2, p.getCategoria()); // Categoria
						stmt.setDouble(3, p.getPreco()); // preco
						stmt.setString(4, p.getDescricao()); // descrição
						stmt.setInt(5, p.getQ_estoque()); // quantidade em estoque

						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "produto cadastrado com sucesso!", "Sucesso!",
								JOptionPane.PLAIN_MESSAGE);
						n.navegarPara("LOGIN");

						stmt.close();
						conn.close();
					} catch (SQLException e) {
					    e.printStackTrace();
					    JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
				;
			}

		} 
	}
	}