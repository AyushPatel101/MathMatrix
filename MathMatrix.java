import java.util.Arrays;
//MathMatrix.java - CS314 Assignment 2

/*  Student information for assignment:

*
*  On my honor, Ayush Patel, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  UTEID: ap55837
*  email address: patayush01@utexas.edu
*  Unique section number: 50865
*  Number of slip days I am using: 0
*/

/**
 * A class that models systems of linear equations (Math Matrices) as used in
 * linear algebra.
 */
public class MathMatrix {
	private int[][] values;

	/**
	 * create a MathMatrix with cells equal to the values in mat. A "deep" copy
	 * of mat is made. Changes to mat after this constructor do not affect this
	 * Matrix and changes to this MathMatrix do not affect mat
	 * 
	 * @param mat mat !=null, mat.length > 0, mat[0].length > 0, mat is a
	 *            rectangular values
	 */
	public MathMatrix(int[][] mat) {
		if(!rectangularMatrix(mat))
			throw new IllegalArgumentException("mat is not a rectangular matrix");
		values = new int[mat.length][mat[0].length];
		int index = 0;
		// for each row in the mat, copy all those values into values[i] (deep
		// copy)
		for (int[] row : mat) {
			System.arraycopy(row, 0, values[index], 0, row.length);
			index++;
		}
	}

	/**
	 * create a MathMatrix of the specified size with all cells set to the
	 * intialValue. <br>
	 * pre: numRows > 0, numCols > 0 <br>
	 * post: create a values with numRows rows and numCols columns. All elements
	 * of this values equal initialVal. In other words after this method has
	 * been called getVal(r,c) = initialVal for all valid r and c.
	 * 
	 * @param numRows    numRows > 0
	 * @param numCols    numCols > 0
	 * @param initialVal all cells of this Matrix are set to initialVal
	 */
	public MathMatrix(int numRows, int numCols, int initialVal) {
		if (numRows <= 0 || numCols <= 0)
			throw new IllegalArgumentException(
					"number of rows and cols have to be > 0");
		values = new int[numRows][numCols];
		// fill each row in values with initialVal
		for (int[] row : values) {
			Arrays.fill(row, initialVal);
		}

	}

	/**
	 * Get the number of rows.
	 * 
	 * @return the number of rows in this MathMatrix
	 */
	public int getNumRows() {
		return values.length;
	}

	/**
	 * Get the number of columns.
	 * 
	 * @return the number of columns in this MathMatrix
	 */
	public int getNumColumns() {
		return values[0].length;
	}

	/**
	 * get the value of a cell in this MathMatrix. <br>
	 * pre: row 0 <= row < getNumRows(), col 0 <= col < getNumColumns()
	 * 
	 * @param row 0 <= row < getNumRows()
	 * @param col 0 <= col < getNumColumns()
	 * @return the value at the specified position
	 */
	public int getVal(int row, int col) {
		if ((row < 0) || row >= getNumRows() || col < 0
				|| col >= getNumColumns())
			throw new IllegalArgumentException(
					"row and/or col are not valid indexes in the matrix");
		return values[row][col];
	}

	/**
	 * implements MathMatrix addition, (this MathMatrix) + rightHandSide. <br>
	 * pre: rightHandSide.getNumRows() = getNumRows(),
	 * rightHandSide.getNumColumns() = getNumColumns() <br>
	 * post: This method does not alter the calling object or rightHandSide
	 * 
	 * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
	 *                      rightHandSide.numCols() = getNumColumns()
	 * @return a new MathMatrix that is the result of adding this Matrix to
	 *         rightHandSide. The number of rows in the returned Matrix is equal
	 *         to the number of rows in this MathMatrix. The number of columns
	 *         in the returned Matrix is equal to the number of columns in this
	 *         MathMatrix.
	 */
	public MathMatrix add(MathMatrix rightHandSide) {
		return changeMat(rightHandSide, (x,y)-> x + y);
	}
	
	/**
	 * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
	 * <br>
	 * pre: rightHandSide.getNumRows() = getNumRows(),
	 * rightHandSide.getNumColumns() = getNumColumns() <br>
	 * post: This method does not alter the calling object or rightHandSide
	 * 
	 * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
	 *                      rightHandSide.numCols() = getNumColumns()
	 * @return a new MathMatrix that is the result of subtracting rightHandSide
	 *         from this MathMatrix. The number of rows in the returned
	 *         MathMatrix is equal to the number of rows in this MathMatrix. The
	 *         number of columns in the returned MathMatrix is equal to the
	 *         number of columns in this MathMatrix.
	 */
	public MathMatrix subtract(MathMatrix rightHandSide) {
		return changeMat(rightHandSide, (x,y)-> x - y);
	}
	
	/* pre: rightHandSide rightHandSide.getNumRows() = getNumRows(),
	 *                    rightHandSide.numCols() = getNumColumns()
     * post: returns a new MathMatrix that is the result of (+/-) rightHandSide
	 *         from this MathMatrix. The number of rows in the returned
	 *         MathMatrix is equal to the number of rows in this MathMatrix. The
	 *         number of columns in the returned MathMatrix is equal to the
	 *         number of columns in this MathMatrix.
	 */
	// Adapted from Mike Scott - Topic 25 slides
	private MathMatrix changeMat(MathMatrix rightHandSide, IntBinaryOperator operator) {
		if (rightHandSide == null || rightHandSide.getNumRows() != getNumRows()
				|| rightHandSide.getNumColumns() != getNumColumns())
			throw new IllegalArgumentException(
					"number of rows and/or columns are not equal for matricies");
		int[][] newValues = new int[getNumRows()][getNumColumns()];
		for (int r = 0; r < getNumRows(); r++) {
			for (int c = 0; c < getNumColumns(); c++) {
				// subtract the values at r and c for both matrices into
				// newValues
				newValues[r][c] = operator.applyAsInt(getVal(r, c), rightHandSide.getVal(r, c));
			}
		}
		// create new MathMatrix with 2D array
		return new MathMatrix(newValues);
		
	}
	/**
	 * implements values multiplication, (this MathMatrix) * rightHandSide. <br>
	 * pre: rightHandSide.getNumRows() = getNumColumns() <br>
	 * post: This method should not alter the calling object or rightHandSide
	 * 
	 * @param rightHandSide rightHandSide.getNumRows() = getNumColumns()
	 * @return a new MathMatrix that is the result of multiplying this
	 *         MathMatrix and rightHandSide. The number of rows in the returned
	 *         MathMatrix is equal to the number of rows in this MathMatrix. The
	 *         number of columns in the returned MathMatrix is equal to the
	 *         number of columns in rightHandSide.
	 */
	public MathMatrix multiply(MathMatrix rightHandSide) {
		if (rightHandSide == null
				|| rightHandSide.getNumRows() != getNumColumns())
			throw new IllegalArgumentException(
					"number of rows for right matrix does not equal number of cols of left matrix");
		// multiplying matrix will form new matrix with same number of rows as
		// original matrix and same number of columns as second matrix
		int[][] newValues = new int[getNumRows()][rightHandSide
				.getNumColumns()];
		for (int r = 0; r < newValues.length; r++) {
			for (int c = 0; c < newValues[0].length; c++) {
				// when multiplying, index is col for original and row for
				// second matrix; will always be the same.

				// Loop condition can be <number of rows of second matrix
				// because this is the same number of cols of first matrix
				// (precondition)
				for (int index = 0; index < rightHandSide.getNumRows(); index++)
					// adding product of original[r][index] and second[index][c]
					// to newValues[r][c]]
					newValues[r][c] += getVal(r, index)
							* rightHandSide.getVal(index, c);
			}
		}
		// create new MathMatrix with 2D array
		return new MathMatrix(newValues);
	}

	/**
	 * Create and return a new Matrix that is a copy of this values, but with
	 * all values multiplied by a scale value. <br>
	 * pre: none <br>
	 * post: returns a new Matrix with all elements in this values multiplied by
	 * factor. In other words after this method has been called
	 * returned_values.getVal(r,c) = original_values.getVal(r, c) * factor for
	 * all valid r and c.
	 * 
	 * @param factor the value to multiply every cell in this Matrix by.
	 * @return a MathMatrix that is a copy of this MathMatrix, but with all
	 *         values in the result multiplied by factor.
	 */
	public MathMatrix getScaledMatrix(int factor) {
		// new 2D array so matricies aren't altered
		int[][] newValues = new int[getNumRows()][getNumColumns()];
		for (int r = 0; r < getNumRows(); r++) {
			for (int c = 0; c < getNumColumns(); c++) {
				// multiply the values at r and c for both matricies into
				// newValues
				newValues[r][c] = getVal(r, c) * factor;
			}
		}
		// create new MathMatrix with 2D array
		return new MathMatrix(newValues);
	}

	/**
	 * accessor: get a transpose of this MathMatrix. This Matrix is not changed.
	 * <br>
	 * pre: none
	 * 
	 * @return a transpose of this MathMatrix
	 */
	public MathMatrix getTranspose() {
		// new 2D array with swapped number of rows and cols compared to
		// original
		// matrix
		int[][] transpose = new int[getNumColumns()][getNumRows()];
		for (int r = 0; r < getNumRows(); r++) {
			for (int c = 0; c < getNumColumns(); c++) {
				// store value in new 2D array of swapped row and col index
				transpose[c][r] = getVal(r, c);
			}
		}
		// create new MathMatrix with 2D array
		return new MathMatrix(transpose);
	}

	/**
	 * override equals.
	 * 
	 * @return true if rightHandSide is the same size as this MathMatrix and all
	 *         values in the two MathMatrix objects are the same, false
	 *         otherwise
	 */
	public boolean equals(Object rightHandSide) {
		/*
		 * CS314 Students. The following is standard equals method code. We will
		 * learn about in the coming weeks.
		 */
		boolean result = false;
		if (rightHandSide != null
				&& this.getClass() == rightHandSide.getClass()) {
			// rightHandSide is a non null MathMatrix
			result = Arrays.deepEquals(values,
					((MathMatrix) rightHandSide).values);
		}
		return result;
	}

	/**
	 * override toString.
	 * 
	 * @return a String with all elements of this MathMatrix. Each row is on a
	 *         separate line. Spacing based on longest element in this Matrix.
	 */
	public String toString() {
		int maxLength = 0;
		// for each value in matrix, convert to a string and see if its length
		// is greater than maxLength (finds longest length in matrix)
		for (int row = 0; row < getNumRows(); row++)
			for (int col = 0; col < getNumColumns(); col++)
				maxLength = Math.max(
						Integer.toString(getVal(row, col)).length(), maxLength);
		StringBuilder output = new StringBuilder();
		// this string means integer passed in will need to take up (maxLength +
		// 1) length
		String form = "%1$" + (maxLength + 1) + "d";
		for (int r = 0; r < getNumRows(); r++) {
			// adds every time we are on new row
			output.append("|");
			for (int c = 0; c < getNumColumns(); c++)
				// formats string to add spaces based on length of getVal(r,c)
				// as a string, and adds it to output
				output.append(String.format(form, getVal(r, c)));
			// adds every time we are done with row
			output.append("|" + '\n');
		}
		return output.toString();
	}

	/**
	 * Return true if this MathMatrix is upper triangular. To be upper
	 * triangular all elements below the main diagonal must be 0.<br>
	 * pre: this is a square values. getNumRows() == getNumColumns()
	 * 
	 * @return <tt>true</tt> if this MathMatrix is upper triangular,
	 *         <tt>false</tt> otherwise.
	 */
	public boolean isUpperTriangular() {
		if (getNumRows() != getNumColumns())
			throw new IllegalArgumentException(
					"matrix does not have same amount of rows as columns");
		for (int r = 0; r < getNumRows(); r++) {
			//only traversing lower triangle looking for values that are != 0
			for (int c = 0; c < r; c++) {
				// if value isn't a 0, then it cannot be upper triangle
				if (getVal(r, c) != 0)
					return false;
			}
		}
		return true;
	}

	// method to ensure mat is rectangular
	// pre: mat != null, mat has at least one row
	// return true if all rows in mat have the same
	// number of columns false otherwise.
	public static boolean rectangularMatrix(int[][] mat) {
		if (mat == null || mat.length == 0) {
			throw new IllegalArgumentException(
					"argument mat may not be null and must "
							+ " have at least one row. mat = "
							+ Arrays.toString(mat));
		}
		boolean isRectangular = true;
		int row = 1;
		final int COLUMNS = mat[0].length;
		while (isRectangular && row < mat.length) {
			isRectangular = (mat[row].length == COLUMNS);
			row++;
		}
		return isRectangular;
	}

}
