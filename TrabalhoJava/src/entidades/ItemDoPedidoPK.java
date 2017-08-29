/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Elida
 */
@Embeddable
public class ItemDoPedidoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_produto")
    private int idProduto;
    @Basic(optional = false)
    @Column(name = "id_pedido")
    private int idPedido;

    public ItemDoPedidoPK() {
    }

    public ItemDoPedidoPK(int idProduto, int idPedido) {
        this.idProduto = idProduto;
        this.idPedido = idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProduto;
        hash += (int) idPedido;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemDoPedidoPK)) {
            return false;
        }
        ItemDoPedidoPK other = (ItemDoPedidoPK) object;
        if (this.idProduto != other.idProduto) {
            return false;
        }
        if (this.idPedido != other.idPedido) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ItemDoPedidoPK[ idProduto=" + idProduto + ", idPedido=" + idPedido + " ]";
    }
    
}
