<h1>Utility Math Library</h1>
<p>This library provides utility classes for mathematical computations. It is designed to be modular and extensible, offering a growing suite of tools to handle various mathematical operations efficiently.</p>
<h2>Current Features</h2>
<ul>
<li>Complex Class
<p>The Complex class provides tools to work with complex numbers. It supports operations like addition, subtraction, multiplication, division, and more.</p>
</li>
</ul>
<h2>How to Use</h2>
<ul>
<li>Installation
<p>Include the library in your project as a dependency. For Maven projects, add the following to your pom.xml:</p>
<code>&nbsp; &nbsp; &lt;dependency&gt;</code><br /><code>&nbsp; &nbsp; &nbsp; &nbsp; &lt;groupId&gt;com.d.util&lt;/groupId&gt;</code><br /><code>&nbsp; &nbsp; &nbsp; &nbsp; &lt;artifactId&gt;JUtil&lt;/artifactId&gt;</code><br /><code>&nbsp; &nbsp; &nbsp; &nbsp; &lt;version&gt;1.0.0&lt;/version&gt;</code><br /><code>&nbsp; &nbsp; &lt;/dependency&gt;</code></li>
</ul>
<p>Using the Complex Class Example</p>
<p style="padding-left: 40px;"><code>import com.example.math.Complex;</code></p>
<p style="padding-left: 40px;"><code><br />public class Main {<br />&nbsp; &nbsp; public static void main(String[] args) {<br /><br />&nbsp; &nbsp; &nbsp; &nbsp; // Create two complex numbers <br />&nbsp; &nbsp; &nbsp; &nbsp; Complex a = new Complex(2, 3); // 2 + 3i<br />&nbsp; &nbsp; &nbsp; &nbsp; Complex b = new Complex(4, -1); // 4 - i<br /><br />&nbsp; &nbsp; &nbsp; &nbsp; // Perform operations<br />&nbsp; &nbsp; &nbsp; &nbsp; Complex sum = a.add(b); // (2 + 3i) + (4 - i)<br />&nbsp; &nbsp; &nbsp; &nbsp; Complex product = a.multiply(b); // (2 + 3i) * (4 - i)<br />&nbsp; &nbsp; &nbsp; &nbsp; Complex conjugate = a.conjugate(); // Conjugate of (2 + 3i)<br /><br />&nbsp; &nbsp; &nbsp; &nbsp; // Display results<br />&nbsp; &nbsp; &nbsp; &nbsp; System.out.println("Sum: " + sum);<br />&nbsp; &nbsp; &nbsp; &nbsp; System.out.println("Product: " + product);<br />&nbsp; &nbsp; &nbsp; &nbsp; System.out.println("Conjugate: " + conjugate);<br />&nbsp; &nbsp; }<br />}</code></p>
<p><code> 
  </code><br />Key Methods in Complex</p>
<ul>
<li>add(Complex other): Returns the sum of this complex number and another.</li>
<li>subtract(Complex other): Returns the difference between this complex number and another.</li>
<li>multiply(Complex other): Returns the product of this complex number and another.</li>
<li>divide(Complex other): Returns the quotient of this complex number divided by another.</li>
<li>conjugate(): Returns the complex conjugate of this number.</li>
<li>magnitude(): Returns the magnitude (absolute value) of this complex number.</li>
<li>toString(): Provides a string representation of the complex number.</li>
</ul>