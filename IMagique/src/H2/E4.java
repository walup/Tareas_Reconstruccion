package H2;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.io.Opener;

public class E4 {
	public static void main(String[] args) {
		String PATH = "C:/Users/walup/Documents/Imágenes/skel_grey.png";
		Opener opener = new Opener();
		ImagePlus img = opener.openImage(PATH);
		//The three combos
		float contrast1 = 2.0f;
		float bright1 = -20;
		
		float contrast2 =0.5f;
		float bright2 = 40;
		
		float contrast3 = 3.0f;
		float bright3 = 0.0f;
		
		ContrastLightModifier contrastManager = new ContrastLightModifier();
		
		ImagePlus combo1 = contrastManager.applyContrastLightModification(img, contrast1, bright1);
		ImagePlus combo2 = contrastManager.applyContrastLightModification(img, contrast2, bright2);
		ImagePlus combo3 = contrastManager.applyContrastLightModification(img, contrast3, bright3);
		
		ImagePlus histOr = img.plotHistogram().getImagePlus();
		ImagePlus hist1 = combo1.plotHistogram().getImagePlus();
		ImagePlus hist2 = combo2.plotHistogram().getImagePlus();
		ImagePlus hist3 = combo3.plotHistogram().getImagePlus();
		
		//Reversed image
		ImagePlus oriRev = contrastManager.complement(img);
		ImagePlus combo1Rev = contrastManager.complement(combo1);
		ImagePlus combo2Rev = contrastManager.complement(combo2);
		ImagePlus combo3Rev = contrastManager.complement(combo3);
		
		//Reversed image
		
		
		
		ImagePlus oriRevHist = oriRev.plotHistogram().getImagePlus();
		ImagePlus combo1RevHist = combo1Rev.plotHistogram().getImagePlus();
		ImagePlus combo2RevHist = combo2Rev.plotHistogram().getImagePlus();
		ImagePlus combo3RevHist = combo3Rev.plotHistogram().getImagePlus();
		
		FileSaver combo1Saver = new FileSaver(combo1);
		FileSaver combo2Saver = new FileSaver(combo2);
		FileSaver combo3Saver = new FileSaver(combo3);
		
		FileSaver hist1Saver = new FileSaver(hist1);
		FileSaver hist2Saver = new FileSaver(hist2);
		FileSaver hist3Saver = new FileSaver(hist3);
		FileSaver histOriSaver = new FileSaver(histOr);
		
		FileSaver combo1RevSaver = new FileSaver(combo1Rev);
		FileSaver combo2RevSaver = new FileSaver(combo2Rev);
		FileSaver combo3RevSaver = new FileSaver(combo3Rev);
		FileSaver oriRevSaver = new FileSaver(oriRev);
		
		FileSaver combo1RevHistSaver = new FileSaver(combo1RevHist);
		FileSaver combo2RevHistSaver = new FileSaver(combo2RevHist);
		FileSaver combo3RevHistSaver = new FileSaver(combo3RevHist);
		FileSaver oriRevHistSaver = new FileSaver(oriRevHist);
		
		
		//Export all the shit
		combo1Saver.saveAsPng("C:/Users/walup/Documents/Imágenes/combo1.png");
		combo2Saver.saveAsPng("C:/Users/walup/Documents/Imágenes/combo2.png");
		combo3Saver.saveAsPng("C:/Users/walup/Documents/Imágenes/combo3.png");
		hist1Saver.saveAsPng("C:/Users/walup/Documents/Imágenes/combo1_hist.png");
		hist2Saver.saveAsPng("C:/Users/walup/Documents/Imágenes/combo2_hist.png");
		hist3Saver.saveAsPng("C:/Users/walup/Documents/Imágenes/combo3_hist.png");
		histOriSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/ori_hist2.png");
		combo1RevSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/combo1_rev.png");
		combo2RevSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/combo2_rev.png");
		combo3RevSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/combo3_rev.png");
		oriRevSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/ori_rev.png");
		combo1RevHistSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/combo_1_rev_hist.png");
		combo2RevHistSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/combo_2_rev_hist.png");
		combo3RevHistSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/combo_3_rev_hist.png");
		oriRevHistSaver.saveAsPng("C:/Users/walup/Documents/Imágenes/ori_rev_hist.png");
		
		
		
		
	}

}
