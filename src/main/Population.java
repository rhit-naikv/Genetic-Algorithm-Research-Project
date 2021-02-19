package main;

import java.util.ArrayList;
import java.util.Collections;

/*
 * A population of chromosomes that can be sorted based on fitness and create the next generation
 * 
 * @author Vidhu Naik and William Chong
 */
public class Population {
	
	private int elitismRate;
	private ArrayList<Chromosome> chromosomes = new ArrayList<>();

	public Population() {
		this.elitismRate = 0;
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

	public Population(int numChromosomes, int numGenes, int elitismRate) {
		this.elitismRate = elitismRate;
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
		Collections.sort(this.chromosomes);
	}

	public void evolutionLoop(int mutationRate) {
		fitnessSort();
		int startingSize = this.chromosomes.size();
		for (int i = 0; i < startingSize / 2; i++) {
			chromosomes.remove(0);
		}
		int clones = (elitismRate/2);
		startingSize = this.chromosomes.size();
		for (int i = 0; i < 2*startingSize - clones; i++) {
			Chromosome c = new Chromosome(chromosomes.get(i/2).getGenes());
			chromosomes.get(i/2).mutate(mutationRate);
			c.mutate(mutationRate);
			chromosomes.add(c);
		}
		for(int i = 0; i < startingSize - clones; i ++) {
			chromosomes.remove(0);
		}
	}

	public ArrayList<Chromosome> getChromosomes() {
		return this.chromosomes;
	}

}



