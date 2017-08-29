/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Produto;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import utilitario.Entity;

/**
 *
 * @author Elida
 */
public class ProdutoDao {
    private String insert = "insert into Produto (descricao) values (?)";
    private String select = "select * from Produto";
    private String selectPorId = "select * from Produto where id=?";
    private String selectPorFiltro = "select * from Produto where id=? or descricao like ?;";
    private String update = "update Produto set  descricao=? WHERE id=?;";
    private String delete = "delete from Produto WHERE id=?;";

    public Produto getProduto(int produtoId) {
        Produto Produto = null;
        String sql = "SELECT * FROM PRODUTO WHERE id= " + produtoId + ";";
        try {
        } catch (NoResultException ex) {
            return null;
        } catch (Exception e) {
            return null;
        }

        return Produto;
    }

    public  void adiciona(Produto produto) throws IOException {
        Connection con = null;
        PreparedStatement stmtAdiciona = null;
        try {
            con = Entity.getConnection();
            stmtAdiciona = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            // seta os valores
            stmtAdiciona.setString(1, produto.getDescricao());

            // executa
            stmtAdiciona.execute();
            //Seta o id do Produto
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            int i = rs.getInt(1);
            produto.setId(i);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmtAdiciona.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao fechar stmt.");
            }
            try {
                con.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao fechar conexão.");
            }

        }
    }

    public List<Produto> getLista() throws SQLException, IOException {
        Connection con = null;
        PreparedStatement stmtLista = null;
        ResultSet rs = null;
        try {
            con = Entity.getConnection();
            stmtLista = con.prepareStatement(select);
            rs = stmtLista.executeQuery();
            List<Produto> Produtos = new ArrayList();
            while (rs.next()) {
                // criando o objeto Produto
                Produto Produto = new Produto();
                Produto.setId(rs.getInt("id"));
                Produto.setDescricao(rs.getString("descricao"));
                Produtos.add(Produto);
            }

            return Produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao fechar rs.");
            }
            try {
                stmtLista.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao fechar stmt.");
            }
            try {
                con.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao fechar conexão.");
            }

        }
        return null;

    }
    
    public List<Produto> getLista(String filtro) throws SQLException, IOException {
        Connection con = null;
        PreparedStatement stmtLista = null;
        ResultSet rs = null;
        try {
            con = Entity.getConnection();
            stmtLista = con.prepareStatement(selectPorFiltro);
            stmtLista.setString(1, filtro);
            stmtLista.setString(2, "%" + filtro + "%");
            
            rs = stmtLista.executeQuery();
            List<Produto> Produtos = new ArrayList();
            while (rs.next()) {
                // criando o objeto Produto
                Produto Produto = new Produto();
                Produto.setId(rs.getInt("id"));
                Produto.setDescricao(rs.getString("descricao"));
                Produtos.add(Produto);
            }

            return Produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao fechar rs.");
            }
            try {
                stmtLista.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao fechar stmt.");
            }
            try {
                con.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao fechar conexão.");
            }

        }
        return null;

    }
    

    public void atualiza(Produto produto) throws SQLException, IOException {
        Connection con = null;
        PreparedStatement stmtAtualiza = null;
        try {
            con = Entity.getConnection();
            stmtAtualiza = con.prepareStatement(update);
            stmtAtualiza.setString(1, produto.getDescricao());
            stmtAtualiza.setInt(2, produto.getId());
            stmtAtualiza.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmtAtualiza.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao fechar stmt.");
            }
            try {
                con.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao fechar conexão.");
            }

        }

    }

    public void excluir(Produto produto) throws SQLException {
        Connection con = null;
        PreparedStatement stmtExcluir = null;
        try {
            con = Entity.getConnection();
            stmtExcluir = con.prepareStatement(delete);
            stmtExcluir.setInt(1, produto.getId());
            stmtExcluir.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmtExcluir.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao fechar stmt.");
            }
            try {
                con.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao fechar conexão.");
            }

        }

    }

    public Produto obterProduto(int id) {
        Connection con = null;
        PreparedStatement stmtObter = null;
        ResultSet rs = null;
        Produto Produto = new Produto();
        try {
            con = Entity.getConnection();
            stmtObter = con.prepareStatement(selectPorId);
            stmtObter.setInt(1, id);
            rs = stmtObter.executeQuery();
            if (rs.next()) {
                Produto.setDescricao(rs.getString("descricao"));
                Produto.setId(rs.getInt("id"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao fechar rs.");
            }
            try {
                stmtObter.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao fechar stmt.");
            }
            try {
                con.close();
            } catch (SQLException ex) {
                throw new RuntimeException("Falha ao fechar conexão.");
            }

        }
        return Produto;

    }
    
}
