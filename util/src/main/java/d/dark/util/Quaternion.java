package d.dark.util;

public class Quaternion {

	private final double r;
	private final double i;
	private final double j;
	private final double k;

	public Quaternion() { this(0, 0, 0, 0); }

	public Quaternion(double real) { this(real, 0, 0, 0); }

	public Quaternion(double real, double i) { this(real, i, 0, 0); }

	public Quaternion(Complex c) { this(c.getReal(), c.getImaginary(), 0, 0); }

	public Quaternion(Complex c, double j, double k) { this(c.getReal(), c.getImaginary(), j, k); }

	public Quaternion(Quaternion q) { this(q.r, q.i, q.j, q.k); }

	public Quaternion(double real, double i, double j, double k) {
		this.r = real;
		this.i = i;
		this.j = j;
		this.k = k;
	}

}
