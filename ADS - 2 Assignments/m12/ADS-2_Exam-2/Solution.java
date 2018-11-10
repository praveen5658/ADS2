import java.util.Scanner;
public final class Solution {
	private Solution() {

	}
	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		String[] input;
		int from = 0;
		int to = 0;
		double weight = 0.0;
		EdgeWeightedGraph graph = new EdgeWeightedGraph(vertices);
		for (int i = 0; i < edges; i++) {
			input = scan.nextLine().split(" ");
			from = Integer.parseInt(input[0]);
			to = Integer.parseInt(input[1]);
			weight = Double.parseDouble(input[2]);
			graph.addEdge(new Edge(from, to, weight));
		}
		String caseToGo = scan.nextLine();
		DijkstraUndirectedSP dijkstra;
		switch (caseToGo) {
		case "Graph":
			System.out.println(vertices + " vertices " + edges + " edges");
			System.out.println(graph);
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			input = scan.nextLine().split(" ");
			from = Integer.parseInt(input[0]);
			to = Integer.parseInt(input[1]);
			dijkstra = new DijkstraUndirectedSP(graph, from);
			if (dijkstra.hasPathTo(to)) {
				System.out.println(dijkstra.distTo(to));
			} else {
				System.out.println("No Path Found.");
			}
			break;

		case "ViaPaths":
			// Handle the case of ViaPaths, where three integers are given.
			// First is the source and second is the via is the one where path should pass throuh.
			// third is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
			input = scan.nextLine().split(" ");
			from = Integer.parseInt(input[0]);
			to = Integer.parseInt(input[2]);
			int via = Integer.parseInt(input[1]);
			int cou = 0;
			DijkstraAllPairsSP dijkstraallpairs = new DijkstraAllPairsSP(graph);
			if (dijkstraallpairs.hasPath(from, to)) {
				System.out.println(dijkstraallpairs.dist(from, via) + dijkstraallpairs.dist(via, to));
				// for (Edge e : dijkstraallpairs.path(from, via)){
				// 	// System.out.println(e);
				// 	// System.out.print(e.either() + " ");
				// }
				// for (Edge e : dijkstraallpairs.path(via, to)){
				// 	System.out.println(e);
				// 	System.out.print(e.either() + " ");
				// }
				// System.out.println();
				Stack<Integer> pa = dijkstraallpairs.path(from, via);
				while (!(pa.isEmpty())) {
					System.out.print(pa.pop() + " ");
				}
				Stack<Integer> pa2 = dijkstraallpairs.path(via, to);
				pa2.pop();
				while (!(pa2.isEmpty())) {
					System.out.println(pa2.pop() + " ");
				}
			} else {
				System.out.println("No Path Found.");
			}
			break;

		default:
			break;
		}

	}
}