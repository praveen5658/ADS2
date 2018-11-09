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
		Color c1,c2;
		for (int i = 0; i< height;i++){
			for (int j = 0; j < width; j++){
				temp = 0;
				temp1 = 0;
				if (((i==0)||(j==0))||((i==(height-1))||(j==(width-1)))){
					ener[i][j] = 1000;
				} else {
					c1 = picture.get(j+1, i);
					c2 = picture.get(j - 1, i);
					temp += Math.pow((c1.getRed() - c2.getRed()), 2);
					temp += Math.pow((c1.getGreen() - c2.getGreen()), 2);
					temp += Math.pow((c1.getBlue() - c2.getBlue()), 2);
					c1 = picture.get(j, i +1);
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
		double mindist = dijkstra1.distTo((height - 1)*width);
		int minindex = (height - 1)*width; 
		for (int i = ((height - 1)*width)+ 1; i < width * height; i ++){
			if (mindist > dijkstra1.distTo(i)){
				mindist = dijkstra1.distTo(i);
				minindex = i;
			}
		}
		System.out.print(minindex + "minindex\n");
		System.out.print(mindist + "mindist\n");
		// for (int i = 0; i < width; i ++){

		// }
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
	// public void relax(int i, int j){

	// }
	public void buildgraph(){
		for (int i = 0; i < height - 1; i ++){
			for (int j = 0; j < width; j++){
				if (j == 0){
					graph.addEdge(new DirectedEdge(((i * width) + j), (((i + 1) * width) + j), ener[i + 1][j]));
					graph.addEdge(new DirectedEdge(((i * width) + j), (((i + 1) * width) + j + 1), ener[i + 1][j + 1]));
				} else if (j == (width - 1)){
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