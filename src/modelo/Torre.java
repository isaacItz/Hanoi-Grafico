package modelo;

import java.awt.Graphics;
import java.util.Stack;

class Torre {
	private int cima;
	private int ejeX;
	private int ejeY;
	private int grosorBaseTorre;
	private int largoBaseTorre;
	private int ejeXPalo;
	private int ejeYPalo;
	private int grosorAltoPalo;
	private int grosorAnchoPalo;
	Stack<Rectangulo> torre;

	public Torre(int x, int y) {
		torre = new Stack<>();
		ejeX = x;
		ejeY = y;
		grosorBaseTorre = 30;
		largoBaseTorre = 300;
		ejeXPalo = ejeX + ((150) - 10);
		cima = ejeY - grosorBaseTorre + 4;
		grosorAltoPalo = 350;
		grosorAnchoPalo = 20;
		ejeYPalo = ejeY - grosorAltoPalo;
	}

	public void dibujo(Graphics g) {
		g.fillRect(ejeX, ejeY, largoBaseTorre, grosorBaseTorre);
		g.fillRect(ejeXPalo, ejeYPalo, grosorAnchoPalo, grosorAltoPalo);
	}

	public void agregarRectangulo(Rectangulo r) {
		torre.push(r);
		cima = cima - r.getAlto();
		grosorAltoPalo -=25;
	}

	public Rectangulo sacar() {
		Rectangulo aux = torre.pop();
		cima = cima + aux.getAlto();
		grosorAltoPalo +=25;
		return aux;
	}

	public int getCima() {
		return cima;
	}

	public void setCima(int cima) {
		this.cima = cima;
	}

	public int getEjeX() {
		return ejeX;
	}

	public void setEjeX(int ejeX) {
		this.ejeX = ejeX;
	}

	public int getEjeY() {
		return ejeY;
	}

	public void setEjeY(int ejeY) {
		this.ejeY = ejeY;
	}

	public int getGrosorBaseTorre() {
		return grosorBaseTorre;
	}

	public void setGrosorBaseTorre(int grosorBaseTorre) {
		this.grosorBaseTorre = grosorBaseTorre;
	}

	public int getLargoBaseTorre() {
		return largoBaseTorre;
	}

	public void setLargoBaseTorre(int largoBaseTorre) {
		this.largoBaseTorre = largoBaseTorre;
	}

	public int getEjeXPalo() {
		return ejeXPalo;
	}

	public void setEjeXPalo(int ejeXPalo) {
		this.ejeXPalo = ejeXPalo;
	}

	public int getEjeYPalo() {
		return ejeYPalo;
	}

	public void setEjeYPalo(int ejeYPalo) {
		this.ejeYPalo = ejeYPalo;
	}

	public int getGrosorAltoPalo() {
		return grosorAltoPalo;
	}

	public void setGrosorAltoPalo(int grosorAltoPalo) {
		this.grosorAltoPalo = grosorAltoPalo;
	}

	public int getGrosorAnchoPalo() {
		return grosorAnchoPalo;
	}

	public void setGrosorAnchoPalo(int grosorAnchoPalo) {
		this.grosorAnchoPalo = grosorAnchoPalo;
	}

}