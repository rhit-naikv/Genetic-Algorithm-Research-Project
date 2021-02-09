package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

public class FileSaver implements ActionListener {
	
	private Chromosome chromosome;
	private ViewerMain main;
	private JFileChooser chooser;
	
	public FileSaver(ViewerMain main) {
		this.main = main;
		this.chooser = new JFileChooser();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.chromosome = this.main.getChromosome();
		// TODO Auto-generated method stub
		this.chooser.setCurrentDirectory(new File("."));
		this.chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int response = this.chooser.showSaveDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			try {
				BufferedWriter fw = new BufferedWriter(new FileWriter(chooser.getSelectedFile()+".txt"));
				for(int i = 0; i < this.chromosome.getGenes().size(); i++) {
					fw.write(this.chromosome.getGenes().get(i));
					fw.newLine();
				}
				fw.close();
				

				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}

}