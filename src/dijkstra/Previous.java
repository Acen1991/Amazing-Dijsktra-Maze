package dijkstra;

import java.util.ArrayList;
import java.util.Hashtable;

public class Previous implements PreviousInterface {
	
	private final Hashtable <VertexInterface,VertexInterface> table;  
	public Previous () 
	{
		table = new Hashtable <VertexInterface,VertexInterface> ();
	}

	@Override
	public void setValue(VertexInterface vertex, VertexInterface value) {
		table.put(vertex, value);
		
		// TODO Auto-generated method stub

	}

	@Override
	public VertexInterface getValue(VertexInterface vertex) {
		// TODO Auto-generated method stub
		return table.get(vertex);
	}

	@Override
	public ArrayList<VertexInterface> getShortsPathTo(VertexInterface vertex) {
		// TODO Auto-generated method stub
		ArrayList <VertexInterface> result = new ArrayList <VertexInterface> ();
		
		while (vertex != null) {
			result.add(vertex);
			vertex = getValue (vertex);
		}
		return result ;
	}

}
