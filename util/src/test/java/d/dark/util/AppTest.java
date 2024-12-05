package d.dark.util;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class AppTest {

	private final Object[][] values = {
			{ new Complex(2, 3), new Complex(1, 6) },
			{ new Complex(4, -1), new Complex(2, 4) },
			{ new Complex(-5.1, 3.6), new Complex(2.7, -4.2) },
			{ new Complex(7.9, 4.2), new Complex(9.2, 0) },
			{ new Complex(7.9, 4.2), new Complex(0, 4.1) },
			{ new Complex(7.9, 4.2), new Complex(0, 0) },
			{ new Complex(0.967250588, -0.253823363805727), new Complex(0, 0) },
			{ new Complex(0, 0), new Complex(0, 0) },
	};

	@Test
	public void testComplex() {
		test("Addition", "+", true, Complex::add, Complex::add);
		test("Subtraction", "-", true, Complex::sub, Complex::sub);
		test("Multiplication", "*", true, Complex::mul, Complex::mul);
		test("Division", "/", true, Complex::div, Complex::div);
		test("Power", "^", true, Complex::pow, Complex::pow);
		test("Root", "√", true, null, Complex::root);
		test("Natural logarithm", "ln", (c1) -> c1.ln());
		test("Tangens", "tan", (c1) -> c1.tan());
		test("Exponential", "exp", (c1) -> c1.exp());
		test("Natural Logarithm", "ln", (c1) -> c1.ln());
		test("Conjugate", "conj", (c1) -> c1.conjugate());
		test("Magnitude", "mag", (c1) -> c1.magnitude());
		test("Argument", "arg", (c1) -> Math.toDegrees(c1.argument()) + "°");
		test("Is Zero", "isZero", (c1) -> c1.isZero());
		test("Is Unit", "isUnit", (c1) -> c1.isUnit());
		assert (true);
	}

	private void test(String testName, String operation, Function<Complex, Object> c) {
		System.out.println(testName);
		for (Object[] o : values) {
			if (o.length == 2 && o[0] instanceof Complex c1) {
				Object result = c.apply(c1);
				System.out.println("    " + operation + "(" + c1 + ") = " + result);
			}
		}
	}

	private void test(String testName, String operation, boolean testReal, BiFunction<Complex, Complex, Object> c, BiFunction<Complex, Double, Object> d) {
		System.out.println(testName);
		for (Object[] o : values) {
			if (o.length == 2 && o[0] instanceof Complex c1 && o[1] instanceof Complex c2) {
				Object result1 = c == null ? null : c.apply(c1, c2);
				Object result2 = d == null ? null : d.apply(c1, c2.getR());
				String resultStr1 = result1 == null ? "" : c1 + " " + operation + " " + c2 + " = " + result1;
				String resultStr2 = result2 == null ? "" : c1 + " " + operation + " " + c2.getR() + " = " + result2;
				if (result1 == null) {
					System.out.println(String.format("    %-80s", resultStr2));
				} else {
					System.out.println(String.format("    %-80s %-80s", resultStr1, resultStr2));
				}
			}
		}
	}
}
