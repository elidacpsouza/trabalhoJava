/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import entidades.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Elida
 */
public class ModeloTabelaProdutos extends AbstractTableModel {

    private String[] colunas = new String[]{"ID", "DESCRIÇÃO"};

    private List<Produto> lista = new ArrayList();

    public ModeloTabelaProdutos(List<Produto> lista) {
        this.lista = lista;
    }

    public ModeloTabelaProdutos() {
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
        Produto customer = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return customer.getId();
            case 1:
                return customer.getDescricao();
            
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        Produto customer = lista.get(row);
        switch (col) {
            case 0:
                customer.setId((int) value); 
                break;
            case 1:
                customer.setDescricao((String) value);
                break;
            default:
        }
        this.fireTableCellUpdated(row, col);
    }

    public boolean removeProduto(Produto customer) {
        int linha = this.lista.indexOf(customer);
        boolean result = this.lista.remove(customer);
        this.fireTableRowsDeleted(linha, linha);//update JTable
        return result;
    }

    public void adicionaProduto(Produto customer) {
        this.lista.add(customer);
        //this.fireTableDataChanged();
        this.fireTableRowsInserted(lista.size() - 1, lista.size() - 1);//update JTable
    }

    public void setListaProdutos(List<Produto> contatos) {
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
        this.fireTableRowsDeleted(0, indice);//update JTable
    }

    public Produto getProduto(int linha) {
        return lista.get(linha);
    }

}
