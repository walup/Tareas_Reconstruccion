package H2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ij.ImagePlus;
import ij.io.FileSaver;

public class StampRollTool extends JFrame{
	private JLabel stampWidthLabel;
	private JLabel stampHeightLabel;
	private JTextField stampWidthField;
	private JTextField stampHeightField;
	private JLabel patternWidthLabel;
	private JLabel patternHeightLabel;
	private JTextField patternWidthField;
	private JTextField patternHeightField;
	private JLabel directoryLabel;
	private JTextField directoryField;
	private JButton createPatternButton;
	private JButton openStampWindowButton;
	private StampWindow stampWindow;
	
	private final int WIDTH = 300;
	private final int HEIGHT = 300;
	
	private final String TITLE = "Stamp Roll Tool by Uriel";
	public StampRollTool() {
		//Set the window shit
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		setVisible(true);
		setTitle(TITLE);
		//Initialize the labels
		stampWidthLabel = new JLabel("Stamp Width");
		stampHeightLabel = new JLabel("Stamp Height");
		patternWidthLabel = new JLabel("Image Width");
		patternHeightLabel = new JLabel("Image Height");
		directoryLabel = new JLabel("Directory");
		
		//Initialize the text fields
		stampWidthField = new JTextField();
		stampHeightField = new JTextField();
		patternWidthField = new JTextField();
		patternHeightField = new JTextField();
		directoryField = new JTextField();
		
		//Default values of the text fields
		stampWidthField.setText("11");
		stampHeightField.setText("11");
		patternWidthField.setText("64");
		patternHeightField.setText("64");
		directoryField.setText("C:/Users/walup/Documents/Imágenes/patron.png");
		
		//Initialize the buttons
		createPatternButton = new JButton("Create pattern");
		openStampWindowButton = new JButton("Open stamp Window");
		
		//We will add everything in a grid
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		listPane.setSize(WIDTH,HEIGHT);
		listPane.setVisible(true);
		add(listPane);
		
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(6,2));
		grid.add(stampWidthLabel);
		grid.add(stampWidthField);
		grid.add(stampHeightLabel);
		grid.add(stampHeightField);
		grid.add(patternWidthLabel);
		grid.add(patternWidthField);
		grid.add(patternHeightLabel);
		grid.add(patternHeightField);
		grid.add(directoryLabel);
		grid.add(directoryField);
		grid.add(openStampWindowButton);
		grid.add(createPatternButton);
		
		listPane.add(grid);
		
		//The listeners
		openStampWindowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				stampWindow = new StampWindow(Integer.parseInt(stampWidthField.getText()),Integer.parseInt(stampHeightField.getText()));
			}
			
		});
		
		createPatternButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int[][] stamp = stampWindow.getMesh();
				int[][] pattern = new int[Integer.parseInt(patternWidthField.getText())][Integer.parseInt(patternHeightField.getText())];
				
				int stampWidth = stamp.length;
				int stampHeight = stamp[0].length;
				for(int i = 0; i< pattern.length;i++) {
					for(int j = 0;j<pattern[0].length;j++) {
						pattern[i][j] = stamp[i%stampWidth][j%stampHeight];
					}
				}
				
				ArrayImageSocket socket = new ArrayImageSocket(pattern);
				ImagePlus image = socket.meshToGrayImage();
				image.show();
				FileSaver saver = new FileSaver(image);
				saver.saveAsPng(directoryField.getText());
				
			}
			
		});
		
		pack();
		
	}
	
	public static void main (String[] args) {
		new StampRollTool();
	}
	

}
