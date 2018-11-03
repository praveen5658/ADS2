import java.util.Scanner;
// class PageRank {

// }

// class WebSearch {

// }


public class Solution {
	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices
		Scanner scan = new Scanner(System.in);
		int verticesnumber = Integer.parseInt(scan.nextLine());
		String[] input;
		Digraph digraph = new Digraph(verticesnumber);
		// iterate count of vertices times
		for (int i = 0; i < verticesnumber; i++) {
			input = scan.nextLine().split(" ");
			if (input.length >= 2) {
				for (int j = 1; j < input.length; j++) {
					digraph.addEdge(Integer.parseInt(input[i]), Integer.parseInt(input[j]));
				}
			}
		}
		System.out.println(digraph);
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
