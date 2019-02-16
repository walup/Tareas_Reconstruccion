package Other;

import ij.ImagePlus;
import ij.io.FileInfo;
import ij.plugin.ChannelSplitter;
import ij.process.ImageStatistics;

public class PhotoInfo {
	
	public final String LINE_JUMP = "\n\r";
	
	//Some useful methods to extract info
	public String extractInfo(ImagePlus image) {
		ImageStatistics statistics = image.getStatistics();
		StringBuilder builder = new StringBuilder();
		//Clase de imagen 
		//Las dimensiones
		builder.append("Dimensiones: "+image.getNDimensions()+LINE_JUMP);
		//EL ancho y alto
		builder.append("Ancho(px): "+image.getWidth()+LINE_JUMP);
		builder.append("Alto(px): "+image.getHeight()+LINE_JUMP);
		//Canales
		ChannelSplitter colorSplitter = new ChannelSplitter();
		ImagePlus[] channels = colorSplitter.split(image);
		int nChannels = channels.length;
		builder.append("Canales: "+nChannels+LINE_JUMP);
		builder.append("Bytes por pixel "+image.getBytesPerPixel()+LINE_JUMP);
		//Tamaño del stack
		builder.append("Tamaño del stack: "+image.getStackSize()+LINE_JUMP);
		//Cortes
		builder.append("Cortes "+image.getNSlices()+LINE_JUMP);
		
		/*For the max and min we will see first how many channel it has first*/
		if(nChannels == 3) {
			builder.append("Rojo Max: "+channels[0].getStatistics().max+LINE_JUMP);
			builder.append("Rojo Min: "+channels[0].getStatistics().min+LINE_JUMP);
			builder.append("Rojo Media: "+channels[0].getStatistics().mean+LINE_JUMP);
			builder.append("Rojo Desv Std: "+channels[0].getStatistics().stdDev+LINE_JUMP);
			builder.append("Verde Max: "+channels[1].getStatistics().max+LINE_JUMP);
			builder.append("Verde Min: "+channels[1].getStatistics().min+LINE_JUMP);
			builder.append("Verde Media: "+channels[1].getStatistics().mean+LINE_JUMP);
			builder.append("Verde Desv Std: "+channels[1].getStatistics().stdDev+LINE_JUMP);
			builder.append("Azul Max: "+channels[2].getStatistics().max+LINE_JUMP);
			builder.append("Azul Min: "+channels[2].getStatistics().min+LINE_JUMP);
			builder.append("Azul Media: "+channels[2].getStatistics().mean+LINE_JUMP);
			builder.append("Azul Desv Std: "+channels[2].getStatistics().stdDev+LINE_JUMP);
		}
		else {
			builder.append("Max: "+statistics.max+LINE_JUMP);
			builder.append("Min: "+statistics.min+LINE_JUMP);
			builder.append("Media: "+statistics.mean+LINE_JUMP);
			builder.append("Desv std: "+statistics.stdDev+LINE_JUMP);
		}
		
		return builder.toString();
		
	}

}
