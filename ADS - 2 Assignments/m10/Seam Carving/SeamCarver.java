import java.awt.Color;
public class SeamCarver {
	// create a seam carver object based on the given picture
	private int width;
	private int height;
	private Picture picture;
	private double[][] ener;
	private int edgeto[];
	private int distto[];
	public SeamCarver(Picture picture1) {
		width = picture1.width();
		height = picture1.height();
		picture = picture1;
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
		int[] arr = new int[width];
		double min = Double.POSITIVE_INFINITY;
		double temp = 0.0;
		for (int j = 1; j < height - 1; j++){
			if (min > ener[1][j]){
				min = ener[1][j];
				arr[1] = j;
				arr[0] = j;
			}
		}
		for (int i = 2; i < width - 1; i++){
			temp = 0.0;
			temp = min + ener[i][arr[i - 1] - 1];
			arr[i] = arr[i - 1] - 1;
			if (temp < (min + ener[i][arr[i - 1]])){
				temp = (min + ener[i][arr[i - 1]]);
				arr[i] = arr[i - 1];
			}
			if (temp < (min + ener[i][arr[i - 1] + 1])){
				temp =- (min + ener[i][arr[i - 1] + 1]);
				arr[i] = arr[i - 1] + 1;
			}
			min = temp;
		}
		arr[width - 1] = arr[width - 2];
		return arr;
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
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
}