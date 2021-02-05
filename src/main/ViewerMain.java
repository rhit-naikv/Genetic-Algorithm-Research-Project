package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Viewer class for chromosome editor
 * 
 * @author Vidhu Naik and William Chong
 *
 */
public class ViewerMain {
	private String fileName; //start with 100.txt
	private Chromosome chromosome;
	
	public ViewerMain() throws FileNotFoundException{
		JFrame frame = new JFrame("100.txt");
		JPanel buttonPanel = new JPanel();
		this.fileName = "100.txt";
		this.chromosome = new Chromosome(this.fileName);
		ChromosomeComponent c = new ChromosomeComponent(this);
		c.setPreferredSize(new Dimension(100, 100));
		JButton switchFiles = new JButton("Switch Files");
		switchFiles.addActionListener(new FileLoader(this.fileName, frame, c, this));
		JButton mutate = new JButton("Mutate");
		
		
		
		
		
		
		buttonPanel.add(switchFiles);
		buttonPanel.add(mutate);
		
		
		
		
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(c, BorderLayout.NORTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		new ViewerMain();
	}
	public void setFileName(String newName) throws FileNotFoundException {
		this.fileName = newName;
		this.chromosome = new Chromosome(this.fileName);
	}
	public Chromosome getChromosome() {
		return this.chromosome;
	}
	public String getFileName() {
		return this.fileName;
	}
	

}