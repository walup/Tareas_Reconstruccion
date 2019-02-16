package H2;

import ij.ImagePlus;
import ij.gui.NewImage;
import ij.plugin.ChannelSplitter;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

public class ArrayImageSocket {
	private int[][] pixels;
	private int width;
	private int height;

	public ArrayImageSocket(int[][] pixels) {
		this.pixels = pixels;
		this.height = pixels.length;
		this.width = pixels[0].length;
	}
	
	public ArrayImageSocket(ImagePlus img) {
		this.width = img.getWidth();
		this.height = img.getHeight();
		
		//El arreglo de pixeles
		int[][] pixels = new int[this.height][this.width];
		ChannelSplitter colorSplitter = new ChannelSplitter();
		ImageConverter imConverter = new ImageConverter(img);
		imConverter.convertToGray8();
		
		for(int i = 0;i<this.width;i++) {
			for(int j = 0;j<this.height;j++) {
				pixels[j][i] = img.getPixel(i, j)[0];
			}
		}
		this.pixels = pixels;
	}
	
	
	
	
	public ImagePlus meshToGrayImage() {
		ImagePlus img = NewImage.createImage("untitled", this.width, this.height, 1, 8, 1);
		ImageProcessor proccesor = img.getProcessor();
		for(int i = 0;i<width;i++) {
			for(int j = 0; j<height;j++) {
			proccesor.putPixel(i,j,pixels[j][i]);
		}
	}	
	return img;
}
	
	public int[][] getPixels() {
		return pixels;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	

	
}
