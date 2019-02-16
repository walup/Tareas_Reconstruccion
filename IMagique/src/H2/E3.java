package H2;

import H2.Equalization.Equalizator;
import ij.ImagePlus;
import ij.io.FileSaver;
import ij.io.Opener;
import ij.plugin.ContrastEnhancer;

//Exercise 3
public class E3 {
	public static void main(String[] args) {
		//Primero guardamos el .dcm como .jpg
		String PATH_DCM = "C:/Users/walup/Documents/Imágenes/im22.dcm";
		Opener opener = new Opener();
		ImagePlus img = opener.openImage(PATH_DCM);
		FileSaver pngSaver = new FileSaver(img);
		pngSaver.saveAsJpeg("C:/Users/walup/Documents/Imágenes/im22.jpg");
		ImagePlus imgJpeg = opener.openImage("C:/Users/walup/Documents/Imágenes/im22.jpg");
		ImagePlus originalHist = imgJpeg.plotHistogram().getImagePlus();
		//Save the original hist
		FileSaver originalHistSaver = new FileSaver(originalHist);
		originalHistSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/ori_hist.png");
		//Equalize the image
		Equalization equalization = new Equalization();
		ImagePlus imgEq = equalization.equalize(imgJpeg, Equalizator.CDF_EQUALIZATOR);
		ImagePlus eqHist = imgEq.plotHistogram().getImagePlus();
		FileSaver eqHistSaver = new FileSaver(eqHist);
		eqHistSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/eq_hist.png");
		FileSaver eqImgSaver = new FileSaver(imgEq);
		eqImgSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/eq_img.png");
		
		//Ahora, restringimos la escala
		ScaleRestrictor restrictor = new ScaleRestrictor();
		ImagePlus restrict128 = restrictor.restrictScale(imgEq, 0, 127);
		ImagePlus restrict68 = restrictor.restrictScale(imgEq, 0, 67);
		FileSaver res128 = new FileSaver(restrict128);
		res128.saveAsPng("C:/Users/walup/Documents/Imágenes/res_128.png");
		FileSaver res68Save = new FileSaver(restrict68);
		res68Save.saveAsPng("C:/Users/walup/Documents/Imágenes/res_68.png");
		
		
		
		//Ahora quiero probar el que viene incluido 
		ContrastEnhancer enhancer = new ContrastEnhancer();
		enhancer.equalize(imgJpeg);
		
		
		
	}

}
