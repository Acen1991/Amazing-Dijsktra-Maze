package model;

import java.awt.Color;


import maze.ABox;
import maze.MBox;
import maze.WBox;

public class Abox extends Mbox 
{
	public ABox createBox(maze.Maze maze,int line,int column) 
	{
		return new ABox(maze, line, column);
		// TODO Auto-generated constructor stub
	}


}
