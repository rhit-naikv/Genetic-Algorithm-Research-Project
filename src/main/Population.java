package main;

import java.util.ArrayList;
import java.util.Collections;

public class Population {

	private ArrayList<Chromosome> chromosomes = new ArrayList<>();

	public Population() {
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

	public Population(int numChromosomes, int numGenes) {
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
		startingSize = this.chromosomes.size();
		for (int i = 0; i < startingSize; i++) {
			Chromosome c = new Chromosome(chromosomes.get(i).getGenes());
			chromosomes.get(i).mutate(mutationRate);
			c.mutate(mutationRate);
			chromosomes.add(c);
		}
	}

	public ArrayList<Chromosome> getChromosomes() {
		return this.chromosomes;
	}

}