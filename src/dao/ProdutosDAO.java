package dao;

import dto.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto(ProdutosDTO produto) {
    conn = null;
    PreparedStatement query = null;

    try {
        conn = new conectaDAO().connectDB();

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        query = conn.prepareStatement(sql);

        query.setString(1, produto.getNome());
        query.setDouble(2, produto.getValor());
        query.setString(3, produto.getStatus());

        int linhasAfetadas = query.executeUpdate();
        return linhasAfetadas > 0; // Retorna true se ao menos uma linha foi afetada
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        return false;
    } finally {
        try {
            if (query != null) query.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar recursos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

