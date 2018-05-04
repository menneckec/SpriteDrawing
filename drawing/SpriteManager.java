package uwstout.cs145.projects.project1.drawing;

/**
 * Stores sprites and adds, finds, and removes them
 * 
 * @author Carolyn
 * @version 3.5.2018
 */
public class SpriteManager {

	private Sprite[] spriteArray;
	private int count;
	private SpriteEvent event;

	/**
	 * Constructor
	 * 
	 * @param size
	 *            size
	 * @throws IllegalArgumentException
	 */
	public SpriteManager(int size) throws IllegalArgumentException {
		if (size < 0) {
			throw new IllegalArgumentException();
		}
		spriteArray = new Sprite[size];
		count = 0;
		event = null;
	}

	/**
	 * Returns number of items in array
	 * 
	 * @return number of items in array
	 */
	public int size() {
		return count;
	}

	/**
	 * Gets the maximum amount of objects that can be stored in the array
	 * 
	 * @return length of the array
	 */
	public int capacity() {
		return spriteArray.length;
	}

	/**
	 * Gets a sprite at a given position
	 * 
	 * @param pos
	 *            position
	 * @return the sprite at that position
	 */
	public Sprite getSprite(int pos) {
		if (pos >= 0 && pos < count) {
			return spriteArray[pos];
		} else {
			return null;
		}
	}

	/**
	 * Adds a sprite to the array
	 * 
	 * @param s
	 *            a new sprite to be added
	 */
	public void addSprite(Sprite s) {
		if (count == spriteArray.length) {
			Sprite[] temp = new Sprite[spriteArray.length + 2];

			for (int i = 0; i < spriteArray.length; i++) {
				temp[i] = spriteArray[i];
			}
			spriteArray = temp;
		}

		spriteArray[count] = s;
		count++;

		if (event != null) {
			event.change(SpriteEventType.ADD, s);
		}
	}

	/**
	 * Getter for SpriteEvent
	 * 
	 * @return event change
	 */
	public SpriteEvent getOnChange() {
		return event;
	}

	/**
	 * Sets SpriteEvent
	 * 
	 * @param nEvent event
	 */
	public void setOnChange(SpriteEvent nEvent) {
		event = nEvent;
	}

	/**
	 * Find a sprite by its id
	 * 
	 * @param id
	 *            id
	 * @return the position in the array
	 */
	public int findSprite(String id) {
		for (int i = 0; i < count; i++) {
			if (id.equals(spriteArray[i].getId())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Removes a sprite from the array
	 * 
	 * @param id
	 *            id of the sprite to remove
	 * @return the sprite removed
	 */
	public Sprite removeSprite(String id) {
		int pos = findSprite(id);
		if (pos == -1) {
			return null;
		}
		Sprite spriteRemoved = spriteArray[pos];

		Sprite[] temp = new Sprite[spriteArray.length - 1];

		for (int i = 0; i < pos; i++) {
			temp[i] = spriteArray[i];
		}

		for (int i = pos; i < (count - 1); i++) {
			temp[i] = spriteArray[(i + 1)];
		}

		count--;

		spriteArray = temp;

		if (event != null) {
			event.change(SpriteEventType.DELETE, spriteRemoved);
		}

		return spriteRemoved;
	}

	/**
	 * Clears the sprite array
	 */
	public void clear() {

		for (int i = 0; i < count; i++) {
			removeSprite(spriteArray[i].toString());
			if (event != null) {
				event.change(SpriteEventType.DELETE, spriteArray[i]);
			}
		}

		count = 0;
	}
}
