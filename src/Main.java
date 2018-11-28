public class Main {

	public static void main(String argv[]) throws InterruptedException {
		Complete test = new Complete(4);
		test.RandomBounds();
		Tree Aldou = Algorithms.Aldou_Broder(test);
		Tree Wilson = Algorithms.Wilson(test, 3);
		Tree Prim = Algorithms.Prim(test, 0);

		System.out.println("GRAPHE");
		test.affiche();
		System.out.println("ALDOU-BRODER");
		Aldou.affiche();
		System.out.println("WILSON");
		Wilson.affiche();
		System.out.println("PRIM");
		Prim.affiche();

	}
}
