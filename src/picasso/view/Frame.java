package picasso.view;

import picasso.view.commands.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import picasso.model.Pixmap;
import picasso.util.ThreadedCommand;

@SuppressWarnings("serial")
public class Frame extends JFrame {
	public Frame(Dimension size) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// create GUI components
		Canvas canvas = new Canvas(this);
		canvas.setSize(size);

		// mutation duration - # of pictures
		int duration = 10;

		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();

		JTextField textfield = new JTextField(10);
		JLabel label = new JLabel(" Enter your expression: ");

		// add commands to test here
		ButtonPanel commands = new ButtonPanel(canvas);
		commands.add("Open Image", new Reader());
		commands.add("Open File", new FileReader(new Evaluator()));
		commands.add("Evaluate", new ThreadedCommand<Pixmap>(canvas, new Evaluator(textfield)));
		commands.add("Save Image", new Writer());

		// add mutator buttons to test here
		ButtonPanel buttons = new ButtonPanel(canvas);
		buttons.add("Create Mutations",
				new ThreadedCommand<Pixmap>(canvas, new Mutator(new Evaluator(), duration)));

		panel1.setLayout(new BorderLayout());
		panel1.add(commands, BorderLayout.NORTH);
		panel1.add(textfield, BorderLayout.CENTER);
		panel1.add(label, BorderLayout.WEST);
		panel2.add(buttons, BorderLayout.SOUTH);

		commands.add("Random", new ThreadedCommand<Pixmap>(canvas, new RandomExpression(textfield)));

		// Add KeyListener to JTextField
		textfield.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    System.out.println("enterpressed");
					ThreadedCommand<Pixmap> threadedCommand = new ThreadedCommand<>(canvas, new Evaluator(textfield));
					threadedCommand.execute(canvas.getPixmap());
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});

		// add our container to Frame and show it
		getContentPane().add(canvas, BorderLayout.CENTER);
		getContentPane().add(panel1, BorderLayout.NORTH);
		getContentPane().add(panel2, BorderLayout.SOUTH);
		pack();
	}
}
