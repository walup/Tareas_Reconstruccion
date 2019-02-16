package H2;

import java.util.ArrayList;

public class ImageSplitter {
	
	
	
	//Split matrix 
	public ArrayList<int[][]> splitMatrix(int pieces,int[][] matrix){
		//We can apprpach this in the dumbest way 
		
		int width = matrix[0].length;
		int height = matrix.length;
		
		int side = (int)Math.sqrt(pieces);
		pieces = pieces-1;
		
		int ySize = height/side;
		int xSize = width/side;
		int yRes = height%side;
		int xRes = width%side;
		
		int i = 0;
		int j = 0;
		int stepX = 0;
		int stepY = 0;
		
		System.out.println("width "+width +" height "+height +" x size "+xSize+ " xRes "+xRes);
		
		ArrayList<int[][]> matrices = new ArrayList<int[][]>();
		
		while(i*ySize+yRes <= height) {
			//Configure the step
			if(i*ySize +yRes == height && yRes != 0 ) {
				stepY = yRes;
			}
			else if(i*ySize+yRes== height &&yRes == 0) {
				System.out.println("broke");
				break;
			}
			else {
				stepY = ySize;
			}
			j = 0;
			while(j*xSize+xRes <= width) {
				//Configure the step
				if(j*xSize+xRes == width && xRes != 0) {
					stepX = xRes;
				}
				else if(j*xSize+xRes== width && xRes == 0) {
					break;
				}
				else {
					stepX = xSize;
				} 
				
				//Get the next submatrix
				int[][] subMatrix = new int[stepY][stepX];
				
				//Fill the submatrix
				for(int k = 0;k<stepY;k++) {
					for(int s = 0;s<stepX;s++) {
						if(j*xSize+s == width) {
							System.out.println("flood x");
						}
						if(i*ySize+k>=height) {
							System.out.println("flood y");
						}
						subMatrix[k][s] = matrix[i*ySize+k][j*xSize+s];
						
					}
				}
				
				System.out.println("*");
				//Once filled we add it to the matrices array 
				matrices.add(subMatrix);
				j+=1;
			}
			i+=1;
		}
		System.out.println("pieces "+matrices.size());
		return matrices;
	}
	
	/*Reconstruct an image from a set of matrices*/
	
	public int[][] reconstruct(ArrayList<int[][]>submatrices,int[][] matrix) {
		
		
		int pieces = submatrices.size();
		int width = matrix[0].length;
		int height = matrix.length;
		
		int[][] reconstructedImage = new int[height][width];
		int markerX = 0;
		int markerY = 0;
		//Iterate over all pieces
		
		for(int i = 0;i<pieces;i++) {
			int[][] piece = submatrices.get(i);
			int pieceWidth = piece[0].length;
			int pieceHeight = piece.length;
			
			//Update the markers if we have too
			
			
			for(int k = 0;k<pieceHeight;k++) {
				for(int s = 0;s<pieceWidth;s++) {
					if(markerY+k>=height) {
						System.out.println("flood y ");
					}
					if(markerX+s>=width) {
						System.out.println("flood x ");
					}
					reconstructedImage[markerY+k][markerX+s] = piece[k][s];
				}
			}
			markerX = markerX+pieceWidth;
			
			if(markerX>=width) {
				markerX = 0;
				markerY = markerY+pieceHeight;
				//System.out.println("updated markers");
			}
			System.out.println("mx"+markerX);
			System.out.println("my"+markerY);
		}
		
		return reconstructedImage;
	}
	

}
