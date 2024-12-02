package com.d.util.JUtil;

public class Complex {

	private double realPart;
	private double imaginaryPart;

	public Complex() { this(0, 0); }

	public Complex(double imaginary) { this(0, imaginary); }

	public Complex(double real, double imaginary) {
		this.realPart = real;
		this.imaginaryPart = imaginary;
	}

	public double getReal() { return realPart; }

	public double getImaginary() { return imaginaryPart; }

}
