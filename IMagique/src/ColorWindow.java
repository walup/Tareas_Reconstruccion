import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import Image.Photo;
import Other.PhotoInfo;
import ij.ImagePlus;
import ij.plugin.ChannelSplitter;

public class ColorWindow extends JFrame{
	/*Esta es la ventana de procesamiento de color*/
	private JFrame parent;
	public PortraitAlbum colorAlbum;
	private final int COLOR_SIZE = 3;
	private int WINDOW_WIDTH = 640;
	private int WINDOW_HEIGHT = 360;
	private final int PHOTO_WIDTH = 200;
	private final int PHOTO_HEIGHT = 200;
	
	//The process button
	private JButton separateButton;
	//The scroll pane
	private JScrollPane fileScrollPane;
	private JList fileList;
	private String[] keys;
	private JButton addToAlbum;
	
	public ColorWindow(MainWindow parent){
		//When the frame is closed we dispose it
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		setTitle("Separate Colors");
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		listPane.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		listPane.setVisible(true);
		add(listPane);
		
		
		
		//Initialize the album
		colorAlbum = new PortraitAlbum(COLOR_SIZE);
		//initialize the button
		separateButton = new JButton("Separate Colors");
		addToAlbum = new JButton("Add to Main Album");
		//Create the list with the parents's album 
		keys = parent.getAlbum().getKeys();
		fileList = new JList(keys);
		fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fileScrollPane = new JScrollPane(fileList);
		
		//The listener
		separateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//First get the corresponding photo
				String key = keys[fileList.getSelectedIndex()];
				Photo photo = parent.getAlbum().getPhoto(key);
				
				if(photo != null) {
					//We will create three photos and add them to the album 
					Photo redPhoto;
					Photo bluePhoto;
					Photo greenPhoto;
				
					//Remember that we need a JPanel an id an some ImagePlus for each of those photos
					JPanel redPanel = new JPanel();
					JPanel bluePanel = new JPanel();
					JPanel greenPanel = new JPanel();
				
					String redId = "red_"+photo.getId();
					String blueId = "blue_"+photo.getId();
					String greenId = "green_"+photo.getId();
					
					ChannelSplitter colorSplitter = new ChannelSplitter();
					ImagePlus[] channels = colorSplitter.split(photo.getImage());
					System.out.println(channels.length);
					if(channels.length == 1) {
						System.out.println("La imagen seleccionada solo tiene un canal");
						return;
						
					}
					ImagePlus redImage = channels[0];
					ImagePlus greenImage = channels[1];
					ImagePlus blueImage = channels[2];
					
					/*We are almost done we now need some labels and icons*/
					Image redImg = redImage.getImage();
					Image redResizedImg = redImg.getScaledInstance(PHOTO_WIDTH, PHOTO_WIDTH, java.awt.Image.SCALE_SMOOTH);
					ImageIcon redIcon = new ImageIcon(redResizedImg);
					JLabel redLabel = new JLabel(redIcon);
					
					Image greenImg = greenImage.getImage();
					Image greenResizedImg = greenImg.getScaledInstance(PHOTO_WIDTH, PHOTO_WIDTH, java.awt.Image.SCALE_SMOOTH);
					ImageIcon greenIcon = new ImageIcon(greenResizedImg);
					JLabel greenLabel = new JLabel(greenIcon);
					
					Image blueImg = blueImage.getImage();
					Image blueResizedImg = blueImg.getScaledInstance(PHOTO_WIDTH, PHOTO_WIDTH, java.awt.Image.SCALE_SMOOTH);
					ImageIcon blueIcon = new ImageIcon(blueResizedImg);
					JLabel blueLabel = new JLabel(blueIcon);
					
					//Add the labels to the respective frames
					redPanel.add(redLabel);
					greenPanel.add(greenLabel);
					bluePanel.add(blueLabel);
					
					PhotoInfo photoInfo = new PhotoInfo();
					//Initialize the photos
					redPhoto = new Photo(redPanel,redId,photoInfo.extractInfo(redImage),redImage);
					bluePhoto = new Photo(bluePanel,blueId,photoInfo.extractInfo(blueImage),blueImage);
					greenPhoto = new Photo(greenPanel,greenId,photoInfo.extractInfo(greenImage),greenImage);
					
					//Add them to the album
					colorAlbum.addPhoto(redPhoto);
					colorAlbum.addPhoto(greenPhoto);
					colorAlbum.addPhoto(bluePhoto);
					pack();
					
				}
			}
			
		});

		
		//Add all of it to the window
		listPane.add(fileScrollPane);
		listPane.add(separateButton);
		listPane.add(colorAlbum);
		pack();
		
		
		
		
	}

}
