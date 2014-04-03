package view ;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import maze.Maze;


public class Window extends JFrame implements ActionListener, KeyListener 
{
	private Labyrinthe labyrinthe = new Labyrinthe();
	private JButton loadmaze = new JButton("LOAD MAZE");
	private JButton savemaze = new JButton("SAVE MAZE");
	static private final String FILENAME = "data/labyrinthe3.txt";
	
	public void keyPressed(KeyEvent e) 
	{
		labyrinthe.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}
	
	public Window() 
	{
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Labyrinthe");
		BorderLayout lay = new BorderLayout();
		this.getContentPane().setLayout(lay);
		loadmaze.addActionListener(this);
		savemaze.addActionListener(this);
		JPanel pan = new JPanel();
		pan.add(loadmaze);
		pan.add(savemaze);
		this.getContentPane().add(pan, BorderLayout.NORTH);
		this.getContentPane().add(labyrinthe, BorderLayout.CENTER);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		Object o = e.getSource();
		if (o == loadmaze) 
		{
			Maze maze = new Maze();
			maze.initFromTextFile(FILENAME);
			this.getContentPane().remove(labyrinthe);
			this.validate();
			labyrinthe = new Labyrinthe(maze);
			this.getContentPane().add(labyrinthe, BorderLayout.CENTER);
			this.validate();
			this.setFocusable(true);
			this.requestFocus();
		} 
		else if (o == savemaze) 
		{
			labyrinthe.SaveMaze();
		}
	}

}
