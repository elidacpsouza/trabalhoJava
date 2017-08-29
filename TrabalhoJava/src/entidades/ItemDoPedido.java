/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Elida
 */
@Entity
@Table(name = "item_do_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemDoPedido.findAll", query = "SELECT i FROM ItemDoPedido i")
    , @NamedQuery(name = "ItemDoPedido.findByQuantidade", query = "SELECT i FROM ItemDoPedido i WHERE i.quantidade = :quantidade")
    , @NamedQuery(name = "ItemDoPedido.findByIdProduto", query = "SELECT i FROM ItemDoPedido i WHERE i.itemDoPedidoPK.idProduto = :idProduto")
    , @NamedQuery(name = "ItemDoPedido.findByIdPedido", query = "SELECT i FROM ItemDoPedido i WHERE i.itemDoPedidoPK.idPedido = :idPedido")})
public class ItemDoPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ItemDoPedidoPK itemDoPedidoPK;
    @Basic(optional = false)
    @Column(name = "quantidade")
    private int quantidade;
    @JoinColumn(name = "id_pedido", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "id_produto", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;

    public ItemDoPedido() {
    }

    public ItemDoPedido(ItemDoPedidoPK itemDoPedidoPK) {
        this.itemDoPedidoPK = itemDoPedidoPK;
    }

    public ItemDoPedido(ItemDoPedidoPK itemDoPedidoPK, int quantidade) {
        this.itemDoPedidoPK = itemDoPedidoPK;
        this.quantidade = quantidade;
    }

    public ItemDoPedido(int idProduto, int idPedido) {
        this.itemDoPedidoPK = new ItemDoPedidoPK(idProduto, idPedido);
    }

    public ItemDoPedidoPK getItemDoPedidoPK() {
        return itemDoPedidoPK;
    }

    public void setItemDoPedidoPK(ItemDoPedidoPK itemDoPedidoPK) {
        this.itemDoPedidoPK = itemDoPedidoPK;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemDoPedidoPK != null ? itemDoPedidoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemDoPedido)) {
            return false;
        }
        ItemDoPedido other = (ItemDoPedido) object;
        if ((this.itemDoPedidoPK == null && other.itemDoPedidoPK != null) || (this.itemDoPedidoPK != null && !this.itemDoPedidoPK.equals(other.itemDoPedidoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ItemDoPedido[ itemDoPedidoPK=" + itemDoPedidoPK + " ]";
    }
    
}
