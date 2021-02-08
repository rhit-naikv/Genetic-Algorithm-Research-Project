package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

public class FileLoader implements ActionListener {
	private String fileName;
	private ViewerMain main;
	private JFrame frame;
	private ChromosomeComponent c;
	
	public FileLoader(String fileName, JFrame frame, ChromosomeComponent c, ViewerMain main) {
		this.fileName = fileName;
		this.frame = frame;
		this.main = main;
		this.c = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (this.fileName.equals("100.txt")) {
			this.fileName = "20.txt";
		}
		else {
			this.fileName = "100.txt";
		}
		try {
			main.setFileName(this.fileName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.setTitle(this.fileName);
		c.repaint();
	}

}
