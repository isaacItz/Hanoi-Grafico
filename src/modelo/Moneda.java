package modelo;

public class Moneda {
	private float valor;
	private String denominacion;

	public Moneda(String denominacion, float valor) {
		this.denominacion = denominacion;
		this.valor = valor;
	}

	public Moneda() {
		this.setDenominacion("pesos");
		;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Moneda [denominacion=" + denominacion + ", valor=" + valor + "]";
	}
}
