package picasso.view.commands;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File; 

import javax.swing.JTextField;

import picasso.model.Pixmap;
import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.language.ExpressionTreeNode;
import picasso.util.Command;

/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall 
 * @author Sara Sprenkle
 * @author Hotshots
 */
public class Evaluator implements Command<Pixmap> {
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;


	protected JTextField textfield;
	protected File file;
	
	//GUI Evaluator
	public Evaluator(JTextField tf) {
		this.textfield = tf;
	}
	
	
	/**
	 * Renders the expression for each pixel on Pixmap.
	 * @param target
	 * @param expression tree node 
	 */
	public void render(Pixmap target, ExpressionTreeNode expr) {
		// evaluate ExpressionTreeNode for each pixel
		Dimension size = target.getSize();
		for (int imageY = 0; imageY < size.height; imageY++) {
			double evalY = imageToDomainScale(imageY, size.height);
			for (int imageX = 0; imageX < size.width; imageX++) {
				double evalX = imageToDomainScale(imageX, size.width);		
						Color pixelColor = expr.evaluate(evalX, evalY).toJavaColor();
						target.setColor(imageX, imageY, pixelColor);
			}
		}		
	}
	/**
	 * Evaluate an expression from GUI input.
	 * @param target
	 * @param expression
	 */
	public void execute(Pixmap target) {
		// create the expression to evaluate
		ExpressionTreeNode expression = createExpression(textfield.getText());
		//render the expression
		render(target, expression);	
	}
	/**
	 * Evaluate an expression from a file.
	 * @param target
	 * @param expression
	 */
	public void execute(Pixmap target, String fileString) {
		// create the expression to evaluate
		ExpressionTreeNode expression = createExpression(fileString);
		//render the expression
		render(target, expression);
	}

	/**
	 * Convert from image space to domain space.
	 */
	protected double imageToDomainScale(int value, int bounds) {
		double range = DOMAIN_MAX - DOMAIN_MIN;
		return ((double) value / bounds) * range + DOMAIN_MIN;
	}

	/**
	 * A place holder for a more interesting way to build the expression.
	 */
	private ExpressionTreeNode createExpression(String input) {
		// Note, when you're testing, you can use the ExpressionTreeGenerator to
		// generate expression trees from strings, or you can create expression
		// objects directly (as in the commented statement below).

		// String test = "floor(y)";
		// String test = "x + y";

		ExpressionTreeGenerator expTreeGen = new ExpressionTreeGenerator();
		return expTreeGen.makeExpression(input);
		
	}

}
