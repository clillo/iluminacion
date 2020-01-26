package com.clillo.dmx.ui.jpanels.opcionesMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorMovingHead;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorMovingHeadBigDipper;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorScanner;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorScannerWPro;

public class PanelControlRobotizados extends PanelMenuGenerico implements ActionListener{

	private static final long serialVersionUID = -5869553409971473557L;
	
	private PanelObservadorMovingHead pnlMovingHead1;
	private PanelObservadorMovingHead pnlMovingHead2;
	private PanelObservadorMovingHeadBigDipper pnlScanner1;
	private PanelObservadorMovingHeadBigDipper pnlScanner2;

	public PanelControlRobotizados() {
	
	    this.configura(1330, 270, "Control Robotizados");
	  	this.setLayout(null);
		
		JPanel pnl1 = new JPanel();
		pnl1.setBorder(new TitledBorder(null, "Moving Head 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl1.setBounds(10, 11, 193, 228);
		add(pnl1);
		pnl1.setLayout(null);
		
		JPanel pnl6 = new JPanel();
		pnl6.setBorder(new TitledBorder(null, "Moving Head 6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl6.setBounds(642, 11, 193, 228);
		add(pnl6);
		pnl6.setLayout(null);
		
		JPanel pnlS2 = new JPanel();
		pnlS2.setBorder(new TitledBorder(null, "Scanner 1 (SRL)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlS2.setBounds(213, 11, 193, 228);
		add(pnlS2);
		pnlS2.setLayout(null);
		
		JPanel pnlS3 = new JPanel();
		pnlS3.setBorder(new TitledBorder(null, "Scanner 2 (SRL)", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlS3.setBounds(416, 11, 216, 228);
		add(pnlS3);
		pnlS3.setLayout(null);

	  	pnlMovingHead1 = new PanelObservadorMovingHead();
	  	pnlMovingHead1.setBounds(10, 23, 173, 197);
	  	pnlMovingHead1.setId("movingHead1");

		pnlMovingHead2 = new PanelObservadorMovingHead();
		pnlMovingHead2.setBounds(10, 22, 173, 197);
		pnlMovingHead2.setId("movingHead2");

		pnlScanner1 = new PanelObservadorMovingHeadBigDipper();
		pnlScanner1.setBounds(10, 21, 173, 196);
		pnlScanner1.setId("movingHead3");
		
		pnlScanner2 = new PanelObservadorMovingHeadBigDipper();
		pnlScanner2.setBounds(10, 22, 196, 195);
		pnlScanner2.setId("movingHead4");
		
	  	pnl1.add(pnlMovingHead1);
		pnlS2.add(pnlScanner1);
		pnlS3.add(pnlScanner2);
	  	pnl6.add(pnlMovingHead2);

		Dmx.registraEnviable(pnlMovingHead1);
		Dmx.registraEnviable(pnlScanner1);
		Dmx.registraEnviable(pnlScanner2);
		Dmx.registraEnviable(pnlMovingHead2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1222, 22, 63, 217);
		add(scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
