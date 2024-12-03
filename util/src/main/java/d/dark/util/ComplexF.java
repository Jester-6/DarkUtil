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
public class ComplexF {

	public static void main(String[] args) { System.out.println("test"); }

	public final float r; // The real part of the complex number (immutable)
	public final float i; // The imaginary part of the complex number (immutable)

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
	public ComplexF(ComplexF complex) { this(complex.getReal(), complex.getImaginary()); }

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

	/**
	 * Adds another complex number to this complex number.
	 *
	 * @param c the complex number to add
	 * @return a new {@link ComplexF} representing the sum
	 * @see #add(float, float)
	 */
	public ComplexF add(ComplexF c) { return add(c.r, c.i); }

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

	/**
	 * Subtracts another complex number from this complex number.
	 *
	 * @param c the complex number to subtract
	 * @return a new {@link ComplexF} representing the difference
	 * @see #sub(float, float)
	 */
	public ComplexF sub(ComplexF c) { return sub(c.r, c.i); }

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

	/**
	 * Multiplies this complex number with another complex number.
	 *
	 * @param c the complex number to multiply by
	 * @return a new {@link ComplexF} representing the product
	 * @see #mul(float, float)
	 */
	public ComplexF mul(ComplexF c) { return mul(c.r, c.i); }

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

	/**
	 * Divides this complex number by another complex number.
	 *
	 * @param c the complex number to divide by
	 * @return a new {@link ComplexF} representing the quotient
	 * @see #div(float, float)
	 */
	public ComplexF div(ComplexF c) { return div(c.r, c.i); }

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
		float len = magnitude();
		float phi = angle();
		float A = (float) Math.pow(len, power);
		float cos = (float) Math.cos(power * phi);
		float sin = (float) Math.sin(power * phi);
		return new ComplexF(A * cos, A * sin);
	}

	/**
	 * Raises this complex number to the power of another complex number.
	 *
	 * @param exponent the complex exponent
	 * @return a new {@link ComplexF} representing this^exponent
	 */
	public ComplexF pow(ComplexF exponent) {
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
		float len = magnitude();
		float phi = angle() / root;
		float A = (float) Math.pow(len, 1.0 / root);
		float cos = (float) Math.cos(phi);
		float sin = (float) Math.sin(phi);
		return new ComplexF(A * cos, A * sin);
	}

	/**
	 * Computes the exponential of this complex number.
	 *
	 * @return a new {@link ComplexF} representing e^(this)
	 */
	public ComplexF exp() {
		float expReal = (float) Math.exp(this.r);
		return new ComplexF(expReal * (float) Math.cos(this.i), expReal * (float) Math.sin(this.i));
	}

	/**
	 * Computes the natural logarithm of this complex number.
	 *
	 * @return a new {@link ComplexF} representing ln(this)
	 */
	public ComplexF ln() {
		float magnitude = (float) Math.log(magnitude());
		float angle = argument();
		return new ComplexF(magnitude, angle);
	}

	/**
	 * Computes the sine of this complex number.
	 *
	 * @return a new {@link ComplexF} representing sin(this)
	 */
	public ComplexF sin() {
		float realPart = (float) (Math.sin(this.r) * Math.cosh(this.i));
		float imaginaryPart = (float) (Math.cos(this.r) * Math.sinh(this.i));
		return new ComplexF(realPart, imaginaryPart);
	}

	/**
	 * Computes the cosine of this complex number.
	 *
	 * @return a new {@link ComplexF} representing cos(this)
	 */
	public ComplexF cos() {
		float realPart = (float) (Math.cos(this.r) * Math.cosh(this.i));
		float imaginaryPart = (float) (-Math.sin(this.r) * Math.sinh(this.i));
		return new ComplexF(realPart, imaginaryPart);
	}

	/**
	 * Computes the tangent of this complex number.
	 *
	 * @return a new {@link ComplexF} representing tan(this)
	 */
	public ComplexF tan() { return this.sin().div(this.cos()); }

	/**
	 * Computes the hyperbolic sine of this complex number.
	 *
	 * @return a new {@link ComplexF} representing sinh(this)
	 */
	public ComplexF sinh() {
		float realPart = (float) (Math.sinh(this.r) * Math.cos(this.i));
		float imaginaryPart = (float) (Math.cosh(this.r) * Math.sin(this.i));
		return new ComplexF(realPart, imaginaryPart);
	}

	/**
	 * Computes the conjugate of this complex number.
	 *
	 * @return a new {@link ComplexF} object representing the conjugate, with the
	 *         same real part and negated imaginary part
	 */
	public ComplexF conjugate() { return new ComplexF(this.r, -this.i); }

	/**
	 * Projects this complex number onto the real axis.
	 *
	 * @return a new {@link ComplexF} with the same real part and zero imaginary
	 *         part
	 */
	public ComplexF projectOntoReal() { return new ComplexF(this.r, 0); }

	/**
	 * Projects this complex number onto the imaginary axis.
	 *
	 * @return a new {@link ComplexF} with zero real part and the same imaginary
	 *         part
	 */
	public ComplexF projectOntoImaginary() { return new ComplexF(0, this.i); }

	/**
	 * Computes the squared magnitude (absolute value squared) of this complex
	 * number.
	 *
	 * @return the squared magnitude, computed as {@code r^2 + i^2}
	 * @see #magnitude()
	 */
	public float absoluteValue() { return r * r + i * i; }

	/**
	 * Computes the magnitude (length) of this complex number.
	 *
	 * @return the magnitude, computed as {@code sqrt(r^2 + i^2)}
	 * @see #absoluteValue()
	 */
	public float magnitude() { return (float) Math.sqrt(absoluteValue()); }

	/**
	 * Computes the magnitude (length) of this complex number.
	 *
	 * @return the magnitude, computed as {@code sqrt(r^2 + i^2)}
	 * @see #absoluteValue()
	 */
	public float modulus() { return magnitude(); }

	/**
	 * Computes the magnitude (length) of this complex number.
	 *
	 * @return the magnitude, computed as {@code sqrt(r^2 + i^2)}
	 * @see #absoluteValue()
	 */
	public float mod() { return magnitude(); }

	/**
	 * Computes the argument (angle) of this complex number.
	 *
	 * @return the argument in radians, computed as {@code atan2(i, r)}
	 * @see Math#atan2(float, float)
	 */
	public float argument() { return (float) Math.atan2(i, r); }

	/**
	 * Computes the argument (angle) of this complex number.
	 *
	 * @return the argument in radians, computed as {@code atan2(i, r)}
	 * @see Math#atan2(float, float)
	 */
	public float angle() { return argument(); }

	/**
	 * Computes the argument (angle) of this complex number.
	 *
	 * @return the argument in radians, computed as {@code atan2(i, r)}
	 * @see Math#atan2(float, float)
	 */
	public float arg() { return argument(); }

	/**
	 * Computes the sum of two complex numbers.
	 *
	 * @param a the first complex number
	 * @param b the second complex number
	 * @return a new {@link ComplexF} object representing the sum
	 * @see #add(ComplexF)
	 */
	public static ComplexF sum(ComplexF a, ComplexF b) {
		float r = a.r + b.r;
		float i = a.i + b.i;
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
	public static ComplexF difference(ComplexF a, ComplexF b) {
		float r = a.r - b.r;
		float i = a.i - b.i;
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
	public static ComplexF product(ComplexF a, ComplexF b) {
		float r = a.r * b.r - a.i * b.i;
		float i = a.r * b.i + a.i * b.r;
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
	public static ComplexF quotient(ComplexF a, ComplexF b) {
		float r = a.r * b.r + a.i * b.i;
		float i = a.i * b.r - a.r * b.i;
		float div = b.r * b.r + b.i * b.i;
		return new ComplexF(r / div, i / div);
	}

	/**
	 * Checks if this complex number is approximately equal to another.
	 *
	 * @param other   the other complex number
	 * @param epsilon the tolerance for equality
	 * @return true if the numbers are approximately equal, false otherwise
	 */
	public boolean equals(ComplexF other, float epsilon) { return Math.abs(this.r - other.r) < epsilon && Math.abs(this.i - other.i) < epsilon; }

	/**
	 * Gets the real part of this complex number.
	 *
	 * @return the real part
	 */
	public float getReal() { return r; }

	/**
	 * Gets the imaginary part of this complex number.
	 *
	 * @return the imaginary part
	 */
	public float getImaginary() { return i; }

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
	private String roundStr(float val) {
		String roundedStr = String.valueOf(Math.round(val * 100000) / 100000.0);
		if (roundedStr.endsWith(".0")) { roundedStr = roundedStr.replace(".0", ""); }
		if (roundedStr.equals("0") || roundedStr.equals("-0")) { roundedStr = ""; }
		return roundedStr;
	}

}
