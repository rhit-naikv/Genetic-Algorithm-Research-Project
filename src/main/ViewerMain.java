package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Viewer class for chromosome editor
 * 
 * @author Vidhu Naik and William Chong
 *
 */
public class ViewerMain {
	private String fileName; //start with 100.txt
	private Chromosome chromosome;
	private ChromosomeComponent c;
	
	public ViewerMain() throws FileNotFoundException{
		JFrame frame = new JFrame("20.txt");
		JPanel buttonPanel = new JPanel();
		this.fileName = "20.txt";
		this.chromosome = new Chromosome(this.fileName);
		c = new ChromosomeComponent(this.chromosome);
		c.setPreferredSize(new Dimension(300, 300));
		JButton switchFiles = new JButton("Switch Files");
		switchFiles.addActionListener(new FileLoader(this.fileName, frame, c, this));
		JButton mutate = new JButton("Mutate");
		JTextField mutateText = new JTextField(5);
		JTextArea rateDisplay = new JTextArea("M Rate: _/N");
		mutate.addActionListener(new MutateListener(mutateText, c, this));
		
		
		
		
		
		
		buttonPanel.add(switchFiles);
		buttonPanel.add(mutate);
		buttonPanel.add(rateDisplay);
		buttonPanel.add(mutateText);
		
		
		
		
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(c, BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		new ViewerMain();
	}
	public void setFileName(String newName) throws FileNotFoundException {
		this.fileName = newName;
		this.chromosome = new Chromosome(this.fileName);
		c.setChromosome(this.chromosome);
		
	}
	public Chromosome getChromosome() {
		return this.chromosome;
	}
	public String getFileName() {
		return this.fileName;
	}
	

}