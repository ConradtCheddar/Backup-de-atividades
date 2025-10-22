package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.Navegador;

public class ProdutoDAO {

	private final Navegador n;

	public ProdutoDAO(Navegador n) {
		this.n = n;
	}
	
	public Produto buscarServicoPorId(int id) {
		Produto p = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MercadoBD", "mercado",
					"1234");
			String sql = "SELECT * FROM Servico WHERE ID_servico = ?";
			var stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			var rs = stmt.executeQuery();
			if (rs.next()) {
				p = new Produto(getStringSafe(rs, "nome_servico", "nome_produto", "nome"), getStringSafe(rs, "categoria", "cat"),
						rs.getDouble("preco"), getStringSafe(rs, "descrição", "descricao"), rs.getInt("q_estoque"));
				p.setId(rs.getInt("ID_produto"));
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return p;
	}
	
	 public void deletarServico(int id) {
	     try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MercadoBD", "mercado",
					"1234");
			
			String sql = "Delete from Produtos where ID_produto = ?";
			var stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			
			stmt.close();
			conn.close();
			} catch (Exception ex) {
		        ex.printStackTrace();
		        JOptionPane.showMessageDialog(null, "Erro ao comprar.", "Erro", JOptionPane.ERROR_MESSAGE);
		    }
	    
	    }

	public void cadastrarProdutos(Produto p) throws ClassNotFoundException {

		if (p.getNome_produto().isEmpty() || p.getCategoria().isEmpty() || p.getPreco() == 0
				|| p.getDescricao().isEmpty() || p.getQ_estoque() == 0) {

			JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);

		} else {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MercadoBD", "mercado",
						"1234");

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
	
	/**
	 * Recupera todos os produtos da tabela Produtos
	 * Retorna uma lista vazia em caso de erro
	 */
	public ArrayList<Produto> buscarTodosProdutos() {
		ArrayList<Produto> lista = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MercadoBD", "mercado",
					"1234");

			String sql = "SELECT * FROM Produtos";
			var stmt = conn.prepareStatement(sql);

			var rs = stmt.executeQuery();

			while (rs.next()) {
				String nome = getStringSafe(rs, "nome_produto", "nomeproduto", "nome");
				String categoria = getStringSafe(rs, "categoria", "cat");
				double preco = 0d;
				try {
					preco = rs.getDouble("preco");
				} catch (Exception ex) {
					try { preco = rs.getDouble(3); } catch (Exception e) { /* ignore */ }
				}
				String descricao = getStringSafe(rs, "descrição", "descricao", "descricao_produto", "descricao_prod");
				int q_estoque = 0;
				try {
					q_estoque = rs.getInt("q_estoque");
				} catch (Exception ex) {
					try { q_estoque = rs.getInt(5); } catch (Exception e) { /* ignore */ }
				}

				Produto p = new Produto(nome, categoria, preco, descricao, q_estoque);
				// Ajuste do id conforme a coluna no banco
				try {
					p.setId(rs.getInt("ID_produto"));
				} catch (Exception ex) {
					try { p.setId(rs.getInt("id")); } catch (Exception e) { /* ignore */ }
				}
				lista.add(p);
			}

			rs.close();
			stmt.close();
			conn.close();
			return lista;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return lista; // retorna lista (possivelmente vazia) em caso de erro
	}
	
	public ArrayList<Produto> buscarTodosServicosPorid(Produto p) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MercadoBD", "mercado",
					"1234");

			String sql = "SELECT * FROM Servico WHERE id_contratante = ?";
			var stmt = conn.prepareStatement(sql);

			stmt.setInt(1, p.getId());

			var rs = stmt.executeQuery();

			ArrayList<Produto> listaServicos = new ArrayList<Produto>();
			while (rs.next()) {
				p = new Produto(getStringSafe(rs, "nome_servico", "nome_produto", "nome"), getStringSafe(rs, "categoria", "cat"),
						rs.getDouble("preco"), getStringSafe(rs, "descrição", "descricao"), rs.getInt("q_estoque"));
				try { p.setId(rs.getInt("ID_produto")); } catch (Exception e) { /* ignore */ }
				listaServicos.add(p);

			}

			rs.close();
			stmt.close();
			conn.close();
			return listaServicos;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Tenta obter o valor de uma coluna usando uma lista de nomes possíveis.
	 * Retorna string vazia caso nenhum nome exista ou ocorra erro.
	 */
	private String getStringSafe(java.sql.ResultSet rs, String... names) {
		for (String name : names) {
			if (name == null) continue;
			try {
				String v = rs.getString(name);
				if (v != null) return v;
			} catch (Exception ex) {
				// tenta próximo nome
			}
		}
		// tentativa por índice (como último recurso)
		try {
			String v = rs.getString(1);
			if (v != null) return v;
		} catch (Exception e) { /* ignore */ }
		return "";
	}
}