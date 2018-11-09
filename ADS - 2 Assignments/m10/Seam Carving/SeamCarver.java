import java.awt.Color;
public class SeamCarver {
	// create a seam carver object based on the given picture
	private int width;
	private int height;
	private Picture picture;
	private double[][] ener;
	private int edgeto[];
	private int distto[];
	private EdgeWeightedDigraph graph;
	public SeamCarver(Picture picture1) {
		width = picture1.width();
		height = picture1.height();
		picture = picture1;
		graph = new EdgeWeightedDigraph(width * height);
		ener = new double[height][width];
		// edgeto = new int[height * width];
		// distto = new int[height * width];
		// for (int i = 0; i < (height * width); i++){
		// 	distto[i] = Integer.POSITIVE_INFINITY;
		// }
		// distto[0] = 0;
		int temp = 0;
		int temp1 = 0;
		Color c1, c2;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				temp = 0;
				temp1 = 0;
				if (((i == 0) || (j == 0)) || ((i == (height - 1)) || (j == (width - 1)))) {
					ener[i][j] = 1000;
				} else {
					c1 = picture.get(j + 1, i);
					c2 = picture.get(j - 1, i);
					temp += Math.pow((c1.getRed() - c2.getRed()), 2);
					temp += Math.pow((c1.getGreen() - c2.getGreen()), 2);
					temp += Math.pow((c1.getBlue() - c2.getBlue()), 2);
					c1 = picture.get(j, i + 1);
					c2 = picture.get(j, i - 1);
					temp1 += Math.pow((c1.getRed() - c2.getRed()), 2);
					temp1 += Math.pow((c1.getGreen() - c2.getGreen()), 2);
					temp1 += Math.pow((c1.getBlue() - c2.getBlue()), 2);
					ener[i][j] = Math.sqrt(temp + temp1);
				}
			}
		}
		// for (int i = 0; i < height; i++){
		// 	for (int j = 0; j < width; j++){

		// 	}
		// }
	}
	// current picture
	public Picture picture() {
		return picture;
	}
	// width of current picture
	public int width() {
		return width;
	}

	// height of current picture
	public int height() {
		return height;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		return ener[y][x];
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		buildgraph();
		DijkstraSP dijkstra1 = new DijkstraSP(graph, 0);
		double mindist1 = dijkstra1.distTo((height - 1) * width);
		int minindex1 = (height - 1) * width;
		for (int i = ((height - 1) * width) + 1; i < width * height; i ++) {
			if (mindist1 > dijkstra1.distTo(i)) {
				mindist1 = dijkstra1.distTo(i);
				minindex1 = i;
			}
		}
		// System.out.print(minindex + "minindex\n");
		// System.out.print(mindist + "mindist\n");
		double mindist2 = 0.0;
		int minindex2 = 0;
		DijkstraSP dijkstra2;
		for (int i = 1; i < width; i ++) {
			dijkstra2 = new DijkstraSP(graph, i);
			mindist2 = dijkstra2.distTo((height - 1) * width);
			minindex2 = (height - 1) * width;
			for (int j = ((height - 1) * width) + 1; j < width * height; j ++) {
				if (mindist2 > dijkstra2.distTo(j)) {
					mindist2 = dijkstra2.distTo(j);
					minindex2 = j;
				}
			}
			if (mindist1 > mindist2){
				dijkstra1 = dijkstra2;
				mindist1 = mindist2;
				minindex1 = minindex2;
			}
		}
		// System.out.print(minindex1 + "minindex1\n");
		// System.out.print(mindist1 + "mindist1\n");
		int[] arr = new int[height];
		int d = height - 2;
		for (DirectedEdge f : dijkstra1.pathTo(minindex1)){
			arr[d] = (f.from())%width;
			d--;
		}
		arr[height - 1] = minindex1 % width;
		return arr;
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
	// public void relax(int i, int j){

	// }
	public void buildgraph() {
		for (int i = 0; i < height - 1; i ++) {
			for (int j = 0; j < width; j++) {
				if (j == 0) {
					graph.addEdge(new DirectedEdge(((i * width) + j), (((i + 1) * width) + j), ener[i + 1][j]));
					graph.addEdge(new DirectedEdge(((i * width) + j), (((i + 1) * width) + j + 1), ener[i + 1][j + 1]));
				} else if (j == (width - 1)) {
					graph.addEdge(new DirectedEdge(((i * width) + j), (((i + 1) * width) + j), ener[i + 1][j]));
					graph.addEdge(new DirectedEdge(((i * width) + j), (((i + 1) * width) + j - 1), ener[i + 1][j - 1]));
				} else {
					graph.addEdge(new DirectedEdge(((i * width) + j), (((i + 1) * width) + j - 1), ener[i + 1][j - 1]));
					graph.addEdge(new DirectedEdge(((i * width) + j), (((i + 1) * width) + j), ener[i + 1][j]));
					graph.addEdge(new DirectedEdge(((i * width) + j), (((i + 1) * width) + j + 1), ener[i + 1][j + 1]));
				}
			}
		}
		System.out.println(graph);
	}
}