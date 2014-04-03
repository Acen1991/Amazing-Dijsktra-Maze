package view;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import dijkstra.Dijkstra;
import dijkstra.Previous;
import dijkstra.VertexInterface;

import maze.ABox;
import maze.DBox;
import maze.EBox;
import maze.MBox;
import maze.Maze;
import maze.WBox;
import model.Abox;
import model.Dbox;
import model.Ebox;
import model.Mbox;
import model.Wbox;


public class Labyrinthe extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color ;
	private static maze.Maze maze ;
	private Mbox builder = new Ebox();
	private char charbox ;
	private GridLayout layout = new GridLayout(Maze.HEIGHT, Maze.WIDTH, 2, 2);
	private MBoxView[][] boxes = new MBoxView[Maze.HEIGHT][Maze.WIDTH];
	private boolean dBoxExists=false ;
	private boolean aBoxExists=false ;
	static private final String FILENAME="Data/labyrinthe2.txt" ;
	
	public Color getColor ()
	{
		return(color);
	}
	
	public maze.Maze getMaze ()
	{
		return(maze) ;
	}
	
	public Mbox getBuilder()
	{
		return(builder) ;
	}
	
	public char getChar ()
	{
		return(charbox);
	}
	
	public void SaveMaze()
	{
		maze.saveToTextFile(FILENAME);
	}
	
	private MBox getDeparture()
	{
		for (int i = 0; i < Maze.HEIGHT; i++) {
			for (int j = 0; j < Maze.WIDTH; j++) {
				if (maze.getBox(i, j) instanceof DBox) {
					return maze.getBox(i, j);
				}
			}
		}
		return null;
	}
	
	private MBox getArrival()
	{
		for (int i = 0; i < Maze.HEIGHT; i++) {
			for (int j = 0; j < Maze.WIDTH; j++) {
				if (maze.getBox(i, j) instanceof ABox) {
					return maze.getBox(i, j);
				}
			}
		}
		return null;
	}
	
	private boolean isCompleted()
	{
		if (dBoxExists && aBoxExists) return(true) ;
		else return (false) ;
	}

	private void onComplete() {
		for (int i = 0; i < Maze.HEIGHT; i++) {
			for (int j = 0; j < Maze.WIDTH; j++) {
				boxes[i][j].removeMouseListener(boxes[i][j]);
			}
		}
		paintShortestPath();
		
	}
	private void paintShortestPath() {
		VertexInterface r = (VertexInterface) getDeparture();
		VertexInterface d = (VertexInterface) getArrival();
		ArrayList<VertexInterface> shortestPath = ((Previous) Dijkstra.dijkstra(maze, r)).getShortsPathTo(d);
		int n = shortestPath.size();
		for (int i = 0; i < n; i++) 
		{
			MBox mb = (MBox) shortestPath.get(i);
			MBoxView b = boxes[mb.getLine()][mb.getColumn()];
			b.setColor(Color.red);
			b.repaint();
		}
	}
	
	public Labyrinthe(Maze maze) throws IllegalArgumentException {
		super();
		if (maze == null) {
			throw new IllegalArgumentException();
		}
		this.setLayout(layout);
		this.setFocusable(true);
		this.requestFocus();
		this.maze = maze;
		updateMaze();
		
	}
	
	public Labyrinthe() {
		this.maze = new Maze();
		this.setLayout(layout);
		for (int i = 0; i < Maze.HEIGHT; i++) {
			for (int j =0; j < Maze.WIDTH; j++) {
				boxes[i][j] = new MBoxView(i, j, this, Color.GREEN, ' ');
				this.add(boxes[i][j]);
				maze.setBox(boxes[i][j].getBox());
			}
		}
		this.setVisible(true);
	}
	public void updateMaze() {
		for (int i = 0; i < Maze.HEIGHT; i++) {
			for (int j = 0; j < Maze.WIDTH; j++) {
				if (maze.getBox(i, j) instanceof DBox) {
					builder = new Dbox();
					boxes[i][j] = new MBoxView(i, j, this, Color.red, 'D');
					dBoxExists = true;
				} else if (maze.getBox(i, j) instanceof ABox) {
					builder = new Abox();
					boxes[i][j] = new MBoxView(i, j, this, Color.red, 'A');
					aBoxExists = true;
				} else if (maze.getBox(i, j) instanceof EBox) {
					builder = new Ebox();
					boxes[i][j] = new MBoxView(i, j, this, Color.green, ' ');
				} else if (maze.getBox(i, j) instanceof WBox) {
					builder = new Wbox();
					boxes[i][j] = new MBoxView(i, j, this, Color.gray, ' ');
				}
				this.add(boxes[i][j]);
			}
		}
		builder = new Ebox();
		if (isCompleted()) {
			onComplete();
		}
	}
	
	public void updateBox(int line, int column) {
		maze.setBox(boxes[line][column].getBox());
		if (maze.getBox(line, column) instanceof DBox) {
			dBoxExists = true;
			color = Color.green;
			builder = new Ebox();
			charbox = ' ';
			boxes[line][column].removeMouseListener(boxes[line][column]);
		}
		if (maze.getBox(line, column) instanceof ABox) {
			aBoxExists = true;
			color = Color.green;
			charbox = ' ';
			builder = new Ebox();
		}
		if (isCompleted()) {
			onComplete();
		}
	}
	
	public void keyPressed(KeyEvent arg0) 
	{
		switch (arg0.getKeyChar()) {
		case 'a' : if (!aBoxExists) 
		{ 
			color = Color.red; 
			builder = new Abox();  
			charbox= 'A'; 
			break;
		}
		case 'd' : if (!dBoxExists) 
		{ 
			color = Color.red; 
			builder = new Dbox(); 
			charbox = 'D'; 
			break;
		}
		case 'e' : 
		{ 
			color = Color.green; 
			builder = new Ebox(); 
			charbox = ' '; 
			break;
		}
		case 'w' : 
		{ 
			color = Color.gray; 
			builder = new Wbox(); 
			charbox = ' '; 
			break; 
		}
		default : break;
		}
		
	}

	public void paintComponent(Graphics g) 
	{
		Graphics2D x = (Graphics2D) g;
		x.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
