package uwstout.cs145.projects.project1.drawing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Reads and writes to files
 * 
 * @author Carolyn
 * @version 3.8.2018
 */
public class SpriteIO {
	private SpriteManager data;

	/**
	 * Constructor
	 * 
	 * @param nData
	 *            array of SpriteManagers
	 */
	public SpriteIO(SpriteManager nData) {
		if (nData == null) {
			data = new SpriteManager(5);
		} else {
			data = nData;
		}
	}

	/**
	 * Default constructor
	 */
	public SpriteIO() {
		data = new SpriteManager(5);
	}

	/**
	 * Getter for sprites
	 * 
	 * @return array of SpriteManagers
	 */
	public SpriteManager getSprites() {
		return data;
	}

	/**
	 * Reads from a scanner
	 * 
	 * @param scan
	 *            Scanner
	 * @return whether it successfully read the file
	 */
	public boolean read(Scanner scan) {
		if (scan == null) {
			return false;
		}
		if (scan != null) {
			data.clear();
		}
		while (scan.hasNext()) {
			String line = scan.nextLine();
			Scanner lineInput = new Scanner(line);

			try {
				int x = lineInput.nextInt();
				int y = lineInput.nextInt();
				int width = lineInput.nextInt();
				int height = lineInput.nextInt();
				String id = lineInput.nextLine().trim();

				data.addSprite(new Sprite(id, x, y, width, height));

			} catch (Exception e) {
				// do nothing
			}
			lineInput.close();
		}

		return true;
	}

	/**
	 * Reads from a file
	 * 
	 * @param fileName
	 *            file name
	 * @return whether it read the file
	 */
	public boolean read(String fileName) {
		if (fileName == null) {
			return false;
		}
		
		File file = new File(fileName);
		try {
			Scanner input = new Scanner(file);

			read(input);

			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Writes from a PrintWriter
	 * 
	 * @param printWrite a PrintWriter
	 * @return whether it succeeded
	 */
	public boolean write(PrintWriter printWrite) {
		if (printWrite == null) {
			return false;
		} else {
			try {

				for (int i = 0; i < data.size(); i++) {
					printWrite.println(data.getSprite(i).getX() + " "
							+ data.getSprite(i).getY() + " " 
							+ data.getSprite(i).getWidth() + " "
							+ data.getSprite(i).getHeight()
							+ " " + data.getSprite(i).getId() 
					);
				}

			} catch (Exception e) {
				return false;
			}

			return true;
		}
	}

	/**
	 * Writes to a file
	 * 
	 * @param fileName
	 *            file name
	 * @return whether it succeeded
	 */
	public boolean write(String fileName) {
		if (fileName == null) {
			return false;
		}
		try {
			File file = new File(fileName);

			PrintWriter print = new PrintWriter(file);
			write(print);
			print.close();

			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}
}
