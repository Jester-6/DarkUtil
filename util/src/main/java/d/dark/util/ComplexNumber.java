package d.dark.util;

public abstract class ComplexNumber {

	/**
	 * Adds another complex number to this complex number.
	 *
	 * @param c the complex number to add
	 * @return a new {@link Complex} representing the sum
	 * @see #add(double, double)
	 */
	public ComplexNumber add(ComplexNumber c) { return add(c.getR(), c.getI()); }

	/**
	 * Adds real number to this complex number.
	 *
	 * @param c the complex number to add
	 * @return a new {@link Complex} representing the sum
	 * @see #add(double, double)
	 */
	public ComplexNumber add(double r) { return new Complex(this.getR() + r, this.getI()); }

	/**
	 * Adds a real and imaginary number to this complex number.
	 *
	 * @param c the complex number to add
	 * @return a new {@link Complex} representing the sum
	 * @see #add(double, double)
	 */
	public ComplexNumber add(double r, double i) { return new Complex(this.getR() + r, this.getI() + i); }

	/**
	 * Subtracts another complex number from this complex number.
	 *
	 * @param c the complex number to subtract
	 * @return a new {@link Complex} representing the difference
	 * @see #sub(double, double)
	 */
	public ComplexNumber sub(ComplexNumber c) { return sub(c.getR(), c.getI()); }

	/**
	 * Subtracts a real number from this complex number.
	 *
	 * @param c the complex number to subtract
	 * @return a new {@link Complex} representing the difference
	 * @see #sub(double, double)
	 */
	public ComplexNumber sub(double r) { return new Complex(this.getR() - r, this.getI()); }

	/**
	 * Subtracts a real and imaginary number from this complex number.
	 *
	 * @param c the complex number to subtract
	 * @return a new {@link Complex} representing the difference
	 * @see #sub(double, double)
	 */
	public ComplexNumber sub(double r, double i) { return new Complex(this.getR() - r, this.getI() - i); }

	/**
	 * Multiplies this complex number with another complex number.
	 *
	 * @param c the complex number to multiply by
	 * @return a new {@link Complex} representing the product
	 * @see #mul(double, double)
	 */
	public ComplexNumber mul(ComplexNumber c) { return mul(c.getR(), c.getI()); }

	/**
	 * Multiplies this complex number by a real number.
	 *
	 * @param c the complex number to multiply by
	 * @return a new {@link Complex} representing the product
	 * @see #mul(double, double)
	 */
	public ComplexNumber mul(double r) { return new Complex(this.getR() * r, this.getI() * r); }

	/**
	 * Multiplies this complex number by a real and imaginary number.
	 *
	 * @param c the complex number to multiply by
	 * @return a new {@link Complex} representing the product
	 * @see #mul(double, double)
	 */
	public ComplexNumber mul(double r, double i) { return new Complex(this.getR() * r - this.getI() * i, this.getR() * i + this.getI() * r); }

	/**
	 * Divides this complex number by another complex number.
	 *
	 * @param c the complex number to divide by
	 * @return a new {@link Complex} representing the quotient
	 * @see #div(double, double)
	 */
	public ComplexNumber div(ComplexNumber c) { return div(c.getR(), c.getI()); }

	/**
	 * Divides this complex number by a real and imaginary number.
	 *
	 * @param c the complex number to divide by
	 * @return a new {@link Complex} representing the quotient
	 * @see #div(double, double)
	 */
	public ComplexNumber div(double r, double i) {
		double A = this.getR() * r + this.getI() * i;
		double B = this.getI() * r - this.getR() * i;
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
	public ComplexNumber div(double r) { return new Complex(this.getR() / r, this.getI() / r); }

	/**
	 * Raises this complex number to a given power.
	 *
	 * @param power the exponent to which the complex number is raised
	 * @return this complex number after exponentiation
	 * @see #root(double)
	 */
	public ComplexNumber pow(double power) {
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
	public ComplexNumber pow(ComplexNumber exponent) {
		// ln(this)
		ComplexNumber logBase = this.ln();
		// (c + di) * ln(this)
		ComplexNumber result = logBase.mul(exponent);
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
	public ComplexNumber root(double root) {
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
	public ComplexNumber exp() {
		double expReal = Math.exp(this.getR());
		return new Complex(expReal * Math.cos(this.getI()), expReal * Math.sin(this.getI()));
	}

	/**
	 * Computes the natural logarithm of this complex number.
	 *
	 * @return a new {@link Complex} representing ln(this)
	 */
	public ComplexNumber ln() {
		double magnitude = Math.log(magnitude());
		double angle = argument();
		return new Complex(magnitude, angle);
	}

	/**
	 * Computes the sine of this complex number.
	 *
	 * @return a new {@link Complex} representing sin(this)
	 */
	public ComplexNumber sin() {
		double realPart = Math.sin(this.getR()) * Math.cosh(this.getI());
		double imaginaryPart = Math.cos(this.getR()) * Math.sinh(this.getI());
		return new Complex(realPart, imaginaryPart);
	}

	/**
	 * Computes the cosine of this complex number.
	 *
	 * @return a new {@link Complex} representing cos(this)
	 */
	public ComplexNumber cos() {
		double realPart = Math.cos(this.getR()) * Math.cosh(this.getI());
		double imaginaryPart = -Math.sin(this.getR()) * Math.sinh(this.getI());
		return new Complex(realPart, imaginaryPart);
	}

	/**
	 * Computes the tangent of this complex number.
	 *
	 * @return a new {@link Complex} representing tan(this)
	 */
	public ComplexNumber tan() { return this.sin().div(this.cos()); }

	/**
	 * Computes the hyperbolic sine of this complex number.
	 *
	 * @return a new {@link Complex} representing sinh(this)
	 */
	public ComplexNumber sinh() {
		double realPart = Math.sinh(this.getR()) * Math.cos(this.getI());
		double imaginaryPart = Math.cosh(this.getR()) * Math.sin(this.getI());
		return new Complex(realPart, imaginaryPart);
	}

	/**
	 * Computes the conjugate of this complex number.
	 *
	 * @return a new {@link Complex} object representing the conjugate, with the
	 *         same real part and negated imaginary part
	 */
	public ComplexNumber conjugate() { return new Complex(this.getR(), -this.getI()); }

	/**
	 * Projects this complex number onto the real axis.
	 *
	 * @return a new {@link Complex} with the same real part and zero imaginary part
	 */
	public ComplexNumber projectOntoReal() { return new Complex(this.getR(), 0); }

	/**
	 * Projects this complex number onto the imaginary axis.
	 *
	 * @return a new {@link Complex} with zero real part and the same imaginary part
	 */
	public ComplexNumber projectOntoImaginary() { return new Complex(0, this.getI()); }

	/**
	 * Computes the squared magnitude (absolute value squared) of this complex
	 * number.
	 *
	 * @return the squared magnitude, computed as {@code r^2 + i^2}
	 * @see #magnitude()
	 */
	public double absoluteValue() { return getR() * getR() + getI() * getI(); }

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
	public double argument() { return Math.atan2(getI(), getR()); }

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
	public static ComplexNumber sum(ComplexNumber a, ComplexNumber b) {
		double r = a.getR() + b.getR();
		double i = a.getI() + b.getI();
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
	public static ComplexNumber difference(ComplexNumber a, ComplexNumber b) {
		double r = a.getR() - b.getR();
		double i = a.getI() - b.getI();
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
	public static ComplexNumber product(ComplexNumber a, ComplexNumber b) {
		double r = a.getR() * b.getR() - a.getI() * b.getI();
		double i = a.getR() * b.getI() + a.getI() * b.getR();
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
	public static ComplexNumber quotient(ComplexNumber a, ComplexNumber b) {
		double r = a.getR() * b.getR() + a.getI() * b.getI();
		double i = a.getI() * b.getR() - a.getR() * b.getI();
		double div = b.getR() * b.getR() + b.getI() * b.getI();
		return new Complex(r / div, i / div);
	}

	/**
	 * Checks if this complex number is approximately equal to another.
	 *
	 * @param other   the other complex number
	 * @param epsilon the tolerance for equality
	 * @return true if the numbers are approximately equal, false otherwise
	 */
	public boolean equals(ComplexNumber other, double epsilon) {
		return Math.abs(this.getR() - other.getR()) < epsilon && Math.abs(this.getI() - other.getI()) < epsilon;
	}

	/**
	 * Gets the {@code real part} of this complex number as {@code double}.
	 *
	 * @return the imaginary part
	 */
	public abstract double getR();

	/**
	 * Gets the {@code imaginary part} of this complex number as {@code double}.
	 *
	 * @return the imaginary part
	 */
	public abstract double getI();

	/**
	 * Gets the {@code real part} of this complex number as {@code float}.
	 *
	 * @return the imaginary part
	 */
	public abstract float floatR();

	/**
	 * Gets the {@code imaginary part} of this complex number as {@code float}.
	 *
	 * @return the imaginary part
	 */
	public abstract float floatI();

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
		String roundedStr = String.valueOf(Math.round(val * 100000) / 100000.0);
		if (roundedStr.endsWith(".0")) { roundedStr = roundedStr.replace(".0", ""); }
		if (roundedStr.equals("0") || roundedStr.equals("-0")) { roundedStr = ""; }
		return roundedStr;
	}

}
