package picasso.view.commands;

import picasso.model.Pixmap;
import picasso.util.Command;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Evaluate an new animation pixel for pixmap
 * 
 * @author Connor Lehman
 */
public class Animator implements Command<Pixmap> {
	protected final int totalSteps;
	private boolean rolling = false;

	// delete when random pixmaps are made
	protected Pixmap preImage = new Pixmap();
	protected Pixmap postImage = new Pixmap();

	/**
	 * Creates an Animator object with max time duration
	 * 
	 * @param duration
	 */
	public Animator(int duration) {
		this.totalSteps = duration;
		rolling = true;
	}

	/**
	 * Renders animation at (t) for each pixel on Pixmap
	 * 
	 * @param preImage
	 * @param postImage
	 * @param t
	 */

	public void animate(Pixmap preImage, Pixmap postImage, int t) {
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
				int red = (((postRed - preRed) / totalSteps) * t) + preRed;
				int green = (((postGreen - preGreen) / totalSteps) * t) + preGreen;
				int blue = (((postBlue - preBlue) / totalSteps) * t) + preBlue;

				// Set new pixel
				Color pixelColor = new Color(red, green, blue);
				preImage.setColor(x, y, pixelColor);
			}
		}

	}

	/**
	 * Executes the Pixmap based on time loop
	 * 
	 * @param target
	 * @see picasso.util.Command#execute(java.lang.Object)
	 */

	@Override
	public void execute(Pixmap target) {

		// Create random preImage
		// Create random postImage
		while (rolling) {
			for (int t = 1; t <= totalSteps; t++) {
				try {
					if (target != null) {
						animate(target, postImage, t);
					} else {
						animate(preImage, postImage, t);

						// Slow down rendering to see transition
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					rolling = false;
				}
			}
		}
	}
}
