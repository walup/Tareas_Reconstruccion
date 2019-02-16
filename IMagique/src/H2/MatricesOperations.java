package H2;

import ij.ImagePlus;

public class MatricesOperations {
	
	//This funciton will get the sum of 2 images
	public ImagePlus sum(ImagePlus i1, ImagePlus i2) {
		ArrayImageSocket socket1 = new ArrayImageSocket(i1);
		ArrayImageSocket socket2 = new ArrayImageSocket(i2);
		
		int[][] imagePixels1 = socket1.getPixels();
		int[][] imagePixels2 = socket2.getPixels();
		
		int width1 = socket1.getWidth();
		int width2 = socket2.getWidth();
		int height1 = socket1.getHeight();
		int height2 = socket2.getHeight();
		
		//Check that the dimensions are the same
		if(width1 != width2 || height1 != height2) {
			System.out.println("Los tamaños de las imagenes no coinciden");
			return null;
		}
		
		int[][] sum = new int[width1][height1];
		int value = 0;
		for(int i = 0;i<width1;i++) {
			for(int j = 0;j<height1;j++) {
				//Max value 
				int max = 256;
				value = imagePixels1[i][j]+imagePixels2[i][j];
				if(value >max) {
					sum[i][j] = max;
				}
				else {
					sum[i][j] = value;
				}
			}
		}
		
		ArrayImageSocket socketSum = new ArrayImageSocket(sum);
		return socketSum.meshToGrayImage();
	}
	
	//Will get the substraction of two images
	public ImagePlus substraction(ImagePlus i1, ImagePlus i2) {
		ArrayImageSocket socket1 = new ArrayImageSocket(i1);
		ArrayImageSocket socket2 = new ArrayImageSocket(i2);
		
		int[][] imagePixels1 = socket1.getPixels();
		int[][] imagePixels2 = socket2.getPixels();
		
		int width1 = socket1.getWidth();
		int width2 = socket2.getWidth();
		int height1 = socket1.getHeight();
		int height2 = socket2.getHeight();
		
		//Check that the dimensions are the same
		if(width1 != width2 || height1 != height2) {
			System.out.println("Los tamaños de las imagenes no coinciden");
			return null;
		}
		
		int[][] substraction = new int[width1][height1];
		int value = 0;
		for(int i = 0;i<width1;i++) {
			for(int j = 0;j<height1;j++) {
				//Max value 
				int min = 0;
				value = (int)((float)imagePixels1[i][j]-(float)imagePixels2[i][j]);
				if(value < min) {
					substraction[i][j] = min;
				}
				else {
					substraction[i][j] = value;
				}
			}
		}
		
		ArrayImageSocket socketSubstraction = new ArrayImageSocket(substraction);
		return socketSubstraction.meshToGrayImage();
	}
	
	//Will get the product of imagese
	public ImagePlus product(ImagePlus i1, ImagePlus i2) {
		ArrayImageSocket socket1 = new ArrayImageSocket(i1);
		ArrayImageSocket socket2 = new ArrayImageSocket(i2);
		
		int[][] imagePixels1 = socket1.getPixels();
		int[][] imagePixels2 = socket2.getPixels();
		
		int width1 = socket1.getWidth();
		int width2 = socket2.getWidth();
		int height1 = socket1.getHeight();
		int height2 = socket2.getHeight();
		
		//Check that the dimensions are the same
		if(width1 != width2 || height1 != height2) {
			System.out.println("Los tamaños de las imagenes no coinciden");
			return null;
		}
		
		int[][] product = new int[width1][height1];
		int value = 0;
		for(int i = 0;i<width1;i++) {
			for(int j = 0;j<height1;j++) {
				//Max value 
				int max = 256;
				value = (int)((float)imagePixels1[i][j]*(float)imagePixels2[i][j]);
				if(value > max) {
					product[i][j] = max;
				}
				else {
					product[i][j] = value;
				}
			}
		}
		
		ArrayImageSocket socketProduct = new ArrayImageSocket(product);
		return socketProduct.meshToGrayImage();
	}
	
	//Will get the product of imagese
		public ImagePlus quotient(ImagePlus i1, ImagePlus i2) {
			ArrayImageSocket socket1 = new ArrayImageSocket(i1);
			ArrayImageSocket socket2 = new ArrayImageSocket(i2);
			
			int[][] imagePixels1 = socket1.getPixels();
			int[][] imagePixels2 = socket2.getPixels();
			
			int width1 = socket1.getWidth();
			int width2 = socket2.getWidth();
			int height1 = socket1.getHeight();
			int height2 = socket2.getHeight();
			
			//Check that the dimensions are the same
			if(width1 != width2 || height1 != height2) {
				System.out.println("Los tamaños de las imagenes no coinciden");
				return null;
			}
			
			int[][] quotient = new int[width1][height1];
			int value = 0;
			for(int i = 0;i<width1;i++) {
				for(int j = 0;j<height1;j++) {
					
					//El caso cero
					if(imagePixels2[i][j] == 0) {
						quotient[i][j] = 255;
					}
					else {
						value = (int)((float)imagePixels1[i][j]/(float)imagePixels2[i][j]);
						quotient[i][j] = 0;
					}
				}
			}
			
			ArrayImageSocket socketQuotient = new ArrayImageSocket(quotient);
			return socketQuotient.meshToGrayImage();
		}
	
	

}
