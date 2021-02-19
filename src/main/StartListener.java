package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/*
 * Listener for the start button in EvolutionMain
 * 
 * @author Vidhu Naik and William Chong
 */
public class StartListener implements ActionListener {
	private EvolutionMain main;
	private EvolutionComponent component;
	private JTextField bitStringLength;
	private JTextField popSize;
	private JTextField generations;
	private JTextField elitismRate;
	private JComboBox<String> cb;
	private JComboBox<String> crossOver;

	public StartListener(EvolutionMain main, EvolutionComponent component, JTextField bitStringLength, JTextField popSize, JTextField generations, JTextField elitismRate, JComboBox<String> cb, JComboBox<String> crossOver) {
		this.main = main;
		this.component = component;
		this.bitStringLength = bitStringLength;
		this.popSize = popSize;
		this.generations = generations;
		this.elitismRate = elitismRate;
		this.cb = cb;
		this.crossOver = crossOver;

	}

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
		if (this.main.getGenerations() == -1) {
			this.component.clear();
		}
		if (this.component.getWeakest().size()==0) {
			if (!this.bitStringLength.getText().isEmpty() && !this.popSize.getText().isEmpty() && !this.elitismRate.getText().isEmpty()) {
				if (this.crossOver.getSelectedItem().equals("YES")) {
					this.main.setPop(new Population(Integer.parseInt(this.popSize.getText()), Integer.parseInt(this.bitStringLength.getText()), Integer.parseInt(this.elitismRate.getText()), fitnessSelect, true));
				}
				else {
					this.main.setPop(new Population(Integer.parseInt(this.popSize.getText()), Integer.parseInt(this.bitStringLength.getText()), Integer.parseInt(this.elitismRate.getText()), fitnessSelect, false));

				}
			}
			else {
				this.main.setPop(new Population());

			}
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
