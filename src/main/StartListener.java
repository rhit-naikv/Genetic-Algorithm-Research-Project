package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

/*
 * Listener for the start button in EvolutionMain
 * 
 * @author Vidhu Naik and William Chong
 */
public class StartListener implements ActionListener {
	//instance variables
	private EvolutionMain main;
	private EvolutionComponent component;
	private JTextField bitStringLength;
	private JTextField popSize;
	private JTextField generations;
	private JTextField elitismRate;
	private JComboBox<String> cb;
	private JComboBox<String> crossOver;
	private JComboBox<String> selection;
	private JFrame frame;
	
	/**
	 * constructor for the start listener
	 * @param main
	 * @param component
	 * @param frame
	 * @param bitStringLength
	 * @param popSize
	 * @param generations
	 * @param elitismRate
	 * @param cb
	 * @param crossOver 
	 */
	public StartListener(EvolutionMain main, EvolutionComponent component, JFrame frame, JTextField bitStringLength, JTextField popSize, JTextField generations, JTextField elitismRate, JComboBox<String> cb, JComboBox<String> crossOver, JComboBox<String> selection) {
		this.main = main;
		this.component = component;
		this.frame = frame;
		this.bitStringLength = bitStringLength;
		this.popSize = popSize;
		this.generations = generations;
		this.elitismRate = elitismRate;
		this.cb = cb;
		this.crossOver = crossOver;
		this.selection = selection;

	}

	/**
	 * the response to the start button being pressed. Contols the beginning of the evolution, pausing, and continuing. 
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
		else if (this.cb.getSelectedItem().equals("Check consecutive 0's")) {
			fitnessSelect = 2;
		}
		else {
			fitnessSelect = 3;
		}
		if (this.main.getGenerations() == -1) {
			this.component.clear();
			this.main.setWidth(600);
			Dimension d = new Dimension(this.main.getWidth(), 450);
			this.frame.setSize(d);
		}
		if (this.component.getWeakest().size()==0) {
			int popSizeInt = 0;
			int bitStringLengthInt = 0;
			int elitismRateInt = 0;
			boolean crossover = true;
			boolean selectionBoolean = true;
			if (!this.popSize.getText().isEmpty()) {
				popSizeInt = Integer.parseInt(this.popSize.getText());
				if (popSizeInt > 100) {
					popSizeInt = 100;
				}
			}
			else {
				popSizeInt = 100;
			}
			if (!this.bitStringLength.getText().isEmpty()) {
				bitStringLengthInt = Integer.parseInt(this.bitStringLength.getText());
				if (bitStringLengthInt > 100) {
					bitStringLengthInt = 100;
				}
				 
			}
			else {
				bitStringLengthInt = 100;
			}
			if (!this.elitismRate.getText().isEmpty()) {
				elitismRateInt = Integer.parseInt(this.elitismRate.getText());
			}
			else {
				elitismRateInt = 0;
			}
			if (this.crossOver.getSelectedItem().equals("YES")) {
				crossover = true;
			}
			else {
				crossover = false;
			}
			if (this.selection.getSelectedItem().equals("Truncation")) {
				selectionBoolean = false;
			}
			else {
				selectionBoolean = true;
			}
			this.main.setPop(new Population(popSizeInt, bitStringLengthInt, elitismRateInt, fitnessSelect, crossover, selectionBoolean));
			if (this.generations.getText().isEmpty()) {
				this.main.setGenerations(50);
			}
			else {
				this.main.setGenerations(Integer.parseInt(this.generations.getText())/2);
			}
		}
		if (this.main.getPause() == true) {
			this.main.setPause(false);
		} else if (this.component.getWeakest().size() != 0 && this.main.getPause() == false) {
			this.main.setPause(true);
		}
	}

}
