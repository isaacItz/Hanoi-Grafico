package modelo;

public class Prueba {

	public static void main(String[] args) {
		hanoi(2, 1, 2, 3);
	}


	static void hanoi(int n, int o, int a, int d) {
		if (n > 0) {
			hanoi(n - 1, o, d, a);
			System.out.println(o + "  a   " + d);
			hanoi(n - 1, a, o, d);
		}

	}
}
