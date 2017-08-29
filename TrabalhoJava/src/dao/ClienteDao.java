/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Cliente;
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
import utilitario.Entity;

/**
 *
 * @author Elida
 */
public class ClienteDao {

    private String insert = "insert into cliente (cpf,nome,sobrenome) values (?,?,?)";
    private String select = "select * from cliente";
    private String selectPorId = "select * from cliente where id=?";
    private String selectPorFiltro = "select * from cliente where id=? or nome like ? or sobrenome like ? or cpf =?;";
    private String update = "update cliente set  cpf=?,nome=?, sobrenome=? WHERE id=?;";
    private String delete = "delete from cliente WHERE id=?;";
    private String sqlTemPedido = "SELECT COUNT(id) FROM pedido WHERE id_cliente=?;";

    public  void adiciona(Cliente cliente) throws IOException {
        Connection con = null;
        PreparedStatement stmtAdiciona = null;
        try {
            con = Entity.getConnection();
            stmtAdiciona = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            // seta os valores
            stmtAdiciona.setString(1, cliente.getCpf());
            stmtAdiciona.setString(2, cliente.getNome());
            stmtAdiciona.setString(3, cliente.getSobrenome());

            // executa
            stmtAdiciona.execute();
            //Seta o id do cliente
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            int i = rs.getInt(1);
            cliente.setId(i);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<Cliente> getLista() throws SQLException, IOException {
        Connection con = null;
        PreparedStatement stmtLista = null;
        ResultSet rs = null;
        try {
            con = Entity.getConnection();
            stmtLista = con.prepareStatement(select);
            rs = stmtLista.executeQuery();
            List<Cliente> clientes = new ArrayList();
            while (rs.next()) {
                // criando o objeto Cliente
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setCpf(rs.getString("cpf"));
                clientes.add(cliente);
            }

            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public List<Cliente> getLista(String filtro) throws SQLException, IOException {
        Connection con = null;
        PreparedStatement stmtLista = null;
        ResultSet rs = null;
        try {
            con = Entity.getConnection();
            stmtLista = con.prepareStatement(selectPorFiltro);
            stmtLista.setString(1, filtro);
            stmtLista.setString(2, "%" + filtro + "%");
            stmtLista.setString(3, "%" + filtro + "%");
            stmtLista.setString(4, filtro);
            
            rs = stmtLista.executeQuery();
            List<Cliente> clientes = new ArrayList();
            while (rs.next()) {
                // criando o objeto Cliente
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setCpf(rs.getString("cpf"));
                clientes.add(cliente);
            }

            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public void atualiza(Cliente cliente) throws SQLException, IOException {
        Connection con = null;
        PreparedStatement stmtAtualiza = null;
        try {
            con = Entity.getConnection();
            stmtAtualiza = con.prepareStatement(update);
            stmtAtualiza.setString(1, cliente.getCpf());
            stmtAtualiza.setString(2, cliente.getNome());
            stmtAtualiza.setString(3, cliente.getSobrenome());
            stmtAtualiza.setInt(4, cliente.getId());
            stmtAtualiza.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public void excluir(Cliente cliente) throws SQLException {
        Connection con = null;
        PreparedStatement stmtExcluir = null;
        try {
            con = Entity.getConnection();
            stmtExcluir = con.prepareStatement(delete);
            stmtExcluir.setInt(1, cliente.getId());
            stmtExcluir.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public Cliente obterCliente(int id) {
        Connection con = null;
        PreparedStatement stmtObter = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();
        try {
            con = Entity.getConnection();
            stmtObter = con.prepareStatement(selectPorId);
            stmtObter.setInt(1, id);
            rs = stmtObter.executeQuery();
            if (rs.next()) {
                cliente.setNome(rs.getString("nome"));
                cliente.setSobrenome(rs.getString("sobrenome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setId(rs.getInt("id"));
            } else {
                return  null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return cliente;

    }
    
    public Boolean clienteTemPedido(int idCliente) {
        boolean temPedido = false;
        Connection con = null;
        PreparedStatement stmtObter = null;
        ResultSet rs = null;
        try {
            con = Entity.getConnection();
            stmtObter = con.prepareStatement(sqlTemPedido);
            stmtObter.setInt(1, idCliente);
            rs = stmtObter.executeQuery();
            if (rs.next()) {
                temPedido = true;
            } else {
                temPedido = false;
            }
        } catch (SQLException e) {
            temPedido = false;
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            temPedido = false;
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            temPedido = false;
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
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
       return temPedido;
    }
}
