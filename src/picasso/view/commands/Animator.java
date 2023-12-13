package picasso.view.commands;

import picasso.model.Pixmap;
import picasso.util.Command;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Evaluate an new animation pixel for pixmap
 * 
 * @author Connor Lehman
 */
public class Animator implements Command<Pixmap> {
	protected final double totalSteps = 40;
	private Evaluator eval;
	private boolean rolling = false;

	/**
	 * Creates an Animator object with max time duration
	 * 
	 * @param duration
	 */
	public Animator(Evaluator eval) {
		this.eval = eval;
	}

	/**
	 * Renders animation at (t) for each pixel on Pixmap
	 * 
	 * @param preImage
	 * @param postImage
	 * @param t
	 */

	public void animate(Pixmap target, Pixmap preImage, Pixmap postImage, int t) {
		// evaluate animation
		Dimension size = preImage.getSize();
		for (int y = 0; y < size.height; y++) {
			for (int x = 0; x < size.width; x++) {

				// Pre-image RBG
				int preRed = preImage.getColor(x, y).getRed();
				int preGreen = preImage.getColor(x, y).getGreen();
				int preBlue = preImage.getColor(x, y).getBlue();

				// Post-image RBG
				int postRed = postImage.getColor(x, y).getRed();
				int postGreen = postImage.getColor(x, y).getGreen();
				int postBlue = postImage.getColor(x, y).getBlue();

				// New RBG for time step (t)
				int red = (int) (((postRed - preRed) / totalSteps) * t) + preRed;
				int green = (int) (((postGreen - preGreen) / totalSteps) * t) + preGreen;
				int blue = (int) (((postBlue - preBlue) / totalSteps) * t) + preBlue;

				// Set new pixel
				Color pixelColor = new Color(red, green, blue);
				target.setColor(x, y, pixelColor);
			}
		}

	}

	/**
	 * 
	 * @return random expression string from expression files
	 */

	public String randomExpression() {
		File expressionsDir = new File(System.getProperty("user.dir"), "expressions");
		File[] files = expressionsDir.listFiles();
		String expression = null;
		String randomExpression = "";

		if (files != null && files.length > 0) {
			Random random = new Random();
			File selectedFile = files[random.nextInt(files.length)];

			try (BufferedReader reader = Files.newBufferedReader(Paths.get(selectedFile.getAbsolutePath()))) {
				String title = reader.readLine();
				// System.out.println(title);
				while ((expression = reader.readLine()) != null) {
					// System.out.println(expression);
					return expression;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return randomExpression;
	}

	/**
	 * Executes the Pixmap based on time loop
	 * 
	 * @param target
	 * @see picasso.util.Command#execute(java.lang.Object)
	 */
	@Override
	public void execute(Pixmap target) {
		if (!rolling) {
			rolling = true;

			while (rolling) {
				Pixmap postImage = new Pixmap(target);
				String randomExpression = randomExpression();
				eval.execute(postImage, randomExpression);

				for (int t = 1; t <= totalSteps; t++) {
					try {
						Pixmap cloneTarget = new Pixmap(target);
						animate(target, cloneTarget, postImage, t);
						Thread.sleep(500);

					} catch (InterruptedException e) {
				//		rolling = false;
					}
				}
				// System.out.println(target.getColor(0, 0));
			}
		} else {
			rolling = false;
			System.out.println("done.");
		}
	}
}
