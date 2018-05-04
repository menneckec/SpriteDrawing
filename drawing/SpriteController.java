package uwstout.cs145.projects.project1.drawing;

/**
 * Controls sprites
 * 
 * Processes events from the interface and tracks selection of sprites. Makes
 * adding, removing, and selecting sprites easier
 * 
 * @author Carolyn
 * @version 4.4.2018
 */
public class SpriteController {

	private SpriteManager manager;
	private Sprite selected;
	private SpriteEvent event;

	private int count = 100;

	/**
	 * Constructor
	 * 
	 * @param nManager
	 *            manager
	 */
	public SpriteController(SpriteManager nManager) {
		manager = nManager;
		selected = null;
		event = null;
	}

	/**
	 * Getter for SpriteManager
	 * 
	 * @return manager SpriteManager object
	 */
	public SpriteManager getSprites() {
		return manager;
	}

	/**
	 * Getter for Sprite
	 * 
	 * @return sprite Sprite
	 */
	public Sprite getSelected() {
		return selected;
	}

	/**
	 * Getter for SpriteEvent
	 * 
	 * @return event SpriteEvent
	 */
	public SpriteEvent getOnSelect() {
		return event;
	}

	/**
	 * Sets event
	 * 
	 * @param e
	 *            event
	 */
	public void setOnSelect(SpriteEvent e) {
		event = e;
	}

	/**
	 * Adds a sprite
	 * 
	 * @param type
	 *            type of Sprite
	 * @param x1
	 *            starting x
	 * @param y1
	 *            starting y
	 * @param x2
	 *            ending x
	 * @param y2
	 *            ending y
	 */
	public void add(SpriteType type, int x1, int y1, int x2, int y2) {
		int width = Math.abs(x2 - x1);
		int height = Math.abs(y2 - y1);
		int x;
		int y;

		if (x1 < x2) {
			x = x1;
		} else {
			x = x2;
		}

		if (y1 < y2) {
			y = y1;
		} else {
			y = y2;
		}

		if (type != null && width > 0 && height > 0) {
			if (type == SpriteType.ELLIPSE) {
				EllipseSprite temp1 = new EllipseSprite(count + "", x,
					y, width, height);
				manager.addSprite(temp1);
			}

			if (type == SpriteType.RECTANGLE) {
				RectangleSprite temp2 = new RectangleSprite(count + "",
					x, y, width, height);
				manager.addSprite(temp2);
			}
		}

		count++;
	}

	private void testNullEvent(SpriteEventType type, Sprite sprite) {
		if (event != null) {
			event.change(type, sprite);
		}
	}

	/**
	 * Selects a sprite
	 * 
	 * @param nX
	 *            x position
	 * @param nY
	 *            y position
	 */
	public void select(int nX, int nY) {
		Sprite temp = null;
		for (int i = 0; i < manager.size(); i++) {
			// find last sprite
			if (manager.getSprite(i).contains(nX, nY)) {
				temp = manager.getSprite(i);
			}
		}

		if (selected != temp) {
			if (selected != null) {
				testNullEvent(SpriteEventType.UNSELECTED, selected);
			}
			selected = temp;
			if (selected != null) {
				testNullEvent(SpriteEventType.SELECTED, selected);
			}
		}
	}

	/**
	 * Removes a sprite
	 */
	public void remove() {
		if (selected != null) {
			// if sprite selected, change to Unselected
			testNullEvent(SpriteEventType.UNSELECTED, selected);
			// remove with SpriteManager
			manager.removeSprite(selected.getId());
			selected = null;
		}
	}

	/**
	 * Resets all sprites
	 */
	public void reset() {
		// if sprite selected, change to Unselected
		if (selected != null) {
			testNullEvent(SpriteEventType.UNSELECTED, selected);
		}
		manager.clear();
		selected = null;
	}

	/**
	 * Clears selected sprite
	 */
	public void clearSelected() {
		if (selected != null) {
			testNullEvent(SpriteEventType.UNSELECTED, selected);
			selected = null;
		}
	}

	/**
	 * Determines if the sprite contains a point
	 * @param nX	x
	 * @param nY	y
	 * @return the sprite that contains that point
	 */
	public Sprite contains(int nX, int nY) {
		Sprite temp = null;
		for (int i = 0; i < manager.size(); i++) {
			// find last sprite
			if (manager.getSprite(i).contains(nX, nY)) {
				temp = manager.getSprite(i);
			}
		}
		if (temp != null) {
			return temp;
		} else {
			return null;
		}
	}
}
