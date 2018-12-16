public class Main {

	public static void main(String argv[]) throws InterruptedException {
		Complete test = new Complete(6);
		test.RandomBounds();
		Grid g = new Grid(10, 10);
		Lollipop lp = new Lollipop(100);
		Tree Aldou;
		Tree Wilson;
		Tree Prim;
		Graph Kruskal;
		long[] times = new long[12];
		long[] endtimes = new long[12];
		double[] result = new double[12];
		double seconds;

		/** CRAPHE COMPLET **/


		times[0] = System.nanoTime();
		Aldou = Algorithms.Aldou_Broder(test);
		endtimes[0] = System.nanoTime();
		seconds = ((endtimes[0] - times[0]) * Math.pow(10, -9));
		result[0] = seconds;


		times[1] = System.nanoTime();
		Wilson = Algorithms.Wilson(test);
		endtimes[1] = System.nanoTime();
		seconds = ((endtimes[1] - times[1]) * Math.pow(10, -9));
		result[1] =seconds;

		times[2] = System.nanoTime();
		Prim = Algorithms.Prim(test, 0);
		endtimes[2] = System.nanoTime();
		seconds = ((endtimes[2] - times[2]) * Math.pow(10, -9));
		result[2] =seconds;


		times[3] = System.nanoTime();
		Kruskal = Algorithms.Kruskal(test);
		endtimes[3] = System.nanoTime();
		seconds = ((endtimes[3] - times[3]) * Math.pow(10, -9));
		result[3] =seconds;

		/*System.out.println("Graphe complet : ");
		System.out.println("ALDOU-BRODER");
		Aldou.affiche();
		System.out.println("WILSON");
		Wilson.affiche();
		System.out.println("PRIM");
		Prim.affiche();
		System.out.println("Kruskal");
		Kruskal.affiche();*/

		/** GRID **/

		times[4] = System.nanoTime();
		Aldou = Algorithms.Aldou_Broder(g.graph);
		endtimes[4] = System.nanoTime();
		seconds = ((endtimes[4] - times[4]) * Math.pow(10, -9));
		result[4] =seconds;

		times[5] = System.nanoTime();
		Wilson = Algorithms.Wilson(g.graph);
		endtimes[5] = System.nanoTime();
		seconds = ((endtimes[5] - times[5]) * Math.pow(10, -9));
		result[5] =seconds;

		times[6] = System.nanoTime();
		Prim = Algorithms.Prim(g.graph, 0);
		endtimes[6] = System.nanoTime();
		seconds = ((endtimes[6] - times[6]) * Math.pow(10, -9));
		result[6] =seconds;


		times[7] = System.nanoTime();
		Kruskal = Algorithms.Kruskal(g.graph);
		endtimes[7] = System.nanoTime();
		seconds = ((endtimes[7] - times[7]) * Math.pow(10, -9));
		result[7] =seconds;

		/*System.out.println("Grid : ");
		System.out.println("ALDOU-BRODER");
		Aldou.affiche();
		System.out.println("WILSON");
		Wilson.affiche();
		System.out.println("PRIM");
		Prim.affiche();
		System.out.println("Kruskal");
		Kruskal.affiche();*/

		/** LOLIPOP **/

		times[8] = System.nanoTime();
		Aldou = Algorithms.Aldou_Broder(lp.graph);
		endtimes[8] = System.nanoTime();
		seconds = ((endtimes[8] - times[8]) * Math.pow(10, -9));
		result[8] =seconds;

		times[9] = System.nanoTime();
		Wilson = Algorithms.Wilson(lp.graph);
		endtimes[9] = System.nanoTime();
		seconds = ((endtimes[9] - times[9]) * Math.pow(10, -9));
		result[9] =seconds;

		times[10] = System.nanoTime();
		Prim = Algorithms.Prim(lp.graph, 0);
		endtimes[10] = System.nanoTime();
		seconds = ((endtimes[10] - times[10]) * Math.pow(10, -9));
		result[10] =seconds;


		times[11] = System.nanoTime();
		Kruskal = Algorithms.Kruskal(lp.graph);
		endtimes[11] = System.nanoTime();
		seconds = ((endtimes[11] - times[11]) * Math.pow(10, -9));
		result[11] =seconds;

		/*System.out.println("Grid : ");
		System.out.println("ALDOU-BRODER");
		Aldou.affiche();
		System.out.println("WILSON");
		Wilson.affiche();
		System.out.println("PRIM");
		Prim.affiche();
		System.out.println("Kruskal");
		Kruskal.affiche();*/



		/** Affichage de l'execution **/

		System.out.println("\u001B[36m" + "Graph Complet :\n"
				+"Aldou : " +result[0] + "s\n"
				+"Wilson : " + result[1] + "s\n"
				+"Prim : " + result[2] + "s\n"
				+"Kruskal : " + result[3] + "s\n"
		);

		System.out.println("\u001B[35m" +"Grid :\n" +
				"Aldou : " + result[4] + "s\n"
				+"Wilson : " + result[5] + "s\n"
				+"Prim : " + result[6] + "s\n"
				+"Kruskal : " + result[7] + "s\n"
		);

		System.out.println("\u001B[34m" +"Lollipop :\n" +
				"Aldou : " + result[8] + "s\n"
				+"Wilson : " + result[9] + "s\n"
				+"Prim : " + result[10] + "s\n"
				+"Kruskal : " + result[11] + "s\n"
		);

	}
}
