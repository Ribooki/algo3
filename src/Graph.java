import java.util.ArrayList;
import java.util.LinkedList;


public class Graph{
    // classe de graphe non orientés permettant de manipuler
    // en même temps des arcs (orientés)
    // pour pouvoir stocker un arbre couvrant, en plus du graphe
    
	protected int V; //nbVertex
	protected ArrayList<LinkedList<Integer>> neighbors;
	protected int[] degV;
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

	public int degVMax(){
		int max = 0;
		for(int i=0; i<degV.length; i++){
			if (max <= degV[i])
				max = degV[i];
		}
		return max;
	}

	public void RandomBounds(){
		for(int i = 0; i<E.size(); i++){
			for(Edge e : E.get(i))
				e.setWeight(Math.random());
		}
	}

	public ArrayList<Integer> verticesDegMax(){
		ArrayList<Integer> degVs = new ArrayList<>();
		int degMax = degVMax();
		for(int i=0; i<degV.length; i++){
			if(degV[i] == degMax)
				degVs.add(degV[i]);
		}
		return degVs;
	}
	
	public Graph(int cardinalVertex) {
		V = cardinalVertex;
		E = new ArrayList<>(cardinalVertex);
		neighbors = new ArrayList<>(cardinalVertex);
		degV = new int[cardinalVertex];
		init();
	}

	private void init(){
		for(int i=0; i<V; i++){
			E.add(new LinkedList<>());
			neighbors.add(new LinkedList<>());
			degV[i] = 0;
		}
	}

	protected void setNeighbors(int from, int to){
		if(!neighbors.get(from).contains(to))
			neighbors.get(from).add(to);
		if(!neighbors.get(to).contains(from))
			neighbors.get(to).add(from);
	}
	
	public void addEdge(int from, int to, double weight) {
		Edge newEdge = new Edge(from, to, weight);

		if(!containsEdge(newEdge)) {
			E.get(from).add(newEdge);
			degV[from]++;
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
