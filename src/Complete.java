
public class Complete extends Graph{

	public Complete(int cardinalVertex) {
		super(cardinalVertex);
		addAllEdges(cardinalVertex);

	}

	private void addAllEdges(int order) {
		for(int i = 0; i < order; i++)
			for (int j = i+1; j < order; j++)
				this.addEdge(i,j,0);
	}

	private boolean isMaxDegree(){
		return (E == (V*(V-1))/2) ? true : false;
	}

	@Override
	public void addEdge(int from, int to, int weight) {
		if (!isMaxDegree()) {
			Edge newEdge = new Edge(from, to, weight);
			adjacency.get(from).add(newEdge);
			E++;
		}
	}
}
