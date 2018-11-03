import java.util.Scanner;
import java.util.Arrays;
class PageRank {
	private Digraph digraph;
	private int verticesnumber;
	private double[] ranks;
	private double[] finalranks;
	private double temp;
	protected PageRank(Digraph d) {
		digraph = d;
		verticesnumber = digraph.V();
		ranks = new double[verticesnumber];
		finalranks = new double[verticesnumber];
		for (int i = 0; i < verticesnumber; i++) {
			ranks[i] = (1 / (double)(verticesnumber));
			// System.out.print(i + " : "+ ranks[i]+"\n");
		}
		Digraph revdigraph = digraph.reverse();
		System.out.println();
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < verticesnumber; j++) {
				// ranks[j] = ((ranks[j])/(digraph.outdegree(j)));
				temp = 0.0;
				for (int k : revdigraph.adj(j)) {
					// System.out.println(k);
					temp += ((ranks[k]) / ((double)(digraph.outdegree(k))));
					// System.out.println(j);
					// System.out.println(temp);
				}
				finalranks[j] = temp;
				// System.out.println(finalranks[j]);
				// System.out.println("one sublist is done");
			}
			if (Arrays.equals(ranks,finalranks)) {
				break;
			} else {
				ranks = finalranks.clone();
			}
			// System.out.println("iteration done");
		}
		for (int i = 0; i < verticesnumber; i++) {
			System.out.print(i + " : " + finalranks[i] + "\n");
		}
	}
}

// class WebSearch {

// }


public class Solution {
	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices
		Scanner scan = new Scanner(System.in);
		int verticesnumber = Integer.parseInt(scan.nextLine());
		String[] input;
		Digraph digraph = new Digraph(verticesnumber);
		Digraph digraphextra = new Digraph(verticesnumber);
		// iterate count of vertices times
		for (int i = 0; i < verticesnumber; i++) {
			input = scan.nextLine().split(" ");
			if (input.length >= 2) {
				for (int j = 1; j < input.length; j++) {
					digraph.addEdge(Integer.parseInt(input[0]), Integer.parseInt(input[j]));
					digraphextra.addEdge(Integer.parseInt(input[0]), Integer.parseInt(input[j]));
				}
			} else {
				for (int h = 0; (h < verticesnumber);h++){
					if (h == i){
						continue;
					}
					else {
						digraphextra.addEdge(Integer.parseInt(input[0]), h);
					}
				}
			}
		}
		System.out.println(digraph);
		System.out.println();
		PageRank pr = new PageRank(digraphextra);
		// System.out.println(digraph);
		// to read the adjacency list from std input
		// and build the graph


		// Create page rank object and pass the graph object to the constructor

		// print the page rank object

		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
