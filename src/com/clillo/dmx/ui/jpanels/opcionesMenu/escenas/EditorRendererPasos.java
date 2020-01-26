package com.clillo.dmx.ui.jpanels.opcionesMenu.escenas;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.clillo.dmx.core.escenas.EscenaCanal;

public class EditorRendererPasos extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private static final long serialVersionUID = 1L;
    private CeldaPasoValor renderer;
    private CeldaPasoValor editor;

    public EditorRendererPasos(EscenaCanal ec) {
    	//System.out.println(ec);
    	 renderer = new CeldaPasoValor(ec);
    	 editor = new CeldaPasoValor(ec);
	}
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	if (value instanceof EscenaCanal)
    		renderer.setValor((EscenaCanal) value, isSelected);
        return renderer;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    	editor.setValor((EscenaCanal) value, isSelected);
        return editor;
    }

    @Override
    public Object getCellEditorValue() {
        return editor.getValor();
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return false;
    }
}
