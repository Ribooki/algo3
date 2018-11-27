
public class Edge implements Comparable<Edge> {
	private int source;
	private int dest;
	private int weight;
	
	public Edge(int source, int dest, int weight) {
		this.source = source;
		this.dest = dest;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge e) {
		if (this.weight == e.weight) return 0;
		if (this.weight < e.weight) return -1;
		return 1;
	}
	
	public int oppositeExtremity(int vertex) {
		return (dest == vertex ? source : dest);
	}
	
	public int getSource() {
		return this.source;
	}
	
	public int getDest() {
		return this.dest;
	}

	public int getWeight(){
		return this.weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
