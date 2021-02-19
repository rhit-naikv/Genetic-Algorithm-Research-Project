package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * Will run and display the generation of evolution 
 * 
 * @author Vidhu Naik and William Chong
 */
public class EvolutionMain {

	//instance variables 
	private Population pop;
	private int generations;
	private boolean pause;
	private int width;
	private int height;
	private EvolutionComponent component;

	public static final int DELAY = 50;

	/**
	 * constucts the evolution main
	 */
	public EvolutionMain() {
		this.width = 600;
		this.height = 300;
		this.pause = false;
		JFrame frame = new JFrame("Evolution Viewer");
		JFrame fittest = new JFrame("Fittest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		EvolutionComponent component = new EvolutionComponent();
		component.setPreferredSize(new Dimension(this.width, this.height));		
		frame.setPreferredSize(new Dimension(this.width, this.height+80));	
		panel.setPreferredSize(new Dimension(this.width, 80));
		
		ChromosomeComponent c = new ChromosomeComponent();
		c.setPreferredSize(new Dimension(300, 300));
		fittest.add(c, BorderLayout.CENTER);
		
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
		
		JTextArea terminateCondition = new JTextArea("Termination Condition: ");
		terminateCondition.setEditable(false);
		JTextField userTerminateCondition = new JTextField(5);
		
		JTextArea elitismRate = new JTextArea("Elitism Rate: ");
		elitismRate.setEditable(false);
		JTextField userElitismRate = new JTextField(5);
		
		String[] selectionChoices = {"Check for 1's", "Check for target Chromosome", "Check consecutive 0's"};
		JTextArea comboBox = new JTextArea("Function: ");
	    JComboBox<String> cb = new JComboBox<String>(selectionChoices);
	    
	    JTextArea crossOver = new JTextArea("Crossover? ");
	    String[] crossOverChoices = {"YES", "NO"};
	    JComboBox<String> userCrossOver = new JComboBox<String>(crossOverChoices);
	    
		JButton start = new JButton("Start");
		start.addActionListener(new StartListener(this, component, frame, userBitStringLength, userPopSize, userGenerations, userElitismRate, cb, userCrossOver));

		Timer t = new Timer(DELAY, new TimerListener(this, component, userMutationRate, frame, panel, userTerminateCondition, cb, c));
		
		panel.add(bitStringLength);
		panel.add(userBitStringLength);
		panel.add(mutationRate);
		panel.add(userMutationRate);
		panel.add(popSize);
		panel.add(userPopSize);
		panel.add(generations);
		panel.add(userGenerations);
		panel.add(terminateCondition);
		panel.add(userTerminateCondition);
		panel.add(elitismRate);
		panel.add(userElitismRate);
		panel.add(comboBox);
		panel.add(cb);
		panel.add(crossOver);
		panel.add(userCrossOver);
		panel.add(start);
		frame.add(panel, BorderLayout.SOUTH);
		frame.add(component, BorderLayout.CENTER);
		t.start();
		frame.pack();
		frame.setVisible(true);
		fittest.pack();
		fittest.setVisible(true);
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setHeight(int value) {
		this.height = value;
	}
	
	public void setWidth(int value) {
		this.width = value;
	}
	
	public EvolutionComponent getComponent() {
		return component;
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



