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

    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

