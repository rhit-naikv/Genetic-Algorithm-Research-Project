package main;

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

	private int elitismRate;
	private int fitnessSelect;
	private boolean crossover;
	private ArrayList<Chromosome> chromosomes = new ArrayList<>();

	public Population() {
		this.elitismRate = 0;
		this.fitnessSelect = 0;
		this.crossover = false;
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

	public Population(int numChromosomes, int numGenes, int elitismRate, int fitnessSelect, boolean crossover) {
		this.elitismRate = elitismRate;
		this.fitnessSelect = fitnessSelect;
		this.crossover = crossover;
		for (int i = 0; i < numChromosomes; i++) {
			ArrayList<Character> genes = new ArrayList<>();
			for (int j = 0; j < numGenes; j++) {
				if (Math.random() > 0.5) {
					genes.add('1');
				} else
					genes.add('0');

			}
			this.chromosomes.add(new Chromosome(genes));
		}
	}

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
		if (this.fitnessSelect == 0) {
			Collections.sort(this.chromosomes, rankOnes);
		} else if (this.fitnessSelect == 1) {
			Collections.sort(this.chromosomes, rankTarget);
		} else {
			Collections.sort(this.chromosomes, rankAlternate);
		}

	}

	public void evolutionLoop(int mutationRate) throws FileNotFoundException {
		fitnessSort();
		int startingSize = this.chromosomes.size();
		int total = 0;
		for (int i = 0; i < startingSize; i++) {
			total += this.chromosomes.get(i).FitnessValue(this.fitnessSelect);
		}
		int sumProb = 0;
		for (int i = 0; i < startingSize; i++) {
		}

		for (int i = 0; i < startingSize / 2; i++) {
			chromosomes.remove(0);
		}
		int clones = (elitismRate / 2);
		startingSize = this.chromosomes.size();

		for (int i = 0; i < 2 * startingSize - clones; i++) {
			Chromosome c;
			if(crossover) {
				ArrayList <Character> newGenes = new ArrayList<>();
				int crosspoint = (int)(chromosomes.get(0).getGenes().size()*Math.random());
				int chromosome1 = (int)(chromosomes.size()*Math.random());
				int chromosome2 = (int)(chromosomes.size()*Math.random());
				for(int j = 0; j < crosspoint; j ++) {
					newGenes.add(chromosomes.get(chromosome1).getGenes().get(j));
				}
				for(int j = crosspoint; j < chromosomes.get(0).getGenes().size(); j ++) {
					newGenes.add(chromosomes.get(chromosome2).getGenes().get(j));
				}
				c = new Chromosome(newGenes);
			}
			else{
				c = new Chromosome(chromosomes.get(i / 2).getGenes());
			}
			c.mutate(mutationRate);
			chromosomes.add(c);
		}
		for (int i = 0; i < startingSize - clones; i++) {
			chromosomes.remove(0);
		}
	}

	public ArrayList<Chromosome> getChromosomes() {
		return this.chromosomes;
	}

}
