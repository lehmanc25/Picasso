package picasso.view.commands;

import javax.swing.JFileChooser;

import picasso.model.Pixmap;
import picasso.util.FileCommand;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Opens a selected file, reads expression, and display in the Pixmap target.
 * 
 * @author Connor Lehman
 */
public class FileReader extends FileCommand<Pixmap> {
	
	private Evaluator fileEvaluator;
	private String expression;

	/**
	 * Creates a Reader object, which prompts users for image files to open
	 */
	public FileReader(Evaluator eval) {
		super(JFileChooser.OPEN_DIALOG);
		fileEvaluator = eval;
	}
	
	/**
	 * Displays the expression from a given file.
	 */
	
	@Override
	public void execute(Pixmap target) {
	    JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir"), "expressions"));
	    String fileName = getFileName(fileChooser);
	    
	    if (fileName != null && !fileName.isEmpty()) {
	        try {
	            expression = new String(Files.readAllBytes(Paths.get(fileName)));
	         
	        } 
	        catch (IOException e) {
	            e.printStackTrace();  
	        }
	        
	        fileEvaluator.execute(target, expression);
	    } 
	    else {
	        System.out.println("File selection cancelled or no file selected.");
	    }
	}

}





