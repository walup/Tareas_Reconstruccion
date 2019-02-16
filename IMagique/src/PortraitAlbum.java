import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Image.Photo;

/*Portrait Album Class*/
public class PortraitAlbum extends JPanel{
	
	private Hashtable<String,Photo> portraitDictionary;
	private final int PORTRAIT_WIDTH = 640;
	private final int PORTRAIT_HEIGHT = 300;
	
	private JScrollPane scrollPane;
	private JPanel photoFrame;
	private int size;
	public PortraitAlbum(int size) {
		this.size = size;
		setSize(PORTRAIT_WIDTH,PORTRAIT_HEIGHT);
		setVisible(true);
		setLayout(new BorderLayout());
	
		//Iniitalize the Hashtable
		portraitDictionary = new Hashtable<String,Photo>();
		//Initialize the scroll pane 
		scrollPane = new JScrollPane();
		//Add the portrait to the scroll pane
		photoFrame = new JPanel();
		/*If we can divide exactly in 
		 * pairs*/
		if(size % 2 == 0) {
			int rows = size/2;
			int cols = 2;
			photoFrame.setLayout(new GridLayout(rows,cols));
			System.out.println("rows "+rows+" columns "+cols);
		}
		
		else {
			int rows = (size+1)/2;
			int cols = 2;
			photoFrame.setLayout(new GridLayout(rows,cols));
		}
		photoFrame.setSize(PORTRAIT_WIDTH,PORTRAIT_HEIGHT);
				
		scrollPane.setViewportView(photoFrame);
		add(scrollPane);
	}
	
	public void addPhoto(Photo photo) {
		//First check if this is not already full
		if(photoFrame.getComponentCount()>size) {
			System.out.println("Se ha rebasado el número de imagenes que se pueden desplegar");
			return;
			
		}
		
		//Now if there is still room we will add to the panel and to the Hashtable
		if(!portraitDictionary.containsKey(photo.getId())) {
			JPanel addPanel = photo.getPanel();
			addPanel.setLayout(new BoxLayout(addPanel,BoxLayout.Y_AXIS));
			JLabel imLabel = new JLabel(photo.getId());
			addPanel.add(imLabel);
			photoFrame.add(addPanel);

			portraitDictionary.put(photo.getId(),photo);
		}
		else {
			System.out.println("The photo already exists in the portrait");
		}
	}
	
	public void removePhoto(Photo photo) {
		if(portraitDictionary.containsKey(photo.getId())) {
			//Remove the frame and the photo
			photoFrame.remove(photo.getPanel());
			portraitDictionary.remove(photo.getId());
		}
		else {
			System.out.println("La foto especificada no existe");
		}
		
	}
	
	
	public void removeAll() {
		//Clear the hashtable
		portraitDictionary.clear();
		//Remove all the pics
		photoFrame.removeAll();
	}
	
	public String[] getKeys() {
		Enumeration<String> keysEnum = portraitDictionary.keys();
		ArrayList<String> arrayListKeys = new ArrayList<String>();
		while(keysEnum.hasMoreElements()) {
			arrayListKeys.add(keysEnum.nextElement());
		}
		//Return the needed arry
		
		String[] strArray = new String[arrayListKeys.size()];
		for(int i = 0;i<arrayListKeys.size();i++) {
			strArray[i] = arrayListKeys.get(i);
		}
		
		return strArray;
		
	}
	
	public Photo getPhoto(String key) {
		/*We will check if the selected key is in our dictionary and if 
		 * so we return the photo*/
		if(portraitDictionary.containsKey(key)) {
			return portraitDictionary.get(key);
			
		}
		else {
			System.out.println("No se pudo encontrar imagen con el nombre "+key);
			return null;
		}
		
	}
	
	
	
}
