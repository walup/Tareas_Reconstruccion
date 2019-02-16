package H2;

import ij.ImagePlus;

public class ScaleRestrictor{
public ImagePlus restrictScale(ImagePlus imgPlus,int infValue,int maxValue){
	float m = (float)(maxValue-infValue)/255.0f;
	float b = (float) +infValue;
	
	ArrayImageSocket socket = new ArrayImageSocket(imgPlus);
	int[][] imgArray = socket.getPixels();
	for(int i = 0;i<socket.getHeight();i++) {
		for(int j = 0;j<socket.getWidth();j++) {
			imgArray[i][j] = (int)(m*(float)(imgArray[i][j])+b);
		}
	}
	ArrayImageSocket socket2 = new ArrayImageSocket(imgArray);
	ImagePlus restrictedImage = socket2.meshToGrayImage();
	
	return restrictedImage;
	}
	
}


