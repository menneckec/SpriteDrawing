package uwstout.cs145.projects.project1.drawing;

import javafx.scene.Node;

/**
 * Represents an object that can be seen on screen
 * 
 * @author Carolyn
 * @version 3.3.2018
 */
public class Sprite {

	private String id;
	private int x;
	private int y;
	private int width;
	private int height;

	/**
	 * Constructor for Sprite
	 * 
	 * @param nId
	 *            identification
	 * 
	 * @param nX
	 *            x position
	 * @param nY
	 *            y position
	 * @param nWidth
	 *            width
	 * @param nHeight
	 *            height
	 * 
	 * 
	 * @throws IllegalArgumentException
	 */
	public Sprite(String nId, int nX, int nY, int nWidth, int nHeight)
			throws IllegalArgumentException {
		if (nId == null || nWidth <= 0 || nHeight <= 0) {
			throw new IllegalArgumentException();
		} else {
			x = nX;
			y = nY;
			width = nWidth;
			height = nHeight;
			id = nId;
		}
	}

	/**
	 * Gets id
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets x
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets y
	 * 
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Gets width
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets height
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the aspect ratio
	 * 
	 * @return aspect ratio
	 */
	public double getAspectRatio() {

		double aspectRatio = (double) width / (double) height;
		return aspectRatio;
	}

	/**
	 * Determines if position is inside sprite
	 * 
	 * @param nX
	 *            x coordinate
	 * @param nY
	 *            y coordinate
	 * @return false always returns false
	 */
	public boolean contains(int nX, int nY) {
		return false;
	}
	
	
	/**
	 * Creates a JavaFX Node to be displayed
	 * 
	 * @return null
	 */
	public Node getGraphic() {
		return null;
	}
}
