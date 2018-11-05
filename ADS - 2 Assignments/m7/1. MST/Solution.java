import java.util.Scanner;
class Solution {
	private Solution() {

	}
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		String[] input;
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices);
		for (int i = 0; i < edges; i++) {
			input = scan.nextLine().split(" ");
			ewg.addEdge(new Edge(Integer.parseInt(
			                         input[0]), Integer.parseInt(
			                         input[1]), Double.parseDouble(
			                         input[2])));
		}
		KruskalMST kmst = new KruskalMST(ewg);
		System.out.format("%.5f\n", kmst.weight());
	}
}