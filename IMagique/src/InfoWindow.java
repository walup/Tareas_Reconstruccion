import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import Image.Photo;

public class InfoWindow extends JFrame{
	
	
	private MainWindow parent;
	
	private JScrollPane fileScroller;
	private JList fileList;
	private JButton displayInfoButton;
	private JTextArea infoField;
	private JScrollPane textScrollPane;
	
	public final int WINDOW_WIDTH = 640;
	public final int WINDOW_HEIGHT = 360;
	
	String[] images;
	
	public InfoWindow(MainWindow parent) {
		//When the window is closed, we dispose it
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		setTitle("Get Image Info");
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		listPane.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		listPane.setVisible(true);
		add(listPane);
		//Store the parent
		this.parent = parent;
		infoField = new JTextArea();
		textScrollPane = new JScrollPane();
		textScrollPane.setViewportView(infoField);
		//Initialize the file scroller
		images = parent.getAlbum().getKeys();
		fileList = new JList(images);
		fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fileScroller = new JScrollPane(fileList);
		//Initialize the button
		displayInfoButton = new JButton("Display information");
		//Add everything to the listPane
		listPane.add(fileScroller);
		listPane.add(displayInfoButton);
		listPane.add(infoField);
		
		
		//The button listener
		displayInfoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//First we will get the selected option
				String selectedKey = images[fileList.getSelectedIndex()];
				//Now get the info
				Photo photo = parent.getAlbum().getPhoto(selectedKey);
				if(photo != null) {
					String info = photo.getInfo();
					infoField.setText("");
					infoField.setText(info);
					System.out.println("info" +info);
					pack();
				}
				else {
					return;
				}
			}
		
		});
		
		
		
		
		
	}

}
