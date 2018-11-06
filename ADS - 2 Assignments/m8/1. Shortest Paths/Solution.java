import java.util.Scanner;
import java.util.ArrayList;
final class Solution {
	private Solution() {
		//unused.
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<String> stationlist = new ArrayList<>();
		String[] counters = scan.nextLine().split(" ");
		String[] stationnames = scan.nextLine().split(" ");
		for (int j = 0; j < stationnames.length; j++) {
			stationlist.add(stationnames[j]);
		}
		EdgeWeightedDigraph edgegraph = new EdgeWeightedDigraph(Integer.parseInt(counters[0]));
		for (int i = 0; i < Integer.parseInt(counters[1]); i++) {
			String[] linkdata = scan.nextLine().split(" ");
			DirectedEdge edgeobj = new DirectedEdge(stationlist.indexOf(linkdata[0]), stationlist.indexOf(linkdata[1]), Double.parseDouble(linkdata[2]));
			DirectedEdge edgeobj1 = new DirectedEdge(stationlist.indexOf(linkdata[1]), stationlist.indexOf(linkdata[0]), Double.parseDouble(linkdata[2]));
			edgegraph.addEdge(edgeobj);
			edgegraph.addEdge(edgeobj1);
		}
		int querynum = Integer.parseInt(scan.nextLine());
		for (int k = 0; k < querynum; k++) {
			String[] querydetails = scan.nextLine().split(" ");
			DijkstraSP dijkobj = new DijkstraSP(edgegraph, stationlist.indexOf(querydetails[0]));
			System.out.println((long)dijkobj.distTo(stationlist.indexOf(querydetails[1])));
		}
	}
}