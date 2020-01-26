package com.clillo.dmx.ui.programas.tabla;

import javax.swing.table.DefaultTableModel;

public class ModeloTablaRGBW extends DefaultTableModel {

    private static final long serialVersionUID = 1L;

    @Override
    public int getColumnCount() {
        return 1;
    }

    public void addRow() {
      //  super.addRow(new Object[]{new Comp(0, 0, "", "")});
    }
}
