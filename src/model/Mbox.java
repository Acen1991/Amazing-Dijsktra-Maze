package model;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import maze.MBox;

public abstract class Mbox 
{
	/**
	 * 
	 */
	private int line;
	private int column ;
	private MBox mBox ;
	private Color color;
	private maze.Maze maze ;
	
	public abstract MBox createBox(maze.Maze maze,int line,int column) ;
	
	
	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	public void drawBox(Graphics g)
	{
		g.setColor(color);
		g.fillRect(line, column, 50, 50);
		
	}
	

	
	
}
