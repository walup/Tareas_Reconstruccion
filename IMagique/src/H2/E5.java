package H2;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.io.Opener;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

public class E5 {
	public static void main(String[] args) {
		String PATH = "C:/Users/walup/Documents/Imágenes/raya_dolar.png";
		Opener opener = new Opener();
		ImagePlus img = opener.openImage(PATH);
		ImageConverter converter = new ImageConverter(img);
		converter.convertToGray8();
		
		double gamma1 = 1.1;
		double gamma2 = 1.5;
		double gamma3 = 0.5;
		double gamma4 =2.2;
		
		ImageProcessor proccesor = img.getProcessor();
		ImagePlus imgGamma1  = opener.openImage(PATH);
		ImageConverter converter1 = new ImageConverter(imgGamma1);
		converter1.convertToGray8();
		proccesor = imgGamma1.getProcessor();
		proccesor.gamma(gamma1);
		
		ImagePlus imgGamma2  = opener.openImage(PATH);
		ImageConverter converter2 = new ImageConverter(imgGamma2);
		converter2.convertToGray8();
		proccesor = imgGamma2.getProcessor();
		proccesor.gamma(gamma2);
		
		ImagePlus imgGamma3  = opener.openImage(PATH);
		ImageConverter converter3 = new ImageConverter(imgGamma3);
		converter3.convertToGray8();
		proccesor = imgGamma3.getProcessor();
		proccesor.gamma(gamma3);
		
		ImagePlus imgGamma4  = opener.openImage(PATH);
		ImageConverter converter4 = new ImageConverter(imgGamma4);
		converter4.convertToGray8();
		proccesor = imgGamma4.getProcessor();
		proccesor.gamma(gamma4);
		
		ImagePlus hist1 = imgGamma1.plotHistogram().getImagePlus();
		ImagePlus hist2 = imgGamma2.plotHistogram().getImagePlus();
		ImagePlus hist3 = imgGamma3.plotHistogram().getImagePlus();
		ImagePlus hist4 = imgGamma4.plotHistogram().getImagePlus();
		ImagePlus histImg = img.plotHistogram().getImagePlus();
		
		FileSaver imgSaver = new FileSaver(img);
		imgSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/raya_dolar_grey.png");
		FileSaver gamma1Saver = new FileSaver(imgGamma1);
		gamma1Saver.saveAsPng("C:/Users/walup/Documents/Imágenes/gamma_1.png");
		FileSaver gamma2Saver = new FileSaver(imgGamma2);
		gamma2Saver.saveAsPng("C:/Users/walup/Documents/Imágenes/gamma_2.png");
		FileSaver gamma3Saver = new FileSaver(imgGamma3);
		gamma3Saver.saveAsPng("C:/Users/walup/Documents/Imágenes/gamma_3.png");
		FileSaver gamma4Saver = new FileSaver(imgGamma4);
		gamma4Saver.saveAsPng("C:/Users/walup/Documents/Imágenes/gamma_4.png");
		FileSaver imgSaverHist = new FileSaver(histImg);
		imgSaverHist.saveAsPng("C:/Users/walup/Documents/Imágenes/raya_dolar_grey_hist.png");
		FileSaver gamma1SaverHist = new FileSaver(hist1);
		gamma1SaverHist.saveAsPng("C:/Users/walup/Documents/Imágenes/gamma_1_hist.png");
		FileSaver gamma2SaverHist = new FileSaver(hist2);
		gamma2SaverHist.saveAsPng("C:/Users/walup/Documents/Imágenes/gamma_2_hist.png");
		FileSaver gamma3SaverHist = new FileSaver(hist3);
		gamma3SaverHist.saveAsPng("C:/Users/walup/Documents/Imágenes/gamma_3_hist.png");
		FileSaver gamma4SaverHist = new FileSaver(hist4);
		gamma4SaverHist.saveAsPng("C:/Users/walup/Documents/Imágenes/gamma_4_hist.png");
		
		imgGamma1.show();
		imgGamma2.show();
		imgGamma3.show();
		imgGamma4.show();
		img.show();
		
		
		
		
		
	}

}
