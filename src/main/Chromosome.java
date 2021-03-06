package main;

import java.awt.Color; 
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Creates and modifies chromosomes, this includes # of genes and mutations
 * 
 * @author Vidhu Naik and William Chong
 */
public class Chromosome {

	//instance variables holding the genes, image of the genes, and the chromosome file name
	private ArrayList<Character> genes;
	private ArrayList<Rectangle2D.Double> geneImage;
	private String chromosomeFileName;
	private double experimentFitness;

	/**
	 * constructs the chromosome when given a file name
	 * @param chromosomeFileName
	 * @throws FileNotFoundException
	 */
	public Chromosome(String chromosomeFileName) throws FileNotFoundException {
		this.experimentFitness = -1;
		this.chromosomeFileName = chromosomeFileName;
		this.genes = new ArrayList<>();
		this.geneImage = new ArrayList<>();
		FileReader file;
		file = new FileReader(this.chromosomeFileName);
		Scanner scanner = new Scanner(file);
		int lineNumber = 0;
		while (scanner.hasNext()) {
			lineNumber++;
			String line = scanner.nextLine();
			genes.add(line.charAt(0));
		}
		for (int i = 0, j = 0; i < this.genes.size(); i++) {
			if (i % 10 == 0 && i != 0) {
				j += 30;
			}
			Rectangle2D.Double newGene = new Rectangle2D.Double(30 * (i % 10), j, 30, 30);
			this.geneImage.add(newGene);
		}

	}

	/**
	 * constucts the chromosome when given what genes we want
	 * @param genes
	 */
	public Chromosome(ArrayList<Character> genes) {
		this.experimentFitness = -1;
		ArrayList<Character> genes1 = new ArrayList<>();
		this.geneImage = new ArrayList<>();
		for (int i = 0; i < genes.size(); i++) {
			genes1.add(genes.get(i));
		}
		this.genes = genes1;
		for (int i = 0, j = 0; i < this.genes.size(); i++) {
			if (i % 10 == 0 && i != 0) {
				j += 30;
			}
			Rectangle2D.Double newGene = new Rectangle2D.Double(30 * (i % 10), j, 30, 30);
			this.geneImage.add(newGene);
		}
	}
	
	
	/**
	 * determines how fit a chromosome is based on the number of 1's
	 * @return
	 */
	public int fitnessOnes() {
		int counter = 0;
		for (Character gene : genes) {
			if (gene == '1') {
				counter++;
			}
		}
		return counter;
	}

	
	/**
	 * determines how fit a chromosome is based on it's accuracy to a target chromosome
	 * @return
	 * @throws FileNotFoundException
	 */
	public int fitnessTarget() throws FileNotFoundException {
		Chromosome chromosome = new Chromosome("creeperawman.txt");
		int counter = 0;
		for (int i = 0; i < this.genes.size(); i++) {
			if (this.genes.get(i)==chromosome.getGenes().get(i)) {
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * determines how fit a chromosome is based on the number of consecutive 0's
	 * @return
	 */
	public int fitnessAlternate() {
		int counter = 0;
		int max = 0;
		for (int i = 0; i < this.genes.size(); i++) {
			if (this.genes.get(i)=='0') {
				counter++;
			}
			else {
				if (counter > max) {
					max = counter; 
				}
				counter = 0;
			}
		}
		return max;
	}
	
	public double fitnessExperiment() {
		ArrayList<Character> genesCopy = new ArrayList<>();
		for (int i = 0; i < this.genes.size(); i++) {
			genesCopy.add(this.genes.get(i));
		}
		double dayThousandReached = 0;
		for (int i = 0; i < 1000; i++) {
			int counter = 0;
			for (int j = 0; j < this.genes.size(); j++) {
				if (this.genes.get(j) == '?') {
					if (Math.random() > 0.5) {
						genesCopy.set(j, '1');
					}
					else {
						genesCopy.set(j, '0');
					}
				}
				if (genesCopy.get(j) == '1') {
					counter++;
				}
			}
			
			if (counter == this.genes.size()) {
				dayThousandReached = i;
				break;
			}
			else {
				dayThousandReached = 1000;
			}
		}
		if(this.experimentFitness == -1) {
			this.experimentFitness = (int)(5*(1.0 + (19.0*(1000.0 - dayThousandReached))/1000.0));
		}
		return this.experimentFitness;
		
	}
	
	/**
	 * returns the fitness value of the chromosome based on what fitness function the user wants to use.
	 * @param fitnessSelect
	 * @return
	 * @throws FileNotFoundException
	 */
	public int FitnessValue(int fitnessSelect) throws FileNotFoundException {
		if (fitnessSelect == 0) {
			return fitnessOnes();
		}
		else if (fitnessSelect == 1) {
			return fitnessTarget();
		}
		else if (fitnessSelect == 2) {
			return fitnessAlternate();
		}
		else {
			return (int) fitnessExperiment();
		}
	}

	/**
	 * mutates the chromosome
	 * @param value
	 */
	public void mutate(int value) {
		double probability = (double) value / genes.size();
		for (int i = 0; i < this.genes.size(); i++) {
			if (Math.random() <= probability) {
				if (genes.get(i) == '1') {
					genes.set(i, '0');
				} else
					genes.set(i, '1');
			}
		}
	}

	/**
	 * gets the genes of the chromosome
	 * @return
	 */
	public ArrayList<Character> getGenes() {
		return this.genes;
	}
	
	public int getOnes() {
		int counter = 0;
		for (int i = 0; i < this.genes.size(); i++) {
			if (this.genes.get(i) == '1') {
				counter++;
			}
		}
		return counter;
	}
	
	public int getZeroes() {
		int counter = 0;
		for (int i = 0; i < this.genes.size(); i++) {
			if (this.genes.get(i) == '0') {
				counter++;
			}
		}
		return counter;
	}
	
	public int getQuestions() {
		int counter = 0;
		for (int i = 0; i < this.genes.size(); i++) {
			if (this.genes.get(i) == '?') {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * gets the gene image of the chromosome (the square sizes)
	 * @return
	 */
	public ArrayList<Rectangle2D.Double> getGeneImage() {
		return this.geneImage;
	}

	/**
	 * draws the chromosome  
	 * @param g2
	 */
	public void drawOn(Graphics2D g2) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.genes.size(); i++) {
			if (this.genes.get(i) == '0') {
				g2.setColor(Color.BLACK);
			}
			else if (this.genes.get(i) == '?') {
				g2.setColor(Color.ORANGE);
			}
			else {
				g2.setColor(Color.GREEN);
			}
			g2.fill(this.geneImage.get(i));
			g2.setColor(Color.YELLOW);
			g2.draw(this.geneImage.get(i));
		}
	}

	
}


