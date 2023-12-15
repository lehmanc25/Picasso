/**
 * 
 */
package picasso.view.commands;

import picasso.model.Pixmap;
import picasso.util.Command;
import javax.swing.JOptionPane;
import picasso.parser.AssignmentAnalyzer;

/**
 * Access the map that saves the expressions and return it 
 * 
 * @author Desire Asinya
 */
public class SavedVariablesReader implements Command<Pixmap> {

	/**
	 * Creates a saved variables reader object
	 */
	public SavedVariablesReader() {
		
	}
	/**
	 * Displays the saved variables on the screen 
	 */
	public void execute(Pixmap target) {
		String mapString = AssignmentAnalyzer.getMapString();
		
		JOptionPane.showMessageDialog(null, mapString, "Saved variables", JOptionPane.INFORMATION_MESSAGE);
	}
}
