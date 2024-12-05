package d.dark.util;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {

	private static final double EPSILON = 0.00001;

	public static void main(String[] args) {
		Matrix m1 = debugPrint(new Matrix(new double[][] { { 1, 8, 1, 4 }, null, { 5, 9, 4.22, 5 } }));
		Matrix m2 = debugPrint(new Matrix());
		Matrix m3 = debugPrint(new Matrix(5, 2));
		Matrix m4 = debugPrint(new Matrix(new double[][] { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 1, 0 } }));
		Matrix m5 = debugPrint(new Matrix(new double[][] { { 1, 8, 1, 4 }, { 5.1, 4.77, 1, 0 }, { 5, 9, 4.22, 5 }, { 0, 0.17, 4, 2 } }));

		System.out.println(m5.subMatrix(2, 2, 2, 2));
	}

	private static Matrix debugPrint(Matrix m) {
		System.out.println(m);
		System.out.println("       isZero: " + m.isZero() + ", isIdentity: " + m.isIdentity() + "\n");
		return m;
	}

	private final int cols;
	private final int rows;

	private final double[] matrix;

	/**
	 * Constructor that initializes the matrix to a 4x4 identity matrix.
	 */
	public Matrix() { this(4, 4); }

	/**
	 * Constructor that initializes the matrix by copying values from another
	 * matrix.
	 *
	 * @param m the matrix to copy
	 * @throws IllegalArgumentException if the provided matrix is null
	 */
	public Matrix(Matrix m) {
		if (m == null) { throw new IllegalArgumentException("Cannot create a copy of a null matrix"); }
		this.cols = m.cols;
		this.rows = m.rows;
		int length = this.cols * this.rows;
		this.matrix = new double[length];
		for (int i = 0; i < length; i++) { this.matrix[i] = m.matrix[i]; }
	}

	/**
	 * Constructor that initializes the matrix with the specified number of columns
	 * and rows.
	 *
	 * @param cols the number of columns in the matrix
	 * @param rows the number of rows in the matrix
	 * @throws IllegalArgumentException if the number of columns or rows is less
	 *                                  than or equal to zero
	 */
	public Matrix(int cols, int rows) {
		if (cols <= 0) { throw new IllegalArgumentException("Cannot create Matrix with 0 or negative columns: " + cols); }
		if (rows <= 0) { throw new IllegalArgumentException("Cannot create Matrix with 0 or negative rows: " + rows); }

		this.cols = cols;
		this.rows = rows;
		this.matrix = new double[cols * rows];
		if (isSquare()) { for (int i = 0; i < matrix.length; i++) { matrix[i] = i % cols == i / cols ? 1.0 : 0.0; } }
	}

	/**
	 * Constructor that initializes the matrix from a 2D array of values.
	 *
	 * @param values the 2D array of values to initialize the matrix
	 * @throws IllegalArgumentException if the input array is null, empty, or if
	 *                                  rows have different lengths
	 */
	public Matrix(double[][] values) {
		if (values == null) { throw new IllegalArgumentException("Cannot create Matrix from null array"); }
		if (values.length == 0) { throw new IllegalArgumentException("Cannot create Matrix from empty array"); }
		this.rows = values.length;
		int cols = -1;
		for (double[] value : values) {
			if (value != null) {
				if (cols == -1) {
					cols = value.length;
				} else if (cols != value.length) { throw new IllegalArgumentException("Cannot create Matrix: all rows must have the same length."); }
			}
		}
		if (cols == -1) { throw new IllegalArgumentException("Cannot create Matrix from empty array (At least 1 row must be defined)"); }
		if (cols == 0) { throw new IllegalArgumentException("Cannot create Matrix from empty array (Rows have length = 0)"); }
		this.cols = cols;

		this.matrix = new double[this.cols * this.rows];

		int row = 0;
		for (double[] value : values) {
			int col = 0;
			if (value != null) { for (double v : value) { this.matrix[col++ + row * this.cols] = v; } }
			row++;
		}
	}

	/**
	 * Sets the value at the specified column and row in the matrix. This method
	 * modifies the matrix and returns the updated matrix for method chaining.
	 *
	 * @param col   the column index
	 * @param row   the row index
	 * @param value the value to set at the specified column and row
	 * @return the updated matrix (this instance) for method chaining
	 * @throws IndexOutOfBoundsException if the specified column or row is out of
	 *                                   bounds for the matrix
	 */
	public Matrix set(int col, int row, double value) {
		if (containsCell(col, row)) {
			matrix[col + row * cols] = value;
		} else {
			throw new IndexOutOfBoundsException("Cell (" + col + ", " + row + ") is out of bounds for matrix [" + cols + "×" + rows + "]");
		}
		return this;
	}

	/**
	 * Sets the value of the matrix at the specified index. The index is treated as
	 * a one-dimensional array of elements. This method modifies the matrix and
	 * returns the updated matrix for method chaining.
	 *
	 * @param index the index of the element to be set
	 * @param value the value to set at the specified index
	 * @return the updated matrix (this instance) for method chaining
	 * @throws IndexOutOfBoundsException if the index is out of bounds for the
	 *                                   matrix
	 */
	public Matrix set(int index, double value) {
		if (index >= 0 && index < matrix.length) {
			matrix[index] = value;
		} else {
			String pos = (index % cols) + ", " + (index / cols);
			throw new IndexOutOfBoundsException("Cell (" + pos + ") is out of bounds for matrix [" + cols + "×" + rows + "]");
		}
		return this;
	}

	/**
	 * Returns the value of the matrix at the specified index. The index is treated
	 * as a one-dimensional array of elements.
	 *
	 * @param index the index of the element to retrieve
	 * @return the value at the specified index in the matrix
	 * @throws IndexOutOfBoundsException if the index is out of bounds for the
	 *                                   matrix
	 */
	public double get(int index) {
		if (index >= 0 && index < matrix.length) {
			return matrix[index];
		} else {
			String pos = (index % cols) + ", " + (index / cols);
			throw new IndexOutOfBoundsException("Cell (" + pos + ") is out of bounds for matrix [" + cols + "×" + rows + "]");
		}
	}

	/**
	 * Gets the value at the specified column and row in the matrix.
	 *
	 * @param col the column index
	 * @param row the row index
	 * @return the value at the specified position
	 * @throws IndexOutOfBoundsException if the specified column or row is out of
	 *                                   bounds
	 */
	public double get(int col, int row) {
		if (containsCell(col, row)) {
			return matrix[col + row * cols];
		} else {
			throw new IndexOutOfBoundsException("Cell (" + col + ", " + row + ") is out of bounds for matrix [" + cols + "×" + rows + "]");
		}
	}

	/**
	 * Gets the entire row at the specified row index.
	 *
	 * @param row the row index
	 * @return an array representing the row
	 * @throws IndexOutOfBoundsException if the row index is out of bounds
	 */
	public double[] getRow(int row) {
		if (row >= 0 && row < rows) {
			return Arrays.copyOfRange(matrix, row * cols, row * cols + cols);
		} else {
			throw new IndexOutOfBoundsException("Row " + row + " is out of bounds for matrix [" + cols + "×" + rows + "]");
		}
	}

	/**
	 * Returns the number of columns in the matrix.
	 *
	 * @return the number of columns in the matrix
	 */
	public int getColumns() { return cols; }

	/**
	 * Returns the number of rows in the matrix.
	 *
	 * @return the number of rows in the matrix
	 */
	public int getRows() { return rows; }

	/**
	 * Gets the entire column at the specified column index.
	 *
	 * @param col the column index
	 * @return an array representing the column
	 * @throws IndexOutOfBoundsException if the column index is out of bounds
	 */
	public double[] getColumn(int col) {
		if (col >= 0 && col < cols) {
			double[] values = new double[rows];
			for (int row = 0; row < rows; row++) { values[row] = matrix[row * cols + col]; }
			return values;
		} else {
			throw new IndexOutOfBoundsException("Column " + col + " is out of bounds for matrix [" + cols + "×" + rows + "]");
		}
	}

	/**
	 * Returns a submatrix of the matrix, starting at the specified column and row.
	 *
	 * @param fromColumn the starting column index of the submatrix
	 * @param fromRow    the starting row index of the submatrix
	 * @param cols       the number of columns in the submatrix
	 * @param rows       the number of rows in the submatrix
	 * @return a new Matrix representing the submatrix
	 * @throws IndexOutOfBoundsException if the specified submatrix is out of bounds
	 */
	public Matrix subMatrix(int fromColumn, int fromRow, int cols, int rows) {
		if (fromColumn < 0 || fromRow <= 0 || fromColumn + cols - 1 >= this.cols || fromRow + rows - 1 >= this.rows) {
			String subDef = "starting at (" + fromColumn + ", " + fromRow + ") with size [" + cols + "×" + rows + "]";
			throw new IndexOutOfBoundsException("subMatrix " + subDef + " is out of bounds for matrix [" + this.cols + "×" + this.rows + "]");
		}
		Matrix subMatrix = new Matrix(cols, rows);
		for (int c = 0; c < cols; c++) {
			for (int r = 0; r < rows; r++) { subMatrix.matrix[c + r * cols] = matrix[c + fromColumn + (r + fromRow) * this.cols]; }
		}
		return subMatrix;
	}

	/**
	 * Fills the matrix with the specified value.
	 *
	 * @param value the value to fill the matrix with
	 */
	public void fill(double value) { Arrays.fill(matrix, value); }

	/**
	 * Zeros out all values in the matrix.
	 */
	public void zero() { Arrays.fill(matrix, 0.0); }

	/**
	 * Sets the matrix to the identity matrix (only for square matrices).
	 *
	 * @throws IllegalStateException if the matrix is not square
	 */
	public void identity() {
		if (!isSquare()) { throw new IllegalStateException("Cannot set this matrix to identity. Matrix is not square [" + cols + "×" + rows + "]"); }
		for (int i = 0; i < matrix.length; i++) { matrix[i] = i % cols == i / cols ? 1.0 : 0.0; }
	}

	/**
	 * Checks if the specified column and row are valid indices within the matrix.
	 *
	 * @param col the column index
	 * @param row the row index
	 * @return true if the specified cell exists in the matrix, false otherwise
	 */
	public boolean containsCell(int col, int row) { return col >= 0 && row >= 0 && col < cols && row < rows; }

	/**
	 * Checks if the matrix is square (i.e., has equal number of rows and columns).
	 *
	 * @return true if the matrix is square, false otherwise
	 */
	public boolean isSquare() { return this.cols == this.rows; }

	/**
	 * Checks if the matrix is approximately zero (all elements are close to zero,
	 * within the specified epsilon).
	 *
	 * @param epsilon the threshold for considering a value to be zero
	 * @return true if the matrix is approximately zero, false otherwise
	 */
	public boolean isZero(double epsilon) {
		for (double value : matrix) { if (Math.abs(value) >= epsilon) { return false; } }
		return true;
	}

	/**
	 * Checks if the matrix is exactly zero (all elements are exactly zero).
	 *
	 * @return true if the matrix is exactly zero, false otherwise
	 */
	public boolean isZero() { return isZero(EPSILON); }

	/**
	 * Checks if the matrix is approximately an identity matrix (within the
	 * specified epsilon).
	 *
	 * @param epsilon the threshold for considering a value to be close to 1 or 0
	 * @return true if the matrix is approximately an identity matrix, false
	 *         otherwise
	 */
	public boolean isIdentity(double epsilon) {
		if (!isSquare()) { return false; }
		int index = 0;
		for (double value : matrix) {
			boolean isDiagonal = index % cols == index++ / cols;
			if (isDiagonal && Math.abs(value - 1.0) >= epsilon) {
				return false;
			} else if (!isDiagonal && Math.abs(value) >= epsilon) { return false; }
		}
		return true;
	}

	/**
	 * Checks if the matrix is exactly an identity matrix.
	 *
	 * @return true if the matrix is exactly an identity matrix, false otherwise
	 */
	public boolean isIdentity() { return isIdentity(EPSILON); }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(matrix);
		result = prime * result + Objects.hash(cols, rows);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if ((obj == null) || (getClass() != obj.getClass())) { return false; }
		Matrix other = (Matrix) obj;
		return cols == other.cols && rows == other.rows && Arrays.equals(matrix, other.matrix);
	}

	@Override
	public String toString() {
		String[][] values = new String[cols][rows];
		int[] longest = new int[cols];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				String value = "" + get(col, row);
				if (value.length() > longest[col]) { longest[col] = value.length(); }
				values[col][row] = value;
			}
		}
		String[] format = new String[cols];
		for (int i = 0; i < cols; i++) { format[i] = "%" + longest[i] + "s"; }
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < rows; row++) {
			sb.append(row == 0 ? "Matrix [" : "\n       [");
			for (int col = 0; col < cols; col++) {
				if (col > 0) { sb.append(", "); }
				sb.append(String.format(format[col], values[col][row]));
			}
			sb.append("]");
		}
		return sb.toString();
	}

}
