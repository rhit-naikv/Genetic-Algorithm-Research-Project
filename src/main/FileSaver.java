package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

/**
 * 
 * opens the file saver so that we can save chromosomes 
 * @author Vidhu Naik and William Chong
 *
 */
public class FileSaver implements ActionListener {

	//instance variables
	private Chromosome chromosome;
	private ViewerMain main;
	private JFileChooser chooser;

	/**
	 * constructs the filesaver
	 * @param main
	 */
	public FileSaver(ViewerMain main) {
		this.main = main;
		this.chooser = new JFileChooser();
	}

	/**
	 * responds to when the save button is pushed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.chromosome = this.main.getChromosome();
		// TODO Auto-generated method stub
		this.chooser.setCurrentDirectory(new File("."));
		this.chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int response = this.chooser.showSaveDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			try {
				BufferedWriter fw = new BufferedWriter(new FileWriter(chooser.getSelectedFile() + ".txt"));
				for (int i = 0; i < this.chromosome.getGenes().size(); i++) {
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
