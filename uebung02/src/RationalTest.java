import ente.algebra.Rational;
import junit.framework.TestCase;

public class RationalTest extends TestCase {
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
		Rational c = a.add(b);

		assertEquals(c, new Rational(7,6));
	}

	// Substraction test.
	public void testSub() {
		Rational a = new Rational(2, 3);
		Rational b = new Rational(1, 2);
		Rational c = a.sub(b);

		assertEquals(c, new Rational(1,6));
	}

    public void testWillAlwaysFail() {
        fail("This test should fail!");
    }
}

