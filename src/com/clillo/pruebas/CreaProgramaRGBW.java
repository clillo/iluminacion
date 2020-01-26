package com.clillo.pruebas;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.configuracion.ProgramasCfg;
import com.clillo.dmx.configuracion.programas.PasoTO;
import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.ui.programas.tabla.CeldaRGBW;
import com.clillo.dmx.ui.programas.tabla.FilaRGBW;
import com.clillo.dmx.ui.programas.tabla.ModeloTablaRGBW;
import com.clillo.dmx.ui.programas.tabla.PanelCellEditorRenderer;

public class CreaProgramaRGBW extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1823403452881818081L;
	private JPanel jPanel;
	private JTable table;    
	private ModeloTablaRGBW modelo = null;
	private JButton btnAgregar;
	private JButton btnBuscar;
	private int maxPaso;
	private JScrollPane scrollPane;
	
	private static final int canales[] = new int[]{150, 160, 170, 180};
	private JComboBox<ProgramaTO> cbxListaProgramas;
	
	public CreaProgramaRGBW() {
		enableEvents(64L);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialize();
	}
	
	private void initialize() {
		setSize(new Dimension(464, 434));
		setContentPane(getJPanel());
	}
	
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == 201){
			dispose();
			Dmx.stop();
			System.exit(0);
		}
	}
	
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			
			btnAgregar = new JButton("Agregar");
			btnAgregar.setBounds(176, 362, 89, 23);
			jPanel.add(btnAgregar);
			btnAgregar.addActionListener(this);

			btnBuscar = new JButton("Buscar");
			btnBuscar.setBounds(370, 10, 68, 23);
			jPanel.add(btnBuscar);
			btnBuscar.addActionListener(this);

			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 42, 428, 310);
			jPanel.add(scrollPane);
			
			cbxListaProgramas = new JComboBox<ProgramaTO>();
			cbxListaProgramas.setBounds(10, 11, 317, 20);
			jPanel.add(cbxListaProgramas);
			
			for (ProgramaTO programa: ProgramasCfg.getListaProgramas())
				cbxListaProgramas.addItem(programa);

		}
		
		return jPanel;
	}
	
   public JTable createCompTable() {
        modelo = new ModeloTablaRGBW();
        modelo.addRow();
        table = new JTable(modelo);
        table.setRowHeight(new CeldaRGBW().getPreferredSize().height);
        table.setTableHeader(null);
        PanelCellEditorRenderer PanelCellEditorRenderer = new PanelCellEditorRenderer();
        table.setDefaultRenderer(Object.class, PanelCellEditorRenderer);
        table.setDefaultEditor(Object.class, PanelCellEditorRenderer);
        return table;
    }
	   
	@Override
	public void actionPerformed(ActionEvent e) {
		ProgramaTO programaTo = (ProgramaTO)cbxListaProgramas.getSelectedItem();

		if (e.getSource().equals(btnBuscar)){
			scrollPane.setViewportView(createCompTable());
			DefaultTableModel tm = (DefaultTableModel) table.getModel();
						
			int n=1;
			for (PasoTO pto: programaTo.getListaPasos()){
				
				HashMap<Integer, Integer> hash = pto.getHashValores();
				
				FilaRGBW c = new FilaRGBW(n++, hash, canales[0], canales[1], canales[2], canales[3], programaTo.getId(), pto.getId());			
				Object o[] = new Object[]{c};
			    tm.addRow(o);
			    maxPaso = pto.getId();
			}
			
			return;
		}
		
		if (e.getSource().equals(btnAgregar)){
			DefaultTableModel tm = (DefaultTableModel) table.getModel();
	
			HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
			hash.put(canales[0], 0); hash.put(canales[0]+1, 0); hash.put(canales[0]+2, 0);
			hash.put(canales[1], 0); hash.put(canales[1]+1, 0); hash.put(canales[1]+2, 0);
			hash.put(canales[2], 0); hash.put(canales[2]+1, 0); hash.put(canales[2]+2, 0);
			hash.put(canales[3], 0); hash.put(canales[3]+1, 0); hash.put(canales[3]+2, 0);
			FilaRGBW c = new FilaRGBW(maxPaso+1, hash, canales[0], canales[1], canales[2], canales[3], programaTo.getId(), maxPaso+1);			
			Object o[] = new Object[]{c};
			tm.addRow(o);
			try {
				ProgramasCfg.agregaPaso(programaTo.getId(), maxPaso+1, hash);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			maxPaso = maxPaso+1;
		}
	}

	public static void main(String[] args) {
		CreaProgramaRGBW vp = new CreaProgramaRGBW();
		vp.validate();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = vp.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		vp.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		vp.setVisible(true);
		
	}
}
