/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto(ProdutosDTO produto) {
    Connection conn = null;
    PreparedStatement pstm = null;

    try {
        conn = new conectaDAO().connectDB(); // ou o método correto de conexão
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, produto.getNome());
        pstm.setInt(2, produto.getValor());
        pstm.setString(3, produto.getStatus());

        pstm.execute();
        return true;

    } catch (SQLException erro) {
        System.out.println("Erro ao cadastrar produto: " + erro.getMessage());
        return false;

    } finally {
        try {
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        } catch (SQLException erro) {
            System.out.println("Erro ao fechar conexão: " + erro.getMessage());
        }
    }
}

    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    ArrayList<ProdutosDTO> lista = new ArrayList<>();
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

    try {
        Connection conn = new conectaDAO().connectDB();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));
            lista.add(produto);
        }

        conn.close();
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
    return lista;
}
    
   public void venderProduto(int id) {
    Connection conn = null;
    PreparedStatement pstm = null;

    try {
        conn = new conectaDAO().connectDB();
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.executeUpdate();
        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
    } finally {
        try {
            if (pstm != null) pstm.close
        ();
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
   }
    public ArrayList<ProdutosDTO> listarTodosProdutos() {
    ArrayList<ProdutosDTO> lista = new ArrayList<>();
    String sql = "SELECT * FROM produtos"; // sem filtro por status

    try {
        Connection conn = new conectaDAO().connectDB();
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));
            lista.add(produto);
        }

        conn.close();
    } catch (Exception e) {
        System.out.println("Erro: " + e.getMessage());
    }
    return lista;
  }
}

