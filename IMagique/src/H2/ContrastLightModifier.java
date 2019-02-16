package H2;

import ij.ImagePlus;

public class ContrastLightModifier {
	
	public ImagePlus applyContrastLightModification(ImagePlus img,float contrast,float light) {
		ArrayImageSocket socket = new ArrayImageSocket(img);
		int[][] imgArray = socket.getPixels();
		
		for(int i = 0;i<socket.getHeight();i++) {
			for(int j = 0;j<socket.getWidth();j++) {
				imgArray[i][j] = (int)(((float)imgArray[i][j])*contrast +light);
			}
		}
		ArrayImageSocket socket2 = new ArrayImageSocket(imgArray);
		ImagePlus modifiedImage = socket2.meshToGrayImage();
		return modifiedImage;
	}
	
	public ImagePlus complement(ImagePlus img) {
		ArrayImageSocket socket = new ArrayImageSocket(img);
		int[][] imgArray = socket.getPixels();
		
		
		for(int i = 0;i<socket.getHeight();i++) {
			for(int j = 0;j<socket.getWidth();j++) {
				imgArray[i][j] = 255-imgArray[i][j];
			}
		}
		ArrayImageSocket socket2 = new ArrayImageSocket(imgArray);
		ImagePlus complement = socket2.meshToGrayImage();
		
		return complement;
	}

}
