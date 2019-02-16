package H2;

import java.util.ArrayList;

import ij.ImagePlus;

public class Equalization {
	
	public enum Equalizator {
		CDF_EQUALIZATOR,
		NORMAL_EQUALIZATOR,
		
		
	}
	
	public float[] obtainCDF (ImagePlus img) {
		int[] histogram = img.getStatistics().histogram;
		float[] cdf = new float[histogram.length];
		int sum = 0;
		for(int i = 0;i<histogram.length;i++) {
			sum += histogram[i];
			cdf[i] = sum;
		}
		//Now we normalize
		
		for(int i = 0;i<histogram.length;i++) {
			cdf[i] = cdf[i]/cdf[histogram.length-1];
		}
		return cdf;
	}
	
	public ImagePlus equalize(ImagePlus img, Equalizator equalizator) {
		switch(equalizator) {
		case CDF_EQUALIZATOR:
			float[] cdf = obtainCDF(img);
			ArrayImageSocket socket = new ArrayImageSocket(img);
			int[][] imageArray = socket.getPixels();
			int value = 0;
			for(int i = 0;i<socket.getHeight();i++) {
				for(int j = 0;j<socket.getWidth();j++) {
					value = imageArray[i][j];
					value = (int)(cdf[value]*255.0);
					
					imageArray[i][j] = value;

				}
			}
			ArrayImageSocket socket2 = new ArrayImageSocket(imageArray);
			ImagePlus equalizedImage = socket2.meshToGrayImage();
			return equalizedImage;
			
		case NORMAL_EQUALIZATOR:
			break;
		
		}
		return null;
	}
	
	public int[][] equalize(int[][]imgMatrix,Equalizator equalizator) {
		
		ArrayImageSocket socket = new ArrayImageSocket(imgMatrix);
		
		ImagePlus eqImg = equalize(socket.meshToGrayImage(),equalizator);
		
		ArrayImageSocket socket2 = new ArrayImageSocket(eqImg);
		return socket2.getPixels();
	}
	
	public ImagePlus piecesEqualization(ImagePlus img, int pieces,Equalizator equalizator) {
		ImageSplitter splitter = new ImageSplitter();
		ArrayImageSocket socket = new ArrayImageSocket(img);
		System.out.println("height"+socket.getHeight());
		ArrayList<int[][]> splitImg = splitter.splitMatrix(pieces, socket.getPixels());
		ArrayList<int[][]> eqImgs = new ArrayList<int[][]>();
		for(int[][] submatrix: splitImg) {
			int[][] subm = equalize(submatrix,equalizator);
			eqImgs.add(subm);
			ArrayImageSocket sock = new ArrayImageSocket(subm);
			sock.meshToGrayImage().show();
		}
		
		int[][] reconstructedImage = splitter.reconstruct(eqImgs, socket.getPixels());
		
		ArrayImageSocket imgSocket = new ArrayImageSocket(reconstructedImage);
		return imgSocket.meshToGrayImage();
		
		
	}
	
	
	
	

}
