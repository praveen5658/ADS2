import java.awt.Color;
public class SeamCarver {
	// create a seam carver object based on the given picture
	private int width;
	private int height;
	private Picture picture;
	private double[][] ener;
	public SeamCarver(Picture picture1) {
		width = picture1.width();
		height = picture1.height();
		picture = picture1;
		ener = new double[height][width];
		int temp = 0;
		int temp1 = 0;
		// System.out.println("entered");
		Color c1,c2;
		for (int i = 0; i< height;i++){
			for (int j = 0; j < width; j++){
				temp = 0;
				temp1 = 0;
				if (((i==0)||(j==0))||((i==(height-1))||(j==(width-1)))){
					// System.out.println("Border");
					ener[i][j] = 1000;
				} else {
					// System.out.println("Inner");
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
					// System.out.println("Inner completed");
				}
			}
		}
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
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}