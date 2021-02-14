package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

/*
 * Listener for the timer, runs per tick
 * 
 * @author Vidhu Naik and William Chong
 */
public class TimerListener implements ActionListener {

	private EvolutionMain main;
	private EvolutionComponent c;
	private JTextField mutationRate;
	private int counter;
	private JFrame frame;
	
	public TimerListener(EvolutionMain main, EvolutionComponent c, JTextField mutationRate, JFrame frame) {
		this.counter = 0;
		this.main = main;
		this.c = c;
		this.mutationRate = mutationRate;
		this.frame = frame;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (this.main.getPop() != null && this.main.getGenerations() >= 0 && this.main.getPause()==false) {
			if(counter >= 150) {
				this.main.setWidth(this.main.getWidth()+2);
				this.c.setPreferredSize(new Dimension(this.main.getWidth(), this.main.getHeight()));
				this.frame.pack();
			}
			this.main.getPop().fitnessSort();
			this.c.addEntry(this.main.getPop().getChromosomes().get(0).FitnessValue(),
					this.main.getPop().getChromosomes().get(this.main.getPop().getChromosomes().size() - 1)
							.FitnessValue(),
					this.main.getPop().getChromosomes().get(this.main.getPop().getChromosomes().size() / 2)
							.FitnessValue());
			if (this.mutationRate.getText().isEmpty()) {
				
				this.main.getPop().evolutionLoop(1);
			}
			else {
				this.main.getPop().evolutionLoop(Integer.parseInt(this.mutationRate.getText()));
			}
			this.c.addEntry(this.main.getPop().getChromosomes().get(0).FitnessValue(),
					this.main.getPop().getChromosomes().get(this.main.getPop().getChromosomes().size() - 1)
							.FitnessValue(),
					this.main.getPop().getChromosomes().get(this.main.getPop().getChromosomes().size() / 2)
							.FitnessValue());
			this.main.setGenerations(this.main.getGenerations() - 1);

		}
		counter++;
		c.repaint();

	}

}



