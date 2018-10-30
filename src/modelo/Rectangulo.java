package modelo;

import java.awt.Color;

class Rectangulo {

	int[] posicion;
	int[] tama�o;
	private Color color;
	Moneda m ;

	public Rectangulo(int x, int y, int alto, int largo) {
		posicion = new int[2];
		tama�o = new int[2];
		posicion[0] = x;
		posicion[1] = y;
		tama�o[0] = alto;
		tama�o[1] = largo;
		m = new Moneda();
	}


	public void setColor(Color c) {
		color = c;
	}

	public Color getColor() {
		return color;
	}

	public int getX() {
		return posicion[0];
	}

	public void setX(int x) {
		this.posicion[0] = x;
	}

	public int getY() {
		return posicion[1];
	}

	public int getAlto() {
		return tama�o[0];
	}

	public int getLargo() {
		return tama�o[1];
	}

}