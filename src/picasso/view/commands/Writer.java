package picasso.view.commands;

import java.io.File;

import javax.swing.JFileChooser;

import picasso.model.Pixmap;
import picasso.util.FileCommand;

/**
 * Save the chosen file.
 * 
 * @author Robert C Duvall
 */
public class Writer extends FileCommand<Pixmap> {
	JFileChooser imageChooser = new JFileChooser(new File(System.getProperty("user.dir"), "images"));
	/**
	 * Creates a Write object, which prompts users for image files to open
	 */
	public Writer() {
		super(JFileChooser.SAVE_DIALOG);
	}

	public void execute(Pixmap target) {
		String fileName = getFileName(imageChooser);
		if (fileName != null) {
			target.write(fileName);
		}
	}
}
