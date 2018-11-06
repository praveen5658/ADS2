/**
 * { item_description }.
 */
import java.util.Scanner;
/**
 * { item_description }.
 */
import java.util.ArrayList;
/**
 * { item_description }.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unused.
    }
    /**
     * { function_description }.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> stationlist = new ArrayList<>();
        String[] counters = scan.nextLine().split(" ");
        String[] stationnames = scan.nextLine().split(" ");
        for (int j = 0; j < stationnames.length; j++) {
            stationlist.add(stationnames[j]);
        }
        EdgeWeightedDigraph edgegraph = new EdgeWeightedDigraph(
            Integer.parseInt(counters[0]));
        for (int i = 0; i < Integer.parseInt(counters[1]); i++) {
            String[] linkdata = scan.nextLine().split(" ");
            DirectedEdge edgeobj = new DirectedEdge(
                stationlist.indexOf(linkdata[0]), stationlist.indexOf(
                    linkdata[1]), Double.parseDouble(linkdata[2]));
            DirectedEdge edgeobj1 = new DirectedEdge(
                stationlist.indexOf(linkdata[1]), stationlist.indexOf(
                    linkdata[0]), Double.parseDouble(linkdata[2]));
            edgegraph.addEdge(edgeobj);
            edgegraph.addEdge(edgeobj1);
        }
        int querynum = Integer.parseInt(scan.nextLine());
        for (int k = 0; k < querynum; k++) {
            String[] querydetails = scan.nextLine().split(" ");
            DijkstraSP dijkobj = new DijkstraSP(
                edgegraph, stationlist.indexOf(querydetails[0]));
            System.out.println((long) dijkobj.distTo(
                                   stationlist.indexOf(querydetails[1])));
        }
    }
}
