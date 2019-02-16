package H2;

import H2.Equalization.Equalizator;
import ij.ImagePlus;
import ij.io.FileSaver;
import ij.io.Opener;
import ij.process.ImageConverter;

public class E6 {
	public static void main(String[] args) {
		//Usemos la misma .dcm, solo porque en ese caso ya sabemos como se ve ecualizada en condiciones normales
		Opener opener = new Opener();
		ImagePlus imgJpeg = opener.openImage("C:/Users/walup/Documents/Imágenes/exodia.jpg");
		ImageConverter converter = new ImageConverter(imgJpeg);
		converter.convertToGray8();
		ImagePlus originalHist = imgJpeg.plotHistogram().getImagePlus();
		//Save the original hist
		FileSaver originalHistSaver = new FileSaver(originalHist);
		originalHistSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/ori_ex_hist.png");
		//Equalize the image
		Equalization equalization = new Equalization();
		ImagePlus imgEq = equalization.equalize(imgJpeg, Equalizator.CDF_EQUALIZATOR);
		imgEq.show();
		/*DIvision in 4 parts*/
		ImagePlus imgEq4 = equalization.piecesEqualization(imgJpeg, 4, Equalizator.CDF_EQUALIZATOR);
		ImagePlus imgEq4Hist = imgEq4.plotHistogram().getImagePlus();
		imgEq4.show();
		/*Division in 9 parts*/
		ImagePlus imgEq9 = equalization.piecesEqualization(imgJpeg, 9, Equalizator.CDF_EQUALIZATOR);
		ImagePlus imgEq9Hist = imgEq9.plotHistogram().getImagePlus();
		imgEq9.show();
	
		//Save the original image and histogram
		FileSaver orExSaver = new FileSaver(imgJpeg);
		orExSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/ori_ex_grey.png");
		//The equalized image and histogram
		FileSaver orExEq = new FileSaver(imgEq);
		orExEq.saveAsPng("C:/Users/walup/Documents/Imágenes/eq_ex_grey.png");
		ImagePlus originaleqHist = imgEq.plotHistogram().getImagePlus();
		//Save the original hist
		FileSaver originaleqHistSaver = new FileSaver(originaleqHist);
		originaleqHistSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/eq_ex_hist.png");
		//Save the reconstructed in 4 parts
		FileSaver eq_4Saver = new FileSaver(imgEq4);
		eq_4Saver.saveAsPng("C:/Users/walup/Documents/Imágenes/eq_ex_grey_4.png");
		ImagePlus eq4Hist = imgEq4.plotHistogram().getImagePlus();
		FileSaver eq4HistSaver = new FileSaver(eq4Hist);
		eq4HistSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/eq_ex_4_hist.png");
		FileSaver eq_9Saver = new FileSaver(imgEq9);
		eq_9Saver.saveAsPng("C:/Users/walup/Documents/Imágenes/eq_ex_grey_9.png");
		ImagePlus eq9Hist = imgEq9.plotHistogram().getImagePlus();
		FileSaver eq9HistSaver = new FileSaver(imgEq9Hist);
		eq9HistSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/eq_ex_9_hist.png");
		
	}

}
