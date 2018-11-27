public class Tree extends Graph {

    private boolean[] lock;

    public Tree(int cardinalVertex) {
        super(cardinalVertex);
        lock = new boolean[V];
        initLock();
    }

    private void initLock(){;
        for (int i=0; i<lock.length; i++){
            lock[i] = false;
        }
    }

    @Override
    public void addEdge(int from, int to, int weight) {
        Edge newEdge = new Edge(from, to, weight);
        if(!containsEdge(newEdge) && !lock[to]) {
            E.get(from).add(newEdge);
            if (!lock[from])
                lock[from] = true;
            lock[to] = true;
            setNeighbors(from, to);
            setNeighbors(to, from);
        }
    }

    public boolean isSpanningTree(){
        return false;
    }
}
