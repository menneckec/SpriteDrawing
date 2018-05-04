package uwstout.cs145.projects.project1.drawing;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests EllipseSprite class
 * 
 * @author Carolyn
 * @version 4.4.2018
 */
public class EllipseSpriteTest {

	/**
	 * Tests the contains method
	 */
	@Test
	public void testContains() {
		// 100 x 100 ellipse at (0, 0)
		EllipseSprite r1 = new EllipseSprite("test1", 0, 0, 100, 100);
		
		assertTrue(r1.contains(50, 50));
		assertTrue(r1.contains(50, 25));
		assertFalse(r1.contains(150, 50));
		
		// 100 x 50 ellipse at 300, 150
		EllipseSprite r2 = new EllipseSprite("test2", 300, 150, 100, 50);
		
		assertTrue(r2.contains(350, 175));
		assertTrue(r2.contains(350, 199));
		assertFalse(r2.contains(50, 25));
	}

}
