package H2;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StampWindow extends JFrame{
	
	private JTextField[][] cells;
	private int width;
	private int height;
	
	
	private final int WIDTH = 300;
	private final int HEIGHT = 300;
	public StampWindow(int width, int height) {
		this.width = width;
		this.height = height;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setSize(WIDTH, HEIGHT);
		
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		listPane.setSize(WIDTH,HEIGHT);
		listPane.setVisible(true);
		add(listPane);
		
		//The grid 
		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(width,height));
		
		cells = new JTextField[width][height];
		
		for(int i = 0; i<width;i++) {
			for(int j = 0; j<height; j++) {
				JTextField field = new JTextField();
				field.setText("0");
				field.setPreferredSize(new Dimension(30,30));
				cells[i][j] = field;
				grid.add(field);
			}
		}
		
		listPane.add(grid);
		pack();
	}
	
	
	public int[][] getMesh(){
		int[][] mesh = new int[width][height];
		for(int i = 0;i<width;i++) {
			for(int j = 0;j<height;j++) {
				mesh[i][j] = Integer.parseInt(cells[i][j].getText());
			}
		}
		
		return mesh;
	}

}
