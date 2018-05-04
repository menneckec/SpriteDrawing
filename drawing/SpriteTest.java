package uwstout.cs145.projects.project1.drawing;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests Sprite
 * 
 * @author Carolyn
 * @version 3.8.2018
 */
public class SpriteTest {

	/**
	 * Tests the constructor
	 */
	@Test
	public void testSprite() {
		Sprite s1 = new Sprite("SP1000", 1, 2, 4, 3);

		assertEquals(1, s1.getX());
		assertEquals(2, s1.getY());
		assertEquals(3, s1.getHeight());
		assertEquals(4, s1.getWidth());
		assertEquals("SP1000", s1.getId());
	}

	/**
	 * Tests if a sprite with width or height 0 throws an exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSpriteException() {
		Sprite s1 = new Sprite("", 0, 0, 0, 0);
		assertEquals(0, s1.getX());
	}

	/**
	 * Tests the aspect ratio method
	 */
	@Test
	public void testGetAspectRatio() {
		Sprite s1 = new Sprite("SP1000", 2, 3, 4, 1);
		double aspectRatio = s1.getAspectRatio();
		assertEquals(4, aspectRatio, 0.001);
	}

}
