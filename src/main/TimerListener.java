package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Listener for the timer, runs per tick 
 * 
 * @author Vidhu Naik and William Chong
 */
public class TimerListener implements ActionListener {

	// declare all instance variables
	private EvolutionMain main;
	private EvolutionComponent c;
	private JTextField mutationRate;
	private int counter;
	private JFrame frame;
	private JTextField stopValue;
	private JComboBox<String> cb;
	private ChromosomeComponent chromosomeC;

	/**
	 * constuctor for the timer listener
	 * @param main
	 * @param c
	 * @param mutationRate
	 * @param frame
	 * @param panel
	 * @param stopValue
	 * @param cb
	 * @param chromosomeC
	 */
	public TimerListener(EvolutionMain main, EvolutionComponent c, JTextField mutationRate, JFrame frame,
			JPanel panel, JTextField stopValue, JComboBox<String> cb, ChromosomeComponent chromosomeC) {
		this.counter = 0;
		this.main = main;
		this.c = c;
		this.mutationRate = mutationRate;
		this.frame = frame;
		this.stopValue = stopValue;
		this.cb = cb;
		this.chromosomeC = chromosomeC;

	}

	/**
	 * response to event. Keeps track of the progress of the evolution
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		int fitnessSelect = 0;
		if (this.cb.getSelectedItem().equals("Check for 1's")) {
			fitnessSelect = 0;
		}
		else if (this.cb.getSelectedItem().equals("Check for target Chromosome")) {
			fitnessSelect = 1;
		}
		else {
			fitnessSelect = 2;
		}
		
		if(this.main.getGenerations() == 0) {
			this.counter = 0;
		}
		if (this.main.getPop() != null && this.main.getGenerations() >= 0 && this.main.getPause() == false) {
			if (counter >= 250) {
				this.main.setWidth(this.main.getWidth() + 4);
				Dimension d = new Dimension(this.main.getWidth(), 450);
				this.frame.setSize(d);
			}
			this.main.getPop().fitnessSort();
			try {
				this.c.addEntry(this.main.getPop().getChromosomes().get(0).FitnessValue(fitnessSelect),
						this.main.getPop().getChromosomes().get(this.main.getPop().getChromosomes().size() - 1)
								.FitnessValue(fitnessSelect),
						this.main.getPop().getChromosomes().get(this.main.getPop().getChromosomes().size() / 2)
								.FitnessValue(fitnessSelect));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			counter+=2;
			if (this.mutationRate.getText().isEmpty()) {

				try {
					this.main.getPop().evolutionLoop(1);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				try {
					this.main.getPop().evolutionLoop(Integer.parseInt(this.mutationRate.getText()));
				} catch (NumberFormatException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			try {
				this.c.addEntry(this.main.getPop().getChromosomes().get(0).FitnessValue(fitnessSelect),
						this.main.getPop().getChromosomes().get(this.main.getPop().getChromosomes().size() - 1)
								.FitnessValue(fitnessSelect),
						this.main.getPop().getChromosomes().get(this.main.getPop().getChromosomes().size() / 2)
								.FitnessValue(fitnessSelect));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.main.setGenerations(this.main.getGenerations() - 1);
			if (this.stopValue.getText().isEmpty()) {
				try {
					if(this.main.getPop().getChromosomes().get(this.main.getPop().getChromosomes().size() - 1).FitnessValue(fitnessSelect)>=100) {
						this.main.setGenerations(0);
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block 
					e1.printStackTrace();
				}
			} else
				try {
					if (this.main.getPop().getChromosomes().get(this.main.getPop().getChromosomes().size() - 1).FitnessValue(fitnessSelect) >= Integer.parseInt(this.stopValue.getText())) {
						this.main.setGenerations(0);
					}
				} catch (NumberFormatException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.chromosomeC.setChromosome(this.main.getPop().getChromosomes().get(this.main.getPop().getChromosomes().size()-1));

			this.chromosomeC.repaint();
		}
		c.repaint();
	}

}
