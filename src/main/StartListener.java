package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class StartListener implements ActionListener {
	private EvolutionMain main;
	private EvolutionComponent component;
	private JTextField bitStringLength;
	private JTextField popSize;
	private JTextField generations;

	public StartListener(EvolutionMain main, EvolutionComponent component, JTextField bitStringLength, JTextField popSize, JTextField generations) {
		this.main = main;
		this.component = component;
		this.bitStringLength = bitStringLength;
		this.popSize = popSize;
		this.generations = generations;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (this.main.getGenerations() == -1) {
			this.component.clear();
		}
		if (this.component.getWeakest().size()==0) {
			if (!this.bitStringLength.getText().isEmpty() && !this.popSize.getText().isEmpty()) {
				
				this.main.setPop(new Population(Integer.parseInt(this.popSize.getText()), Integer.parseInt(this.bitStringLength.getText())));
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
