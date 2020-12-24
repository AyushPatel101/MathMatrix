import java.util.Random;

/*  Student information for assignment:
 *
 *  UTEID: ap55837
 *  email address: patayush01@utexas.edu
 *  Grader name: Tony
 *  Number of slip days I am using: 0
 */

/* CS314 Students. Put your experiment results and
 * answers to questions here.
 */

// Experiment 1: For N = 200, 1000 repetitions took: 2.481861563 seconds
// Experiment 1: For N = 400, 1000 repetitions took: 8.815260355 seconds
// Experiment 1: For N = 800, 1000 repetitions took: 35.920671196 seconds
// Experiment 2: For N = 200, 100 repetitions took: 1.582950107 seconds
// Experiment 2: For N = 400, 100 repetitions took: 13.523855958 seconds
// Experiment 2: For N = 800, 100 repetitions took: 130.491990223 seconds

// Questions 
// 1) It would approximately take 143 seconds if I doubled N again for the add method (experiment 1)
// 2) The BigO would be O(N^2). My timing data does support this, as for double N, the runtime approximately
// quadruples.
// 3) It would approximately take 1040 seconds if I doubled N again for the multiply method (experiment 2)
// 4) The BigO would be O(N^3). My timing data does support this, as for double N, the runtime increases by 
// approximately x8. 
// 5) I was able to create a matrix of 14,500 by 14,500 before my program ran out of memory. This would
// have created 14,500 * 14,500 = 210,250,000 ints. Since each int in java takes up 4 bytes, my program
// is allocated 210,250,000 * 4 = 841,000,000 bytes, or 802MB of memory
/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

	/**
	 * main method that runs simple test on the MathMatrix class
	 *
	 * @param args not used
	 */
	public static void main(String[] args) {
		int[][] data1, data2, e1;

		// Experiment 1
		experimentOne(200);
		// Experiment 2
		experimentTwo(200);
		// Question 5
		questionFive();

		// test 1, getNumRows
		MathMatrix mat1 = new MathMatrix(new int[10][2]);
		if (mat1.getNumRows() == 10) {
			System.out.println("passed test 1, getNumRows method.");
		} else {
			System.out.println("failed test 1, getNumRows method.");
		}
		// test 2, getNumRows
		e1 = new int[][] { { 1, 2, 3, 4, 5, }, { 1, 2, 3, 4, 5 },
				{ 2, 3, 1, 5, 6 } };
		mat1 = new MathMatrix(e1);
		if (mat1.getNumRows() == e1.length) {
			System.out.println("passed test 2, getNumRows method.");
		} else {
			System.out.println("failed test 2, getNumRows method.");
		}

		// test 3, getNumColumns
		e1 = new int[10][2];
		mat1 = new MathMatrix(e1);
		if (mat1.getNumColumns() == e1[0].length) {
			System.out.println("passed test 3, getNumColumns method.");
		} else {
			System.out.println("failed test 3, getNumColumns method.");
		}

		// test 4, getNumColumns
		e1 = new int[][] { { 1, 2, 3, 4, 5, }, { 1, 2, 3, 4, 5 },
				{ 2, 3, 1, 5, 6 }, { 1, 4, 5, 6, 7 } };
		mat1 = new MathMatrix(e1);
		if (mat1.getNumColumns() == e1[0].length) {
			System.out.println("passed test 4, getNumColumns method.");
		} else {
			System.out.println("failed test 4, getNumColumns method.");
		}

		// test 5, getVal
		mat1 = new MathMatrix(1, 4, 19);
		if (mat1.getVal(0, 3) == 19) {
			System.out.println("passed test 5, getVal method.");
		} else {
			System.out.println("failed test 3, getVal method.");
		}

		// test 6, getVal
		e1 = new int[][] { { 1, 2, 3, 4, 5, }, { 1, 2, 3, 4, 5 },
				{ 2, 3, 1, 5, 6 }, { 1, 4, 5, 6, 7 } };
		mat1 = new MathMatrix(e1);
		if (mat1.getVal(2, 4) == 6) {
			System.out.println("passed test 6, getVal method.");
		} else {
			System.out.println("failed test 6, getVal method.");
		}

		// test 7, add
		data1 = new int[][] { { 1, 2, 3, 4, 5 }, { 0, 0, 0, 0, 0 } };
		data2 = new int[][] { { 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 } };
		e1 = new int[][] { { 2, 4, 6, 8, 10 }, { 1, 2, 3, 4, 5 } };
		mat1 = new MathMatrix(data1);
		MathMatrix mat2 = new MathMatrix(data2);
		MathMatrix mat3 = mat1.add(mat2);

		printTestResult(get2DArray(mat3), e1, 7,
				"add method. Testing mat3 correct result.");

		// test 8, add
		data1 = new int[][] { { 10, 9, 8, 7, 6 }, { 7, 7, 7, 7, 7 } };
		data2 = new int[][] { { 5, 4, 3, 2, 1 }, { 6, 7, 8, 9, 10 } };
		e1 = new int[][] { { 15, 13, 11, 9, 7 }, { 13, 14, 15, 16, 17 } };
		mat1 = new MathMatrix(data1);
		mat2 = new MathMatrix(data2);
		mat3 = mat1.add(mat2);

		printTestResult(get2DArray(mat3), e1, 8,
				"add method. Testing mat3 correct result.");

		// test 9, subtract
		data1 = new int[][] { { 1, 2, 3, 4, 5 }, { 0, 0, 0, 0, 0 } };
		data2 = new int[][] { { 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 5 } };
		e1 = new int[][] { { 0, 0, 0, 0, 0 }, { -1, -2, -3, -4, -5 } };
		mat1 = new MathMatrix(data1);
		mat2 = new MathMatrix(data2);
		mat3 = mat1.subtract(mat2);

		printTestResult(get2DArray(mat3), e1, 9,
				"subtract method. Testing mat3 correct result.");

		// test 10, subtract
		data1 = new int[][] { { 10, 9, 8, 7, 6 }, { 7, 7, 7, 7, 7 } };
		data2 = new int[][] { { 5, 4, 3, 2, 1 }, { 6, 7, 8, 9, 10 } };
		e1 = new int[][] { { 5, 5, 5, 5, 5 }, { 1, 0, -1, -2, -3 } };
		mat1 = new MathMatrix(data1);
		mat2 = new MathMatrix(data2);
		mat3 = mat1.subtract(mat2);

		printTestResult(get2DArray(mat3), e1, 10,
				"subtract method. Testing mat3 correct result.");

		// test 11, multiply
		data1 = new int[][] { { 1, 2, 3, 4, 5 }, { 0, 0, 0, 0, 0 } };
		data2 = new int[][] { { 1, 2 }, { 3, 4 }, { 5, 1 }, { 2, 3 },
				{ 4, 5 } };
		e1 = new int[][] { { 50, 50 }, { 0, 0 } };
		mat1 = new MathMatrix(data1);
		mat2 = new MathMatrix(data2);
		mat3 = mat1.multiply(mat2);

		printTestResult(get2DArray(mat3), e1, 11,
				"multiply method. Testing mat3 correct result.");

		// test 12, multiply
		data1 = new int[][] { { 10, 9, 8, 7, 6 }, { -1, -2, -3, -4, -5 } };
		data2 = new int[][] { { 5, 4 }, { 3, 2 }, { 1, 6 }, { 7, 8 },
				{ 9, 10 } };
		e1 = new int[][] { { 188, 222 }, { -87, -108 } };
		mat1 = new MathMatrix(data1);
		mat2 = new MathMatrix(data2);
		mat3 = mat1.multiply(mat2);

		printTestResult(get2DArray(mat3), e1, 12,
				"multiply method. Testing mat3 correct result.");

		// test 13, getScaledMatrix
		data1 = new int[][] { { 10, 9, 8, 7, 6 }, { 7, 7, 7, 7, 7 } };
		e1 = new int[][] { { -10, -9, -8, -7, -6 }, { -7, -7, -7, -7, -7 } };
		mat1 = new MathMatrix(data1);
		mat2 = new MathMatrix(data2);
		mat3 = mat1.getScaledMatrix(-1);

		printTestResult(get2DArray(mat3), e1, 13,
				"getScaledMatrix method. Testing mat3 correct result.");

		// test 14, getScaledMatrix
		data1 = new int[][] { { 10, 9, 8, 7, 6 }, { 5, 4, 3, 2, 1 } };
		e1 = new int[][] { { 20, 18, 16, 14, 12 }, { 10, 8, 6, 4, 2 } };
		mat1 = new MathMatrix(data1);
		mat2 = new MathMatrix(data2);
		mat3 = mat1.getScaledMatrix(2);

		printTestResult(get2DArray(mat3), e1, 14,
				"getScaledMatrix method. Testing mat3 correct result.");

		// test 15, getTranspose
		data1 = new int[][] { { 10, 9, 8 }, { 7, 6, 5 } };
		e1 = new int[][] { { 10, 7 }, { 9, 6 }, { 8, 5 } };
		mat1 = new MathMatrix(data1);
		mat3 = mat1.getTranspose();

		printTestResult(get2DArray(mat3), e1, 15,
				"getTranspose method. Testing mat3 correct result.");

		// test 16, transpose
		data1 = new int[][] { { 1, 2 } };
		e1 = new int[][] { { 1 }, { 2 } };
		mat1 = new MathMatrix(data1);
		mat3 = mat1.getTranspose();

		printTestResult(get2DArray(mat3), e1, 16,
				"getTranspose method. Testing mat3 correct result.");

		// test 17, equals
		data1 = new int[][] { { 10, 9, 8, 7, 6 }, { 5, 4, 3, 2, 1 } };
		data2 = new int[][] { { 10, 9, 8, 7, 6 }, { 5, 4, 3, 2, 1 } };
		mat1 = new MathMatrix(data1);
		mat2 = new MathMatrix(data2);
		if (mat1.equals(mat2))

		{
			System.out.println("passed test 17, equals method.");
		} else {
			System.out.println("failed test 17, equals method.");
		}

		// test 18, equals
		data1 = new int[][] { { 10, 9, 8, 7, 6 }, { 5, 4, 3, 2, 1 } };
		data2 = new int[][] { { 10, 9, 8, 7, 6 } };
		mat1 = new MathMatrix(data1);
		mat2 = new MathMatrix(data2);
		if (!mat1.equals(mat2)) {
			System.out.println("passed test 18, equals method.");
		} else {
			System.out.println("failed test 18, equals method.");
		}

		// test 19, toString
		String data1String = "| 10  9  8  7  6|\n";

		data1 = new int[][] { { 10, 9, 8, 7, 6 } };
		mat1 = new MathMatrix(data1);
		if (mat1.toString().equals(data1String)) {
			System.out.println("passed test 19, toString method.");
		} else {
			System.out.println("failed test 19, toString method.");
		}
		// test 20, toString
		data1String = "|  10   9   8   7   6|" + '\n' + "|   5   4   3   2 100|"
				+ '\n';
		data1 = new int[][] { { 10, 9, 8, 7, 6 }, { 5, 4, 3, 2, 100 } };
		mat1 = new MathMatrix(data1);
		if (mat1.toString().equals(data1String)) {
			System.out.println("passed test 20, toString method.");
		} else {
			System.out.println("failed test 20, toString method.");
		}

		// test 21, isUpperTriangle
		data1 = new int[1][1];
		mat1 = new MathMatrix(data1);
		if (mat1.isUpperTriangular()) {
			System.out.println("passed test 21, isUpperTriangle method.");
		} else {
			System.out.println("failed test 21, isUpperTriangle method.");
		}

		// test 22, isUpperTriangle
		data1 = new int[][] { { 10, 10, 10 }, { 0, -6, -190483295 },
				{ 0, 0, 10 } };
		mat1 = new MathMatrix(data1);
		if (mat1.isUpperTriangular()) {
			System.out.println("passed test 22, isUpperTriangle method.");
		} else {
			System.out.println("failed test 22, isUpperTriangle method.");
		}

	}

	private static void printTestResult(int[][] data1, int[][] data2,
			int testNum, String testingWhat) {
		System.out.print("Test number " + testNum + " tests the " + testingWhat
				+ ". The test ");
		String result = equals(data1, data2) ? "passed" : "failed";
		System.out.println(result);
	}

	// pre: m != null, m is at least 1 by 1 in size
	// return a 2d array of ints the same size as m and with
	// the same elements
	private static int[][] get2DArray(MathMatrix m) {
		// check precondition
		if ((m == null) || (m.getNumRows() == 0) || (m.getNumColumns() == 0)) {
			throw new IllegalArgumentException(
					"Violation of precondition: get2DArray");
		}

		int[][] result = new int[m.getNumRows()][m.getNumColumns()];
		for (int r = 0; r < result.length; r++) {
			for (int c = 0; c < result[0].length; c++) {
				result[r][c] = m.getVal(r, c);
			}
		}
		return result;
	}

	// pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1
	// matrices
	// data1 and data2 are rectangular matrices
	// post: return true if data1 and data2 are the same size and all elements
	// are
	// the same
	private static boolean equals(int[][] data1, int[][] data2) {
		// check precondition
		if ((data1 == null) || (data1.length == 0) || (data1[0].length == 0)
				|| !rectangularMatrix(data1) || (data2 == null)
				|| (data2.length == 0) || (data2[0].length == 0)
				|| !rectangularMatrix(data2)) {
			throw new IllegalArgumentException(
					"Violation of precondition: equals check on 2d arrays of ints");
		}
		boolean result = (data1.length == data2.length)
				&& (data1[0].length == data2[0].length);
		int row = 0;
		while (result && row < data1.length) {
			int col = 0;
			while (result && col < data1[0].length) {
				result = (data1[row][col] == data2[row][col]);
				col++;
			}
			row++;
		}

		return result;
	}

	// method to ensure mat is rectangular
	// pre: mat != null, mat is at least 1 by 1
	private static boolean rectangularMatrix(int[][] mat) {
		if (mat == null || mat.length == 0 || mat[0].length == 0) {
			throw new IllegalArgumentException("Violation of precondition: "
					+ " Parameter mat may not be null"
					+ " and must be at least 1 by 1");
		}
		return MathMatrix.rectangularMatrix(mat);
	}

	private static void experimentOne(int N) {
		Stopwatch s = new Stopwatch();
		for (int run = 1; run <= 3; run++) {
			s.start();
			for (int i = 0; i < 1000; i++) {
				int[][] valuesOne = new int[N][N];
				int[][] valuesTwo = new int[N][N];
				for (int r = 0; r < valuesOne.length; r++)
					for (int c = 0; c < valuesOne[0].length; c++) {
						valuesOne[r][c] = (int) (Math.random() * 1000);
						valuesTwo[r][c] = (int) (Math.random() * 1000);
					}
				MathMatrix tester1 = new MathMatrix(valuesOne);
				MathMatrix tester2 = new MathMatrix(valuesTwo);
				tester1.add(tester2);
			}

			s.stop();
			System.out.println("Experiment 1: For N = " + N
					+ ", 1000 repetitions took: " + s.time() + " seconds");
			N *= 2;
		}
	}

	private static void experimentTwo(int N) {
		Stopwatch s = new Stopwatch();
		for (int run = 1; run <= 3; run++) {
			s.start();
			for (int i = 0; i < 100; i++) {
				int[][] valuesOne = new int[N][N];
				int[][] valuesTwo = new int[N][N];
				for (int r = 0; r < valuesOne.length; r++)
					for (int c = 0; c < valuesOne[0].length; c++) {
						valuesOne[r][c] = (int) (Math.random() * 1000);
						valuesTwo[r][c] = (int) (Math.random() * 1000);
					}
				MathMatrix tester1 = new MathMatrix(valuesOne);
				MathMatrix tester2 = new MathMatrix(valuesTwo);
				tester1.multiply(tester2);
			}
			s.stop();
			System.out.println("Experiment 2: For N = " + N
					+ ", 100 repetitions took: " + s.time() + " seconds");
			N *= 2;
		}
	}

	private static void questionFive() {
		Stopwatch s = new Stopwatch();
		MathMatrix tester2;
		int N = 4000;
		while (true)

		{
			s.start();
			System.out.println("N= " + N);
			tester2 = new MathMatrix(new int[N][N]);
			s.stop();
			N += 100;
		}

	}
}
