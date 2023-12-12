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
        
       // create animation constraints
        int totalSteps = 50;

        JPanel panel = new JPanel();
        JTextField textfield = new JTextField(10);
        JLabel label = new JLabel(" Enter your expression: ");

        // add commands to test here
        ButtonPanel commands = new ButtonPanel(canvas);
        commands.add("Open Image", new Reader());
        commands.add("Open File", new FileReader(new Evaluator()));
        commands.add("Evaluate", new ThreadedCommand<Pixmap>(canvas, new Evaluator(textfield)));
        commands.add("Animate", new ThreadedCommand<Pixmap>(canvas, new Animator(totalSteps)));
        commands.add("Save Image", new Writer());

        panel.setLayout(new BorderLayout());
        panel.add(commands, BorderLayout.NORTH);
        panel.add(textfield, BorderLayout.CENTER);
        panel.add(label, BorderLayout.WEST);

       
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
        getContentPane().add(panel, BorderLayout.NORTH);
        pack();
    }
}
