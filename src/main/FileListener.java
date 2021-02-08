package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileListener implements ActionListener {
	private JFrame frame;
	private JFileChooser chooser;
	
	public FileListener() {
		this.frame = new JFrame("Choose/Save File");
		this.chooser = new JFileChooser();
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
			
		}
		
		this.frame.add(this.chooser);
		this.frame.pack();
		this.frame.setVisible(true);
		
	}

}
