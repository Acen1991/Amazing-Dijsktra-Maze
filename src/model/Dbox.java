package model;

import java.awt.Color;


import maze.DBox;
import maze.MBox;
import maze.WBox;


	public class Dbox extends Mbox 
	{
		public DBox createBox(maze.Maze maze,int line,int column) 
		{
			return new DBox(maze, line, column);
			// TODO Auto-generated constructor stub
		}

	
}
