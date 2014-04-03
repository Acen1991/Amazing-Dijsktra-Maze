package model;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



import maze.EBox;
import maze.MBox;
import maze.WBox;

public class Ebox extends Mbox 
{
	public EBox createBox(maze.Maze maze,int line,int column) 
	{
		return new EBox(maze, line, column);
		// TODO Auto-generated constructor stub
	}

	
}
