package com.clillo.dmx.ui.jpanels.opcionesMenu.escenas;

import javax.swing.table.AbstractTableModel;

import com.clillo.dmx.core.escenas.Escena;

public class ModeloTablaPasos extends AbstractTableModel {

	private static final long serialVersionUID = 3133387224757466213L;

	//private Escena escenaActual;
	
	private String[] columnNames;
    private Object[][] data;

    public ModeloTablaPasos(Escena escenaActual) {
		//this.escenaActual = escenaActual;
		columnNames = escenaActual.getTitulosPasos();
		data = escenaActual.getDatosTabla();
	}
    
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
 