package d.dark.util;

/**
 * <h1>Complex Number</h1> <br>
 * A class representing a <big>complex number</big> in the form `a + bi` with
 * float precision, where:
 * <ul>
 * <li><b>a</b> is the real part</li>
 * <li><b>b</b> is the imaginary part</li>
 * </ul>
 * for double precision Complex number, use {@link Complex}
 * <p>
 * It provides methods for performing common operations on complex numbers such
 * as:
 * <ul>
 * <li>Addition: {@link #add(ComplexNumber)}</li>
 * <li>Substraction: {@link #sub(ComplexNumber)}</li>
 * <li>Multiplication: {@link #mul(double)}, {@link #mul(ComplexNumber)}</li>
 * <li>Division: {@link #div(double)}, {@link #div(ComplexNumber)}</li>
 * <li>Raising to the power: {@link #pow(double)},
 * {@link #pow(ComplexNumber)}</li>
 * <li>Root: {@link #root(double)}</li>
 * <li>Exponential: {@link #exp()}</li>
 * <li>Natural Logarithm: {@link #ln()}</li>
 * <li>Conjugate: {@link #conjugate()}</li>
 * <li>Magnitude (modulus): {@link #mag()}</li>
 * <li>Angle (argument): {@link #angle()}</li>
 * <li>Hyperbolic sine of this number: {@link #sinh()}</li>
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
public class ComplexF extends ComplexNumber {

	/* Immutable real part of the complex number */
	public final float r;
	/* Immutable imaginary part of the complex number */
	public final float i;

	/**
	 * Default constructor initializing the complex number to `0 + 0i`.
	 */
	public ComplexF() { this(0, 0); }

	/**
	 * Constructor initializing the complex number with a real part of `0` and the
	 * given imaginary part.
	 *
	 * @param imaginary the imaginary part of the complex number
	 */
	public ComplexF(float imaginary) { this(0, imaginary); }

	/**
	 * Constructor initializing the complex number as a copy of another complex
	 * number.
	 *
	 * @param complex the complex number to copy
	 * @see #Complex(float, float)
	 */
	public ComplexF(ComplexNumber complex) { this(complex.floatR(), complex.floatI()); }

	/**
	 * Constructor initializing the complex number with the given real and imaginary
	 * parts.
	 *
	 * @param real      the real part
	 * @param imaginary the imaginary part
	 */
	public ComplexF(float real, float imaginary) {
		this.r = real;
		this.i = imaginary;
	}

	@Override
	public ComplexF add(ComplexNumber c) { return add(c.floatR(), c.floatI()); }

	/**
	 * Adds real number to this complex number.
	 *
	 * @param c the complex number to add
	 * @return a new {@link ComplexF} representing the sum
	 * @see #add(float, float)
	 */
	public ComplexF add(float r) { return new ComplexF(this.r + r, this.i); }

	/**
	 * Adds a real and imaginary number to this complex number.
	 *
	 * @param c the complex number to add
	 * @return a new {@link ComplexF} representing the sum
	 * @see #add(float, float)
	 */
	public ComplexF add(float r, float i) { return new ComplexF(this.r + r, this.i + i); }

	@Override
	public ComplexF sub(ComplexNumber c) { return sub(c.floatR(), c.floatI()); }

	/**
	 * Subtracts a real number from this complex number.
	 *
	 * @param c the complex number to subtract
	 * @return a new {@link ComplexF} representing the difference
	 * @see #sub(float, float)
	 */
	public ComplexF sub(float r) { return new ComplexF(this.r - r, this.i); }

	/**
	 * Subtracts a real and imaginary number from this complex number.
	 *
	 * @param c the complex number to subtract
	 * @return a new {@link ComplexF} representing the difference
	 * @see #sub(float, float)
	 */
	public ComplexF sub(float r, float i) { return new ComplexF(this.r - r, this.i - i); }

	@Override
	public ComplexF mul(ComplexNumber c) { return mul(c.floatR(), c.floatI()); }

	/**
	 * Multiplies this complex number by a real number.
	 *
	 * @param c the complex number to multiply by
	 * @return a new {@link ComplexF} representing the product
	 * @see #mul(float, float)
	 */
	public ComplexF mul(float r) { return new ComplexF(this.r * r, this.i * r); }

	/**
	 * Multiplies this complex number by a real and imaginary number.
	 *
	 * @param c the complex number to multiply by
	 * @return a new {@link ComplexF} representing the product
	 * @see #mul(float, float)
	 */
	public ComplexF mul(float r, float i) { return new ComplexF(this.r * r - this.i * i, this.r * i + this.i * r); }

	@Override
	public ComplexF div(ComplexNumber c) { return div(c.floatR(), c.floatI()); }

	/**
	 * Divides this complex number by a real and imaginary number.
	 *
	 * @param c the complex number to divide by
	 * @return a new {@link ComplexF} representing the quotient
	 * @see #div(float, float)
	 */
	public ComplexF div(float r, float i) {
		float A = this.r * r + this.i * i;
		float B = this.i * r - this.r * i;
		float div = r * r + i * i;
		return new ComplexF(A / div, B / div);
	}

	/**
	 * Divides this complex number by a real number.
	 *
	 * @param c the complex number to divide by
	 * @return a new {@link ComplexF} representing the quotient
	 * @see #div(float, float)
	 */
	public ComplexF div(float r) { return new ComplexF(this.r / r, this.i / r); }

	/**
	 * Raises this complex number to a given power.
	 *
	 * @param power the exponent to which the complex number is raised
	 * @return this complex number after exponentiation
	 * @see #root(float)
	 */
	public ComplexF pow(float power) {
		double len = magnitude();
		double phi = angle();
		float A = (float) Math.pow(len, power);
		float cos = (float) Math.cos(power * phi);
		float sin = (float) Math.sin(power * phi);
		return new ComplexF(A * cos, A * sin);
	}

	@Override
	public ComplexF pow(ComplexNumber exponent) {
		// ln(this)
		ComplexF logBase = this.ln();
		// (c + di) * ln(this)
		ComplexF result = logBase.mul(exponent);
		// e^result
		return result.exp();
	}

	/**
	 * Computes the root of this complex number.
	 *
	 * @param root the root to compute (e.g., 2 for square root, 3 for cube root)
	 * @return this complex number after computing the root
	 * @see #pow(float)
	 */
	public ComplexF root(float root) {
		double len = magnitude();
		double phi = angle() / root;
		float A = (float) Math.pow(len, 1.0 / root);
		float cos = (float) Math.cos(phi);
		float sin = (float) Math.sin(phi);
		return new ComplexF(A * cos, A * sin);
	}

	@Override
	public ComplexF exp() {
		float expReal = (float) Math.exp(this.r);
		return new ComplexF(expReal * (float) Math.cos(this.i), expReal * (float) Math.sin(this.i));
	}

	@Override
	public ComplexF ln() {
		float magnitude = (float) Math.log(magnitude());
		float angle = argumentF();
		return new ComplexF(magnitude, angle);
	}

	@Override
	public ComplexF sin() {
		float realPart = (float) (Math.sin(this.r) * Math.cosh(this.i));
		float imaginaryPart = (float) (Math.cos(this.r) * Math.sinh(this.i));
		return new ComplexF(realPart, imaginaryPart);
	}

	@Override
	public ComplexF cos() {
		float realPart = (float) (Math.cos(this.r) * Math.cosh(this.i));
		float imaginaryPart = (float) (-Math.sin(this.r) * Math.sinh(this.i));
		return new ComplexF(realPart, imaginaryPart);
	}

	@Override
	public ComplexF tan() { return this.sin().div(this.cos()); }

	@Override
	public ComplexF sinh() {
		float realPart = (float) (Math.sinh(this.r) * Math.cos(this.i));
		float imaginaryPart = (float) (Math.cosh(this.r) * Math.sin(this.i));
		return new ComplexF(realPart, imaginaryPart);
	}

	@Override
	public ComplexF conjugate() { return new ComplexF(this.r, -this.i); }

	@Override
	public ComplexF projectOntoReal() { return new ComplexF(this.r, 0); }

	@Override
	public ComplexF projectOntoImaginary() { return new ComplexF(0, this.i); }

	/**
	 * Computes the squared magnitude (absolute value squared) of this complex
	 * number.
	 *
	 * @return the squared magnitude, computed as {@code r^2 + i^2}
	 * @see #magnitude()
	 */
	public float absoluteValueF() { return r * r + i * i; }

	/**
	 * Computes the magnitude (length) of this complex number.
	 *
	 * @return the magnitude, computed as {@code sqrt(r^2 + i^2)}
	 * @see #absoluteValue()
	 */
	public float magnitudeF() { return (float) Math.sqrt(absoluteValue()); }

	/**
	 * Computes the magnitude (length) of this complex number.
	 *
	 * @return the magnitude, computed as {@code sqrt(r^2 + i^2)}
	 * @see #absoluteValue()
	 */
	public float modulusF() { return magnitudeF(); }

	/**
	 * Computes the magnitude (length) of this complex number.
	 *
	 * @return the magnitude, computed as {@code sqrt(r^2 + i^2)}
	 * @see #absoluteValue()
	 */
	public float modF() { return magnitudeF(); }

	/**
	 * Computes the argument (angle) of this complex number.
	 *
	 * @return the argument in radians, computed as {@code atan2(i, r)}
	 * @see Math#atan2(float, float)
	 */
	public float argumentF() { return (float) Math.atan2(i, r); }

	/**
	 * Computes the argument (angle) of this complex number.
	 *
	 * @return the argument in radians, computed as {@code atan2(i, r)}
	 * @see Math#atan2(float, float)
	 */
	public float angleF() { return argumentF(); }

	/**
	 * Computes the argument (angle) of this complex number.
	 *
	 * @return the argument in radians, computed as {@code atan2(i, r)}
	 * @see Math#atan2(float, float)
	 */
	public float argF() { return argumentF(); }

	/**
	 * Computes the sum of two complex numbers.
	 *
	 * @param a the first complex number
	 * @param b the second complex number
	 * @return a new {@link ComplexF} object representing the sum
	 * @see #add(ComplexF)
	 */
	public static ComplexF sum(ComplexNumber a, ComplexNumber b) {
		float r = a.floatR() + b.floatR();
		float i = a.floatI() + b.floatI();
		return new ComplexF(r, i);
	}

	/**
	 * Computes the difference of two complex numbers.
	 *
	 * @param a the first complex number
	 * @param b the second complex number
	 * @return a new {@link ComplexF} object representing the difference
	 * @see #sub(ComplexF)
	 */
	public static ComplexF difference(ComplexNumber a, ComplexNumber b) {
		float r = a.floatR() - b.floatR();
		float i = a.floatI() - b.floatI();
		return new ComplexF(r, i);
	}

	/**
	 * Computes the product of two complex numbers.
	 *
	 * @param a the first complex number
	 * @param b the second complex number
	 * @return a new {@link ComplexF} object representing the product
	 * @see #mul(ComplexF)
	 */
	public static ComplexF product(ComplexNumber a, ComplexNumber b) {
		float r = a.floatR() * b.floatR() - a.floatI() * b.floatI();
		float i = a.floatR() * b.floatI() + a.floatI() * b.floatR();
		return new ComplexF(r, i);
	}

	/**
	 * Computes the quotient of two complex numbers.
	 *
	 * @param a the numerator complex number
	 * @param b the denominator complex number
	 * @return a new {@link ComplexF} object representing the quotient
	 * @see #div(ComplexF)
	 */
	public static ComplexF quotient(ComplexNumber a, ComplexNumber b) {
		float r = a.floatR() * b.floatR() + a.floatI() * b.floatI();
		float i = a.floatI() * b.floatR() - a.floatR() * b.floatI();
		float div = b.floatR() * b.floatR() + b.floatI() * b.floatI();
		return new ComplexF(r / div, i / div);
	}

	/**
	 * Checks if this complex number is approximately equal to another.
	 *
	 * @param other   the other complex number
	 * @param epsilon the tolerance for equality
	 * @return true if the numbers are approximately equal, false otherwise
	 */
	public boolean equals(ComplexNumber other, float epsilon) {
		return Math.abs(this.r - other.floatR()) < epsilon && Math.abs(this.i - other.floatI()) < epsilon;
	}

	@Override
	public double getR() { return r; }

	@Override
	public double getI() { return i; }

	@Override
	public float floatR() { return r; }

	@Override
	public float floatI() { return i; }

}
