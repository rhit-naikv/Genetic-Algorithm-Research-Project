package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EvolutionMain {

	private Population pop;
	private int generations;

	public static final int DELAY = 50;

	public EvolutionMain() {
		JFrame frame = new JFrame("Evolution Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		EvolutionComponent component = new EvolutionComponent();
		component.setPreferredSize(new Dimension(600, 300));
		JButton start = new JButton("Start");
		start.addActionListener(new StartListener(this, component));
		panel.add(start);

		Timer t = new Timer(DELAY, new TimerListener(this, component));

		frame.add(panel, BorderLayout.SOUTH);
		frame.add(component, BorderLayout.CENTER);
		t.start();
		frame.pack();
		frame.setVisible(true);
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
