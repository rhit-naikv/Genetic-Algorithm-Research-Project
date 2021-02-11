package main;

import java.util.ArrayList;

public class Population {
	
	private ArrayList<Chromosome> chromosomes;
	
	public Population() {
		for (int i = 0; i < 100; i++) {
			this.chromosomes.add(new Chromosome(100));
		}
	}
	
	public Population(int numChromosomes, int numGenes) {
		for (int i = 0; i < numChromosomes; i++) {
			this.chromosomes.add(new Chromosome(numGenes));
		}
	}
	
	
	

}
