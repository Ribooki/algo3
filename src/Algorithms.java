import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Algorithms {
    private static Random rand = new Random();
    private static int randomiseInt(int max){
        return rand.nextInt(max);
    }

    private static boolean CycleFinding(Graph g, int source, int current, int old, boolean[] visited){
        visited[current] = true;
        for (int i=0; i<g.neighbors.get(current).size(); i++){
            if (g.neighbors.get(current).get(i)!= old && g.neighbors.get(current).get(i) == source){
                    return true;
            }
            else if (g.neighbors.get(current).get(i) != old && !visited[g.neighbors.get(current).get(i)]) {
                old = current;
                current = g.neighbors.get(current).get(i);
                CycleFinding(g, source, current, old, visited);
            }
        }
        return false;
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

    public static Graph Kruskal(Graph g){
        Graph spanning = new Tree(g.getV());
        Edge e;
        PriorityQueue<Edge> PQ = new PriorityQueue<>(2*g.getV(), new boundsComparator());
        for (int i = 0; i<g.E.size(); i++){
            for (Edge a : g.E.get(i)){
                PQ.add(a);
            }
        }
        while(PQ.size() != 0){
            e = PQ.poll();
            spanning.addEdge(e.getSource(), e.getDest(), e.getWeight());
            if (CycleFinding(spanning, e.getSource(), e.getSource(), -1, new boolean[spanning.getV()]))
                spanning.removeEdge(e);
        }
        return spanning;
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



    private static void walkOn(Graph g, Tree wilson ,int u, boolean[] inTree, ArrayList<Integer> out){
        Tree ways = new Tree(g.getV());
        int nextIndex, nextV, old = -1;
        int start = u;
        while(!inTree[start]){
            nextIndex = randomiseInt(g.neighbors.get(start).size());
            while(g.neighbors.get(start).size() >1 && g.neighbors.get(start).get(nextIndex) == old )
                nextIndex = randomiseInt(g.neighbors.get(start).size());
            nextV = g.neighbors.get(start).get(nextIndex);
            ways.addEdge(start, nextV, 0);
            old = start;
            start= nextV;
        }
        for (int i = 0; i < ways.E.size(); i++) {
            for (Edge e : ways.E.get(i)){
                inTree[e.getSource()] = true;
                out.remove((Integer) e.getSource());
                if (!wilson.isLock(e.getSource()) && !wilson.isLock(e.getDest()) || wilson.isLock(e.getSource()) && !wilson.isLock(e.getDest()))
                    wilson.addEdge(e.getSource(), e.getDest(), 0);
                else if(!wilson.isLock(e.getSource()) && wilson.isLock(e.getDest()))
                    wilson.addEdge(e.getDest(), e.getSource(), 0);
                else if (wilson.isLock(e.getSource()) && wilson.isLock(e.getDest())) {
                    wilson.unLock(e.getDest());
                    wilson.addEdge(e.getSource(), e.getDest(), 0);
                }
            }
        }
    }

    private static void initArray(ArrayList<Integer> a, int size, int v){
        for(int i = 0; i<size; i++){
            if(i != v)
                a.add(i);
        }
    }

    public static Tree Wilson(Graph g){
        Tree wilson = new Tree(g.getV());
        boolean[] isOnTree = new boolean[g.getV()];
        initBoolTab(isOnTree);
        int v = currentMaxDeg(g), u;
        isOnTree[v] = true;
        ArrayList<Integer> verticesNotInTree = new ArrayList<>(g.getV());
        initArray(verticesNotInTree, g.getV(), v);

        while(!isAllvisited(isOnTree)){
            u = randVertexNotInTree(verticesNotInTree);
            walkOn(g, wilson, u, isOnTree, verticesNotInTree);
        }

        return wilson;
    }

    private static int randVertexNotInTree(ArrayList<Integer> verticesNotInTree) {
        int vertex = verticesNotInTree.get(randomiseInt(verticesNotInTree.size()));
        return vertex;
    }




}
