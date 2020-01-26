package com.clillo.dmx.core.fixtures;

import java.util.ArrayList;
import java.util.Random;

import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.core.fixtures.robotizados.ColaPosiciones;
import com.clillo.dmx.core.fixtures.robotizados.ListenerFinMovimiento;
import com.clillo.dmx.core.fixtures.robotizados.Punto;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.ListenerCambioPosicion;

public abstract class FixtureRobotica extends Fixture implements ListenerFinMovimiento{

	private int posX;
	private int posY;

	private int posXFine;
	private int posYFine;

	private int ventanaMinX = 10;
	private int ventanaMaxX = 40;
	private int ventanaMinY = 10;
	private int ventanaMaxY = 40;

	private int fakeX, fakeY;
	private int velocidadActual;

	private ArrayList<ListenerCambioPosicion> listenerCambioPosicion;
	
	private ColaPosiciones lista;
	private Thread thLista = null;

	private ProgramaTO programaMovimientoEnEjecucion;
	private boolean moviendose;
	
	private int panFine = 0;
	private int tiltFine = 0;

	public FixtureRobotica(String nombre) {
		super(nombre);
		
		lista = new ColaPosiciones(this);
		lista.setListenerFinMovimiento(this);
		
		thLista = new Thread(lista);
		thLista.setName("Lista de "+nombre);
		thLista.start();		

		posX = 125;
		posY = 125;
		velocidadActual = 10;
	}
	
	public ProgramaTO getProgramaMovimientoEnEjecucion() {
		return programaMovimientoEnEjecucion;
	}

	public void setProgramaMovimientoEnEjecucion(ProgramaTO programaMovimientoEnEjecucion) {
		this.programaMovimientoEnEjecucion = programaMovimientoEnEjecucion;
	}
	
	@Override
	public void finalizaMovimiento() {
		//System.out.println("Finalizando: "+programaMovimientoEnEjecucion);
		if (programaMovimientoEnEjecucion!=null){
			programaMovimientoEnEjecucion.setEjecutandoMovimiento(false);
			programaMovimientoEnEjecucion.setSiguienteEjecucion();
			programaMovimientoEnEjecucion = null;
		}
	}

	public int getPosicionAleatoriaPan(Random rndPan){
		int ancho = ventanaMaxX - ventanaMinX;
		int pos =  rndPan.nextInt(ancho);
		return ventanaMinX + pos;
	}

	public int getPosicionAleatoriaTilt(Random rndTilt){
		int alto = ventanaMaxY - ventanaMinY;
		int pos =  rndTilt.nextInt(alto);
		return ventanaMinY + pos;
	}
	
	public int getPosicionInversaPan(int pan){
		int ancho = ventanaMaxX - ventanaMinX;
		int pos =  ancho - pan + ventanaMinX;
		return ventanaMinX + pos;
	}
	
	public int getPosicionInversaTilt(int tilt){
		int alto = ventanaMaxY - ventanaMinY;
		int pos =  alto - tilt + ventanaMinY;
		return ventanaMinY + pos;
	}

	public int getPosicionVentanaMediaPan(){
		int ancho = ventanaMaxX - ventanaMinX;
		return ventanaMinX + ancho/2;
	}

	public int getPosicionVentanaMediaTilt(){
		int alto = ventanaMaxY - ventanaMinY;
		return ventanaMinY + alto/2;
	}
	
	public int getAnchoVentana(){
		return ventanaMaxX - ventanaMinX;
	}

	public int getAltoVentana(){
		return ventanaMaxY - ventanaMinY;
	}
	
	public void derecha(){
		posXFine = 0;
		posYFine = 0;
		actualizaFine();
		moverA(posX+1, posY, velocidadActual); 
	}

	public void izquerda(){
		posXFine = 0;
		posYFine = 0;
		actualizaFine();
		moverA(posX-1, posY, velocidadActual);
	}

	public void arriba(){
		posXFine = 0;
		posYFine = 0;
		actualizaFine();
		moverA(posX, posY-1, velocidadActual);
	}

	public void abajo(){
		posXFine = 0;
		posYFine = 0;
		actualizaFine();
		moverA(posX, posY+1, velocidadActual);
	}
	
	public void derecha(boolean fine){
		posXFine++;
		if (posXFine>255)
			posXFine=255;
		actualizaFine();
	}

	public void izquerda(boolean fine){
		posXFine--;
		if (posXFine<0)
			posXFine=0;
		actualizaFine();
	}

	public void arriba(boolean fine){
		posYFine--;
		if (posYFine<0)
			posYFine=0;
		actualizaFine();
	}

	public void abajo(boolean fine){
		posYFine++;
		if (posYFine>255)
			posYFine=255;
		actualizaFine();
	}
	
	private void actualizaFine(){
		if (listenerCambioPosicion!=null)
			for (ListenerCambioPosicion l: listenerCambioPosicion)
				l.moverHasta(posXFine, posYFine, true);
	}
	
	public void moverARelativo(double x, double y){
		int alto = ventanaMaxY - ventanaMinY;
		int posy =  (int) (alto*y/100.0 + ventanaMinY)+1;
		
		int ancho = ventanaMaxX - ventanaMinX;
		int posx =  (int) (ancho*x/100.0 + ventanaMinX)+1;

		//System.out.println(posx+","+posy+"\t"+x+","+y);
		
		if (posx == this.posX && posy == this.posY)
			posx++;
			
		moverA(posx, posy);
	}
	
	public void moverA(int x, int y){
		moverA(x, y, velocidadActual);
	}

	public void saltarAFine(int x, int y){
		posXFine = x;
		posYFine = y;
		actualizaFine();
	}
	
	private void moverA(int x, int y, int velocidad) {	
		int deltax = Math.abs(x - posX);
		int deltay = Math.abs(y - posY);
		boolean steep = deltay > deltax;
		int error;
		int xstep = (posX < x)?1:-1;
		int ystep = (posY < y)?1:-1;
		int i;

		fakeX = posX;
		fakeY = posY;

		error = 0;
	
		if (steep) {
			for (i = 0; i < deltay; i++) {
				incy(ystep);

				error = error + deltax;
				if (2 * error >= deltay) {
					incx(xstep);
					error = error - deltay;
				}
				punto(velocidad);
			}
		} else {
			for (i = 0; i < deltax; i++) {
				incx(xstep);
				error = error + deltay;
				if (2 * error >= deltax) {
					incy(ystep);
					error = error - deltax;
				}
				punto(velocidad);
			}
		}	
	}
	
	private void punto(int velocidad) {
		lista.agregar(new Punto(fakeX, fakeY, velocidad));
		//System.out.println(new Punto(fakeX, fakeY, velocidad));
	}
	
	private void incx(int p) {
		if (p == 1) 
			fakeX++;
		else 
			fakeX--;
	}

	private void incy(int p) {
		if (p == 1) 
			fakeY++;
		else
			fakeY--;
	}

	public void saltarA(int x, int y) {
		setPosX(x);
		setPosY(y);
		//System.out.println("Moviendo a "+x+","+y);
		if (listenerCambioPosicion!=null)
			for (ListenerCambioPosicion l: listenerCambioPosicion)
				l.moverHasta(x, y, false);
	}
	
	public void freeze(){
		lista.freeze();
	}

	public void setVelocidadActual(int velocidadActual) {
		this.velocidadActual = velocidadActual;
	}
	
	public int getVentanaMinX() {
		return ventanaMinX;
	}

	public void setVentanaMinX(int ventanaMinX) {
		this.ventanaMinX = ventanaMinX;
	}

	public int getVentanaMaxX() {
		return ventanaMaxX;
	}

	public void setVentanaMaxX(int ventanaMaxX) {
		this.ventanaMaxX = ventanaMaxX;
	}

	public int getVentanaMinY() {
		return ventanaMinY;
	}

	public void setVentanaMinY(int ventanaMinY) {
		this.ventanaMinY = ventanaMinY;
	}

	public int getVentanaMaxY() {
		return ventanaMaxY;
	}

	public void setVentanaMaxY(int ventanaMaxY) {
		this.ventanaMaxY = ventanaMaxY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
		
	public int getPosXFine() {
		return posXFine;
	}

	public int getPosYFine() {
		return posYFine;
	}

	public void setListenerCambioPosicion(ListenerCambioPosicion listenerCambioPosicion) {
		if (this.listenerCambioPosicion == null)
			this.listenerCambioPosicion = new ArrayList<ListenerCambioPosicion>();
		this.listenerCambioPosicion.add(listenerCambioPosicion);
	}

	public void setMoviendose(boolean moviendose) {
		this.moviendose = moviendose;
	}

	public boolean isMoviendose() {
		return moviendose;
	}

	public int getPanFine() {
		return panFine;
	}

	public void setPanFine(int panFine) {
		this.panFine = panFine;
	}

	public int getTiltFine() {
		return tiltFine;
	}

	public void setTiltFine(int tiltFine) {
		this.tiltFine = tiltFine;
	}
}
