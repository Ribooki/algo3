import java.util.ArrayList;
import java.util.LinkedList;


public class Graph{
    // classe de graphe non orientés permettant de manipuler
    // en même temps des arcs (orientés)
    // pour pouvoir stocker un arbre couvrant, en plus du graphe
    
	protected int V;
	protected int E;

	protected ArrayList<LinkedList<Edge>> adjacency;
	protected ArrayList<LinkedList<Arc>> inAdjacency;
	protected ArrayList<LinkedList<Arc>> outAdjacency;
	
	public boolean isVertex(int index) {
	    return( index >= 0 && index < V) ? true : false;
	}
	
	public Graph(int cardinalVertex) {
		V = cardinalVertex;
		E = 0;
		adjacency = new ArrayList<>();
	}
	
	public void addArc(Arc arc) {
	}
	
	public void addEdge(int from, int to, int weight) {
		Edge newEdge = new Edge(from, to, weight);
		if(!adjacency.get(from).contains(newEdge)) {
			adjacency.get(from).add(newEdge);
			E++;
		}
	}

	public void removeEdge(Edge e){
		adjacency.get(e.getSource()).remove(e);
	}
	
}
