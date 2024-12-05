package d.dark.util;

import java.util.Objects;

/**
 * <h1>Complex Number</h1> <br>
 * A class representing a <big>complex number</big> in the form `a + bi`, where:
 * <ul>
 * <li><b>a</b> is the real part</li>
 * <li><b>b</b> is the imaginary part</li>
 * </ul>
 * <p>
 * It provides methods for performing common operations on complex numbers such
 * as:
 * <ul>
 * <li>Addition: {@link #add(Complex)}</li>
 * <li>Subtraction: {@link #sub(Complex)}</li>
 * <li>Multiplication: {@link #mul(double)}, {@link #mul(Complex)}</li>
 * <li>Division: {@link #div(double)}, {@link #div(Complex)}</li>
 * <li>Raising to a power: {@link #pow(double)}, {@link #pow(Complex)}</li>
 * <li>Root: {@link #root(double)}</li>
 * <li>Exponential: {@link #exp()}</li>
 * <li>Natural Logarithm: {@link #ln()}</li>
 * <li>Conjugate: {@link #conjugate()}</li>
 * <li>Magnitude (modulus): {@link #mag()}</li>
 * <li>Angle (argument): {@link #angle()}</li>
 * <li>Hyperbolic sine: {@link #sinh()}</li>
 * </ul>
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
public class Complex implements Comparable<Complex> {

	private static final double EPSILON = 0.00001;

	/* (0 + 0i) */
	public static final Complex ZERO = new Complex(0, 0);

	/* (Double.POSITIVE_INFINITY + 0i) */
	public static final Complex INF = new Complex(Double.POSITIVE_INFINITY, 0);

	/* (Double.NEGATIVE_INFINITY + 0i) */
	public static final Complex NEG_INF = new Complex(Double.NEGATIVE_INFINITY, 0);

	/* Immutable real part of the complex number */
	public final double r;
	/* Immutable imaginary part of the complex number */
	public final double i;

	private final double modulus;
	private final double argument;

	/**
	 * Default constructor initializing the complex number to {@code 0 + 0i}.
	 */
	public Complex() { this(0, 0); }

	/**
	 * Constructor initializing the complex number with an imaginary part of
	 * {@code 0} and the given real part.
	 *
	 * @param real the real part of the complex number
	 */
	public Complex(double real) { this(real, 0); }

	/**
	 * Constructor initializing the complex number as a copy of another complex
	 * number.
	 *
	 * @param complex the complex number to copy
	 * @see #Complex(double, double)
	 */
	public Complex(Complex complex) { this(complex.getR(), complex.getI()); }

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
		this.modulus = Math.sqrt(r * r + i * i);
		this.argument = Math.atan2(getI(), getR());
	}

	/**
	 * Adds another complex number to this complex number.
	 *
	 * @param c the complex number to add
	 * @return a new {@link Complex} representing the sum
	 * @see #add(double)
	 * @see #add(double, double)
	 */
	public Complex add(Complex c) { return add(c.getR(), c.getI()); }

	/**
	 * Adds a real number to this complex number.
	 *
	 * @param r the real part to add
	 * @return a new {@link Complex} representing the sum
	 * @see #add(Complex)
	 * @see #add(double, double)
	 */
	public Complex add(double r) { return new Complex(this.r + r, this.i); }

	/**
	 * Adds a real and imaginary number to this complex number.
	 *
	 * @param r the real part to add
	 * @param i the imaginary part to add
	 * @return a new {@link Complex} representing the sum
	 * @see #add(Complex)
	 * @see #add(double)
	 */
	public Complex add(double r, double i) { return new Complex(this.r + r, this.i + i); }

	/**
	 * Subtracts another complex number from this complex number.
	 *
	 * @param c the complex number to subtract
	 * @return a new {@link Complex} representing the difference
	 * @see #sub(double)
	 * @see #sub(double, double)
	 */
	public Complex sub(Complex c) { return sub(c.getR(), c.getI()); }

	/**
	 * Subtracts a real number from this complex number.
	 *
	 * @param r the real part to subtract
	 * @return a new {@link Complex} representing the difference
	 * @see #sub(Complex)
	 * @see #sub(double, double)
	 */
	public Complex sub(double r) { return new Complex(this.r - r, this.i); }

	/**
	 * Subtracts a real and imaginary number from this complex number.
	 *
	 * @param r the real part to subtract
	 * @param i the imaginary part to subtract
	 * @return a new {@link Complex} representing the difference
	 * @see #sub(Complex)
	 * @see #sub(double)
	 */
	public Complex sub(double r, double i) { return new Complex(this.r - r, this.i - i); }

	/**
	 * Multiplies this complex number by another complex number.
	 *
	 * @param c the complex number to multiply by
	 * @return a new {@link Complex} representing the product
	 * @see #mul(double)
	 * @see #mul(double, double)
	 */
	public Complex mul(Complex c) { return mul(c.getR(), c.getI()); }

	/**
	 * Multiplies this complex number by a real number.
	 *
	 * @param r the value to multiply by
	 * @return a new {@link Complex} representing the product
	 * @see #mul(Complex)
	 * @see #mul(double, double)
	 */
	public Complex mul(double r) { return new Complex(this.r * r, this.i * r); }

	/**
	 * Multiplies this complex number by a real and imaginary number.
	 *
	 * @param r the real part to multiply by
	 * @param i the imaginary part to multiply by
	 * @return a new {@link Complex} representing the product
	 * @see #mul(Complex)
	 * @see #mul(double)
	 */
	public Complex mul(double r, double i) { return new Complex(this.r * r - this.i * i, this.r * i + this.i * r); }

	/**
	 * Divides this complex number by another complex number.
	 *
	 * @param c the complex number to divide by
	 * @return a new {@link Complex} representing the quotient
	 * @see #div(double)
	 * @see #div(double, double)
	 */
	public Complex div(Complex c) {
		if (c.isZero()) { return INF; }
		return div(c.getR(), c.getI());
	}

	/**
	 * Divides this complex number by a real number.
	 *
	 * @param r the real part to divide by
	 * @return a new {@link Complex} representing the quotient
	 * @see #div(Complex)
	 * @see #div(double, double)
	 */
	public Complex div(double r) {
		if (Math.abs(r) <= Math.ulp(r)) { return INF; }
		return new Complex(this.getR() / r, this.getI() / r);
	}

	/**
	 * Divides this complex number by a real and imaginary number.
	 *
	 * @param r the real part to divide by
	 * @param i the imaginary part to divide by
	 * @return a new {@link Complex} representing the quotient
	 * @see #div(Complex)
	 * @see #div(double)
	 */
	public Complex div(double r, double i) {
		double A = this.getR() * r + this.getI() * i;
		double B = this.getI() * r - this.getR() * i;
		double div = r * r + i * i;
		if (Math.abs(div) < Math.ulp(div)) { return INF; }
		return new Complex(A / div, B / div);
	}

	/**
	 * Raises this complex number to a given power.
	 *
	 * @param power the exponent to which the complex number is raised
	 * @return this complex number after exponentiation
	 * @see #root(double)
	 */
	public Complex pow(double exponent) {
		if (compareNumbers(exponent, 0.0, EPSILON)) { return new Complex(1.0, 0.0); }
		double A = Math.pow(this.magnitude(), exponent);
		double cos = Math.cos(exponent * this.argument);
		double sin = Math.sin(exponent * this.argument);
		return new Complex(A * cos, A * sin);
	}

	/**
	 * Raises this complex number to the power of another complex number.
	 *
	 * @param exponent the complex exponent
	 * @return a new {@link Complex} representing this^exponent
	 */
	public Complex pow(Complex exponent) {
		if (compareNumbers(exponent.r, 0.0, EPSILON) && compareNumbers(exponent.i, 0.0, EPSILON)) { return new Complex(1.0, 0.0); }
		double lnR = Math.log(this.modulus);
		double mR = lnR * exponent.r - this.argument * exponent.i;
		double mI = lnR * exponent.i + this.argument * exponent.r;
		double expR = Math.exp(mR);
		return new Complex(expR * Math.cos(mI), expR * Math.sin(mI));
	}

	/**
	 * Computes the root of this complex number.
	 *
	 * @param root the root to compute (e.g., 2 for square root, 3 for cube root)
	 * @return this complex number after computing the root
	 * @see #pow(double)
	 */
	public Complex root(double root) {
		if (Math.abs(root) < Math.ulp(root)) { return ZERO; }
		double len = this.modulus;
		double phi = this.argument / root;
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
		double expReal = Math.exp(this.getR());
		return new Complex(expReal * Math.cos(this.getI()), expReal * Math.sin(this.getI()));
	}

	/**
	 * Computes the natural logarithm of this complex number.
	 *
	 * @return a new {@link Complex} representing ln(this)
	 */
	public Complex ln() {
		if (isZero()) { return NEG_INF; }
		return new Complex(Math.log(this.modulus), this.argument);
	}

	/**
	 * Computes the sine of this complex number.
	 *
	 * @return a new {@link Complex} representing sin(this)
	 */
	public Complex sin() {
		double realPart = Math.sin(this.getR()) * Math.cosh(this.getI());
		double imaginaryPart = Math.cos(this.getR()) * Math.sinh(this.getI());
		return new Complex(realPart, imaginaryPart);
	}

	/**
	 * Computes the cosine of this complex number.
	 *
	 * @return a new {@link Complex} representing cos(this)
	 */
	public Complex cos() {
		double realPart = Math.cos(this.getR()) * Math.cosh(this.getI());
		double imaginaryPart = -Math.sin(this.getR()) * Math.sinh(this.getI());
		return new Complex(realPart, imaginaryPart);
	}

	/**
	 * Computes the tangent of this complex number.
	 *
	 * @return a new {@link Complex} representing tan(this)
	 */
	public Complex tan() {
		double sinR = Math.sin(this.getR()) * Math.cosh(this.getI());
		double sinI = Math.cos(this.getR()) * Math.sinh(this.getI());

		double cosR = Math.cos(this.getR()) * Math.cosh(this.getI());
		double cosI = -Math.sin(this.getR()) * Math.sinh(this.getI());

		double A = sinR * cosR + sinI * cosI;
		double B = sinI * cosR - sinR * cosI;
		double div = cosR * cosR + cosI * cosI;
		if (Math.abs(div) < Math.ulp(div)) { return ZERO; }

		return new Complex(A / div, B / div);
	}

	/**
	 * Computes the hyperbolic sine of this complex number.
	 *
	 * @return a new {@link Complex} representing sinh(this)
	 */
	public Complex sinh() {
		double realPart = Math.sinh(this.getR()) * Math.cos(this.getI());
		double imaginaryPart = Math.cosh(this.getR()) * Math.sin(this.getI());
		return new Complex(realPart, imaginaryPart);
	}

	/**
	 * Computes the conjugate of this complex number.
	 *
	 * Conjugate is represented by 'a - bi'.
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
	public double absoluteValue() { return getR() * getR() + getI() * getI(); }

	/**
	 * Computes the sum of two complex numbers.
	 *
	 * @param a the first complex number
	 * @param b the second complex number
	 * @return a new {@link Complex} object representing the sum
	 * @see #add(Complex)
	 */
	public static Complex sum(Complex a, Complex b) { return sum(a, b); }

	/**
	 * Computes the difference of two complex numbers.
	 *
	 * @param a the first complex number
	 * @param b the second complex number
	 * @return a new {@link Complex} object representing the difference
	 * @see #sub(Complex)
	 */
	public static Complex difference(Complex a, Complex b) { return difference(a, b); }

	/**
	 * Computes the product of two complex numbers.
	 *
	 * @param a the first complex number
	 * @param b the second complex number
	 * @return a new {@link Complex} object representing the product
	 * @see #mul(Complex)
	 */
	public static Complex product(Complex a, Complex b) { return product(a, b); }

	/**
	 * Computes the quotient of two complex numbers.
	 *
	 * @param a the numerator complex number
	 * @param b the denominator complex number
	 * @return a new {@link Complex} object representing the quotient
	 * @see #div(Complex)
	 */
	public static Complex quotient(Complex a, Complex b) { return quotient(a, b); }

	/**
	 * Computes the distance to another complex number.
	 *
	 * @param c the other complex number to calculate distance to
	 * @return the distance between this complex number and other complex number
	 */
	public double distanceTo(Complex c) { return distanceTo(c.getR(), c.getI()); }

	/**
	 * Computes the distance to a real nmumber.
	 *
	 * @param r the real part to calculate distance to
	 * @return the distance between this complex number and other complex number
	 */
	public double distanceTo(double r) {
		double dr = this.getR() - r;
		return Math.sqrt(dr * dr + this.i * this.i);
	}

	/**
	 * Computes the distance to another complex number.
	 *
	 * @param r the real part to calculate distance to
	 * @param i the imaginary part to calculate distance to
	 * @return the distance between this complex number and real and imaginary parts
	 */
	public double distanceTo(double r, double i) {
		double dr = this.getR() - r;
		double di = this.getI() - i;
		return Math.sqrt(dr * dr + di * di);
	}

	/**
	 * Computes the magnitude (modulus) of this complex number.
	 *
	 * @return the magnitude, computed as {@code sqrt(r^2 + i^2)}
	 * @see #absoluteValue()
	 */
	public double magnitude() { return this.modulus; }

	/**
	 * Computes the modulus (magnitude) of this complex number.
	 *
	 * @return the modulus, computed as {@code sqrt(r^2 + i^2)}
	 * @see #absoluteValue()
	 */
	public double modulus() { return magnitude(); }

	/**
	 * Computes the argument (angle) of this complex number.
	 *
	 * @return the argument in radians, computed as {@code atan2(i, r)}
	 * @see Math#atan2(double, double)
	 */
	public double argument() { return this.argument; }

	/**
	 * Computes the angle (argument) of this complex number.
	 *
	 * @return the angle in radians, computed as {@code atan2(i, r)}
	 * @see Math#atan2(double, double)
	 */
	public double angle() { return argument(); }

	/**
	 * Gets the {@code real part} of this complex number.
	 *
	 * @return the imaginary part
	 */
	public double getR() { return r; }

	/**
	 * Gets the {@code imaginary part} of this complex number.
	 *
	 * @return the imaginary part
	 */
	public double getI() { return i; }

	@Override
	public int hashCode() { return Objects.hash(r, i); }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if ((obj == null) || (getClass() != obj.getClass())) {
			if (obj instanceof Number n) { return equals(n.doubleValue(), EPSILON); }
			return false;
		}
		Complex other = (Complex) obj;
		return equals(other, EPSILON);
	}

	/**
	 * Checks if this complex number is approximately equal to another.
	 *
	 * @param other   the other complex number
	 * @param epsilon the tolerance for equality
	 * @return true if the numbers are approximately equal, false otherwise
	 */
	public boolean equals(Complex other, double epsilon) { return equals(other.r, other.i, epsilon); }

	/**
	 * Checks if this complex number is approximately equal to a number.
	 *
	 * @param r       the other number to compare with
	 * @param epsilon the tolerance for equality
	 * @return true if the numbers are approximately equal, false otherwise
	 */
	public boolean equals(double r, double epsilon) { return compareNumbers(this.r, r, epsilon) && compareNumbers(this.i, 0.0, epsilon); }

	/**
	 * Checks if this complex number is approximately equal to a real and imaginary
	 * parts.
	 *
	 * @param r       the real part of the other complex number
	 * @param i       the imaginary part of the other complex number
	 * @param epsilon the tolerance for equality
	 * @return true if the numbers are approximately equal, false otherwise
	 */
	public boolean equals(double r, double i, double epsilon) { return compareNumbers(this.r, r, epsilon) && compareNumbers(this.i, i, epsilon); }

	private boolean compareNumbers(double r1, double r2, double epsilon) {
		double t = Math.abs(r1) + Math.abs(r2);
		if (t > epsilon) { epsilon *= t; }
		return Math.abs(r1 - r2) <= epsilon;
	}

	/**
	 * Tests if both real and imaginary part of this complex number is approximately
	 * equal to 0
	 *
	 * @param epsilon the tolerance for equality
	 * @return true if real and imaginary parts of this complex numer equals
	 *         approximately 0.0
	 */
	public boolean isZero(double epsilon) { return Math.abs(r) < epsilon && Math.abs(i) < epsilon; }

	/**
	 * Tests if magnitude of this complex number is approximately equal to 1
	 *
	 * @param epsilon the tolerance for equality
	 * @return true if the magnitude of this complex number is approximately equal
	 *         to 1.0
	 */
	public boolean isUnit(double epsilon) { return Math.abs(magnitude() - 1.0) < epsilon; }

	/**
	 * Tests if both real and imaginary part of this complex number is approximately
	 * equal to 0.<br>
	 * Tolerance is equal to {@value #EPSILON}
	 *
	 * @return true if real and imaginary parts of this complex numer equals
	 *         approximately 0.0
	 * @see isZero(double)
	 */
	public boolean isZero() { return isZero(EPSILON); }

	/**
	 * Tests if magnitude of this complex number is approximately equal to 1.<br>
	 * Tolerance is equal to {@value #EPSILON}
	 *
	 * @return true if the magnitude of this complex number is approximately equal
	 *         to 1.0
	 * @see isUnit(double)
	 */
	public boolean isUnit() { return isUnit(EPSILON); }

	@Override
	public int compareTo(Complex o) {
		if (Math.abs(this.r - o.r) < EPSILON) {
			return Math.abs(this.i + o.i) < EPSILON ? 0 : this.i > o.i ? 1 : -1;
		} else {
			return this.r > o.r ? 1 : -1;
		}
	}

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
		String real = roundStr(getR());
		String imaginary = roundStr(getI());

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
		if (val == Double.POSITIVE_INFINITY) {
			return "∞";
		} else if (val == Double.NEGATIVE_INFINITY) { return "-∞"; }
		String roundedStr = String.valueOf(Math.round(val * 100000) / 100000.0);
		if (roundedStr.endsWith(".0")) { roundedStr = roundedStr.replace(".0", ""); }
		if (roundedStr.equals("0") || roundedStr.equals("-0")) { roundedStr = ""; }
		return roundedStr;
	}
}
