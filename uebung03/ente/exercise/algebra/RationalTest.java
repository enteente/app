package src.java.ente.algebra;

import junit.framework.TestCase;

public class RationalTest extends TestCase {
	// Testcase constructor.
	public RationalTest(String testName) {
		super(testName);
	}

	// Test setup.
	protected void setUp() throws Exception {
		super.setUp();
	}

	// Test cleanup.
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	// Addition test.
	public void testAdd() {
		Rational a = new Rational(1, 2);
		Rational b = new Rational(2, 3);
		a.add(b);

		assertEquals(a, new Rational(7,6));
	}

	// Substraction test.
	public void testSub() {
		Rational a = new Rational(2, 3);
		Rational b = new Rational(1, 2);
		a.sub(b);

		assertEquals(a, new Rational(7,6));
	}
}

