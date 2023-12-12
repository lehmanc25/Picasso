package picasso.view.commands;

import picasso.parser.ParseException;
import picasso.parser.language.BuiltinFunctionsReader;
import picasso.parser.language.ExpressionTreeNode;

import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import picasso.parser.tokens.TokenFactory;
import javax.swing.JTextField;
import picasso.model.Pixmap;
import picasso.util.Command;

public class RandomExpression implements Command<Pixmap> {
	
	static List<String> functions;
	static List<String> operations;
	static List<String> original;
	
	
	static String operator;
	static String function;
	static String xy;
	static String color;
	static String input;
	static ExpressionTreeNode node;
	JTextField textfield;
	
	private static final String EXPRESSION_PACKAGE = "picasso.parser.language.expressions.";
	private static final String OPS_FILE = "conf/operations.prop";
 	
 	static List<String> expression = new ArrayList<String>();

	public RandomExpression(JTextField tf) {
		this.textfield = tf;
		original = BuiltinFunctionsReader.getFunctionsList();
		functions = new ArrayList<String>(original);
		
		int i = 0;
		for (String function : functions) {
			functions.set(i, TokenFactory.capitalize(function));
			i++;	
		}
		operations = getOperationsList();
		
//		remove special functions and operators
		functions.remove("ImageWrap");
		functions.remove("Random");
		functions.remove("ImageClip");
		operations.remove("Assignment");
	}
	public List<String> getOperationsList() {
		// gets list of operators
		Properties opProps = new Properties();
		List<String> ops = new ArrayList<String>();
		try {
			opProps.load(new FileReader(OPS_FILE));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		for (Object op : opProps.keySet()) {
			String opName = (String) opProps.get(op);
			ops.add(opName);
		}
		return ops;	
	}
	
	public String generateFunction() {
		//generates random function
		int upperbound = functions.size();
		java.util.Random rd = new java.util.Random();
		int int_random = rd.nextInt(upperbound);
		String function = functions.get(int_random);
		return function;
		
	}
	
	public String generateOperator() {
		//generates random operator
		int upperbound = operations.size();
		java.util.Random rd = new java.util.Random();
		int int_random = rd.nextInt(upperbound);
		return operations.get(int_random);
		
	}

	
	
	
	public String generateXY() {
		//generates x or y 
		int upperbound = 2;
		java.util.Random rd = new java.util.Random();
		int int_random = rd.nextInt(upperbound);
		if (int_random == 0) {
			return "X";
		}
		else {
			return "Y";
		}
	}
	
	public String generateColor() {
		//generates random color
		return "Random";	
	}
	
	
	public ExpressionTreeNode generateExpression() {
		try {
			int upperbound = 4;
			java.util.Random rd = new java.util.Random();
			int int_random = rd.nextInt(upperbound);
			
			if (int_random == 0) {
				// generate random function
				function = generateFunction();
				input = EXPRESSION_PACKAGE + function ;
				if (function.equals("PerlinColor") | function.equals("PerlinBW")) {
					node = (ExpressionTreeNode) Class.forName(input).getDeclaredConstructor(ExpressionTreeNode.class, ExpressionTreeNode.class).newInstance(generateExpression(), generateExpression());
				}
				else {
					node = (ExpressionTreeNode) Class.forName(input).getDeclaredConstructor(ExpressionTreeNode.class).newInstance(generateExpression());
				}
			}
			else if (int_random == 1) {
				// generate operator
				operator = generateOperator();
				input = EXPRESSION_PACKAGE + operator;
				if (operator.equals("Neg")) {
					node = (ExpressionTreeNode) Class.forName(input).getDeclaredConstructor(ExpressionTreeNode.class).newInstance(generateExpression());
				}
				else {
					node = (ExpressionTreeNode) Class.forName(input).getDeclaredConstructor(ExpressionTreeNode.class, ExpressionTreeNode.class).newInstance(generateExpression(), generateExpression());
				}
			}
			else if (int_random == 2) {
				// generate x or y
				xy = generateXY();
				input = EXPRESSION_PACKAGE + xy;
				node = (ExpressionTreeNode) Class.forName(input).getDeclaredConstructor().newInstance();
			} 
			else {
				// generate color
				color = generateColor();
				input = EXPRESSION_PACKAGE + color;
				node = (ExpressionTreeNode) Class.forName(input).getDeclaredConstructor().newInstance();
				
			}
			return node;
		}	
		catch (ClassNotFoundException e) {
			throw new ParseException(input + " not found " + e);
		}
		catch (InstantiationException e) {
			throw new ParseException(input + " not instantiated " + e);
		}
		catch (IllegalAccessException e) {
			throw new ParseException(input + " not creatable " + e);
		}
		catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new ParseException(input + " not creatable " + e);
		}
		catch (StackOverflowError e) {
			return generateExpression();
		}
	}
		
	public void execute(Pixmap target) {

		ExpressionTreeNode output = this.generateExpression();
		String output_str = String.valueOf(output);
		textfield.setText(output_str);
		ExpressionTreeNode expr = output;
		

		Dimension size = target.getSize();
		for (int imageY = 0; imageY < size.height; imageY++) {
			double evalY = Evaluator.imageToDomainScale(imageY, size.height);
			for (int imageX = 0; imageX < size.width; imageX++) {
				double evalX = Evaluator.imageToDomainScale(imageX, size.width);
				Color pixelColor = expr.evaluate(evalX, evalY).toJavaColor();
				target.setColor(imageX, imageY, pixelColor);
			}
		}
	}
}