package dijkstra;
import java.util.ArrayList;



public interface GraphInterface {
	public ArrayList<VertexInterface> getAllVertices ();
	public ArrayList<VertexInterface> getSuccessor (VertexInterface vertex);
	public int getWeight (VertexInterface src,VertexInterface dst);

}
