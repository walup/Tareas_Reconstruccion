package H2;

import H2.Profiler.Profiles;
import Other.UTools;
import ij.ImagePlus;
import ij.gui.Plot;
import ij.io.FileSaver;
import ij.io.Opener;

public class E2 {
	public static void main(String[] args) {
	String PATH = "C:/Users/walup/Documents/Imágenes/skeleton.png";
	String PATH_2 = "C:/Users/walup/Documents/Imágenes";
	//Open the image
	Opener opener = new Opener();
	ImagePlus img = opener.openImage(PATH);
	
	ArrayImageSocket socket = new ArrayImageSocket(img);
	int[][] imgArray = socket.getPixels();
	Profiler profiler = new Profiler();
	
	int midCol = (int)(socket.getWidth()/2.0);
	int midRow = (int)(socket.getHeight()/2.0);
	
	//Get the profiles
	int[] horizProfile = profiler.getProfile(Profiles.HORIZONTAL, midRow, imgArray,socket.getWidth(),socket.getHeight());
	int[] vertProfile = profiler.getProfile(Profiles.VERTICAL, midCol, imgArray,socket.getWidth(),socket.getHeight());
	int[] diagProfile = profiler.getProfile(Profiles.DIAGONAL_LEFT, 0, imgArray,socket.getWidth(),socket.getHeight());
	
	//Plot them 
	
	Plot horizPlot = new Plot("Perfil horizontal,fila: "+midRow,"Pixel","Valor");
	Plot vertPlot = new Plot("Perfil vertical,columna: "+midCol,"Pixel","Valor");
	Plot diagPlot = new Plot("Perfil diagonal","Pixel","Valor");
	UTools tools = new UTools();
	
	//for(int value:horizProfile) {
		//System.out.println(value);
	//}
	socket.meshToGrayImage().show();
	horizPlot.add("dot", tools.copyFromIntArray(horizProfile));
	vertPlot.add("dot",tools.copyFromIntArray(vertProfile));
	diagPlot.add("dot",tools.copyFromIntArray(diagProfile));
	ImagePlus horiz = horizPlot.getImagePlus();
	ImagePlus vert = vertPlot.getImagePlus();
	ImagePlus diag = diagPlot.getImagePlus();
	
	FileSaver saverHoriz = new FileSaver(horiz);
	saverHoriz.saveAsPng(PATH_2+"/horiz.png");
	FileSaver saverVert = new FileSaver(vert);
	saverVert.saveAsPng(PATH_2+"/vert.png");
	FileSaver saverDiag = new FileSaver(diag);
	saverDiag.saveAsPng(PATH_2+"/diag.png");
	FileSaver saverSkel = new FileSaver(socket.meshToGrayImage());
	saverSkel.saveAsPng(PATH_2+"/skel_grey.png");
	horizPlot.show();
	vertPlot.show();
	diagPlot.show();
	}
	
	
	


}
