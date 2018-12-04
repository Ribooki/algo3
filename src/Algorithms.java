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

    private static void drunkenWalk(Graph g, Tree t, boolean[] visited, int current, int w){
        int source = current;
        int dest;
        while(!isAllvisited(visited)){
            dest = randomiseInt(g.neighbors.get(source).size());
            t.addEdge(source, g.neighbors.get(source).get(dest), 0);
            source = g.neighbors.get(source).get(dest);
            visited[source] = true;
            if(source == w)
                break;
        }
    }


    public static Tree Wilson(Graph g, int w){
        Tree spanning = new Tree(g.getV());
        int current;
        boolean[] visited = new boolean[spanning.getV()];
        initBoolTab(visited);

        current = currentMaxDeg(g);
        while(current == w){
            current = currentMaxDeg(g);
        }
        visited[current] = true;
        drunkenWalk(g, spanning, visited, current, w);

        return spanning;
    }
}
