/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Elida
 */
public class ItemDoPedidoDao {
    private String insert = "insert into item_do_pedido (id_pedido,Id_produto,qtdade) values (?,?,?)";
    private String select = "select * from item_do_pedido";
    private String selectPorIdPedido = "select * from item_do_pedido where pedido_id=?";
    private String selectPorFiltro = "select * from item_do_pedido p inner join produto pr "
            + "on pr.id = p.id_produto where pr.id=? or pr.descricao like ?;";
    private String updateqtd = "update item_do_pedido set qtdade=? WHERE id_pedido=? and id_produto=?;";
    private String delete = "delete from item_do_pedido WHERE id_pedido=? and id_produto=?;";
}
