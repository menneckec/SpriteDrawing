package uwstout.cs145.projects.project1.drawing;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * Creates a circle sprite
 * 
 * @author Carolyn
 * @version 4.4.2018
 */
public class EllipseSprite extends Sprite {
	private int x;
	private int y;
	private int width;
	private int height;

	private Ellipse shape;

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
	public EllipseSprite(String nId, int nX, int nY, int nWidth, int nHeight)
			throws IllegalArgumentException {
		super(nId, nX, nY, nWidth, nHeight);
		x = nX;
		y = nY;
		width = nWidth;
		height = nHeight;

		shape = new Ellipse(x + width / 2, y + height / 2, width / 2,
				height / 2);
	}

	/**
	 * Determines if point is inside sprite
	 * 
	 * @param a
	 *            x
	 * @param b
	 *            y
	 */
	@Override
	public boolean contains(int a, int b) {

		double rX = width / 2;
		double rY = height / 2;

		double h = x + rX;
		double k = y + rY;

		double num1 = (a - h);
		double num2 = (b - k);

		return (((num1 * num1) / (rX * rX)) + ((num2 * num2) / (rY * rY)))
				<= 1;
	}

	/**
	 * Creates a JavaFX Ellipse
	 */
	@Override
	public Node getGraphic() {
		shape.setFill(Color.RED);
		return shape;
	}

}
