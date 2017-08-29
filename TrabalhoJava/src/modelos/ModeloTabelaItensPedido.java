/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import entidades.ItemDoPedido;
import entidades.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Elida
 */
public class ModeloTabelaItensPedido extends AbstractTableModel {

    private String[] colunas = new String[]{"ID PRODUTO", "DESCRIÇÃO", "QTD"};

    private List<ItemDoPedido> lista = new ArrayList();

    public ModeloTabelaItensPedido() {
    }

    public ModeloTabelaItensPedido(List<ItemDoPedido> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public String getColumnName(int index) {
        return this.colunas[index];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ItemDoPedido customer = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return customer.getProduto().getId();
            case 1:
                return customer.getProduto().getDescricao();
            case 2:
                customer.getQuantidade();
            default:
                return null;
        }
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        Class retorno = null;
        switch (columnIndex) {

            case 0: {
                retorno = Integer.class;
                break;
            }
            case 1: {
                retorno = String.class;
                break;
            }
            case 2: {
                retorno = Integer.class;
                break;
            }
        }
        return retorno;
    }

    public boolean removeItemPedido(ItemDoPedido customer) {
        int linha = this.lista.indexOf(customer);
        boolean result = this.lista.remove(customer);
        this.fireTableRowsDeleted(linha, linha);//update JTable
        return result;
    }

    public void adicionaPedido(ItemDoPedido customer) {
        this.lista.add(customer);
        this.fireTableRowsInserted(lista.size() - 1, lista.size() - 1);//update JTable
    }

    public void setListaPedidos(List<ItemDoPedido> contatos) {
        this.lista = contatos;
        this.fireTableDataChanged();
        //this.fireTableRowsInserted(0,contatos.size()-1);//update JTable
    }

    public void limpaTabela() {
        int indice = lista.size() - 1;
        if (indice < 0) {
            indice = 0;
        }
        this.lista = new ArrayList();
        this.fireTableRowsDeleted(0, indice);
    }

    public ItemDoPedido getPedido(int linha) {
        return lista.get(linha);
    }

}
