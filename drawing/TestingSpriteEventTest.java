package uwstout.cs145.projects.project1.drawing;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests TestingSpriteEvent, as written by Prof. Turner
 * 
 * @author Carolyn
 * @version 4.4.2018
 */
public class TestingSpriteEventTest {

	private SpriteController createController() {
		SpriteManager sprites = new SpriteManager(5);
		SpriteController control = new SpriteController(sprites);
		// add a few sprites
		// rectangle at (50, 100) with a size of 50 x 75
		control.add(SpriteType.RECTANGLE, 50, 100, 100, 175);
		// ellipse at (75, 100) with a size of 50 x 50
		// overlaps the rectangle slightly
		control.add(SpriteType.ELLIPSE, 75, 100, 125, 150);
		return control;
	}

	/**
	 * * Basic test of new event functionality in addSprite
	 */
	@Test
	public void testAddSpriteEvent() {
		// create manager
		SpriteManager sprites = new SpriteManager(5);
		// add SpriteEvent to SpriteManager so that we can see
		// when sprites are added/removed
		TestingSpriteEvent events = new TestingSpriteEvent(10);
		sprites.setOnChange(events);
		EllipseSprite e1 = new EllipseSprite("test1", 15, 20, 100, 200);
		sprites.addSprite(e1);
		// check events
		// exactly one event should have occurred
		assertEquals(1, events.size());
		// it should have been a selected event
		assertEquals(SpriteEventType.ADD, events.getEvent(0));
		// it should have been the same sprite as before
		assertEquals(e1, events.getSprite(0));
	}

	/**
	 * Basic test for events in clear method
	 */
	@Test
	public void testClearEvents() {
		SpriteManager sprites = new SpriteManager(5);
		// add SpriteEvent to SpriteManager so that we can see
		// when sprites are added/removed
		EllipseSprite e1 = new EllipseSprite("test1", 15, 20, 100, 200);
		EllipseSprite e2 = new EllipseSprite("test2", 50, 200, 15, 15);
		sprites.addSprite(e1);
		sprites.addSprite(e2);
		// add SpriteEvent after the adds since we only care about events
		// from calling clear
		TestingSpriteEvent events = new TestingSpriteEvent(10);
		sprites.setOnChange(events);
		sprites.clear();
		// check events
		// exactly one event should have occurred
		assertEquals(2, events.size());
		// they should all be delete events
		assertEquals(SpriteEventType.DELETE, events.getEvent(0));
		assertEquals(SpriteEventType.DELETE, events.getEvent(1));
		// it should have been the same sprites as added
		// the order may need to be changed here if the events are
		// generated in a different order
		assertEquals(e1, events.getSprite(0));
		assertEquals(e2, events.getSprite(1));
	}

	/**
	 * Tests basic select functionality
	 */
	@Test
	public void testSelect() {
		SpriteController control = createController();
		// add SpriteEvent to SpriteController so that we can see
		// when the selection changes
		TestingSpriteEvent events = new TestingSpriteEvent(10);
		control.setOnSelect(events);
		control.select(55, 125);
		// should select the rectangle sprite
		// check getSprite
		// the one selected should be the first one in the list
		// can use equals here since they should be the same object
		assertEquals(control.getSprites().getSprite(0), control.getSelected());
		// check if event occurred
		// exactly one event should have occurred
		assertEquals(1, events.size());
		// it should have been a selected event
		assertEquals(SpriteEventType.SELECTED, events.getEvent(0));
		// it should have been the same sprite as before
		assertEquals(control.getSprites().getSprite(0), events.getSprite(0));
		// test when nothing gets selected
		events.clear();
		// get rid of old events
		control.select(5, 225);
		// should select nothing
		assertNull(control.getSelected());
		// check if event occurred
		// exactly one event should have occurred - an unselected
		assertEquals(1, events.size());
		// it should have been an unselected event
		assertEquals(SpriteEventType.UNSELECTED, events.getEvent(0));
		// it should have been the sprite selected previously
		assertEquals(control.getSprites().getSprite(0), events.getSprite(0));
	}

	/**
	 * Tests basic remove functionality
	 */
	@Test
	public void testRemove() {
		SpriteController control = createController();
		// add SpriteEvent to SpriteController so that we can see
		// when the selection changes
		TestingSpriteEvent events = new TestingSpriteEvent(10);
		control.setOnSelect(events);
		// try removing
		control.remove();
		// nothing was selected, so nothing was removed
		assertEquals(2, control.getSprites().size());
		// event
		assertEquals(0, events.size());
		// no event either
		// select the ellipse
		control.select(105, 125);
		assertEquals(control.getSprites().getSprite(1), control.getSelected());
		Sprite ellipse = control.getSprites().getSprite(1);
		events.clear();
		// reset events for remove
		// remove
		control.remove();
		// should work now
		assertEquals(1, control.getSprites().size());
		// event
		assertEquals(1, events.size());
		// it should have been an unselected event
		assertEquals(SpriteEventType.UNSELECTED, events.getEvent(0));
		// it should have been the sprite selected previously
		assertEquals(ellipse, events.getSprite(0));
	}
}
