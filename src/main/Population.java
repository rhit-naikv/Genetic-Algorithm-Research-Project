package main;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/*
 * A population of chromosomes that can be sorted based on fitness and create the next generation 
 * 
 * @author Vidhu Naik and William Chong
 */
public class Population {

	//added instance variables
	private int elitismRate;
	private int fitnessSelect;
	private boolean crossover;
	private boolean roulette;
	private ArrayList<Chromosome> chromosomes = new ArrayList<>();

	/**
	 * added constructor for population
	 */
	public Population() {
		this.elitismRate = 0;
		this.fitnessSelect = 0;
		this.crossover = false;
		this.roulette = false;
		for (int i = 0; i < 100; i++) {
			ArrayList<Character> genes = new ArrayList<>();
			for (int j = 0; j < 100; j++) {
				if (Math.random() > 0.5) {
					genes.add('1');
				} else
					genes.add('0');

			}
			this.chromosomes.add(new Chromosome(genes));
		}
	}

	/**
	 * another constructor for population
	 * @param numChromosomes
	 * @param numGenes
	 * @param elitismRate
	 * @param fitnessSelect
	 * @param crossover
	 * @param roulette
	 */
	public Population(int numChromosomes, int numGenes, int elitismRate, int fitnessSelect, boolean crossover,
			boolean roulette) {
		this.roulette = roulette;
		this.elitismRate = elitismRate;
		this.fitnessSelect = fitnessSelect;
		this.crossover = crossover;
		for (int i = 0; i < numChromosomes; i++) {
			ArrayList<Character> genes = new ArrayList<>();
			if (fitnessSelect != 3) {
				for (int j = 0; j < numGenes; j++) {
					if (Math.random() > 0.5) {
						genes.add('1');
					} else
						genes.add('0');
				}
			}
			else {
				for (int j = 0; j < numGenes; j++) {
					if (Math.random() > 0.5) {
						genes.add('?');
					}
					else {
						if (Math.random() > 0.5) {
							genes.add('1');
						}
						else {
							genes.add('0');
						}
					}
					System.out.println(genes.get(j));
				}
			}
			this.chromosomes.add(new Chromosome(genes));
		}
	}

	/**
	 * added sorting method
	 */
	public void fitnessSort() {
		Comparator<Chromosome> rankOnes = new Comparator<Chromosome>() {
			public int compare(Chromosome c1, Chromosome c2) {
				return c1.fitnessOnes() - c2.fitnessOnes();
			}
		};
		Comparator<Chromosome> rankTarget = new Comparator<Chromosome>() {
			public int compare(Chromosome c1, Chromosome c2) {
				try {
					return c1.fitnessTarget() - c2.fitnessTarget();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
			}
		};
		Comparator<Chromosome> rankAlternate = new Comparator<Chromosome>() {
			public int compare(Chromosome c1, Chromosome c2) {
				return c1.fitnessAlternate() - c2.fitnessAlternate();
			}
		};
		Comparator<Chromosome> rankExperiment = new Comparator<Chromosome>() {
			public int compare(Chromosome c1, Chromosome c2) {
				return (int) (c1.fitnessExperiment() - c2.fitnessExperiment());
			}
		};
		if (this.fitnessSelect == 0) {
			Collections.sort(this.chromosomes, rankOnes);
		} else if (this.fitnessSelect == 1) {
			Collections.sort(this.chromosomes, rankTarget);
		} else  if (this.fitnessSelect == 2){
			Collections.sort(this.chromosomes, rankAlternate);
		} else {
			Collections.sort(this.chromosomes, rankExperiment);
		}

	}

	/**
	 * this is our evolutionary loop
	 * @param mutationRate
	 * @throws FileNotFoundException
	 */
	public void evolutionLoop(int mutationRate) throws FileNotFoundException {
		fitnessSort();
		int startingSize = this.chromosomes.size();
		Chromosome c;
		ArrayList<Chromosome> x = new ArrayList<>();
		for(int i = 0; i < startingSize; i ++) {
			x.add(this.chromosomes.get(i));
		}
		if (roulette) { // Roulette code
			int clones = (elitismRate * startingSize) / 100;
			for (int i = 0; i < startingSize - clones; i++) {
				if (crossover) {
					int chromosome1 = rouletteResult(startingSize,x);
					int chromosome2 = rouletteResult(startingSize,x);
					c = crossoverResult(chromosome1, chromosome2);
				} else {
					c = new Chromosome(chromosomes.get(rouletteResult(startingSize,x)).getGenes());
				}
				c.mutate(mutationRate);
				chromosomes.add(c);
			}
			for (int i = 0; i < clones; i++) {
				c = new Chromosome(chromosomes.get(99 - i).getGenes());
				c.mutate(mutationRate);
				chromosomes.add(c);
			}
			for (int i = 0; i < startingSize; i++) {
				chromosomes.remove(0);
			}

		} else {// Truncation code
			for (int i = 0; i < startingSize / 2; i++) {
				chromosomes.remove(0);
			}
			int clones = (elitismRate / 2);
			startingSize = this.chromosomes.size();

			for (int i = 0; i < 2 * startingSize - clones; i++) {
				if (crossover) {
					int chromosome1 = (int) (chromosomes.size() * Math.random());
					int chromosome2 = (int) (chromosomes.size() * Math.random());
					c = crossoverResult(chromosome1, chromosome2);
				} else {
					c = new Chromosome(chromosomes.get(i / 2).getGenes());
				}
				c.mutate(mutationRate);
				chromosomes.add(c);
			}
			for (int i = 0; i < startingSize - clones; i++) {
				chromosomes.remove(0);
			}
		}
	}

	/**
	 * created roulette wheel selection method
	 * @param startingSize
	 * @param x
	 * @return
	 */
	public int rouletteResult(int startingSize, ArrayList<Chromosome> x) {
		double total = 0;
		double[] fitness = new double[startingSize];
		HashMap<Chromosome, Double> probabilities = new HashMap<>();
		for (int i = 0; i < startingSize; i++) {
			try {
				fitness[i] = x.get(i).FitnessValue(this.fitnessSelect);
				total += fitness[i];
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < startingSize; i++) {
			double prob = 0;
			prob = fitness[i] / total;
			if (probabilities.isEmpty()) {
				probabilities.put(x.get(i), prob);
			} else {
				probabilities.put(x.get(i), probabilities.get(x.get(i - 1)) + prob);
			}
		}
		double finalValue = Math.random();
		for (int i = 0; i < startingSize; i++) {
			if (i == 0) {
				if (probabilities.get(x.get(0)) > finalValue) {
					return 0;
				}
			} else if (probabilities.get(x.get(i - 1)) < finalValue
					&& probabilities.get(x.get(i)) > finalValue) {
				return i;
			}
		}
		return startingSize - 1;
	}

	/**
	 * created method for crossover
	 * @param chromosome1
	 * @param chromosome2
	 * @return
	 */
	public Chromosome crossoverResult(int chromosome1, int chromosome2) {
		Chromosome c;
		ArrayList<Character> newGenes = new ArrayList<>();
		int crosspoint = (int) (chromosomes.get(0).getGenes().size() * Math.random());

		for (int j = 0; j < crosspoint; j++) {
			newGenes.add(chromosomes.get(chromosome1).getGenes().get(j));
		}
		for (int j = crosspoint; j < chromosomes.get(0).getGenes().size(); j++) {
			newGenes.add(chromosomes.get(chromosome2).getGenes().get(j));
		}
		c = new Chromosome(newGenes);
		return c;
	}

	/**
	 * created method for hamming distance
	 * @return
	 */
	public int getHammingDistance() {
		double averageHammingDistance = 0;
		for (int i = 0; i < this.chromosomes.size(); i++) {
			double hammingDistance = 0;
			for (int j = 0; j < this.chromosomes.size(); j++) {
				double counter = 0;
				if (i != j) {
					for (int k = 0; k < this.chromosomes.get(i).getGenes().size(); k++) {
						if (this.chromosomes.get(i).getGenes().get(k) != this.chromosomes.get(j).getGenes().get(k)) {
							counter++;
						}
					}
					hammingDistance += counter / this.chromosomes.get(i).getGenes().size();
				} 
			}
			averageHammingDistance += hammingDistance;
		}
		return (int) averageHammingDistance / this.chromosomes.size();
	}

	/**
	 * gets the chromosomes arraylist
	 * @return
	 */
	public ArrayList<Chromosome> getChromosomes() {
		return this.chromosomes;
	} 
	
	/**
	 * sets the selection method
	 * @param select
	 */
	public void setSelection(boolean select) {
		this.roulette = select;
	}

	/**
	 * draws the population
	 * @param g2
	 * @throws FileNotFoundException
	 */
	public void drawOn(Graphics2D g2) throws FileNotFoundException {
		fitnessSort();
		for (int i = 0; i < this.chromosomes.size(); i++) {
			g2.setColor(Color.GREEN);
			g2.setStroke(new BasicStroke(5));
			g2.drawLine(5 * i, 110 - this.chromosomes.get(i).FitnessValue(this.fitnessSelect), 5 * i,
					110 - this.chromosomes.get(i).FitnessValue(this.fitnessSelect));
		}
	}

	/**
	 * sets the fitness function
	 * @param fitnessSelect2
	 */
	public void setFitnessSelct(int fitnessSelect2) {
		this.fitnessSelect = fitnessSelect2;
		
	}

}
