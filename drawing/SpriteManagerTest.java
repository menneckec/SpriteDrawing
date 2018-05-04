package uwstout.cs145.projects.project1.drawing;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests SpriteManager
 * 
 * @author Carolyn
 * @version 3.8.2018
 */
public class SpriteManagerTest {

	private static final Sprite[] VALUES = { 
		new Sprite("a", 1, 1, 1, 1), 
		new Sprite("b", 2, 2, 2, 2),
		new Sprite("c", 3, 3, 3, 3), 
		new Sprite("d", 4, 4, 4, 4) 
		};
	
	/**
	 * Tests if exception is thrown when size<0
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testException() {
		SpriteManager nope = new SpriteManager(-1);
		assertEquals(-1, nope.size());
	}
	
	/**
	 * Tests an invalid position in getter
	 */
	@Test
	public void testInvalidPos() {
		SpriteManager inval = new SpriteManager(5);
		assertNull(inval.getSprite(-1));
	}

	/**
	 * Tests the method to find capacity of array
	 */
	@Test
	public void testCapacity() {
		SpriteManager manager = new SpriteManager(3);
		for (int i = 0; i < VALUES.length; i++) {
			manager.addSprite(VALUES[i]);
		}

		assertEquals(5, manager.capacity());

		SpriteManager manager2 = new SpriteManager(4);
		for (int i = 0; i < VALUES.length; i++) {
			manager2.addSprite(VALUES[i]);
		}

		assertEquals(4, manager2.capacity());
	}

	/**
	 * Tests adding a sprite to the array
	 */
	@Test
	public void testAddSprite() {
		SpriteManager manager = new SpriteManager(3);
		for (int i = 0; i < VALUES.length; i++) {
			manager.addSprite(VALUES[i]);
		}

		assertEquals(VALUES.length, manager.size());
		for (int i = 0; i < manager.size(); i++) {
			assertEquals(VALUES[i].getX(), manager.getSprite(i).getX());
		}
	}

	/**
	 * Tests finding a specific sprite in the array
	 */
	@Test
	public void testFindSprite() {
		SpriteManager manager = new SpriteManager(3);
		for (int i = 0; i < VALUES.length; i++) {
			manager.addSprite(VALUES[i]);
		}

		assertEquals(0, manager.findSprite("a"));
	}

	/**
	 * Tests removing a sprite
	 */
	@Test
	public void testRemoveSprite() {
		SpriteManager manager = new SpriteManager(4);
		for (int i = 0; i < VALUES.length; i++) {
			manager.addSprite(VALUES[i]);
		}

		assertEquals(VALUES[2], manager.removeSprite("c"));
		assertEquals(VALUES[3].getX(), manager.getSprite(2).getX());
	}

	/**
	 * Tests clearing the array
	 */
	@Test
	public void testClear() {
		SpriteManager manager = new SpriteManager(3);
		for (int i = 0; i < VALUES.length; i++) {
			manager.addSprite(VALUES[i]);
		}

		manager.clear();

		assertEquals(0, manager.size());
	}

}
