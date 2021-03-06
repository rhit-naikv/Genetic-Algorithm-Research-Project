package main; 

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JComponent;

/*
 * Draws on the weakest, strongest, and average of each generation
 * 
 * @author Vidhu Naik and William Chong 
 */
public class EvolutionComponent extends JComponent {

	//define arraylists for certain chromosomes 
	private ArrayList<Integer> weakest = new ArrayList<>();
	private ArrayList<Integer> strongest = new ArrayList<>();
	private ArrayList<Integer> average = new ArrayList<>();
	private ArrayList<Integer> hamming = new ArrayList<>();
	private ArrayList<Integer> ones = new ArrayList<>();
	private ArrayList<Integer> zeroes = new ArrayList<>();
	private ArrayList<Integer> questions = new ArrayList<>();

	/**
	 * adds an entry to the arrayLists
	 * @param weakest
	 * @param strongest
	 * @param average
	 */
	public void addEntry(int weakest, int strongest, int average, int hammingDistance, int one, int zero, int question) {
		this.weakest.add(weakest);
		this.strongest.add(strongest);
		this.average.add(average);
		this.hamming.add(hammingDistance);
		this.ones.add(one);
		this.zeroes.add(zero);
		this.questions.add(question);
	}

	/**
	 * clears the array lists
	 */
	public void clear() {
		this.weakest.clear();
		this.strongest.clear();
		this.average.clear();
		this.hamming.clear();
		this.ones.clear();
		this.zeroes.clear();
		this.questions.clear();
	}
	
	/**
	 * gets the list of historically weak chromosomes
	 * @return
	 */
	public ArrayList<Integer> getWeakest(){
		return this.weakest;
	}

	/**
	 * displays the strongest, weakest, and average chromosome over time
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		if (!this.weakest.isEmpty()) {
			for (int i = 0; i < this.weakest.size() - 1; i++) {
				g2.setColor(Color.BLACK);
				g2.drawLine(25, 255, 5000, 255);
				g2.drawLine(25, 45, 5000, 45);
				g2.drawLine(25, 50, 25, 500);
				for (int j = 0; j <= 10; j++) {
					String s = "" + j * 10;
					g2.drawString(s, 2, 255 - 20 * j);
				}
				for (int j = 1; j <= 60; j++) {
					String s = "" + j * 50;
					g2.drawString(s, 25 + j * 100, 265);
				}
				g2.setStroke(new BasicStroke(2));
				
				
				
				
				
				
				g2.setColor(Color.MAGENTA);
				g2.drawString("1's", 250, 10);
				g2.drawLine(25 + (2 * i), 250 - (2*this.ones.get(i)), 25 + (2 * (i + 1)),
						250 - (2*this.ones.get(i + 1)));
				
				g2.setColor(Color.CYAN);
				g2.drawString("0's", 310, 10);
				g2.drawLine(25 + (2 * i), 250 - (2*this.zeroes.get(i)), 25 + (2 * (i + 1)),
						250 - (2*this.zeroes.get(i + 1)));
				
				g2.setColor(Color.BLACK);
				g2.drawString("?'s", 370, 10);
				g2.drawLine(25 + (2 * i), 250 - (2*this.questions.get(i)), 25 + (2 * (i + 1)),
						250 - (2*this.questions.get(i + 1)));
				
				g2.setColor(Color.RED);
				g2.drawString("Weakest", 10, 10);
				g2.drawLine(25 + (2 * i), 250 - (2 * this.weakest.get(i)), 25 + (2 * (i + 1)),
						250 - (2 * this.weakest.get(i + 1)));
				
				g2.setColor(Color.GREEN);
				g2.drawString("Strongest", 70, 10);
				g2.drawLine(25 + (2 * i), 250 - (2 * this.strongest.get(i)), 25 + (2 * (i + 1)),
						250 - (2 * this.strongest.get(i + 1)));
				
				g2.setColor(Color.BLUE);
				g2.drawString("Average", 130, 10);
				g2.drawLine(25 + (2 * i), 250 - (2 * this.average.get(i)), 25 + (2 * (i + 1)),
						250 - (2 * this.average.get(i + 1)));
				g2.setColor(Color.ORANGE);
				g2.drawString("HammingDistance", 190, 10);
				g2.drawLine(25 + (2 * i), 250 - (2*this.hamming.get(i)), 25 + (2 * (i + 1)),
						250 - (2*this.hamming.get(i + 1)));
				

			}
		}
	}
}




