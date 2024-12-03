package d.dark.util;

/**
 * <h1>Complex Number</h1> <br>
 * A class representing a <big>complex number</big> in the form `a + bi` with
 * double precision, where:
 * <ul>
 * <li><b>a</b> is the real part</li>
 * <li><b>b</b> is the imaginary part</li>
 * </ul>
 * for float precision Complex number, use {@link ComplexF}
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
public class Complex extends ComplexNumber {

	/* Immutable real part of the complex number */
	public final double r;
	/* Immutable imaginary part of the complex number */
	public final double i;

	/**
	 * Default constructor initializing the complex number to {@code 0 + 0i}.
	 */
	public Complex() { this(0, 0); }

	/**
	 * Constructor initializing the complex number with a real part of {@code 0} and
	 * the given imaginary part.
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
	public Complex(ComplexNumber complex) { this(complex.getR(), complex.getI()); }

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

	@Override
	public Complex add(ComplexNumber c) { return add(c.getR(), c.getI()); }

	@Override
	public Complex add(double r) { return new Complex(this.r + r, this.i); }

	@Override
	public Complex add(double r, double i) { return new Complex(this.r + r, this.i + i); }

	@Override
	public Complex sub(ComplexNumber c) { return sub(c.getR(), c.getI()); }

	@Override
	public Complex sub(double r) { return new Complex(this.r - r, this.i); }

	@Override
	public Complex sub(double r, double i) { return new Complex(this.r - r, this.i - i); }

	@Override
	public Complex mul(ComplexNumber c) { return mul(c.getR(), c.getI()); }

	@Override
	public Complex mul(double r) { return new Complex(this.r * r, this.i * r); }

	@Override
	public Complex mul(double r, double i) { return new Complex(this.r * r - this.i * i, this.r * i + this.i * r); }

	@Override
	public Complex div(ComplexNumber c) { return div(c.getR(), c.getI()); }

	@Override
	public Complex div(double r, double i) { return (Complex) super.div(r, i); }

	@Override
	public Complex div(double r) { return new Complex(this.r / r, this.i / r); }

	@Override
	public Complex pow(double exponent) { return (Complex) super.pow(exponent); }

	@Override
	public Complex pow(ComplexNumber exponent) { return (Complex) super.pow(exponent); }

	@Override
	public Complex root(double root) { return (Complex) super.root(root); }

	@Override
	public Complex exp() { return (Complex) super.exp(); }

	@Override
	public Complex ln() { return (Complex) super.ln(); }

	@Override
	public Complex sin() { return (Complex) super.sin(); }

	@Override
	public Complex cos() { return (Complex) super.cos(); }

	@Override
	public Complex tan() { return (Complex) super.tan(); }

	@Override
	public Complex sinh() { return (Complex) super.sinh(); }

	@Override
	public Complex conjugate() { return new Complex(this.r, -this.i); }

	@Override
	public Complex projectOntoReal() { return new Complex(this.r, 0); }

	@Override
	public Complex projectOntoImaginary() { return new Complex(0, this.i); }

	public static Complex sum(ComplexNumber a, ComplexNumber b) { return (Complex) ComplexNumber.sum(a, b); }

	public static Complex difference(ComplexNumber a, ComplexNumber b) { return (Complex) ComplexNumber.difference(a, b); }

	public static Complex product(ComplexNumber a, ComplexNumber b) { return (Complex) ComplexNumber.product(a, b); }

	public static Complex quotient(ComplexNumber a, ComplexNumber b) { return (Complex) ComplexNumber.quotient(a, b); }

	@Override
	public double getR() { return r; }

	@Override
	public double getI() { return i; }

	@Override
	public float floatR() { return (float) r; }

	@Override
	public float floatI() { return (float) i; }

}
