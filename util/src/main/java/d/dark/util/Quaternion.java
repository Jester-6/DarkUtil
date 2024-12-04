package d.dark.util;

/**
 * <h1>Quaternion</h1> <br>
 * A class representing a <big>quaternion</big> in the form `a + bi + cj + dk`,
 * where:
 * <ul>
 * <li><b>a</b> is the real part (scalar part)</li>
 * <li><b>b</b> is the imaginary part i (vector part)</li>
 * <li><b>c</b> is the imaginary part j (vector part)</li>
 * <li><b>d</b> is the imaginary part k (vector part)</li>
 * </ul>
 * <p>
 * It provides methods for performing common operations on quaternions such as:
 * <ul>
 * <li>Addition: {@link #add(Quaternion)}</li>
 * <li>Substraction: {@link #sub(Quaternion)}</li>
 * <li>Multiplication: {@link #mul(double)}, {@link #mul(Quaternion)}</li>
 * <li>Division: {@link #div(double)}, {@link #div(Quaternion)}</li>
 * <li><strike>Raising to the power: {@link #pow(double)},
 * {@link #pow(Quaternion)}</strike></li>
 * <li><strike>Root: {@link #root(double)}</strike></li>
 * <li><strike>Exponential: {@link #exp()}</strike></li>
 * <li><strike>Natural Logarithm: {@link #ln()}</strike></li>
 * <li>Conjugate: {@link #conjugate()}</li>
 * <li>Norm (its length in 4D space): {@link #norm()}</li>
 * <li>Versor (unit quaternion): {@link #unit()}, {@link #unit()}</li>
 * </ul>
 * </p>
 * <p>
 * <b>Usage Example:</b>
 *
 * <pre>
 * Quaternion c1 = new Quaternion(3, 4, 7, 1); // 3 + 4i + 7j + 1k
 * Quaternion c2 = new Quaternion(1, -2, 9.2, -6); // 1 - 2i + 9.2j - 6k
 * Quaternion result = c1.add(c2); // 4 + 2i + 16j - 5k
 * </pre>
 * </p>
 */
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

	public Quaternion add(double real) { return new Quaternion(this.r + real, this.i, this.j, this.k); }

	public Quaternion add(double real, double i) { return new Quaternion(this.r + real, this.i + i, this.j, this.k); }

	public Quaternion add(double real, double i, double j) { return new Quaternion(this.r + real, this.i + i, this.j + j, this.k); }

	public Quaternion add(double real, double i, double j, double k) { return new Quaternion(this.r + real, this.i + i, this.j + j, this.k + k); }

	public Quaternion sub(Complex c) { return sub(c.getR(), c.getI()); }

	public Quaternion sub(Complex c, double i) { return sub(c.getR(), c.getI(), i); }

	public Quaternion sub(Complex c, double i, double k) { return sub(c.getR(), c.getI(), i, k); }

	public Quaternion sub(Quaternion c) { return sub(this.r, this.i, this.j, this.k); }

	public Quaternion sub(double real) { return new Quaternion(this.r - real, this.i, this.j, this.k); }

	public Quaternion sub(double real, double i) { return new Quaternion(this.r - real, this.i - i, this.j, this.k); }

	public Quaternion sub(double real, double i, double j) { return new Quaternion(this.r - real, this.i - i, this.j - j, this.k); }

	public Quaternion sub(double real, double i, double j, double k) { return new Quaternion(this.r - real, this.i - i, this.j - j, this.k - k); }

	public Quaternion mul(double r, double i, double j, double k) {
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

	public Quaternion mul(Quaternion q) { return mul(q.r, q.i, q.j, q.k); }

	public Quaternion mul(Complex c) { return mul(c.getR(), c.getI()); }

	public Quaternion div(Quaternion q) { return div(q.r, q.i, q.j, q.k); }

	public Quaternion div(Complex c, double j, double k) { return div(c.getR(), c.getI(), j, k); }

	public Quaternion div(Complex c, double j) { return div(c.getR(), c.getI(), j); }

	public Quaternion div(Complex c) { return div(c.getR(), c.getI()); }

	public Quaternion div(double real) { return new Quaternion(this.r / real, this.i / real, this.j / real, this.k / real); }

	public Quaternion div(double r, double i, double j, double k) {
		double d = r * r + i * i + j * j + k * k;
		double R = r * this.r + i * this.i + j * this.j + k * this.k;
		double I = r * this.i - i * this.r - j * this.k + k * this.j;
		double J = r * this.j + i * this.k - j * this.r - k * this.i;
		double K = r * this.k - i * this.j + j * this.i - k * this.r;
		return new Quaternion(R / d, I / d, J / d, K / d);
	}

	public Quaternion div(double r, double i, double j) {
		double d = r * r + i * i + j * j;
		double R = r * this.r + i * this.i + j * this.j;
		double I = r * this.i - i * this.r - j * this.k;
		double J = r * this.j + i * this.k - j * this.r;
		double K = r * this.k - i * this.j + j * this.i;
		return new Quaternion(R / d, I / d, J / d, K / d);
	}

	public Quaternion div(double r, double i) {
		double d = r * r + i * i;
		double R = r * this.r + i * this.i;
		double I = r * this.i - i * this.r;
		double J = r * this.j + i * this.k;
		double K = r * this.k - i * this.j;
		return new Quaternion(R / d, I / d, J / d, K / d);
	}

	public Quaternion conjugate() { return new Quaternion(this.r, -this.i, -this.j, -this.k); }

	public Quaternion multiplicativeInverse() {
		double d = quaredNorm();
		return new Quaternion(r / d, i / d, j / d, k / d);
	}

	public Quaternion versor() { return unit(); }

	public Quaternion unit() { return this.div(norm()); }

	public double quaredNorm() { return r * r + i * i + j * j + k * k; }

	public double distanceTo(double r, double i, double j, double k) {
		double dr = this.r - r;
		double di = this.i - i;
		double dj = this.j - j;
		double dk = this.k - k;
		return Math.sqrt(dr * dr + di * di + dj * dj + dk * dk);
	}

	public Quaternion exp() {
		double vectorNorm = Math.sqrt(i * i + j * j + k * k);
		double R = Math.exp(r);

		if (testIfZero(vectorNorm)) { return new Quaternion(R, 0, 0, 0); }

		double sinNorm = Math.sin(vectorNorm) / vectorNorm;

		return new Quaternion(R * Math.cos(vectorNorm), R * sinNorm * i, R * sinNorm * j, R * sinNorm * k);
	}

	public Quaternion ln() {
		double norm = norm();
		if (testIfZero(norm)) { throw new ArithmeticException("Cannot compute logarithm of zero quaternion."); }
		double vectorNorm = Math.sqrt(i * i + j * j + k * k);
		double angle = Math.acos(r / norm);

		if (vectorNorm == 0) { return new Quaternion(Math.log(norm), 0, 0, 0); }

		double coeff = angle / vectorNorm;

		return new Quaternion(Math.log(norm), coeff * i, coeff * j, coeff * k);
	}

	public Quaternion pow(Quaternion q) { return ln().mul(q).exp(); }

	public Quaternion pow(double scalar) {
		double norm = norm();
		double angle = Math.acos(r / norm);
		double normPowered = Math.pow(norm, scalar);
		double newAngle = scalar * angle;

		double sinNewAngle = Math.sin(newAngle);
		double coeff = normPowered * sinNewAngle / Math.sqrt(i * i + j * j + k * k);

		return new Quaternion(
				normPowered * Math.cos(newAngle),
				coeff * i,
				coeff * j,
				coeff * k);
	}

	public Quaternion root(double n) {
		if (n <= 0) { throw new IllegalArgumentException("Root order must be positive."); }

		double norm = norm();
		double angle = Math.acos(r / norm);
		double rootNorm = Math.pow(norm, 1.0 / n);
		double newAngle = angle / n;

		double sinNewAngle = Math.sin(newAngle);
		double coeff = rootNorm * sinNewAngle / Math.sqrt(i * i + j * j + k * k);

		return new Quaternion(
				rootNorm * Math.cos(newAngle),
				coeff * i,
				coeff * j,
				coeff * k);
	}

	public double norm() { return Math.sqrt(quaredNorm()); }

	public double getR() { return r; }

	public double getI() { return i; }

	public double getJ() { return j; }

	public double getK() { return k; }

	public double getScalar() { return r; }

	public double getVectorI() { return i; }

	public double getVectorJ() { return j; }

	public double getVectorK() { return k; }

	@Override
	public String toString() {
		String real = roundStr(r);
		String strI = signStr(roundStr(this.i), "i");
		String strJ = signStr(roundStr(this.j), "j");
		String strK = signStr(roundStr(this.k), "k");

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

	public boolean isZero() { return testIfZero(r) && testIfZero(i) && testIfZero(j) && testIfZero(k); }

	private boolean testIfZero(double value) { return Math.abs(value) < Math.ulp(value); }

	public boolean isUnit() { return Math.abs(norm() - 1.0) < Math.ulp(1.0); }

}
