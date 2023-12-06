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
	
	private Evaluator FileEvaluator;
	private String expression;

	/**
	 * Creates a Reader object, which prompts users for image files to open
	 */
	public FileReader(Evaluator eval) {
		super(JFileChooser.OPEN_DIALOG);
		FileEvaluator = eval;
	}
	
	/**
	 * Displays the expression from a given file.
	 */
	
	@Override
	public void execute(Pixmap target) {
		//FileCommand Name
		String fileName = getFileName();
		
		//Getting absolute path
		String filePath = new File(fileName).getAbsolutePath();
		
		//Read file into expression
		try {
			expression = new String(Files.readAllBytes(Paths.get(filePath)));
			System.out.println(expression);
        } 
		
		catch (IOException e) {
            e.printStackTrace();
        }
		FileEvaluator.execute(target, expression);		
	}
}






