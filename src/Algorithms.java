import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Algorithms {
    private static Random rand = new Random();
    private static int randomiseInt(int max){
        return rand.nextInt(max);
    }

    private static void CycleFinding(Graph g, int source, int old, int[] visited){
        visited[source]++;
        if(visited[source] == 2) {
            Edge e = new Edge(old, source, 0);
            Edge e2 = new Edge(source, old, 0);
            if (!g.removeEdge(e)) {
                g.removeEdge(e2);
            }
        }
        for (int i = 0; i < g.neighbors.get(source).size(); i++) {
            if (i!=old && visited[i] < 2){
                CycleFinding(g, i, source, visited);
            }
        }
    }

    private static void initBoolTab(boolean[] boolTab){
        for(int i = 0; i < boolTab.length; i++){
            boolTab[i] = false;
        }
    }



    private static void addEdgesTo(PriorityQueue<Edge> PQ, Graph g, int vertex){
        for(Edge e : g.E.get(vertex)){
            PQ.add(e);
        }
    }

    private static void PrimSearch(Graph g, Tree t, PriorityQueue<Edge> PQ, boolean[] isOnTree){
        int current;
        Edge rm;
        while(PQ.size() != 0){
                rm = PQ.poll();
                current = rm.getDest();
                if(!isOnTree[current]){
                    t.addEdge(rm.getSource(), rm.getDest(), rm.getWeight());
                    isOnTree[current] = true;
                    addEdgesTo(PQ, g, current);
                }
        }
    }
    /** 3.1 **/
    public static Tree Prim(Graph g, int starter){
        Tree spanning = new Tree(g.getV());
        boolean[] isOnTree = new boolean[g.getV()];
        PriorityQueue<Edge> PQ = new PriorityQueue<>(2*g.getV(), new boundsComparator());
        isOnTree[starter] = true;
        addEdgesTo(PQ, g, starter);
        PrimSearch(g, spanning, PQ, isOnTree);
        return spanning;
    }

    public static Graph Kruskal(){

        return null;
    }
    /**     ALDON-BRODER    **/
    private static boolean isAllvisited(boolean[] visited){
        for (int i=0; i< visited.length; i++)
            if (visited[i] == false)
                return false;
        return true;
    }

    private static void drunkenWalk(Graph g, Tree t, boolean[] visited, int current){
        int source = current;
        int dest;
        while(!isAllvisited(visited)){
            dest = randomiseInt(g.neighbors.get(source).size());
            t.addEdge(source, g.neighbors.get(source).get(dest), 0);
            source = g.neighbors.get(source).get(dest);
            visited[source] = true;
        }
    }

    public static Tree Aldou_Broder(Graph g){
        Tree spanning = new Tree(g.getV());
        boolean[] visited = new boolean[spanning.getV()];
        initBoolTab(visited);

        int currentVertex = randomiseInt(spanning.getV());
        visited[currentVertex] = true;
        drunkenWalk(g, spanning, visited, currentVertex);

        return spanning;
    }
    /**     WILSON      **/

    private static int currentMaxDeg( Graph g){
        ArrayList<Integer> verticesMaxDeg = g.verticesDegMax();
        int rand = randomiseInt(verticesMaxDeg.size());
        return verticesMaxDeg.get(rand);
    }



    private static void walkOn(Graph g, Graph ways ,int u, boolean[] inTree, ArrayList<Integer> out){
        int[] visited;
        int nextIndex, nextV;
        int start = u;
        while(!inTree[start]){
            nextIndex = randomiseInt(g.neighbors.get(start).size());
            nextV = g.neighbors.get(start).get(nextIndex);
            inTree[start] = true;
            ways.addEdge(start, nextV, 0);
            start= nextV;
        }
        visited = new int[ways.getV()];
        CycleFinding(ways, u, -1, visited);

    }

    private static void initArray(ArrayList<Integer> a, int size, int v){
        for(int i = 0; i<size; i++){
            if(i != v)
                a.add(i);
        }
    }

    public static Graph Wilson(Graph g){
        Graph ways = new Graph(g.getV());
        boolean[] isOnTree = new boolean[g.getV()];
        initBoolTab(isOnTree);
        int v = currentMaxDeg(g), u;
        isOnTree[v] = true;
        ArrayList<Integer> verticesNotInTree = new ArrayList<>(g.getV());
        initArray(verticesNotInTree, g.getV(), v);

        while(!isAllvisited(isOnTree)){
            u = randVertexNotInTree(verticesNotInTree);
            walkOn(g, ways, u, isOnTree, verticesNotInTree);
        }

        return ways;
    }

    private static int randVertexNotInTree(ArrayList<Integer> verticesNotInTree) {
        int vertex = verticesNotInTree.get(randomiseInt(verticesNotInTree.size()));
        return vertex;
    }




}
