import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Algorithms {
    private static Random rand = new Random();
    private static int randomiseInt(int max){
        return rand.nextInt(max);
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

    public static void Kruskal(){

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

    private static void drunkenWalk(Graph g, Tree t, boolean[] visited, ArrayList<Integer> a1, ArrayList<Integer> a2, int u, int w){
    }


    public static Tree Wilson(Graph g){
        Tree spanning = new Tree(g.getV());
        int w,u;
        boolean[] visited = new boolean[spanning.getV()];
        ArrayList<Integer> verticesInTree = new ArrayList<>(spanning.getV());
        ArrayList<Integer> verticesNotInTree = new ArrayList<>(g.getV());
        initBoolTab(visited);

        w = currentMaxDeg(g);
        visited[w] = true;
        verticesInTree.add(w);

        for(int i=0; i<g.getV(); i++){
            if(i != w){
                verticesNotInTree.add(i);
            }
        }

        while(!isAllvisited(visited)){
            u = randVertexNotInTree(verticesNotInTree);
            drunkenWalk(g, spanning, visited, verticesInTree, verticesNotInTree, u, w);
            w = randVertexInTree(verticesInTree);
        }

        return spanning;
    }

    private static int randVertexNotInTree(ArrayList<Integer> verticesNotInTree) {
        int vertex = verticesNotInTree.get(randomiseInt(verticesNotInTree.size()));
        return vertex;
    }

    private static int randVertexInTree(ArrayList<Integer> verticesInTree) {
        int vertex = verticesInTree.get(randomiseInt(verticesInTree.size()));
        return vertex;
    }

}
