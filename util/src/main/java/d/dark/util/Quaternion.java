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
 * This class provides methods for performing common operations on quaternions
 * such as:
 * <ul>
 * <li>Addition: {@link #add(Quaternion)}</li>
 * <li>Subtraction: {@link #sub(Quaternion)}</li>
 * <li>Multiplication: {@link #mul(double)}, {@link #mul(Quaternion)}</li>
 * <li>Division: {@link #div(double)}, {@link #div(Quaternion)}</li>
 * <li>Exponentiation: {@link #pow(double)}, {@link #pow(Quaternion)}</li>
 * <li>Root: {@link #root(double)}</li>
 * <li>Exponential: {@link #exp()}</li>
 * <li>Natural Logarithm: {@link #ln()}</li>
 * <li>Conjugate: {@link #conjugate()}</li>
 * <li>Norm (the length of the quaternion in 4D space): {@link #norm()}</li>
 * <li>Versor (unit quaternion): {@link #unit()}, {@link #unit()}</li>
 * <li>Multiplicative inverse (q⁻¹): {@link #inverse()}</li>
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

	private static final Quaternion INF = new Quaternion(Double.POSITIVE_INFINITY);
	private static final Quaternion NEG_INF = new Quaternion(Double.NEGATIVE_INFINITY);

	/* The real part (scalar) of this quaternion */
	public final double r;
	/* The first imaginary part (i) of this quaternion */
	public final double i;
	/* The second imaginary part (j) of this quaternion */
	public final double j;
	/* The third imaginary part (k) of this quaternion */
	public final double k;

	/**
	 * Default constructor initializing the quaternion to {@code 0 + 0i + 0j + 0k}.
	 */
	public Quaternion() { this(0, 0, 0, 0); }

	/**
	 * Constructor initializing the quaternion with the given real part and setting
	 * the vector part (i, j, k) to {@code (0i + 0j + 0k)}.
	 *
	 * @param real the real part of the quaternion
	 */
	public Quaternion(double real) { this(real, 0, 0, 0); }

	/**
	 * Constructor initializing the quaternion to (<b>real + i</b> + 0.0j + 0.0k)
	 *
	 * @param real the real part of the quaternion
	 * @param i    the first imaginary part of the quaternion (i)
	 */
	public Quaternion(double real, double i) { this(real, i, 0, 0); }

	/**
	 * Constructor initializing the quaternion to (<b>real + i + j</b> + 0.0k)
	 *
	 * @param real the real part of the quaternion
	 * @param i    the first imaginary part of the quaternion (i)
	 * @param j    the second imaginary part of the quaternion (j)
	 */
	public Quaternion(double real, double i, double j) { this(real, i, j, 0); }

	/**
	 * Constructor initializing the quaternion to (<b>real + i + j + k</b>)
	 *
	 * @param real the real part of the quaternion
	 * @param i    the first imaginary part of the quaternion (i)
	 * @param j    the second imaginary part of the quaternion (j)
	 * @param k    the third imaginary part of the quaternion (k)
	 */
	public Quaternion(double real, double i, double j, double k) {
		this.r = real;
		this.i = i;
		this.j = j;
		this.k = k;
	}

	/**
	 * Constructor initializing the quaternion to (<b>complex.real +
	 * complex.imaginary</b> + 0.0j + 0.0k).
	 *
	 * @param complex the complex number to create a quaternion from
	 */
	public Quaternion(Complex complex) { this(complex.getR(), complex.getI(), 0, 0); }

	/**
	 * Constructor initializing the quaternion to (<b>complex.real +
	 * complex.imaginary + j</b> + 0.0k).
	 *
	 * @param complex the complex number to create a quaternion from
	 * @param j       the third imaginary component (j)
	 */
	public Quaternion(Complex complex, double j) { this(complex.getR(), complex.getI(), j, 0); }

	/**
	 * Constructor initializing the quaternion to (<b>complex.real +
	 * complex.imaginary + j + k</b>).
	 *
	 * @param complex the complex number to create a quaternion from
	 * @param j       the second imaginary component (j)
	 * @param k       the third imaginary component (k)
	 */
	public Quaternion(Complex complex, double j, double k) { this(complex.getR(), complex.getI(), j, k); }

	/**
	 * Constructor initializing the quaternion as a copy of another quaternion.
	 *
	 * @param quaternion the quaternion to copy
	 * @see #Quaternion(double, double, double, double)
	 */
	public Quaternion(Quaternion quaternion) { this(quaternion.r, quaternion.i, quaternion.j, quaternion.k); }

	/**
	 * Adds another complex number to this quaternion.
	 *
	 * @param c the complex number to add
	 * @return a new {@link Quaternion} representing the sum
	 * @see #add(double)
	 * @see #add(Quaternion)
	 * @see #add(double, double, double, double)
	 */
	public Quaternion add(Complex c) { return add(c.getR(), c.getI()); }

	/**
	 * Adds a complex number and a real number to this quaternion.
	 *
	 * @param c the complex number to add
	 * @param i the imaginary part of the complex number to add
	 * @return a new {@link Quaternion} representing the sum
	 * @see #add(Complex)
	 * @see #add(Quaternion)
	 * @see #add(double, double, double, double)
	 */
	public Quaternion add(Complex c, double i) { return add(c.getR(), c.getI(), i); }

	/**
	 * Adds a complex number and two real numbers to this quaternion.
	 *
	 * @param c the complex number to add
	 * @param i the imaginary part of the complex number to add
	 * @param k the real number to add
	 * @return a new {@link Quaternion} representing the sum
	 * @see #add(Complex)
	 * @see #add(Quaternion)
	 * @see #add(double, double, double, double)
	 */
	public Quaternion add(Complex c, double i, double k) { return add(c.getR(), c.getI(), i, k); }

	/**
	 * Adds another quaternion to this quaternion.
	 *
	 * @param c the quaternion to add
	 * @return a new {@link Quaternion} representing the sum
	 * @see #add(Complex)
	 * @see #add(Quaternion)
	 * @see #add(double, double, double, double)
	 */
	public Quaternion add(Quaternion c) { return add(this.r, this.i, this.j, this.k); }

	/**
	 * Adds a real number to this quaternion.
	 *
	 * @param real the real part to add
	 * @return a new {@link Quaternion} representing the sum
	 * @see #add(Complex)
	 * @see #add(Quaternion)
	 * @see #add(double, double, double, double)
	 */
	public Quaternion add(double real) { return new Quaternion(this.r + real, this.i, this.j, this.k); }

	/**
	 * Adds a real number and an imaginary number (i) to this quaternion.
	 *
	 * @param real the real part to add
	 * @param i    the imaginary part to add
	 * @return a new {@link Quaternion} representing the sum
	 * @see #add(Complex)
	 * @see #add(Quaternion)
	 * @see #add(double, double, double, double)
	 */
	public Quaternion add(double real, double i) { return new Quaternion(this.r + real, this.i + i, this.j, this.k); }

	/**
	 * Adds a real number and two imaginary numbers (i, j) to this quaternion.
	 *
	 * @param real the real part to add
	 * @param i    the imaginary part (i) to add
	 * @param j    the imaginary part (j) to add
	 * @return a new {@link Quaternion} representing the sum
	 * @see #add(Complex)
	 * @see #add(Quaternion)
	 * @see #add(double, double, double, double)
	 */
	public Quaternion add(double real, double i, double j) { return new Quaternion(this.r + real, this.i + i, this.j + j, this.k); }

	/**
	 * Adds a real number and three imaginary numbers (i, j, k) to this quaternion.
	 *
	 * @param real the real part to add
	 * @param i    the imaginary part (i) to add
	 * @param j    the imaginary part (j) to add
	 * @param k    the imaginary part (k) to add
	 * @return a new {@link Quaternion} representing the sum
	 * @see #add(double)
	 * @see #add(Complex)
	 * @see #add(Quaternion)
	 */
	public Quaternion add(double real, double i, double j, double k) { return new Quaternion(this.r + real, this.i + i, this.j + j, this.k + k); }

	/**
	 * Subtracts another complex number from this quaternion.
	 *
	 * @param c the complex number to subtract
	 * @return a new {@link Quaternion} representing the difference
	 * @see #sub(double)
	 * @see #sub(Quaternion)
	 * @see #sub(double, double, double, double)
	 */
	public Quaternion sub(Complex c) { return sub(c.getR(), c.getI()); }

	/**
	 * Subtracts a complex number and a real number from this quaternion.
	 *
	 * @param c the complex number to subtract
	 * @param i the imaginary part of the complex number to subtract
	 * @return a new {@link Quaternion} representing the difference
	 * @see #sub(Complex)
	 * @see #sub(Quaternion)
	 * @see #sub(double, double, double, double)
	 */
	public Quaternion sub(Complex c, double i) { return sub(c.getR(), c.getI(), i); }

	/**
	 * Subtracts a complex number and two real numbers from this quaternion.
	 *
	 * @param c the complex number to subtract
	 * @param i the imaginary part of the complex number to subtract
	 * @param k the real number to subtract
	 * @return a new {@link Quaternion} representing the difference
	 * @see #sub(Complex)
	 * @see #sub(Quaternion)
	 * @see #sub(double, double, double, double)
	 */
	public Quaternion sub(Complex c, double i, double k) { return sub(c.getR(), c.getI(), i, k); }

	/**
	 * Subtracts another quaternion from this quaternion.
	 *
	 * @param c the quaternion to subtract
	 * @return a new {@link Quaternion} representing the difference
	 * @see #sub(double)
	 * @see #sub(Complex)
	 * @see #sub(double, double, double, double)
	 */
	public Quaternion sub(Quaternion c) { return sub(this.r, this.i, this.j, this.k); }

	/**
	 * Subtracts a real number from this quaternion.
	 *
	 * @param real the real part to subtract
	 * @return a new {@link Quaternion} representing the difference
	 * @see #sub(Complex)
	 * @see #sub(Quaternion)
	 * @see #sub(double, double, double, double)
	 */
	public Quaternion sub(double real) { return new Quaternion(this.r - real, this.i, this.j, this.k); }

	/**
	 * Subtracts a real number and an imaginary number (i) from this quaternion.
	 *
	 * @param real the real part to subtract
	 * @param i    the imaginary part to subtract
	 * @return a new {@link Quaternion} representing the difference
	 * @see #sub(Complex)
	 * @see #sub(Quaternion)
	 * @see #sub(double, double, double, double)
	 */
	public Quaternion sub(double real, double i) { return new Quaternion(this.r - real, this.i - i, this.j, this.k); }

	/**
	 * Subtracts a real number and two imaginary numbers (i, j) from this
	 * quaternion.
	 *
	 * @param real the real part to subtract
	 * @param i    the imaginary part (i) to subtract
	 * @param j    the imaginary part (j) to subtract
	 * @return a new {@link Quaternion} representing the difference
	 * @see #sub(Complex)
	 * @see #sub(Quaternion)
	 * @see #sub(double, double, double, double)
	 */
	public Quaternion sub(double real, double i, double j) { return new Quaternion(this.r - real, this.i - i, this.j - j, this.k); }

	/**
	 * Subtracts a real number and three imaginary numbers (i, j, k) from this
	 * quaternion.
	 *
	 * @param real the real part to subtract
	 * @param i    the imaginary part (i) to subtract
	 * @param j    the imaginary part (j) to subtract
	 * @param k    the imaginary part (k) to subtract
	 * @return a new {@link Quaternion} representing the difference
	 * @see #sub(double)
	 * @see #sub(Complex)
	 * @see #sub(Quaternion)
	 */
	public Quaternion sub(double real, double i, double j, double k) { return new Quaternion(this.r - real, this.i - i, this.j - j, this.k - k); }

	/**
	 * Multiplies this quaternion by another quaternion represented by (r, i, j, k).
	 *
	 * @param r the real part of the quaternion to multiply
	 * @param i the imaginary part (i) of the quaternion to multiply
	 * @param j the imaginary part (j) of the quaternion to multiply
	 * @param k the imaginary part (k) of the quaternion to multiply
	 * @return a new {@link Quaternion} representing the product
	 * @see #mul(double)
	 * @see #mul(Complex)
	 * @see #mul(Quaternion)
	 */
	public Quaternion mul(double r, double i, double j, double k) {
		double R = this.r * r - this.i * i - this.j * j - this.k * k;
		double I = this.r * i + this.i * r + this.j * k - this.k * j;
		double J = this.r * j - this.i * k + this.j * r + this.k * i;
		double K = this.r * k + this.i * j - this.j * i + this.k * r;

		return new Quaternion(R, I, J, K);
	}

	/**
	 * Multiplies this quaternion by another quaternion represented by (r, i, j).
	 *
	 * @param r the real part of the quaternion to multiply
	 * @param i the imaginary part (i) of the quaternion to multiply
	 * @param j the imaginary part (j) of the quaternion to multiply
	 * @return a new {@link Quaternion} representing the product
	 * @see #mul(double)
	 * @see #mul(Complex)
	 * @see #mul(Quaternion)
	 * @see #mul(double, double, double, double)
	 */
	public Quaternion mul(double r, double i, double j) {
		double R = this.r * r - this.i * i - this.j * j;
		double I = this.r * i + this.i * r - this.k * j;
		double J = this.r * j + this.j * r + this.k * i;
		double K = this.i * j - this.j * i + this.k * r;

		return new Quaternion(R, I, J, K);
	}

	/**
	 * Multiplies this quaternion by another quaternion represented by (r, i).
	 *
	 * @param r the real part of the quaternion to multiply
	 * @param i the imaginary part (i) of the quaternion to multiply
	 * @return a new {@link Quaternion} representing the product
	 * @see #mul(double)
	 * @see #mul(Complex)
	 * @see #mul(Quaternion)
	 * @see #mul(double, double, double, double)
	 */
	public Quaternion mul(double r, double i) {
		double R = this.r * r - this.i * i;
		double I = this.r * i + this.i * r;
		double J = this.j * r + this.k * i;
		double K = this.j * i + this.k * r;

		return new Quaternion(R, I, J, K);
	}

	/**
	 * Multiplies this quaternion by a scalar (real number).
	 *
	 * @param r the scalar to multiply by
	 * @return a new {@link Quaternion} representing the product
	 * @see #mul(Complex)
	 * @see #mul(Quaternion)
	 * @see #mul(double, double, double, double)
	 */
	public Quaternion mul(double r) { return new Quaternion(this.r * r, this.i * r, this.j * r, this.k * r); }

	/**
	 * Multiplies this quaternion by another quaternion.
	 *
	 * @param q the quaternion to multiply with
	 * @return a new {@link Quaternion} representing the product
	 * @see #mul(double)
	 * @see #mul(Complex)
	 * @see #mul(double, double, double, double)
	 */
	public Quaternion mul(Quaternion q) { return mul(q.r, q.i, q.j, q.k); }

	/**
	 * Multiplies this quaternion by a complex number.
	 *
	 * @param c the complex number to multiply with
	 * @return a new {@link Quaternion} representing the product
	 * @see #mul(double)
	 * @see #mul(Quaternion)
	 * @see #mul(double, double, double, double)
	 */
	public Quaternion mul(Complex c) { return mul(c.getR(), c.getI()); }

	/**
	 * Divides this quaternion by another quaternion represented by (r, i, j, k).
	 *
	 * @param q the quaternion to divide by
	 * @return a new {@link Quaternion} representing the result of the division
	 * @see #div(double)
	 * @see #div(Complex)
	 * @see #div(double, double, double, double)
	 */
	public Quaternion div(Quaternion q) { return div(q.r, q.i, q.j, q.k); }

	/**
	 * Divides this quaternion by a complex number and two additional components (j,
	 * k).
	 *
	 * @param c the complex number to divide by
	 * @param j the imaginary part (j) of the quaternion to divide by
	 * @param k the imaginary part (k) of the quaternion to divide by
	 * @return a new {@link Quaternion} representing the result of the division
	 * @see #div(double)
	 * @see #div(Complex)
	 * @see #div(Quaternion)
	 * @see #div(double, double, double, double)
	 */
	public Quaternion div(Complex c, double j, double k) { return div(c.getR(), c.getI(), j, k); }

	/**
	 * Divides this quaternion by a complex number and one additional component (j).
	 *
	 * @param c the complex number to divide by
	 * @param j the imaginary part (j) of the quaternion to divide by
	 * @return a new {@link Quaternion} representing the result of the division
	 * @see #div(double)
	 * @see #div(Complex)
	 * @see #div(Quaternion)
	 * @see #div(double, double, double, double)
	 */
	public Quaternion div(Complex c, double j) { return div(c.getR(), c.getI(), j); }

	/**
	 * Divides this quaternion by a complex number.
	 *
	 * @param c the complex number to divide by
	 * @return a new {@link Quaternion} representing the result of the division
	 * @see #div(double)
	 * @see #div(Quaternion)
	 * @see #div(double, double, double, double)
	 */
	public Quaternion div(Complex c) { return div(c.getR(), c.getI()); }

	/**
	 * Divides this quaternion by a scalar (real number).
	 *
	 * @param real the scalar to divide by
	 * @return a new {@link Quaternion} representing the result of the division
	 * @see #div(Complex)
	 * @see #div(Quaternion)
	 * @see #div(double, double, double, double)
	 */
	public Quaternion div(double real) { return new Quaternion(this.r / real, this.i / real, this.j / real, this.k / real); }

	/**
	 * Divides this quaternion by another quaternion represented by (r, i, j, k).
	 *
	 * @param r the real part of the quaternion to divide by
	 * @param i the imaginary part (i) of the quaternion to divide by
	 * @param j the imaginary part (j) of the quaternion to divide by
	 * @param k the imaginary part (k) of the quaternion to divide by
	 * @return a new {@link Quaternion} representing the result of the division
	 * @see #div(double)
	 * @see #div(Complex)
	 * @see #div(Quaternion)
	 */
	public Quaternion div(double r, double i, double j, double k) {
		double d = r * r + i * i + j * j + k * k;
		double R = r * this.r + i * this.i + j * this.j + k * this.k;
		double I = r * this.i - i * this.r - j * this.k + k * this.j;
		double J = r * this.j + i * this.k - j * this.r - k * this.i;
		double K = r * this.k - i * this.j + j * this.i - k * this.r;
		return new Quaternion(R / d, I / d, J / d, K / d);
	}

	/**
	 * Divides this quaternion by another quaternion represented by (r, i, j).
	 *
	 * @param r the real part of the quaternion to divide by
	 * @param i the imaginary part (i) of the quaternion to divide by
	 * @param j the imaginary part (j) of the quaternion to divide by
	 * @return a new {@link Quaternion} representing the result of the division
	 * @see #div(double)
	 * @see #div(Complex)
	 * @see #div(Quaternion)
	 * @see #div(double, double, double, double)
	 */
	public Quaternion div(double r, double i, double j) {
		double d = r * r + i * i + j * j;
		double R = r * this.r + i * this.i + j * this.j;
		double I = r * this.i - i * this.r - j * this.k;
		double J = r * this.j + i * this.k - j * this.r;
		double K = r * this.k - i * this.j + j * this.i;
		return new Quaternion(R / d, I / d, J / d, K / d);
	}

	/**
	 * Divides this quaternion by another quaternion represented by (r, i).
	 *
	 * @param r the real part of the quaternion to divide by
	 * @param i the imaginary part (i) of the quaternion to divide by
	 * @return a new {@link Quaternion} representing the result of the division
	 * @see #div(double)
	 * @see #div(Complex)
	 * @see #div(Quaternion)
	 * @see #div(double, double, double, double)
	 */
	public Quaternion div(double r, double i) {
		double d = r * r + i * i;
		double R = r * this.r + i * this.i;
		double I = r * this.i - i * this.r;
		double J = r * this.j + i * this.k;
		double K = r * this.k - i * this.j;
		return new Quaternion(R / d, I / d, J / d, K / d);
	}

	/**
	 * Returns the conjugate of this quaternion. The conjugate of a quaternion is
	 * obtained by negating the imaginary components (i, j, k) while keeping the
	 * real component (r) unchanged.
	 *
	 * @return a new {@link Quaternion} representing the conjugate of this
	 *         quaternion
	 */
	public Quaternion conjugate() { return new Quaternion(this.r, -this.i, -this.j, -this.k); }

	/**
	 * Returns the multiplicative inverse of this quaternion. The multiplicative
	 * inverse of a quaternion q = (r, i, j, k) is given by q⁻¹ = (r/d, -i/d, -j/d,
	 * -k/d), where d is the squared norm of the quaternion.
	 *
	 * @return a new {@link Quaternion} representing the multiplicative inverse of
	 *         this quaternion
	 */
	public Quaternion inverse() {
		double d = squaredNorm();
		return new Quaternion(r / d, i / d, j / d, k / d);
	}

	/**
	 * Returns the versor of this quaternion, which is the same as its unit
	 * quaternion. A versor is a quaternion with unit norm, often used to represent
	 * rotations.
	 *
	 * @return a new {@link Quaternion} representing the versor (unit quaternion) of
	 *         this quaternion
	 */
	public Quaternion versor() { return unit(); }

	/**
	 * Returns the unit quaternion of this quaternion. The unit quaternion is
	 * obtained by dividing the quaternion by its norm.
	 *
	 * @return a new {@link Quaternion} representing the unit quaternion (normalized
	 *         quaternion)
	 */
	public Quaternion unit() { return this.div(norm()); }

	/**
	 * Returns the squared norm (magnitude squared) of this quaternion. The squared
	 * norm of a quaternion q = (r, i, j, k) is calculated as r² + i² + j² + k².
	 *
	 * @return the squared norm (r² + i² + j² + k²) of this quaternion
	 */
	public double squaredNorm() { return r * r + i * i + j * j + k * k; }

	/**
	 * Returns the Euclidean distance between this quaternion and another quaternion
	 * or point (r, i, j, k). This distance is computed as the square root of the
	 * sum of the squared differences of the components.
	 *
	 * @param r the real component of the other point
	 * @param i the imaginary component (i) of the other point
	 * @param j the imaginary component (j) of the other point
	 * @param k the imaginary component (k) of the other point
	 * @return the Euclidean distance between this quaternion and the point (r, i,
	 *         j, k)
	 */
	public double distanceTo(double r, double i, double j, double k) {
		double dr = this.r - r;
		double di = this.i - i;
		double dj = this.j - j;
		double dk = this.k - k;
		return Math.sqrt(dr * dr + di * di + dj * dj + dk * dk);
	}

	/**
	 * Computes the exponential of this quaternion. The exponential of a quaternion
	 * is defined as exp(q) = exp(r) * (cos(v) + sin(v) * v̂), where v is the vector
	 * part of the quaternion.
	 *
	 * @return a new {@link Quaternion} representing the exponential of this
	 *         quaternion
	 */
	public Quaternion exp() {
		double vectorNorm = Math.sqrt(i * i + j * j + k * k);
		double R = Math.exp(r);

		if (testIfZero(vectorNorm)) { return new Quaternion(R, 0, 0, 0); }

		double sinNorm = Math.sin(vectorNorm) / vectorNorm;

		return new Quaternion(R * Math.cos(vectorNorm), R * sinNorm * i, R * sinNorm * j, R * sinNorm * k);
	}

	/**
	 * Computes the natural logarithm of this quaternion. The logarithm of a
	 * quaternion q = (r, i, j, k) is defined as ln(q) = ln(norm(q)) + angle(q) *
	 * (î, ĵ, k̂), where angle(q) is the angle of the quaternion.
	 *
	 * @return a new {@link Quaternion} representing the logarithm of this
	 *         quaternion
	 * @throws ArithmeticException if the quaternion has zero norm, as the logarithm
	 *                             is undefined in that case
	 */
	public Quaternion ln() {
		double norm = norm();
		if (testIfZero(norm)) { return NEG_INF; }
		double vectorNorm = Math.sqrt(i * i + j * j + k * k);
		double angle = Math.acos(r / norm);

		if (vectorNorm == 0) { return new Quaternion(Math.log(norm), 0, 0, 0); }

		double coeff = angle / vectorNorm;

		return new Quaternion(Math.log(norm), coeff * i, coeff * j, coeff * k);
	}

	/**
	 * Raises this quaternion to the power of another quaternion. The power of a
	 * quaternion is computed by taking the natural logarithm of the quaternion,
	 * multiplying it by the other quaternion, and then exponentiating the result.
	 *
	 * @param q the quaternion to raise this quaternion to the power of
	 * @return a new {@link Quaternion} representing this quaternion raised to the
	 *         power of q
	 */
	public Quaternion pow(Quaternion q) {
		double norm = norm();
		if (testIfZero(norm)) { return NEG_INF; }
		double vectorNorm = Math.sqrt(i * i + j * j + k * k);
		double angle = Math.acos(r / norm);

		if (vectorNorm == 0) { return new Quaternion(Math.log(norm), 0, 0, 0); }

		double coeff = angle / vectorNorm;

		double lnR = Math.log(norm);
		double lnI = coeff * i;
		double lnJ = coeff * j;
		double lnK = coeff * k;

		double mulR = lnR * q.r - lnI * q.i - lnJ * q.j - lnK * q.k;
		double mulI = lnR * q.i + lnI * q.r + lnJ * q.k - lnK * q.j;
		double mulJ = lnR * q.j - lnI * q.k + lnJ * q.r + lnK * q.i;
		double mulK = lnR * q.k + lnI * q.j - lnJ * q.i + lnK * q.r;

		double mulVectorNorm = Math.sqrt(mulI * mulI + mulJ * mulJ + mulK * mulK);
		double expR = Math.exp(mulR);

		if (testIfZero(mulVectorNorm)) { return new Quaternion(expR, 0, 0, 0); }

		double sinNorm = Math.sin(mulVectorNorm) / mulVectorNorm;

		return new Quaternion(expR * Math.cos(mulVectorNorm), expR * sinNorm * mulI, expR * sinNorm * mulJ, expR * sinNorm * mulK);
	}

	/**
	 * Raises this quaternion to a scalar power. The quaternion is raised to the
	 * given scalar power by first calculating the norm and angle of the quaternion,
	 * then applying the power to the norm and angle.
	 *
	 * @param scalar the scalar to raise this quaternion to the power of
	 * @return a new {@link Quaternion} representing this quaternion raised to the
	 *         scalar power
	 */
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

	/**
	 * Computes the n-th root of this quaternion. The n-th root of a quaternion is
	 * computed by first calculating the norm and angle of the quaternion, then
	 * taking the n-th root of the norm and scaling the angle accordingly.
	 *
	 * @param n the order of the root (must be positive)
	 * @return a new {@link Quaternion} representing the n-th root of this
	 *         quaternion
	 * @throws IllegalArgumentException if n is less than or equal to 0
	 */
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

	/**
	 * Returns the norm (magnitude) of this quaternion. The norm of a quaternion is
	 * the square root of the sum of the squares of its components: √(r² + i² + j² +
	 * k²).
	 *
	 * @return the norm (magnitude) of this quaternion
	 */
	public double norm() { return Math.sqrt(squaredNorm()); }

	/**
	 * Returns the real part (r) of the quaternion. This is the scalar component of
	 * the quaternion, often referred to as the real part.
	 *
	 * @return the real part (r) of the quaternion
	 */
	public double getR() { return r; }

	/**
	 * Returns the i component (i) of the quaternion. This is the imaginary
	 * component along the i-axis, often referred to as the i component.
	 *
	 * @return the i component (i) of the quaternion
	 */
	public double getI() { return i; }

	/**
	 * Returns the j component (j) of the quaternion. This is the imaginary
	 * component along the j-axis, often referred to as the j component.
	 *
	 * @return the j component (j) of the quaternion
	 */
	public double getJ() { return j; }

	/**
	 * Returns the k component (k) of the quaternion. This is the imaginary
	 * component along the k-axis, often referred to as the k component.
	 *
	 * @return the k component (k) of the quaternion
	 */
	public double getK() { return k; }

	/**
	 * Returns the scalar (real) part of the quaternion. This is equivalent to the
	 * real part, r. It's provided as an alias for convenience.
	 *
	 * @return the scalar part (r) of the quaternion
	 */
	public double getScalar() { return r; }

	/**
	 * Returns the i component of the quaternion, which represents the imaginary
	 * part along the i-axis. This is the same as the method {@link #getI()},
	 * provided for convenience with a more descriptive name.
	 *
	 * @return the i component (i) of the quaternion
	 */
	public double getVectorI() { return i; }

	/**
	 * Returns the j component of the quaternion, which represents the imaginary
	 * part along the j-axis. This is the same as the method {@link #getJ()},
	 * provided for convenience with a more descriptive name.
	 *
	 * @return the j component (j) of the quaternion
	 */
	public double getVectorJ() { return j; }

	/**
	 * Returns the k component of the quaternion, which represents the imaginary
	 * part along the k-axis. This is the same as the method {@link #getK()},
	 * provided for convenience with a more descriptive name.
	 *
	 * @return the k component (k) of the quaternion
	 */
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

	/**
	 * Checks if the quaternion is a zero quaternion. A zero quaternion has all
	 * components (real and imaginary) equal to zero.
	 *
	 * @return true if the quaternion is zero (all components are zero), false
	 *         otherwise
	 */
	public boolean isZero() { return testIfZero(r) && testIfZero(i) && testIfZero(j) && testIfZero(k); }

	/**
	 * Checks if the quaternion is a unit quaternion. A unit quaternion has a norm
	 * (magnitude) of 1. It is often used to represent rotations. This method
	 * compares the norm to 1 and checks if the difference is smaller than the
	 * smallest positive number that can be represented as a float (Math.ulp(1.0)),
	 * which accounts for floating-point precision errors.
	 *
	 * @return true if the quaternion is a unit quaternion (norm is 1), false
	 *         otherwise
	 */
	public boolean isUnit() { return Math.abs(norm() - 1.0) < Math.ulp(1.0); }

	private boolean testIfZero(double value) { return Math.abs(value) < Math.ulp(value); }

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
