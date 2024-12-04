# /////////////////////////////////
# <ul>WORK IN PROGRESS</ul>
# /////////////////////////////////

<br><br>

# Utility Math Library

This library provides utility classes for mathematical computations. It is designed to be modular and extensible, offering a growing suite of tools to handle various mathematical operations efficiently.

## Current Features

### `Complex` Class

- The `Complex` class provides tools to work with complex numbers. It supports operations like addition, 	   subtraction, multiplication, division, and more.

----------

## How to Use

### Installation

Include the library in your project as a dependency.

For Maven projects, add the following to your `pom.xml`:

```
<repositories>
  <repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/Jester-6/dark/util</url>
  </repository>
</repositories>

<dependency>
  <groupId>d.dark</groupId>
  <artifactId>util</artifactId>
  <version>1.0.0.1-SNAPSHOT</version>
</dependency>
```

----------

### Using the `Complex` Class

#### Example
```
import com.example.math.Complex;

public class Main {
    public static void main(String[] args) {
        // Create two complex numbers
        Complex a = new Complex(2, 3); // 2 + 3i
        Complex b = new Complex(4, -1); // 4 - i

        // Perform operations
        Complex sum = a.add(b); // (2 + 3i) + (4 - i)
        Complex product = a.mul(b); // (2 + 3i) * (4 - i)
        Complex conjugate = a.conjugate(); // Conjugate of (2 + 3i)

        // Display results
        System.out.println("Sum: " + sum);
        System.out.println("Product: " + product);
        System.out.println("Conjugate: " + conjugate);
    }
}
```

#### Key Methods in `Complex`

-   **`add(Complex other)`**: Returns the sum of this complex number and another.
-   **`sub(Complex other)`**: Returns the difference between this complex number and another.
-   **`mul(Complex other)`**: Returns the product of this complex number and another.
-   **`div(Complex other)`**: Returns the quotient of this complex number divided by another.
-   **`pow(double power)`**: Returns this complex number raised to the given power.
-   **`root(double power)`**: Returns the root of this number.
-   **`conjugate()`**: Returns the complex conjugate of this number.
-   **`magnitude()`**: Returns the magnitude (absolute value) of this complex number.
-   **`argument()`**: Returns the argument (angle to real axis) of this complex number.
-   **`toString()`**: Provides a string representation of the complex number.
