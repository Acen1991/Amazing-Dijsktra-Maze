package dijkstra;

import java.util.Hashtable;

public class Pi implements PiInterface {
	
	private final Hashtable <VertexInterface,Integer> table ;
	public Pi ()
	{
		table = new Hashtable<VertexInterface,Integer> ();
	}

	@Override
	public void setValue(VertexInterface vertex, int value) {
		table.put (vertex , new Integer (value));
		// TODO Auto-generated method stub

	}

	@Override
	public int getValue(VertexInterface vertex) {
		// TODO Auto-generated method stub
		return table.get(vertex).intValue ();
	}

}
