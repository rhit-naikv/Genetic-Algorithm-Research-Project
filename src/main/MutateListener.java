package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class MutateListener implements ActionListener{
	
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
			double probability = (double)mutationRate/(double)this.chromosome.getGenes().size();
			for (int i = 0; i < this.chromosome.getGenes().size(); i++) {
				if (Math.random() <= probability) {
					char flip = '0';
					if (this.chromosome.getGenes().get(i) == '1') {
						flip = '0';
					}
					else {
						flip = '1';
					}
					this.chromosome.getGenes().set(i, flip);
				}
			}
			c.repaint();
			
		}
		
		
	}

}
