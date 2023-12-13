/**
 * 
 */
package picasso.view.commands;

import picasso.model.Pixmap;
import picasso.util.Command;
import javax.swing.JOptionPane;
import picasso.parser.AssignmentAnalyzer;

/**
 * 
 */
public class SavedVariablesReader implements Command<Pixmap> {

	/**
	 * 
	 */
	public SavedVariablesReader() {
		
	}

	public void execute(Pixmap target) {
		String mapString = AssignmentAnalyzer.getMapString();
		
		JOptionPane.showMessageDialog(null, mapString, "Saved variables", JOptionPane.INFORMATION_MESSAGE);
	}
}
