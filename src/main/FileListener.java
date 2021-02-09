package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileListener implements ActionListener {
	private JFrame mainFrame;
	private JFrame frame;
	private JFileChooser chooser;
	private ViewerMain main;
	private ChromosomeComponent c;
	
	public FileListener(ViewerMain main, ChromosomeComponent c, JFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.frame = new JFrame("Choose/Save File");
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
			
			this.mainFrame.setTitle(file.getName());
		}
		this.c.repaint();
		
		
		this.frame.add(this.chooser);
		this.frame.pack();
		this.frame.setVisible(true);
		
	}

}
