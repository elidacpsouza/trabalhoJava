/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Cliente;
import entidades.Pedido;
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
import utilitario.Utilitario;

/**
 *
 * @author Elida
 */
public class PedidoDao {

    private String insert = "insert into pedido (data, id_cliente) values (?,?)";
    private String select = "select * from pedido";
    private String selectPorId = "select * from pedido where id=?";
    private String selectPorFiltro = "select * from pedido p inner join cliente c "
            + "on c.id = p.id_cliente where p.id=? or c.nome like ?;";
    private String update = "update pedido set  data=?, id_cliente=? WHERE id=?;";
    private String delete = "delete from pedido WHERE id=?;";

    public void adiciona(Pedido pedido) throws IOException {
        Connection con = null;
        PreparedStatement stmtAdiciona = null;
        try {
            con = Entity.getConnection();
            stmtAdiciona = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stmtAdiciona.setString(1, Utilitario.getDateTimeToStringBD(pedido.getData()));
            stmtAdiciona.setInt(2, pedido.getIdCliente().getId());
            
            stmtAdiciona.execute();
            //Seta o id do Pedido
            ResultSet rs = stmtAdiciona.getGeneratedKeys();
            rs.next();
            int i = rs.getInt(1);
            pedido.setId(i);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<Pedido> getLista() throws SQLException, IOException {
        Connection con = null;
        PreparedStatement stmtLista = null;
        ResultSet rs = null;
        try {
            con = Entity.getConnection();
            stmtLista = con.prepareStatement(select);
            rs = stmtLista.executeQuery();
            List<Pedido> Pedidos = new ArrayList();
            while (rs.next()) {
                // criando o objeto Pedido
                Pedido Pedido = new Pedido();
                Pedido.setId(rs.getInt("id"));
                Pedido.setData(rs.getDate("data"));
                Cliente cl;
                ClienteDao dao = new ClienteDao();
                cl = dao.obterCliente(rs.getInt("id_cliente"));
                Pedido.setIdCliente(cl);
                Pedidos.add(Pedido);
            }

            return Pedidos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<Pedido> getLista(String filtro) throws SQLException, IOException {
        Connection con = null;
        PreparedStatement stmtLista = null;
        ResultSet rs = null;
        try {
            con = Entity.getConnection();
            stmtLista = con.prepareStatement(selectPorFiltro);
            stmtLista.setString(1, filtro);
            stmtLista.setString(2, "%" + filtro + "%");

            rs = stmtLista.executeQuery();
            List<Pedido> pedidos = new ArrayList();
            while (rs.next()) {
                // criando o objeto Pedido
                Pedido Pedido = new Pedido();
                Pedido.setId(rs.getInt("id"));
                Cliente cl;
                ClienteDao dao = new ClienteDao();
                cl = dao.obterCliente(rs.getInt("id_cliente"));
                Pedido.setIdCliente(cl);
                Pedido.setData(rs.getDate("data"));

                pedidos.add(Pedido);
            }

            return pedidos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public void atualiza(Pedido pedido) throws SQLException, IOException {
        Connection con = null;
        PreparedStatement stmtAtualiza = null;
        try {
            con = Entity.getConnection();
            stmtAtualiza = con.prepareStatement(update);
            stmtAtualiza.setString(1, Utilitario.getDateTimeToStringBD(pedido.getData()));
            stmtAtualiza.setInt(2, pedido.getIdCliente().getId());
            stmtAtualiza.setInt(3, pedido.getId());
            stmtAtualiza.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public void excluir(Pedido pedido) throws SQLException {
        Connection con = null;
        PreparedStatement stmtExcluir = null;
        try {
            con = Entity.getConnection();
            stmtExcluir = con.prepareStatement(delete);
            stmtExcluir.setInt(1, pedido.getId());
            stmtExcluir.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public Pedido obterPedido(int id) {
        Connection con = null;
        PreparedStatement stmtObter = null;
        ResultSet rs = null;
        Pedido Pedido = new Pedido();
        try {
            con = Entity.getConnection();
            stmtObter = con.prepareStatement(selectPorId);
            stmtObter.setInt(1, id);
            rs = stmtObter.executeQuery();
            if (rs.next()) {
                Pedido.setData(Utilitario.getStringToDate("dd/MM/yyyy hh:mm", rs.getString("data")));
                Pedido.setId(rs.getInt("id"));
                Cliente cl;
                ClienteDao dao = new ClienteDao();
                cl = dao.obterCliente(rs.getInt("id_cliente"));
                Pedido.setIdCliente(cl);
            } else {
                throw new RuntimeException("Id inválido=" + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PedidoDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return Pedido;

    }
}
