
package modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	private Rectangulo rectangulos[];
	private int cantRectangulos;
	Torre[] torres;
	int ejeX;
	int ejeY;
	Rectangulo rAux;
	int tiempo;
	boolean cambio;
	int to, td;
	int total;
	boolean bandera;

	/// jiji
	public Vista() {
		bandera = false;
		setLayout(new GridLayout(10, 3));
		JButton bot = new JButton("pausar");
		add(bot);
		bot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				bandera = !bandera;
				bot.setText(bandera ? "despausar" : "pausar");
			}
		});
		Object[] recs = { 1, 2, 3, 4, 5, 6, 7, 8 };
		this.cantRectangulos = (int) JOptionPane.showInputDialog(null, "Cuantos Discos quieres", "elegir",
				JOptionPane.QUESTION_MESSAGE, null, recs, recs[2]);
		this.rectangulos = new Rectangulo[cantRectangulos];
		tiempo = (int) JOptionPane.showInputDialog(null, "Elige el tiempo de cada movimiento", "elegir",
				JOptionPane.QUESTION_MESSAGE, null, recs, 0);
		torres = new Torre[3];
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width - 50,
				Toolkit.getDefaultToolkit().getScreenSize().height - 50);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ejeX = resX(10);
		ejeY = resY(90);
		getContentPane().setBackground(Color.blue);
		setVisible(true);
		setPositionT();
		setTamanoRectangulos(cantRectangulos - 1, torres[0]);
		hanoi(cantRectangulos, 1, 2, 3);
		JOptionPane.showMessageDialog(getContentPane(), "Fin\nTotal de Movimientos: " + total, "Fin",
				JOptionPane.INFORMATION_MESSAGE);

	}

	public static void main(String[] args) {
		new Vista();
	}

	private void pausa() {
		while (bandera) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void mover(int from, int to) {
		rAux = torres[from - 1].sacar();
		moverYAriiba(rAux);
		if (from < to)
			moverXDerecha(to);
		if (from > to)
			moverXIzquierda(to - 1);
		moverYAbajo(to - 1, rAux);
		torres[to - 1].agregarRectangulo(rAux);
	}

	public void setTamanoRectangulos(int n, Torre t) {
		int tamanoInicial = 300;
		int ejeX = (this.ejeX);
		int ejeY = (this.ejeY) - 25;
		for (int i = 0; i <= n; i++) {
			rectangulos[i] = new Rectangulo(ejeX, ejeY, 25, tamanoInicial);
			rectangulos[i].setColor(
					new Color(new Random().nextInt(254), new Random().nextInt(254), new Random().nextInt(254)));
			rectangulos[i].m.setValor(cantRectangulos - i);
			ejeY = ejeY - 25;
			ejeX += 20;
			tamanoInicial -= 40;
			t.agregarRectangulo(rectangulos[i]);
		}
	}

	public void hanoi(int n, int o, int a, int d) {

		if (n > 0) {
			hanoi(n - 1, o, d, a);
			to = o;
			td = d;
			total++;
			mover(o, d);
			hanoi(n - 1, a, o, d);
		}

	}

	public void moverXDerecha(int torre) {

		int torreDestino = (torres[torre - 1].getEjeXPalo() - (rAux.getLargo() / 2)) + 9;

		for (; rAux.getX() <= torreDestino; rAux.posicion[0]++) {
			pausa();
			try {
				Thread.sleep(tiempo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

	public void moverXIzquierda(int torre) {

		int torreDestino = (torres[torre].getEjeXPalo() - (rAux.getLargo() / 2)) + 11;

		for (; rAux.getX() >= torreDestino; rAux.posicion[0]--) {
			pausa();
			try {
				Thread.sleep(tiempo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			repaint();
		}
	}

	public void moverYAriiba(Rectangulo r) {
		for (; r.getY() >= resY(20); r.posicion[1]--) {
			pausa();
			try {
				Thread.sleep(tiempo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			paint(getGraphics());
		}
	}

	public void moverYAbajo(int t, Rectangulo r) {
		for (; r.getY() <= torres[t].getCima(); r.posicion[1]++) {
			pausa();
			try {
				Thread.sleep(tiempo);
			} catch (Exception e) {
				e.printStackTrace();
			}

			paint(getGraphics());
		}
		cambio = true;
		paint(getGraphics());

	}

	void setPositionT() {
		int x = this.ejeX;

		for (int i = 0; i < 3; i++) {
			torres[i] = new Torre(x, (this.ejeY));
			x += resX(30);
		}
	}

	@Override
	public void paint(Graphics g) {

		g.drawString("Movimiento ejecutado de " + to + " a " + td, resX(45), resY(16));

		g.setColor(Color.gray);
		for (Torre t : torres) {
			t.dibujo(g);
		}
		g.setColor(Color.CYAN.darker());

		for (int i = 0; i < cantRectangulos; i++) {
			g.setColor(rectangulos[i].getColor());
			if (cambio) {
				g.clearRect(resX(45), resY(16) - 15, 400, 25);
				g.clearRect(rAux.getX(), rAux.getY(), rAux.getLargo(), rAux.getAlto());
			}
			cambio = false;
			g.fillRoundRect(rectangulos[i].getX(), rectangulos[i].getY(), rectangulos[i].getLargo(),
					rectangulos[i].getAlto(), 30, 30);
			g.setColor(Color.BLACK);
			g.drawString("" + rectangulos[i].m.getValor(),
					rectangulos[i].posicion[0] + (rectangulos[i].getLargo() / 2) - 10, rectangulos[i].posicion[1] + 16);
		}

		g.setColor(rAux.getColor());

		g.clearRect(rAux.getX() - 1, rAux.getY(), 3, rAux.getAlto());
		g.clearRect(rAux.getX(), rAux.getY() - 1, rAux.getLargo(), 2);
		g.clearRect(rAux.getX(), rAux.getY() + 25, rAux.getLargo(), 2);
		g.clearRect(rAux.getX() + rAux.getLargo(), rAux.getY(), 2, 25);

		g.fillRoundRect(rAux.getX(), rAux.getY(), rAux.getLargo(), rAux.getAlto(), 30, 30);
	}

	public int resX(int n) {
		int ancho = getWidth();
		return (ancho / 100) * n;

	}

	public int resY(int n) {
		int alto = getHeight();
		return (alto / 100) * n;

	}

}
