package com.clillo.dmx.ui.jpanels.opcionesMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.comm.dmx.DmxEnviable;
import com.clillo.dmx.configuracion.programas.to.CanalValorTO;
import com.clillo.dmx.core.ListaFixtures;
import com.clillo.dmx.core.fixtures.Fixture;
import com.clillo.dmx.ui.dialogos.EligeNivelSniffer;

public class PanelSnifferDMX extends PanelMenuGenerico implements DmxEnviable, MouseListener {

	private static final long serialVersionUID = -5869553409971473557L;
	private JTextField txtValores[];
	
	private HashMap<Integer, String> canalesStr = new HashMap<Integer, String>();
	
	private int max;

	public PanelSnifferDMX() {
		this.configura(700, 580, "Sniffer DMX");
		setLayout(null);
		
		txtValores = new JTextField[1024];

		int n=0;
		for (int j = 0; j <= 25 && n<512; j++){
			JLabel lblNewLabel = new JLabel((n+1) + " - " + (n+20));
			lblNewLabel.setBounds(0, 0 + j * 20, 46, 14);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
			add(lblNewLabel);
			
			int x = 50;
			for (int i = 0; i < 20 && n<512; i++) {
				txtValores[n] = new JTextField();
				txtValores[n].setText("0");
				txtValores[n].setHorizontalAlignment(SwingConstants.CENTER);
				txtValores[n].setFont(new Font("Tahoma", Font.PLAIN, 9));
				if (i%5==0)
					x=x+5;
				txtValores[n].setBounds(x, 0 + j * 20, 25, 17);
				add(txtValores[n]);
				
				txtValores[n].setToolTipText("Canal "+(n+1));
				canalesStr.put(n+1, "sin uso");
				txtValores[n].addMouseListener(this);
				n++;
				x+=30;
			}
		}
		
		max = n;
		
		ArrayList<Fixture> lista = ListaFixtures.obtieneListadoFixtures();
		for (Fixture fix: lista){
				ArrayList<CanalValorTO> listaCanales = fix.getListaCanales();
				if (listaCanales!=null){
					for (int i=0; i<fix.getListaCanales().size(); i++){
						int nn = fix.getCanalDMXInicial()+i-1;
						if (txtValores[nn]==null)
							continue;
						if (fix.getColor()!=null)
							txtValores[nn].setBackground(fix.getColor());
						else
							txtValores[nn].setBackground(Color.red);
						String str =  (nn+1) + " (" + listaCanales.get(i).getCanalId() + ")";
						txtValores[nn].setToolTipText("Canal " +str);
						
						if (!canalesStr.get(nn+1).equals("sin uso")){
							txtValores[nn].setToolTipText("Conflicto!!");
							txtValores[nn].setBackground(Color.red);
						}
						canalesStr.remove(nn+1);
						canalesStr.put(nn+1, str);
					}
					if (fix.getnCanales()>fix.getListaCanales().size())
						for (int i=fix.getListaCanales().size(); i<fix.getnCanales(); i++){
							int nn = fix.getCanalDMXInicial()+i-1;
							if (txtValores[nn]==null)
								continue;
							
							txtValores[nn].setBackground(Color.lightGray);
							String str =  (nn+1) + " (" + fix.getNombre()+" + "+(i+1) + ")";
							txtValores[nn].setToolTipText("Canal " +str);
							canalesStr.remove(nn+1);
							canalesStr.put(nn+1, str);
						}
				}
			
			}
		
		Dmx.registraEnviable(this);
	}
	
	@Override
	public int[] getCanales() {
		int valores[] = new int[max];
		for (int i=0;i<max; i++)
			valores[i] = i;
		return valores;
	}

	@Override
	public void actualizaEnvioDmx(int canal, int valor) {
		if (canal==0)
			System.err.println("Canal 0");
		
		txtValores[canal-1].setText(valor+"");
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		for(int i=0; i<max; i++)
			if (arg0.getSource().equals(txtValores[i])){
				//System.out.println("Canal "+(i+1));
		
				EligeNivelSniffer.setCanalesStr(canalesStr);
				EligeNivelSniffer.setNivelInicial(i+1);
				EligeNivelSniffer.createAndShowGUI();
			}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
