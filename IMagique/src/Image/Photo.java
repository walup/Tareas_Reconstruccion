package Image;

import javax.swing.JPanel;

import ij.ImagePlus;

public class Photo {
	private JPanel panel;
	private String id;
	private String info;
	private ImagePlus image;
	
	
	public Photo(JPanel panel, String id,ImagePlus image) {
		this.panel = panel;
		this.id = id;
		this.image = image;
	}
	
	public Photo(JPanel panel,String id, String info,ImagePlus image) {
		this.panel = panel;
		this.id = id;
		this.info = info;
		this.image = image;
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public void setImage(ImagePlus image) {
		this.image = image;
	}
	
	public ImagePlus getImage(){
		return image;
	}
	
	public String toString() {
		return id;
	}
	
	
	
}
