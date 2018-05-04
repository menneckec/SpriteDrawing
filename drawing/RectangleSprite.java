package uwstout.cs145.projects.project1.drawing;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Creates a rectangle sprite
 * 
 * @author Carolyn
 * @version 4.4.2018
 */
public class RectangleSprite extends Sprite {

	private int x;
	private int y;
	private int width;
	private int height;

	private Rectangle shape;

	/**
	 * Constructor
	 * 
	 * @param nId
	 *            ID
	 * @param nX
	 *            x
	 * @param nY
	 *            y
	 * @param nWidth
	 *            width
	 * @param nHeight
	 *            height
	 * @throws IllegalArgumentException
	 */
	public RectangleSprite(String nId, int nX, int nY, int nWidth, int nHeight)
			throws IllegalArgumentException {
		super(nId, nX, nY, nWidth, nHeight);
		x = nX;
		y = nY;
		width = nWidth;
		height = nHeight;

		shape = new Rectangle(x, y, width, height);
	}

	/**
	 * Determines if a position is inside the sprite
	 * 
	 * @param nX
	 *            x
	 * @param nY
	 *            y
	 */
	@Override
	public boolean contains(int nX, int nY) {
		return (nX >= x && nX <= (x + width) && nY >= y && nY <= (y + height));
	}

	/**
	 * Creates a JavaFX Rectangle
	 */
	@Override
	public Node getGraphic() {
		shape.setFill(Color.BLUE);
		return shape;
	}
}
