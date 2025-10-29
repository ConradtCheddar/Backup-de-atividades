package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    
    public ArrayList<Produto> buscarTodosProdutos() {
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/MercadoBD",
                "mercado",
                "1234"
            );

            String sql = "SELECT * FROM Produtos";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                try {
                    // Converte o preço de VARCHAR para Double
                    String precoStr = rs.getString("preco").replace(",", ".");
                    Double preco = Double.parseDouble(precoStr);
                    
                    Produto p = new Produto(
                        rs.getInt("ID_produto"),
                        rs.getString("nome_produto"),
                        rs.getString("categoria"),
                        rs.getDouble("preco"),
                        rs.getString("descricao"),
                        rs.getInt("q_estoque")
                    );
                    listaProdutos.add(p);
                } catch (Exception e) {
                    System.err.println("[ERROR] Erro ao carregar produto: " + e.getMessage());
                }
            }
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.err.println("[ERROR] Erro ao buscar produtos: " + ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.err.println("[ERROR] Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
        return listaProdutos;
    }

    public boolean deletarProduto(int id) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/MercadoBD",
                "mercado",
                "1234"
            );

            String sql = "DELETE FROM Produtos WHERE ID_produto = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            return rowsAffected > 0;
        } catch (Exception ex) {
            System.err.println("[ERROR] Erro ao deletar produto: " + ex.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.err.println("[ERROR] Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }

    public Produto buscarProdutoPorId(int id) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/MercadoBD",
                "mercado",
                "1234"
            );

            String sql = "SELECT * FROM Produtos WHERE ID_produto = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String precoStr = rs.getString("preco").replace(",", ".");
                Double preco = Double.parseDouble(precoStr);
                
                Produto p = new Produto(
                    rs.getInt("ID_produto"),
                    rs.getString("nome_produto"),
                    rs.getString("categoria"),
                    rs.getDouble("preco"),
                    rs.getString("descricao"),
                    rs.getInt("q_estoque")
                );
                rs.close();
                stmt.close();
                return p;
            }
        } catch (Exception ex) {
            System.err.println("[ERROR] Erro ao buscar produto: " + ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.err.println("[ERROR] Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
        return null;
    }

    public boolean atualizarProduto(int idProduto, Produto p) {
        // Validações de acordo com o schema do banco
        if (p.getNome_produto().length() > 20 || p.getCategoria().length() > 20) {
            JOptionPane.showMessageDialog(null, "Nome do produto e categoria devem ter no máximo 20 caracteres", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String precoStr = String.format("%.2f", p.getPreco());
        if (precoStr.length() > 10) {
            JOptionPane.showMessageDialog(null, "Valor do preço muito grande", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/MercadoBD",
                "mercado",
                "1234"
            );
            
            String sql = "UPDATE Produtos SET nome_produto = ?, categoria = ?, preco = ?, descricao = ?, q_estoque = ? WHERE ID_produto = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, p.getNome_produto());
            stmt.setString(2, p.getCategoria());
            stmt.setDouble(3, p.getPreco());
            stmt.setString(4, p.getDescricao());
            stmt.setInt(5, p.getQ_estoque());
            stmt.setInt(6, idProduto);
            
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            return rowsAffected > 0;
        } catch (Exception ex) {
            System.err.println("[ERROR] Erro ao atualizar produto: " + ex.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.err.println("[ERROR] Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }

    public void cadastrarProdutos(Produto p) throws ClassNotFoundException {
        // Validações de acordo com o schema do banco
        if (p.getNome_produto().length() > 20 || p.getCategoria().length() > 20) {
            JOptionPane.showMessageDialog(null, "Nome do produto e categoria devem ter no máximo 20 caracteres", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String precoStr = String.format("%.2f", p.getPreco());
        if (precoStr.length() > 10) {
            JOptionPane.showMessageDialog(null, "Valor do preço muito grande", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (p.getNome_produto().isEmpty() || p.getCategoria().isEmpty() || p.getPreco() == 0
                || p.getDescricao().isEmpty() || p.getQ_estoque() == 0) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/MercadoBD",
                "mercado",
                "1234"
            );

            String sql = "INSERT INTO Produtos (nome_produto, categoria, preco, descricao, q_estoque) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, p.getNome_produto());
            stmt.setString(2, p.getCategoria());
            stmt.setDouble(3, p.getPreco());
            stmt.setString(4, p.getDescricao());
            stmt.setInt(5, p.getQ_estoque());

            stmt.executeUpdate();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.err.println("[ERROR] Erro ao cadastrar produto: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.err.println("[ERROR] Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }
}