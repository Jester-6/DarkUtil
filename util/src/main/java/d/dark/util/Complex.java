package d.dark.util;

/**
 * A class representing a **complex number** in the form `a + bi`, where:
 * <ul>
 * <li><b>a</b> is the real part</li>
 * <li><b>b</b> is the imaginary part</li>
 * </ul>
 * <p>
 * This class is immutable and thread-safe due to its use of final fields. It
 * provides methods for performing common operations on complex numbers such as:
 * addition, subtraction, multiplication, division, exponentiation, and root
 * calculation. It also includes methods to calculate properties like magnitude
 * and argument.
 * </p>
 * <p>
 * <b>Usage Example:</b>
 *
 * <pre>
 * Complex c1 = new Complex(3, 4); // 3 + 4i
 * Complex c2 = new Complex(1, -2); // 1 - 2i
 * Complex result = c1.add(c2); // 4 + 2i
 * </pre>
 * </p>
 */
public class Complex {

	public static void main(String[] args) { System.out.println("test"); }

	public final double r; // The real part of the complex number (immutable)
	public final double i; // The imaginary part of the complex number (immutable)

	/**
	 * Default constructor initializing the complex number to `0 + 0i`.
	 */
	public Complex() { this(0, 0); }

	/**
	 * Constructor initializing the complex number with a real part of `0` and the
	 * given imaginary part.
	 *
	 * @param imaginary the imaginary part of the complex number
	 */
	public Complex(double imaginary) { this(0, imaginary); }

	/**
	 * Constructor initializing the complex number as a copy of another complex
	 * number.
	 *
	 * @param complex the complex number to copy
	 * @see #Complex(double, double)
	 */
	public Complex(Complex complex) { this(complex.getReal(), complex.getImaginary()); }

	/**
	 * Constructor initializing the complex number with the given real and imaginary
	 * parts.
	 *
	 * @param real      the real part
	 * @param imaginary the imaginary part
	 */
	public Complex(double real, double imaginary) {
		this.r = real;
		this.i = imaginary;
	}

	/**
	 * Adds another complex number to this complex number.
	 *
	 * @param c the complex number to add
	 * @return a new {@link Complex} representing the sum
	 * @see #add(double, double)
	 */
	public Complex add(Complex c) { return add(c.r, c.i); }

	/**
	 * Adds real number to this complex number.
	 *
	 * @param c the complex number to add
	 * @return a new {@link Complex} representing the sum
	 * @see #add(double, double)
	 */
	public Complex add(double r) { return new Complex(this.r + r, this.i); }

	/**
	 * Adds a real and imaginary number to this complex number.
	 *
	 * @param c the complex number to add
	 * @return a new {@link Complex} representing the sum
	 * @see #add(double, double)
	 */
	public Complex add(double r, double i) { return new Complex(this.r + r, this.i + i); }

	/**
	 * Subtracts another complex number from this complex number.
	 *
	 * @param c the complex number to subtract
	 * @return a new {@link Complex} representing the difference
	 * @see #sub(double, double)
	 */
	public Complex sub(Complex c) { return sub(c.r, c.i); }

	/**
	 * Subtracts a real number from this complex number.
	 *
	 * @param c the complex number to subtract
	 * @return a new {@link Complex} representing the difference
	 * @see #sub(double, double)
	 */
	public Complex sub(double r) { return new Complex(this.r - r, this.i); }

	/**
	 * Subtracts a real and imaginary number from this complex number.
	 *
	 * @param c the complex number to subtract
	 * @return a new {@link Complex} representing the difference
	 * @see #sub(double, double)
	 */
	public Complex sub(double r, double i) { return new Complex(this.r - r, this.i - i); }

	/**
	 * Multiplies this complex number with another complex number.
	 *
	 * @param c the complex number to multiply by
	 * @return a new {@link Complex} representing the product
	 * @see #mul(double, double)
	 */
	public Complex mul(Complex c) { return mul(c.r, c.i); }

	/**
	 * Multiplies this complex number by a real number.
	 *
	 * @param c the complex number to multiply by
	 * @return a new {@link Complex} representing the product
	 * @see #mul(double, double)
	 */
	public Complex mul(double r) { return new Complex(this.r * r, this.i * r); }

	/**
	 * Multiplies this complex number by a real and imaginary number.
	 *
	 * @param c the complex number to multiply by
	 * @return a new {@link Complex} representing the product
	 * @see #mul(double, double)
	 */
	public Complex mul(double r, double i) { return new Complex(this.r * r - this.i * i, this.r * i + this.i * r); }

	/**
	 * Divides this complex number by another complex number.
	 *
	 * @param c the complex number to divide by
	 * @return a new {@link Complex} representing the quotient
	 * @see #div(double, double)
	 */
	public Complex div(Complex c) { return div(c.r, c.i); }

	/**
	 * Divides this complex number by a real and imaginary number.
	 *
	 * @param c the complex number to divide by
	 * @return a new {@link Complex} representing the quotient
	 * @see #div(double, double)
	 */
	public Complex div(double r, double i) {
		double A = this.r * r + this.i * i;
		double B = this.i * r - this.r * i;
		double div = r * r + i * i;
		return new Complex(A / div, B / div);
	}

	/**
	 * Divides this complex number by a real number.
	 *
	 * @param c the complex number to divide by
	 * @return a new {@link Complex} representing the quotient
	 * @see #div(double, double)
	 */
	public Complex div(double r) { return new Complex(this.r / r, this.i / r); }

	/**
	 * Raises this complex number to a given power.
	 *
	 * @param power the exponent to which the complex number is raised
	 * @return this complex number after exponentiation
	 * @see #root(double)
	 */
	public Complex pow(double power) {
		double len = magnitude();
		double phi = angle();
		double A = Math.pow(len, power);
		double cos = Math.cos(power * phi);
		double sin = Math.sin(power * phi);
		return new Complex(A * cos, A * sin);
	}

	/**
	 * Raises this complex number to the power of another complex number.
	 *
	 * @param exponent the complex exponent
	 * @return a new {@link Complex} representing this^exponent
	 */
	public Complex pow(Complex exponent) {
		// ln(this)
		Complex logBase = this.ln();
		// (c + di) * ln(this)
		Complex result = logBase.mul(exponent);
		// e^result
		return result.exp();
	}

	/**
	 * Computes the root of this complex number.
	 *
	 * @param root the root to compute (e.g., 2 for square root, 3 for cube root)
	 * @return this complex number after computing the root
	 * @see #pow(double)
	 */
	public Complex root(double root) {
		double len = magnitude();
		double phi = angle() / root;
		double A = Math.pow(len, 1.0 / root);
		double cos = Math.cos(phi);
		double sin = Math.sin(phi);
		return new Complex(A * cos, A * sin);
	}

	/**
	 * Computes the exponential of this complex number.
	 *
	 * @return a new {@link Complex} representing e^(this)
	 */
	public Complex exp() {
		double expReal = Math.exp(this.r);
		return new Complex(expReal * Math.cos(this.i), expReal * Math.sin(this.i));
	}

	/**
	 * Computes the natural logarithm of this complex number.
	 *
	 * @return a new {@link Complex} representing ln(this)
	 */
	public Complex ln() {
		double magnitude = Math.log(magnitude());
		double angle = argument();
		return new Complex(magnitude, angle);
	}

	/**
	 * Computes the sine of this complex number.
	 *
	 * @return a new {@link Complex} representing sin(this)
	 */
	public Complex sin() {
		double realPart = Math.sin(this.r) * Math.cosh(this.i);
		double imaginaryPart = Math.cos(this.r) * Math.sinh(this.i);
		return new Complex(realPart, imaginaryPart);
	}

	/**
	 * Computes the cosine of this complex number.
	 *
	 * @return a new {@link Complex} representing cos(this)
	 */
	public Complex cos() {
		double realPart = Math.cos(this.r) * Math.cosh(this.i);
		double imaginaryPart = -Math.sin(this.r) * Math.sinh(this.i);
		return new Complex(realPart, imaginaryPart);
	}

	/**
	 * Computes the tangent of this complex number.
	 *
	 * @return a new {@link Complex} representing tan(this)
	 */
	public Complex tan() { return this.sin().div(this.cos()); }

	/**
	 * Computes the hyperbolic sine of this complex number.
	 *
	 * @return a new {@link Complex} representing sinh(this)
	 */
	public Complex sinh() {
		double realPart = Math.sinh(this.r) * Math.cos(this.i);
		double imaginaryPart = Math.cosh(this.r) * Math.sin(this.i);
		return new Complex(realPart, imaginaryPart);
	}

	/**
	 * Computes the conjugate of this complex number.
	 *
	 * @return a new {@link Complex} object representing the conjugate, with the
	 *         same real part and negated imaginary part
	 */
	public Complex conjugate() { return new Complex(this.r, -this.i); }

	/**
	 * Projects this complex number onto the real axis.
	 *
	 * @return a new {@link Complex} with the same real part and zero imaginary part
	 */
	public Complex projectOntoReal() { return new Complex(this.r, 0); }

	/**
	 * Projects this complex number onto the imaginary axis.
	 *
	 * @return a new {@link Complex} with zero real part and the same imaginary part
	 */
	public Complex projectOntoImaginary() { return new Complex(0, this.i); }

	/**
	 * Computes the squared magnitude (absolute value squared) of this complex
	 * number.
	 *
	 * @return the squared magnitude, computed as {@code r^2 + i^2}
	 * @see #magnitude()
	 */
	public double absoluteValue() { return r * r + i * i; }

	/**
	 * Computes the magnitude (length) of this complex number.
	 *
	 * @return the magnitude, computed as {@code sqrt(r^2 + i^2)}
	 * @see #absoluteValue()
	 */
	public double magnitude() { return Math.sqrt(absoluteValue()); }

	/**
	 * Computes the magnitude (length) of this complex number.
	 *
	 * @return the magnitude, computed as {@code sqrt(r^2 + i^2)}
	 * @see #absoluteValue()
	 */
	public double modulus() { return magnitude(); }

	/**
	 * Computes the magnitude (length) of this complex number.
	 *
	 * @return the magnitude, computed as {@code sqrt(r^2 + i^2)}
	 * @see #absoluteValue()
	 */
	public double mod() { return magnitude(); }

	/**
	 * Computes the argument (angle) of this complex number.
	 *
	 * @return the argument in radians, computed as {@code atan2(i, r)}
	 * @see Math#atan2(double, double)
	 */
	public double argument() { return Math.atan2(i, r); }

	/**
	 * Computes the argument (angle) of this complex number.
	 *
	 * @return the argument in radians, computed as {@code atan2(i, r)}
	 * @see Math#atan2(double, double)
	 */
	public double angle() { return argument(); }

	/**
	 * Computes the argument (angle) of this complex number.
	 *
	 * @return the argument in radians, computed as {@code atan2(i, r)}
	 * @see Math#atan2(double, double)
	 */
	public double arg() { return argument(); }

	/**
	 * Computes the sum of two complex numbers.
	 *
	 * @param a the first complex number
	 * @param b the second complex number
	 * @return a new {@link Complex} object representing the sum
	 * @see #add(Complex)
	 */
	public static Complex sum(Complex a, Complex b) {
		double r = a.r + b.r;
		double i = a.i + b.i;
		return new Complex(r, i);
	}

	/**
	 * Computes the difference of two complex numbers.
	 *
	 * @param a the first complex number
	 * @param b the second complex number
	 * @return a new {@link Complex} object representing the difference
	 * @see #sub(Complex)
	 */
	public static Complex difference(Complex a, Complex b) {
		double r = a.r - b.r;
		double i = a.i - b.i;
		return new Complex(r, i);
	}

	/**
	 * Computes the product of two complex numbers.
	 *
	 * @param a the first complex number
	 * @param b the second complex number
	 * @return a new {@link Complex} object representing the product
	 * @see #mul(Complex)
	 */
	public static Complex product(Complex a, Complex b) {
		double r = a.r * b.r - a.i * b.i;
		double i = a.r * b.i + a.i * b.r;
		return new Complex(r, i);
	}

	/**
	 * Computes the quotient of two complex numbers.
	 *
	 * @param a the numerator complex number
	 * @param b the denominator complex number
	 * @return a new {@link Complex} object representing the quotient
	 * @see #div(Complex)
	 */
	public static Complex quotient(Complex a, Complex b) {
		double r = a.r * b.r + a.i * b.i;
		double i = a.i * b.r - a.r * b.i;
		double div = b.r * b.r + b.i * b.i;
		return new Complex(r / div, i / div);
	}

	/**
	 * Checks if this complex number is approximately equal to another.
	 *
	 * @param other   the other complex number
	 * @param epsilon the tolerance for equality
	 * @return true if the numbers are approximately equal, false otherwise
	 */
	public boolean equals(Complex other, double epsilon) { return Math.abs(this.r - other.r) < epsilon && Math.abs(this.i - other.i) < epsilon; }

	/**
	 * Gets the real part of this complex number.
	 *
	 * @return the real part
	 */
	public double getReal() { return r; }

	/**
	 * Gets the imaginary part of this complex number.
	 *
	 * @return the imaginary part
	 */
	public double getImaginary() { return i; }

	/**
	 * Returns a string representation of this complex number.
	 * <p>
	 * The string representation is formatted as:
	 * <ul>
	 * <li><code>0</code> if both real and imaginary parts are zero</li>
	 * <li><code>a</code> if the imaginary part is zero</li>
	 * <li><code>bi</code> if the real part is zero</li>
	 * <li><code>(a + bi)</code> if both parts are nonzero</li>
	 * </ul>
	 * </p>
	 *
	 * @return the string representation of this complex number
	 */
	@Override
	public String toString() {
		String real = roundStr(r);
		String imaginary = roundStr(i);

		if (real.isBlank() && imaginary.isBlank()) {
			return "0";
		} else if (real.isBlank()) {
			return imaginary + "i";
		} else if (imaginary.isBlank()) {
			return real;
		} else {
			imaginary = imaginary.startsWith("-") ? imaginary.replace("-", " - ") : " + " + imaginary;
			return "(" + real + imaginary + "i)";
		}
	}

	/**
	 * Rounds the given value to 5 decimal places and converts it to a string.
	 *
	 * @param val the value to round
	 * @return the rounded value as a string, or an empty string if the result is
	 *         zero
	 */
	private String roundStr(double val) {
		String roundedStr = String.valueOf(Math.round(val * 100000) / 100000.0);
		if (roundedStr.endsWith(".0")) { roundedStr = roundedStr.replace(".0", ""); }
		if (roundedStr.equals("0") || roundedStr.equals("-0")) { roundedStr = ""; }
		return roundedStr;
	}

}
