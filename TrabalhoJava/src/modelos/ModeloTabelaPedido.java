/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import entidades.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import utilitario.Utilitario;

/**
 *
 * @author Elida
 */
public class ModeloTabelaPedido extends AbstractTableModel {

    private String[] colunas = new String[]{"PEDIDO", "DATA", "CLIENTE"};

    private List<Pedido> lista = new ArrayList();

    public ModeloTabelaPedido(List<Pedido> lista) {
        this.lista = lista;
    }

    public ModeloTabelaPedido() {
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
        Pedido customer = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return customer.getId();
            case 1:
                return Utilitario.getDateToString("dd-MM-yyyy HH:mm:ss", customer.getData());
            case 2:
                if (customer.getIdCliente() != null) {
                    return customer.getIdCliente().getNome() + " " + customer.getIdCliente().getSobrenome();
                } else {
                    return "";
                }
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
                retorno = String.class;
                break;
            }
        }
        return retorno;
    }

    public boolean removePedido(Pedido customer) {
        int linha = this.lista.indexOf(customer);
        boolean result = this.lista.remove(customer);
        this.fireTableRowsDeleted(linha, linha);//update JTable
        return result;
    }

    public void adicionaPedido(Pedido customer) {
        this.lista.add(customer);
        //this.fireTableDataChanged();
        this.fireTableRowsInserted(lista.size() - 1, lista.size() - 1);//update JTable
    }

    public void setListaPedidos(List<Pedido> contatos) {
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

    public Pedido getPedido(int linha) {
        return lista.get(linha);
    }

}
