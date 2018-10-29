import java.util.Scanner;
interface Graph {
	public int V();
	public int E();
	public void addEdge(int v, int w);
	public Iterable<Integer> adj(int v);
	public boolean hasEdge(int v, int w);
}
class AdjacencyList implements Graph {
	Bag<Integer>[] bags;
	private int vertexval;
	private int edgenum;
	AdjacencyList(int vertex) {
		this.vertexval = vertex;
		bags = (Bag<Integer>[]) new Bag[vertex];
		for (int l = 0; l < vertex; l++) {
			bags[l] = new Bag();
		}
		this.edgenum = 0;
	}
	public int V() {
		return this.vertexval;
	}
	public int E() {
		return this.edgenum;
	}
	public void addEdge(int v, int w) {
		if (v == w || hasEdge(v, w)) {
			return;
		}
		bags[v].add(w);
		bags[w].add(v);
		edgenum++;
	}
	public boolean hasEdge(int v, int w) {
		for (Integer eachval : bags[v]) {
			if (eachval == w) {
				return true;
			}
		}
		return false;
	}
	public Iterable<Integer> adj(int v) {
		Queue<Integer> queue = new Queue<>();
		for (Integer eachval : bags[v]) {
			queue.enqueue(eachval);
		}
		return queue;
	}
	public String toString() {
		if (edgenum == 0) {
			System.out.println(vertexval + " vertices, " + edgenum + " edges");
			System.out.println("No edges");
			return null;
		}
		System.out.println(vertexval + " vertices, " + edgenum + " edges");
		return null;
	}
}
class AdjacencyMatrix implements Graph {
	private int edgenum;
	private int[][] adjmatrix;
	private int vertexval;
	AdjacencyMatrix(int vertex) {
		this.vertexval = vertex;
		adjmatrix = new int[vertex][vertex];
		this.edgenum = 0;
	}
	public int V() {
		return this.vertexval;
	}
	public int E() {
		return this.edgenum;
	}
	public void addEdge(int v, int w) {
		if (v == w || hasEdge(v, w)) {
			return;
		}
		adjmatrix[v][w] = 1;
		adjmatrix[w][v] = 1;
		edgenum++;
	}
	public boolean hasEdge(int v, int w) {
		return adjmatrix[v][w] == 1;
	}
	public Iterable<Integer> adj(int v) {
		return null;
	}
	public String toString() {
		if (edgenum == 0) {
			System.out.println(vertexval + " vertices, " + edgenum + " edges");
			System.out.println("No edges");
			return null;
		}
		System.out.println(vertexval + " vertices, " + edgenum + " edges");
		for (int i = 0; i < vertexval; i++) {
			String str = "";
			for (int j = 0; j < vertexval; j++) {
				str = str + adjmatrix[i][j] + " ";
			}
			System.out.println(str);
		}
		return null;
	}
}
class Solution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String lineone = scan.nextLine();
		int vertexnum = Integer.parseInt(scan.nextLine());
		int edgenum = Integer.parseInt(scan.nextLine());
		String[] placenames = scan.nextLine().split(",");
		SeparateChainingHashST<Integer, String> schsobj = new SeparateChainingHashST<>();
		int count = 0;
		for (String eachname : placenames) {
			schsobj.put(count, eachname);
			count++;
		}
		switch (lineone) {
		case "List":
			AdjacencyList listobj = new AdjacencyList(vertexnum);
			for (int k = 0; k < edgenum; k++) {
				String[] edges = scan.nextLine().split(" ");
				listobj.addEdge(Integer.parseInt(edges[0]), Integer.parseInt(edges[1]));
			}
			listobj.toString();

			for (int j = 0; j < vertexnum; j++) {
				String str = "";
				if (listobj.E() == 0) {
					break;
				}
				str = str + schsobj.get(j) + ": ";
				for (Integer each : listobj.adj(j)) {
					str = str + schsobj.get(each) + " ";
				}
				System.out.println(str);
			}
			break;
		case "Matrix":
			AdjacencyMatrix matrixobj = new AdjacencyMatrix(vertexnum);
			for (int i = 0; i < edgenum; i++) {
				String[] edges = scan.nextLine().split(" ");
				matrixobj.addEdge(Integer.parseInt(edges[0]), Integer.parseInt(edges[1]));
			}
			matrixobj.toString();
			break;
		}
	}
}