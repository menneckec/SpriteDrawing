package uwstout.cs145.projects.project1.drawing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.Test;

/**
 * Tests SpriteIO
 * 
 * @author Carolyn
 * @version 3.8.2018
 */
public class SpriteIOTest {

	/**
	 * Tests read methods
	 */
	@Test
	public void testRead() {
		SpriteIO io = new SpriteIO();
		
		assertTrue(io.read("spriteFile.txt"));
		assertEquals(4, io.getSprites().size());
		assertEquals(-93, io.getSprites().getSprite(0).getX());
		assertEquals(-80, io.getSprites().getSprite(1).getX());
		assertEquals(89, io.getSprites().getSprite(2).getX());
		assertEquals(28, io.getSprites().getSprite(3).getX());
		
	}
	
	/**
	 * Tests if any parameters are null
	 */
	@Test
	public void testNull() {
		SpriteIO io = new SpriteIO(null);
		assertEquals(5, io.getSprites().capacity());
		
		Scanner scan = null;
		assertEquals(false, io.read(scan));
		
		String fileName = null;
		assertEquals(false, io.read(fileName));
		assertEquals(false, io.write(fileName));
		
		PrintWriter p = null;
		assertEquals(false, io.write(p));
	}

	/**
	 * Tests both write methods
	 */
	@Test
	public void testWrite() {
		SpriteManager sManage = new SpriteManager(4);
		// add some values
		sManage.addSprite(new Sprite("SP1000", -93, 51, 95, 83));
		sManage.addSprite(new Sprite("SP 1001", -80, 101, 43, 92));
		sManage.addSprite(new Sprite("SP1002", 89, 57, 83, 89));
		sManage.addSprite(new Sprite("SP1004", 28, 26, 63, 4));
		// create SpriteIO with some data
		SpriteIO spriteOut = new SpriteIO(sManage);
		// write it to a file
		spriteOut.write("testOut1.txt");
		// now check file
		File file = new File("testOut1.txt");
		try {
			Scanner in = new Scanner(file);
			String line;
			// read in a line and remove some
			// whitespace are the beginning and end
			// (just in case)
			// may need to be careful about the spacing between
			// values on each line
			// put the expected values in an array instead?
			// then I could use a loop
			line = in.nextLine().trim();
			assertEquals("-93 51 95 83 SP1000", line);
			line = in.nextLine().trim();
			assertEquals("-80 101 43 92 SP 1001", line);
			line = in.nextLine().trim();
			assertEquals("89 57 83 89 SP1002", line);
			line = in.nextLine().trim();
			assertEquals("28 26 63 4 SP1004", line);
			in.close();
		} catch (FileNotFoundException e) {
			fail("Couldn't open file");
		}
		// Remove the file
		file.delete();
	}
}
