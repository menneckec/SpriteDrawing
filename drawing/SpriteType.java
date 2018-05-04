package uwstout.cs145.projects.project1.drawing;

/**
 * Enum for sprite types
 * 
 * @author Carolyn
 * @version 4.4.2018
 */
public enum SpriteType {
	/**
	 * A rectangle sprite
	 */
	RECTANGLE("Rectangle"),
	/**
	 * An ellipse sprite
	 */
	ELLIPSE("Ellipse");

	/**
	 * The name of the type
	 */
	private String name;

	/**
	 * Constructor
	 * 
	 * @param nName
	 *            name
	 */
	SpriteType(String nName) {
		name = nName;
	}

	/**
	 * Returns the name of the selected type
	 * 
	 * @return name
	 */
	public String toString() {
		return name;
	}
}
