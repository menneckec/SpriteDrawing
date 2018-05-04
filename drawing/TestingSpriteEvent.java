package uwstout.cs145.projects.project1.drawing;

/**
 * Class designed by Prof. Turner to make testing SpriteManager and
 * SpriteController easier.
 * 
 * When change method is called, the parameters are recorded
 * 
 * @author Carolyn
 * @version 4.4.2018
 */
public class TestingSpriteEvent implements SpriteEvent {

	/**
	 * This is a private inner class. It is not visible outside of
	 * TestingSpriteEvent and can only be used here. It is designed to store a
	 * SpriteEventType and a Sprite together
	 * 
	 * @author turners
	 * @version Mar 24, 2018
	 * 
	 */
	private class Event {
		private SpriteEventType eventType;
		private Sprite sprite;

		Event(SpriteEventType type, Sprite sp) {
			eventType = type;
			sprite = sp;
		}

		public SpriteEventType getEvent() {
			return eventType;
		}

		public Sprite getSprite() {
			return sprite;
		}
	}

	private Event[] events;
	private int count;

	/**
	 * Creates an array to store events that occur when change is called. 
	 * There is no bounds checking and the size of the array is fixed.
	 * 
	 * @param size
	 *            Size of the array
	 */
	public TestingSpriteEvent(int size) {
		events = new Event[size];
		count = 0;
	}

	/**
	 * Records when a change event occurs
	 * 
	 * @param type
	 *            The type of the event (DELETE, ADD, etc.)
	 * @param sp
	 *            The sprite involved with the event
	 */
	@Override
	public void change(SpriteEventType type, Sprite sp) {

		events[count] = new Event(type, sp);
		count++;
	}

	/**
	 * * Returns the number of events recorded
	 * 
	 * @return Number of events
	 */
	public int size() {
		return count;
	}

	/**
	 * * Gets the specified event type from the array
	 * 
	 * @param pos
	 *            Position in the array
	 * @return The type of event (DELETE, ADD, etc.)
	 */
	public SpriteEventType getEvent(int pos) {
		return events[pos].getEvent();
	}

	/**
	 * Gets the sprite associated with the event from the array
	 * 
	 * @param pos
	 *            Position in the array
	 * @return The sprite affected by the event
	 */
	public Sprite getSprite(int pos) {
		return events[pos].getSprite();
	}

	/**
	 * * Removes the events from the array
	 */
	public void clear() {
		// Since the getters do no checks, clear the array
		for (int i = 0; i < count; i++) {
			events[i] = null;
		}
		count = 0;
	}

}
