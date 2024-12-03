package d.dark.util;

public class Quaternion {

	public final double r;
	public final double i;
	public final double j;
	public final double k;

	public Quaternion() { this(0, 0, 0, 0); }

	public Quaternion(double real) { this(real, 0, 0, 0); }

	public Quaternion(double real, double i) { this(real, i, 0, 0); }

	public Quaternion(Complex c) { this(c.getR(), c.getI(), 0, 0); }

	public Quaternion(Complex c, double j, double k) { this(c.getR(), c.getI(), j, k); }

	public Quaternion(Quaternion q) { this(q.r, q.i, q.j, q.k); }

	public Quaternion(double real, double i, double j, double k) {
		this.r = real;
		this.i = i;
		this.j = j;
		this.k = k;
	}

	public Quaternion add(Complex c) { return add(c.getR(), c.getI()); }

	public Quaternion add(Complex c, double i) { return add(c.getR(), c.getI(), i); }

	public Quaternion add(Complex c, double i, double k) { return add(c.getR(), c.getI(), i, k); }

	public Quaternion add(Quaternion c) { return add(this.r, this.i, this.j, this.k); }

	public Quaternion add(double real) { return new Quaternion(this.r + real, this.i, this.j, this.j); }

	public Quaternion add(double real, double i) { return new Quaternion(this.r + real, this.i + i, this.j, this.j); }

	public Quaternion add(double real, double i, double j) { return new Quaternion(this.r + real, this.i + i, this.j + j, this.j); }

	public Quaternion add(double real, double i, double j, double k) { return new Quaternion(this.r + real, this.i + i, this.j + j, this.j + k); }

	public Quaternion sub(Complex c) { return sub(c.getR(), c.getI()); }

	public Quaternion sub(Complex c, double i) { return sub(c.getR(), c.getI(), i); }

	public Quaternion sub(Complex c, double i, double k) { return sub(c.getR(), c.getI(), i, k); }

	public Quaternion sub(Quaternion c) { return sub(this.r, this.i, this.j, this.k); }

	public Quaternion sub(double real) { return new Quaternion(this.r - real, this.i, this.j, this.j); }

	public Quaternion sub(double real, double i) { return new Quaternion(this.r - real, this.i - i, this.j, this.j); }

	public Quaternion sub(double real, double i, double j) { return new Quaternion(this.r - real, this.i - i, this.j - j, this.j); }

	public Quaternion sub(double real, double i, double j, double k) { return new Quaternion(this.r - real, this.i - i, this.j - j, this.j - k); }

	public Quaternion mul(double r, double i, double j, double k) {
		// (this.r + this.i + this.j + this.k) * (r, i, j, k) =

		// = this.r * r + this.r * i(i) + this.r * j(j) + this.r * k(k) +
		// + this.i(i) * r + this.i(i) * i(i) + this.i(i) * j(j) + this.i(i) * k(k) +
		// + this.j(j) * r + this.j(j) * i(i) + this.j(j) * j(j) + this.j(j) * k(k) +
		// + this.k(k) * r + this.k(k) * i(i) + this.k(k) * j(j) + this.k(k) * k(k) =

		// = (this.r * r) + (this.r * i)I + (this.r * j)J + (this.r * k)K +
		// + (this.i * r)I - (this.i * i) + (this.i * j)K - (this.i * k)J +
		// + (this.j * r)J - (this.j * i)K - (this.j * j) + (this.j * k)I +
		// + (this.k * r)K + (this.k * i)J - (this.k * j)I - (this.k * k) =

		// = (this.r * r) + (this.r * i)I + (this.r * j)J + (this.r * k)K -
		// - (this.i * i) + (this.i * r)I - (this.i * k)J + (this.i * j)K -
		// - (this.j * j) + (this.j * k)I + (this.j * r)J - (this.j * i)K -
		// - (this.k * k) - (this.k * j)I + (this.k * i)J + (this.k * r)K

		// = (this.r * r - this.i * i - this.j * j - this.k * k) +
		// + (this.r * i + this.i * r + this.j * k - this.k * j)I +
		// + (this.r * j - this.i * k + this.j * r + this.k * i)J +
		// + (this.r * k + this.i * j - this.j * i + this.k * r)K

		double R = this.r * r - this.i * i - this.j * j - this.k * k;
		double I = this.r * i + this.i * r + this.j * k - this.k * j;
		double J = this.r * j - this.i * k + this.j * r + this.k * i;
		double K = this.r * k + this.i * j - this.j * i + this.k * r;

		return new Quaternion(R, I, J, K);
	}

	public Quaternion mul(double r, double i, double j) {

		double R = this.r * r - this.i * i - this.j * j;
		double I = this.r * i + this.i * r - this.k * j;
		double J = this.r * j + this.j * r + this.k * i;
		double K = this.i * j - this.j * i + this.k * r;

		return new Quaternion(R, I, J, K);
	}

	public Quaternion mul(double r, double i) {

		double R = this.r * r - this.i * i;
		double I = this.r * i + this.i * r;
		double J = this.j * r + this.k * i;
		double K = this.j * i + this.k * r;

		return new Quaternion(R, I, J, K);
	}

	public Quaternion mul(double r) { return new Quaternion(this.r * r, this.i * r, this.j * r, this.k * r); }

	public Quaternion mul(Quaternion q) { return mul(q.r, q.i, q.j, q.j); }

	public Quaternion mul(Complex c) { return mul(c.getR(), c.getI()); }

	// TODO: divide by quaternion, complex and (r, i, j, k), (r, i, j), (r, i)
	public Quaternion div(double real) { return new Quaternion(this.r / real, this.i / real, this.j / real, this.k / real); }

	public Quaternion conjugate() { return new Quaternion(this.r, -this.i, -this.j, -this.k); }

	public Quaternion multiplicativeInverse() {
		double d = absoluteValue();
		return new Quaternion(r / d, i / d, j / d, k / d);
	}

	public double absoluteValue() { return r * r + i * i + j * j + k * k; }

	public double magnitude() { return Math.sqrt(absoluteValue()); }

	public double modulus() { return magnitude(); }

	public double mag() { return mag(); }

	@Override
	public String toString() {
		String real = roundStr(r);
		String strI = signStr(roundStr(i), "i");
		String strJ = signStr(roundStr(j), "j");
		String strK = signStr(roundStr(k), "k");

		if (real.isBlank() && strI.isBlank() && strJ.isBlank() && strK.isBlank()) {
			return "0";
		} else {
			real = real.isBlank() ? "0" : real;
			return "(" + real + strI + strJ + strK + ")";
		}
	}

	private String roundStr(double val) {
		String roundedStr = String.valueOf(Math.round(val * 100000) / 100000.0);
		if (roundedStr.endsWith(".0")) { roundedStr = roundedStr.replace(".0", ""); }
		if (roundedStr.equals("0") || roundedStr.equals("-0")) { roundedStr = ""; }
		return roundedStr;
	}

	private String signStr(String val, String imaginary) {
		if (val.isBlank()) {
			return " + 0";
		} else {
			if (val.startsWith("-")) {
				return val.replace("-", " - ") + imaginary;
			} else {
				return " + " + val + imaginary;
			}
		}
	}

}
