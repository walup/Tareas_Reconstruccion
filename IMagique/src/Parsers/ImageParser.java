package Parsers;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Image.Photo;
import Other.PhotoInfo;
import ij.ImagePlus;
import ij.io.FileInfo;
import ij.io.Opener;
import ij.plugin.FolderOpener;

public class ImageParser{
	private final int PHOTO_WIDTH = 300;
	private final int PHOTO_HEIGHT = 300;
	
	private static final String JLabel = null;
	//The file opener and the image
	private Opener opener;
	private ImagePlus image;
	private String id = "";
	
	
	public ImageParser() {
		//initialize the folder opener
		opener = new Opener();
		image = null;
	}

	public void openImage(String directory, String fileName) {
		//Let's use our folder opener to obtain an image
		String dir = directory+"/"+fileName;
		this.image = opener.openImage(dir);
		this.id = fileName;
	}

	public JPanel getImagePanel() {
		if(image != null) {
			Image img = image.getImage();
			Image resizedImg = img.getScaledInstance(PHOTO_WIDTH, PHOTO_WIDTH, java.awt.Image.SCALE_SMOOTH);
			//redraw the image with the new size
			System.out.println("Width "+resizedImg.getWidth(null)+" Height "+resizedImg.getHeight(null));
			ImageIcon icon = new ImageIcon(resizedImg);
			//We add the icon to a label 
			JLabel label = new JLabel(icon);
			//Now you just add the label to a frame and return it
			JPanel panel = new JPanel();
			panel.add(label);
			return panel;
		}
		return null;
	}
	
	//Method to get a photo object
	public Photo getPhoto() {
		if(image != null) {
			Photo photo = new Photo(getImagePanel(),this.id,getImageInfo(),image);
			return photo;
		}
		return null;
	}
	
	

	public String getImageInfo() {
		if(image != null) {
			PhotoInfo info = new PhotoInfo();
			return info.extractInfo(image);
		}
		return "Image is null";
	}
}
