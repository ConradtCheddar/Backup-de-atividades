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
            // Normalizar CPF (remover pontos/traços) para comparação consistente
            String cpfNorm = cpf == null ? "" : cpf.replaceAll("\\D+", "");
            if (cpfNorm.length() != 11) {
                JOptionPane.showMessageDialog(null, "CPF inválido. Insira 11 dígitos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/MercadoBD",
                "mercado",
                "1234"
            );

            String sql = "SELECT ID_usuario, nome_usuario, CPF, admin FROM Usuarios WHERE nome_usuario = ? AND REPLACE(REPLACE(REPLACE(CPF, '.', ''), '-', ''), ' ', '') = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, cpfNorm);

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
            // Se não encontrou a combinação nome+CPF, somente retornar null; a controller lida com mensagens específicas
            rs.close();
            stmt.close();

        } catch (Exception ex) {
            System.err.println("[ERROR] Erro ao fazer login: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.err.println("[ERROR] Erro ao fechar conex\u00e3o: " + e.getMessage());
                }
            }
        }
        return null;
    }
    
    /**
     * Retorna o nome do usuário associado ao CPF (normaliza o CPF antes da busca). Retorna null se não encontrado.
     */
    public String buscarNomePorCPF(String cpf) {
        if (cpf == null) return null;
        String cpfNorm = cpf.replaceAll("\\D+", "");
        if (cpfNorm.length() != 11) return null;

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/MercadoBD",
                "mercado",
                "1234"
            );

            String sqlCpfOnly = "SELECT nome_usuario FROM Usuarios WHERE REPLACE(REPLACE(REPLACE(CPF, '.', ''), '-', ''), ' ', '') = ?";
            PreparedStatement stmtCpf = conn.prepareStatement(sqlCpfOnly);
            stmtCpf.setString(1, cpfNorm);
            ResultSet rsCpf = stmtCpf.executeQuery();
            if (rsCpf.next()) {
                String nomeCadastrado = rsCpf.getString("nome_usuario");
                rsCpf.close();
                stmtCpf.close();
                return nomeCadastrado;
            }
            rsCpf.close();
            stmtCpf.close();
        } catch (Exception ex) {
            System.err.println("[ERROR] Erro ao buscar CPF: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.err.println("[ERROR] Erro ao fechar conex\u00e3o: " + e.getMessage());
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
        
        // Normalizar CPF (remover pontos/traços) e validar 11 dígitos
        String cpfNorm = u.getCpf() == null ? "" : u.getCpf().replaceAll("\\D+", "");
        if (cpfNorm.length() != 11) {
            JOptionPane.showMessageDialog(null, "CPF inválido. Insira 11 dígitos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validar tamanho do nome conforme esquema do banco
        if (u.getUsuario().length() > 50) {
            JOptionPane.showMessageDialog(null, "Nome de usu\u00e1rio muito longo (m\u00e1x 50 caracteres)", "Erro", JOptionPane.ERROR_MESSAGE);
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

            // Checar se CPF já existe antes de inserir (comparar sem formatação)
            String checkSql = "SELECT ID_usuario FROM Usuarios WHERE REPLACE(REPLACE(REPLACE(CPF, '.', ''), '-', ''), ' ', '') = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, cpfNorm);
            ResultSet rsCheck = checkStmt.executeQuery();
            if (rsCheck.next()) {
                JOptionPane.showMessageDialog(null, "CPF j\u00e1 cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                rsCheck.close();
                checkStmt.close();
                return;
            }
            rsCheck.close();
            checkStmt.close();

            String sql = "INSERT INTO Usuarios (nome_usuario, CPF, admin) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, u.getUsuario());
            // Armazenar CPF normalizado (apenas dígitos) para consistência
            stmt.setString(2, cpfNorm);
            stmt.setBoolean(3, u.isAdmin());

            stmt.executeUpdate();
            stmt.close();
            
            JOptionPane.showMessageDialog(null, "Usu\u00e1rio cadastrado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.err.println("[ERROR] Erro ao cadastrar usu\u00e1rio: " + e.getMessage());
            if (e.getMessage().contains("Duplicate")) {
                JOptionPane.showMessageDialog(null, "CPF j\u00e1 cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.err.println("[ERROR] Erro ao fechar conex\u00e3o: " + e.getMessage());
                }
            }
        }
    }
}