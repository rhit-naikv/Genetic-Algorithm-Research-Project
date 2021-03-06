package main; 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

/*
 * Listens to the mutate button
 * 
 * @author Vidhu Naik and William Chong 
 */
public class MutateListener implements ActionListener {

	//instance variables
	private Chromosome chromosome;
	private JTextField mutateText;
	private ChromosomeComponent c;
	private ViewerMain main;

	//constructs the mutate listener
	public MutateListener(JTextField mutateText, ChromosomeComponent c, ViewerMain main) {
		this.main = main;
		this.chromosome = null;

		this.mutateText = mutateText;
		this.c = c;

	}
	
	/**
	 * responds to the mutate button being pushed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.chromosome = main.getChromosome();
		if (!mutateText.getText().isEmpty()) {
			int mutationRate = Integer.parseInt(mutateText.getText());
			this.chromosome.mutate(mutationRate);
		}
		c.repaint();
	}

}
