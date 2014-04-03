package maze;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;



public class Maze implements GraphInterface {
	public static final int WIDTH = 10 ;
	public static final int HEIGHT = 10;
	
	final MBox [][] boxes ;
	public Maze () {
		boxes = new MBox [HEIGHT][WIDTH];
	}
	
	public final MBox getBox (int line, int column ){
		return boxes [line][column];
	}
	
	public void setBox(MBox box) {
		boxes[box.getLine()][box.getColumn()] = box;
	}
	
	@Override
	public ArrayList <VertexInterface> getSuccessor(VertexInterface vertex) {
		ArrayList<VertexInterface> successors 
		= new ArrayList<VertexInterface>();
		MBox box = (MBox)vertex;
		int line = box.getLine();
		int column = box.getColumn ();
		if (line>0 ) {
			MBox neighbor =boxes [line-1][column];
			if (neighbor.isAccessible()) successors.add(neighbor);
			
		}
		
		if (line< HEIGHT-1) {
			MBox neighbor = boxes [line+1][column];
			if (neighbor.isAccessible()) successors.add(neighbor);
			
		}
		if (column> 0) {
			MBox neighbor = boxes [line][column-1];
			if (neighbor.isAccessible()) successors.add(neighbor);
		}
		if (column<WIDTH -1) {
			MBox neighbor = boxes [line][column+1];
			if (neighbor.isAccessible()) successors.add(neighbor);
		}
		return successors ;
		
		
	}
	
	@Override
	public final int getWeight ( VertexInterface src, VertexInterface dst) { return 1 ;} ;
	
	public final void initFromTextFile (String fileName) {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader (fileName);
			br = new BufferedReader(fr);
			
			for (int lineNo = 0 ; lineNo< HEIGHT ; lineNo ++) {
				String line = br.readLine();
				
				if (line==null) throw new MazeReadingException (fileName,lineNo, "not enough lines");
				if (line.length()<WIDTH) throw new MazeReadingException (fileName,lineNo, "line too short");
				if (line.length()>WIDTH) throw new MazeReadingException (fileName,lineNo, "line too long");
		
				for (int colNo =0 ; colNo < WIDTH; colNo ++) {
					switch (line.charAt(colNo)) {
					case 'D' : boxes [lineNo][colNo] = new DBox (this, lineNo,colNo); break;
					case 'A' : boxes [lineNo][colNo] = new ABox (this, lineNo,colNo); break;
					case 'W' : boxes [lineNo][colNo] = new WBox (this, lineNo,colNo); break;
					case 'E' : boxes [lineNo][colNo] = new EBox (this, lineNo,colNo); break;
					default :
						throw new MazeReadingException (fileName,lineNo,"unknown char'"+boxes[lineNo][colNo]+"'");
						
					}
				}
				
				
			}
		} catch (MazeReadingException e) {System.err.println(e.getMessage());}
		catch (FileNotFoundException e) {System.err.println("error class Maze, initFromTextFile: file not found \""+ fileName + "\"");}
		catch (IOException e) {System.err.println("error class Maze, initFromTextFile: read error on file \""+ fileName + "\"");}
		catch (Exception e){System.err.println("Error:Unknown error.");e.printStackTrace(System.err);}
		finally 
		{if (fr != null) try {fr.close();} catch (Exception e){};if (br !=null) try {br.close();} catch (Exception e){} }


		
		
		
	}
	
		public void saveToTextFile (String fileName) 
		{
			PrintWriter pw = null;
			
			try 
			{
				pw = new PrintWriter (fileName);
				
				for (int lineNo =0 ; lineNo < HEIGHT ; lineNo++) 
				{
					MBox [] line = boxes [lineNo];
					for ( int colNo=0 ; colNo < WIDTH; colNo++)
						line[colNo].writeCharTo(pw);
					pw.println();
					
				}
				
			}catch (FileNotFoundException e) 
			{
				System.err.println ("Error class Maze,saveToTextFile:file not found \""+fileName+"\"");
				
			}
			catch (SecurityException e) 
			{
				System.err.println ("error class Maze,saveToextFile: secrit exception \""+fileName+"\"");	
				
				
			}catch (Exception ex) 
			{
				System.err.println ("Error : unknown error.");
				ex.printStackTrace (System.err);
				
			}
			finally 
			{
				if (pw != null ) try { pw.close(); } catch (Exception e) {} ;
			}
					
			
		}

		@Override
		public ArrayList<VertexInterface> getAllVertices() {
			ArrayList<VertexInterface> list = new ArrayList<VertexInterface>();
			for (int i = 0; i < HEIGHT; i++) {
				for (int j =0; j < WIDTH; j++) {
					list.add(boxes[i][j]);
				}
			}
			return list;
		}
	
	


}




