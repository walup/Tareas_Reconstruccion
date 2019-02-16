package H2;

public class Profiler {
	public enum Profiles{
		HORIZONTAL,
		VERTICAL,
		DIAGONAL_LEFT,
		DIAGONAL_RIGHT
	};
	
	//param in the case of vertical or horizontal profiling
	//is the row or column to profile
	public int[] getProfile(Profiles profile,int param,int[][]image,int width,int height) {
		
		int[] line = null;
		switch(profile) {
		case HORIZONTAL:
			//Get the specified row
			line = new int[width];
			for(int i = 0;i<width;i++) {
				line[i] = image[param][i];
			}
			break;
			
		
		case VERTICAL:
			line = new int[height];
			System.out.println(image[0].length);
			for(int i = 0;i<height;i++) {
				line[i] = image[i][param];
			}
			break;
		case DIAGONAL_LEFT:
			//we will apply a linear equation using the width and the height
			float m = -((float)height-1)/(float)width;
			float b = (float) height-1;
			
			int[] yPixels = new int[width];
			
			//Gather the y pixels
			for(int i = 0;i<width;i++) {
				int y =(int)(m*(float)i +b);
				yPixels[width-1-i] = y;
			}
			
			
			//Now geth the profile
			line = new int[width];
			for(int i = 0;i<width;i++) {
				line[i] = image[yPixels[i]][i];
			}
			break;
		case DIAGONAL_RIGHT:
			//we will apply a linear equation using the width and the height
			float m2 = -((float)height-1)/(float)width;
			float b2 = (float) height-1;
			
			int[] yPixels2 = new int[width];
			
			//Gather the y pixels
			for(int i = 0;i<width;i++) {
				int y =(int)(m2*(float)i +b2);
				yPixels2[i] = y;
			}
			
			
			//Now geth the profile
			line = new int[width];
			for(int i = 0;i<width;i++) {
				line[i] = image[yPixels2[i]][i];
			}
			break;
			
		}
		return line;
	}
}
