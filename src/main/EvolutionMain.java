package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EvolutionMain {

	private Population pop;
	private int generations;
	private boolean pause;

	public static final int DELAY = 50;

	public EvolutionMain() {
		this.pause = false;
		JFrame frame = new JFrame("Evolution Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		EvolutionComponent component = new EvolutionComponent();
		component.setPreferredSize(new Dimension(600, 300));
		
		
		
		JTextArea bitStringLength = new JTextArea("Bit String Length: ");
		bitStringLength.setEditable(false);
		JTextField userBitStringLength = new JTextField(5);
		JTextArea mutationRate = new JTextArea("Mutation Rate: ");
		mutationRate.setEditable(false);
		JTextField userMutationRate = new JTextField(5);
		JTextArea popSize = new JTextArea("Population Size: ");
		popSize.setEditable(false);
		JTextField userPopSize = new JTextField(5);
		JTextArea generations = new JTextArea("generations: ");
		generations.setEditable(false);
		JTextField userGenerations = new JTextField(5);
		
		JButton start = new JButton("Start");
		start.addActionListener(new StartListener(this, component, userBitStringLength, userPopSize, userGenerations));

		Timer t = new Timer(DELAY, new TimerListener(this, component, userMutationRate));
		panel.add(bitStringLength);
		panel.add(userBitStringLength);
		panel.add(mutationRate);
		panel.add(userMutationRate);
		panel.add(popSize);
		panel.add(userPopSize);
		panel.add(generations);
		panel.add(userGenerations);
		panel.add(start);
		frame.add(panel, BorderLayout.SOUTH);
		frame.add(component, BorderLayout.CENTER);
		t.start();
		frame.pack();
		frame.setVisible(true);
	}
	
	public boolean getPause() {
		return this.pause;
	}
	public void setPause(boolean button) {
		this.pause = button;
	}

	public static void main(String[] args) {
		new EvolutionMain();
	}

	public Population getPop() {
		return this.pop;
	}

	public void setPop(Population pop) {
		this.pop = pop;
	}

	public void setGenerations(int generations) {
		this.generations = generations;
	}

	public int getGenerations() {
		return this.generations;
	}

}



