package picasso.view.commands;

import javax.swing.JFileChooser;

import picasso.model.Pixmap;
import picasso.util.FileCommand;
import java.io.File;

/**
 * Open the chosen image file and display in the Pixmap target.
 * 
 * @author Robert C Duvall
 */
public class Reader extends FileCommand<Pixmap> {
	/**
	 * Creates a Reader object, which prompts users for image files to open
	 */
	public Reader() {
		super(JFileChooser.OPEN_DIALOG);
	}
	/**
	 * Displays the image file on the given target.
	 */
	public void execute(Pixmap target) {
		JFileChooser imageChooser = new JFileChooser(new File(System.getProperty("user.dir"), "images"));
	    String fileName = getFileName(imageChooser);

	    if (fileName != null && !fileName.isEmpty()) {
	        target.read(fileName);
	    } 
	    else {
	        System.out.println("File selection cancelled or no file selected.");
	    }
	}
}

