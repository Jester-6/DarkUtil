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
<dependency>
  <groupId>com.d.util</groupId>
  <artifactId>j-util</artifactId>
  <version>1.0.0.0-SNAPSHOT</version>
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
        Complex product = a.multiply(b); // (2 + 3i) * (4 - i)
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
-   **`subtract(Complex other)`**: Returns the difference between this complex number and another.
-   **`multiply(Complex other)`**: Returns the product of this complex number and another.
-   **`divide(Complex other)`**: Returns the quotient of this complex number divided by another.
-   **`conjugate()`**: Returns the complex conjugate of this number.
-   **`magnitude()`**: Returns the magnitude (absolute value) of this complex number.
-   **`toString()`**: Provides a string representation of the complex number.