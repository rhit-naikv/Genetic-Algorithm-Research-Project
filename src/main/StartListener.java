package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartListener implements ActionListener {
	private EvolutionMain main;
	private EvolutionComponent component;

	public StartListener(EvolutionMain main, EvolutionComponent component) {
		this.main = main;
		this.component = component;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.main.setPop(new Population());
		this.main.setGenerations(200);
		this.component.clear();
	}

}
