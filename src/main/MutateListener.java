package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class MutateListener implements ActionListener {

	private Chromosome chromosome;
	private JTextField mutateText;
	private ChromosomeComponent c;
	private ViewerMain main;

	public MutateListener(JTextField mutateText, ChromosomeComponent c, ViewerMain main) {
		this.main = main;
		this.chromosome = null;

		this.mutateText = mutateText;
		this.c = c;

	}

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
