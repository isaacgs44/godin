/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lenovo
 */
public class TablaIndicadores extends AbstractTableModel {
    
    String[] columnNames=null;
    final Object[][] data;
    
    public TablaIndicadores (int filas){
        data = new Object[filas][2];
              final String[] columnNames1 = {"Nombre", 
                                "Valor"
                               };
                this.columnNames=columnNames1;
    }
    
boolean[] canEdit2 = new boolean [] {
                false, false
            };
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit2 [columnIndex];
    }
    @Override
    public int getRowCount()
    {   
        return data.length;
    }
    
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int column)
    {
        return data[row][column];
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}