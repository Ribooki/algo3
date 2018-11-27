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
    /** 3.1 **/
    public static void Prim(){

    }

    public static void Krubal(){

    }

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
}
