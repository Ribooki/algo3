import java.util.ArrayList;
import java.util.LinkedList;


public class Graph{
    // classe de graphe non orientés permettant de manipuler
    // en même temps des arcs (orientés)
    // pour pouvoir stocker un arbre couvrant, en plus du graphe
    
	protected int V; //nbVertex
	protected ArrayList<LinkedList<Integer>> neighbors;
	protected ArrayList<LinkedList<Edge>> E;
	
	public boolean isVertex(int index) {
	    return( index >= 0 && index < V) ? true : false;
	}

	public int getV(){
		return V;
	}

	public int getE(){
		return E.size();
	}

	public boolean containsEdge(Edge e){
		return (E.get(e.getSource()).contains(e)) ? true : false;
	}
	
	public Graph(int cardinalVertex) {
		V = cardinalVertex;
		E = new ArrayList<>(cardinalVertex);
		neighbors = new ArrayList<>(cardinalVertex);
		init();
	}

	private void init(){
		for(int i=0; i<V; i++){
			E.add(new LinkedList<>());
			neighbors.add(new LinkedList<>());
		}
	}
	
	public void addArc(Edge e) {
		Arc newArc = new Arc(e, true);
	}

	protected void setNeighbors(int from, int to){
		if(!neighbors.get(from).contains(to))
			neighbors.get(from).add(to);
		if(!neighbors.get(to).contains(from))
			neighbors.get(to).add(from);
	}
	
	public void addEdge(int from, int to, int weight) {
		Edge newEdge = new Edge(from, to, weight);

		if(!containsEdge(newEdge)) {
			E.get(from).add(newEdge);
			setNeighbors(from, to);
			setNeighbors(to, from);
		}
	}

	public void removeEdge(Edge e){
		E.get(e.getSource()).remove(e);
	}

	public void affiche(){
		for(int i=0; i<E.size(); i++){
			for(Edge e: E.get(i)){
				System.out.println(e.getSource() + " ---> " + e.getDest() + " poids : " + e.getWeight());
			}
		}
	}
	
}
