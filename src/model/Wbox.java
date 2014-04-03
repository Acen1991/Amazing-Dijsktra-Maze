package model;

import java.awt.Color;


import maze.MBox;
import maze.WBox;

public class Wbox extends Mbox 
{
	public WBox createBox(maze.Maze maze,int line,int column) 
	{
		return new WBox(maze, line, column);
		// TODO Auto-generated constructor stub
	}


}
