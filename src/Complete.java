public class Complete extends Graph{

	public Complete(int cardinalVertex) {
		super(cardinalVertex);
		addAllEdges(cardinalVertex);
	}

	private void addAllEdges(int size) {
		for(int i = 0; i < size; i++)
			for (int j = i+1; j < size; j++) {
				this.addEdge(i, j, 0);
				setNeighbors(i, j);
				setNeighbors(j, i);
			}

	}

	private boolean isMaxDegree(){
		return (E.size() == (V*(V-1))/2) ? true : false; //Dans un graphe complet E = (n(n-1))/2 n étant le nombres de sommets du graphes
	}

	@Override
	public void addEdge(int from, int to, double weight) {
		if (!isMaxDegree()) {
			E.get(from).add(new Edge(from, to, weight));
		}
	}
}
