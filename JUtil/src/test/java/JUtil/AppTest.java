package com.d.util.JUtil;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AppTest {
	@ParameterizedTest
	@CsvSource({
			"13.4, 22.7",
			"2.9, -7.4"
	})
	public void shouldAnswerWithTrue(double r, double i) {
		System.out.println(new Complex(r, i).root(2.72));
		assertTrue(true);
	}
}
