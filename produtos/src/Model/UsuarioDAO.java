package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    
    public Usuario login(String nome, String cpf) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/MercadoBD",
                "mercado",
                "1234"
            );

            String sql = "SELECT ID_usuario, nome_usuario, CPF, admin FROM Usuarios WHERE nome_usuario = ? AND CPF = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cpf);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario(
                    rs.getString("nome_usuario"),
                    rs.getString("CPF"),
                    rs.getBoolean("admin")
                );
                u.setId(rs.getInt("ID_usuario"));
                rs.close();
                stmt.close();
                return u;
            }
        } catch (Exception ex) {
            System.err.println("[ERROR] Erro ao fazer login: " + ex.getMessage());
            ex.printStackTrace();
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
    
    public void cadastrarU(Usuario u) throws ClassNotFoundException {
        if (u.getUsuario().isEmpty() || u.getCpf().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validar tamanho do nome e CPF conforme esquema do banco
        if (u.getUsuario().length() > 50) {
            JOptionPane.showMessageDialog(null, "Nome de usuário muito longo (máx 50 caracteres)", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (u.getCpf().length() > 15) {
            JOptionPane.showMessageDialog(null, "CPF inválido (máx 15 caracteres)", "Erro", JOptionPane.ERROR_MESSAGE);
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

            String sql = "INSERT INTO Usuarios (nome_usuario, CPF, admin) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, u.getUsuario());
            stmt.setString(2, u.getCpf());
            stmt.setBoolean(3, u.isAdmin());

            stmt.executeUpdate();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.err.println("[ERROR] Erro ao cadastrar usuário: " + e.getMessage());
            if (e.getMessage().contains("Duplicate")) {
                JOptionPane.showMessageDialog(null, "CPF já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
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