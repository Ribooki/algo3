public class Main {

	public static void main(String argv[]) throws InterruptedException {
		Complete test = new Complete(5);
		Tree spanningTree = Algorithms.Aldou_Broder(test);

		System.out.println("GRAPHE");
		test.affiche();
		System.out.println("ARBRE COUVRANT");
		spanningTree.affiche();
	}
}
