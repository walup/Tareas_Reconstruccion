import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Image.Photo;
import Parsers.ImageParser;

/*This is the menu window for now it will just 
 * have a menu and the option to display the image in 
 * some panel, we will have to be able to open .png, .jpg
 * .dcm, .TIF, .PCX*/
public class MainWindow extends JFrame{
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JTextField directoryField;
	private JTextField fileNameField;
	private JLabel directoryLabel;
	private JLabel fileNameLabel;
	public final String TITLE = "Reconstrucción Imagenes (Uriel)";
	public final int WINDOW_WIDTH = 640;
	public final int WINDOW_HEIGHT = 640;
	public final int ROW_HEIGHT = 30;
	public final int ROW_WIDTH = WINDOW_WIDTH-10;
	public final int PORTRAIT_ALBUM_SIZE = 6;
	
	
	private JMenuItem maskWindow;
	private JMenuItem imageInfo;
	private JMenuItem colorSeparation;
	private JButton uploadButton;
	private ImageParser parser;
	private PortraitAlbum album;
	
	private MainWindow instance;
	
	
	//The constructor
	public MainWindow() {
		instance = this;
		//Close operation of the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set the window size
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		setVisible(true);
		setTitle(TITLE);
		//Initialize the menu
		maskWindow = new JMenuItem("Apply Mask");
		imageInfo = new JMenuItem("Show image info");
		colorSeparation = new JMenuItem("Separate colors");
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		menu.add(imageInfo);
		menu.add(maskWindow);
		menu.add(colorSeparation);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		//Initialize the parser
		parser = new ImageParser();
		
		//Initialize the labels
		directoryLabel = new JLabel("Directorio");
		fileNameLabel = new JLabel("Nombre");
		
		//Initialize the fields
		directoryField = new JTextField();
		fileNameField = new JTextField();
		
		directoryField.setText("C:/Users/walup/Documents/Imágenes");
		
		// Initialize the upload button
		uploadButton = new JButton("Upload Image");
		
		//The layout is a box layout
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		listPane.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		listPane.setVisible(true);
		add(listPane);
		//We will add things to such layout
		JPanel directoryRow = new JPanel();
		directoryRow.setSize(ROW_WIDTH,ROW_HEIGHT);
		directoryRow.setVisible(true);
		directoryRow.setLayout(new GridLayout(1,5));
		directoryRow.add(directoryLabel);
		directoryRow.add(directoryField);
		directoryRow.add(fileNameLabel);
		directoryRow.add(fileNameField);
		directoryRow.add(uploadButton);
		
		//Create the album
		album = new PortraitAlbum(PORTRAIT_ALBUM_SIZE);
		
		//Add stuff to the listpane
		
		listPane.add(directoryRow);
		listPane.add(album);
		pack();
		
		
		//The listener for the button 
		uploadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//We will open the image
				String directoryString = directoryField.getText();
				String nameString = fileNameField.getText();
				
				parser.openImage(directoryString, nameString);
				//Get the photo
				Photo newPhoto = parser.getPhoto();
				//Add the photo to the album
				album.addPhoto(newPhoto);
				pack();
			}
		});
		
		imageInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//We will open an InfoWindow with our instance
				new InfoWindow(instance);
			}
			
		});
		
		colorSeparation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//We will open a ColorWindow
				new ColorWindow(instance);
				
			}
			
		});
		
		
		
	}
	
	public PortraitAlbum getAlbum() {
		return album;
	}
	

}
