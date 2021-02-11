package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {

	private EvolutionMain main;
	private EvolutionComponent c;

	public TimerListener(EvolutionMain main, EvolutionComponent c) {
		this.main = main;
		this.c = c;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (this.main.getPop() != null && this.main.getGenerations() > 0) {
			this.main.getPop().fitnessSort();
			this.c.addEntry(this.main.getPop().getChromosomes().get(0).FitnessValue(),
					this.main.getPop().getChromosomes().get(this.main.getPop().getChromosomes().size() - 1)
							.FitnessValue(),
					this.main.getPop().getChromosomes().get(this.main.getPop().getChromosomes().size() / 2)
							.FitnessValue());
			this.main.getPop().evolutionLoop(1);
			this.c.addEntry(this.main.getPop().getChromosomes().get(0).FitnessValue(),
					this.main.getPop().getChromosomes().get(this.main.getPop().getChromosomes().size() - 1)
							.FitnessValue(),
					this.main.getPop().getChromosomes().get(this.main.getPop().getChromosomes().size() / 2)
							.FitnessValue());
			this.main.setGenerations(this.main.getGenerations() - 1);

		}
		c.repaint();

	}

}



