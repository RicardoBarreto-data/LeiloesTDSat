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

    
    public ArrayList<ProdutosDTO> listarProdutos() {
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet resultset = null;

    try {
        conn = new conectaDAO().connectDB(); // substitua com seu método real de conexão
        String sql = "SELECT * FROM produtos";
        pstm = conn.prepareStatement(sql);
        resultset = pstm.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));

            listagem.add(produto);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
    } finally {
        try {
            if (resultset != null) resultset.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }

    return listagem;}
   
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    ArrayList<ProdutosDTO> vendidos = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet resultset = null;

    try {
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        pstm = conn.prepareStatement(sql);
        resultset = pstm.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));

            vendidos.add(produto);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
    } finally {
        try {
            if (resultset != null) resultset.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }

    return vendidos;
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
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
  }
}

