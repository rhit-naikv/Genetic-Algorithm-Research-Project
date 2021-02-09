package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class FileLoader implements ActionListener {
	private JTextArea chromosomeName;
	private JFileChooser chooser;
	private ViewerMain main;
	private ChromosomeComponent c;
	
	public FileLoader(ViewerMain main, ChromosomeComponent c, JTextArea chromosomeName) {
		this.chromosomeName = chromosomeName;
		this.chooser = new JFileChooser();
		this.main = main;
		this.c = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.chooser.setCurrentDirectory(new File("."));
		this.chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int response = chooser.showOpenDialog(null);
		File file;
		if (response == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			try {
				this.main.setFileName(file.getName());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.chromosomeName.setText(file.getName());
		}
		this.c.repaint();		
	}

}
