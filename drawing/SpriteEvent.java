package uwstout.cs145.projects.project1.drawing;

/**
 * Interface for spriteEvents
 * 
 * @author Carolyn
 * @version 4.4.2018
 */
public interface SpriteEvent {

	/**
	 * Determines the type of change and object it occurred on
	 * 
	 * @param type
	 *            type of sprite event
	 * @param sprite
	 *            sprite
	 */
	void change(SpriteEventType type, Sprite sprite);
}
