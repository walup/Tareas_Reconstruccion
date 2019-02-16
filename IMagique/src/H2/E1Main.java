package H2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JMenuBar;

import ij.ImagePlus;
import ij.gui.ImageWindow;
import ij.gui.Plot;
import ij.gui.PlotCanvas;
import ij.io.FileSaver;
import ij.io.Opener;

public class E1Main {
	public static void main(String[] args) {
	//First exercise
	String PATH_1 = "C:/Users/walup/Documents/Imágenes/patron_space.png";
	String PATH_2 = "C:/Users/walup/Documents/Imágenes/adventure.png";
	String PATH = "C:/Users/walup/Documents/Imágenes";
	ImagePlus img1,img2,imgSum,imgSubstraction,imgProduct,imgQuotient;
	//Open the images
	Opener opener = new Opener();
	img1 = opener.openImage(PATH_1);
	img2 = opener.openImage(PATH_2);
	MatricesOperations operator = new MatricesOperations();
	//Sum them
	imgSum = operator.sum(img1, img2);
	//substract them 
	imgSubstraction = operator.substraction(img1, img2);
	//do the product
	imgProduct = operator.product(img1, img2);
	//The quotient
	imgQuotient = operator.quotient(img1, img2);
	
	//Show all of them 
	imgSum.show();
	FileSaver saverSum = new FileSaver(imgSum);
	saverSum.saveAsPng(PATH+"/suma.png");
	
	imgSubstraction.show();
	FileSaver saverSubstraction = new FileSaver(imgSubstraction);
	saverSubstraction.saveAsPng(PATH+"/substraction.png");
	
	imgProduct.show();
	FileSaver saverProduct = new FileSaver(imgProduct);
	saverProduct.saveAsPng(PATH+"/product.png");
	
	imgQuotient.show();
	FileSaver saverQuotient = new FileSaver(imgQuotient);
	saverQuotient.saveAsPng(PATH+"/quotient.png");
	
	
	
}
}
