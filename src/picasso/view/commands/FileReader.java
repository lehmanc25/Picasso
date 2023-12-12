package picasso.view.commands;

import javax.swing.JFileChooser;

import picasso.model.Pixmap;
import picasso.util.FileCommand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

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
	        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))) {
	            
	        	while ((expression = reader.readLine()) != null) {
	                fileEvaluator.execute(target, expression);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("File selection cancelled or no file selected.");
	    }
	}
}






