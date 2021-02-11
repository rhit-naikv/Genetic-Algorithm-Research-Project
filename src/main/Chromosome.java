package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Chromosome implements Comparable<Chromosome> {

	private ArrayList<Character> genes;
	private ArrayList<Rectangle2D.Double> geneImage;
	private String chromosomeFileName;

	public Chromosome(String chromosomeFileName) throws FileNotFoundException {

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

	public Chromosome(ArrayList<Character> genes) {
		this.genes = genes;
	}

	public int FitnessValue() {
		int counter = 0;
		for (Character gene : genes) {
			if (gene == 1) {
				counter++;
			}
		}
		return counter;
	}

	public int FitnessMatchValue(Chromosome other) {
		int counter = 0;
		for (int i = 0; i < this.genes.size(); i++) {
			if (other.getGenes().get(i) == this.getGenes().get(i)) {
				counter++;
			}
		}
		return counter;
	}

	public int FitnessSpecialValue(int index, int counter, int maxCounter) {
		for(int i = index; i < this.genes.size(); i ++) {
			if(index==genes.size()-1) {
				if(genes.get(index-1)=='0') {
					if(genes.get(index)=='1') {
						return 1;
					}
					else return 0;
				}
				else if(genes.get(index)=='1') {
					return 0;
				}
				else return 1;
			}
			
			if(counter > maxCounter) {
				maxCounter = counter;
			}
			
			if(genes.get(i)=='1'&&genes.get(i+1)=='0') {
					return 1 + FitnessSpecialValue(index+1,counter+1,maxCounter);
			}
			else if(genes.get(i+1)=='1') {
					return 1 + FitnessSpecialValue(index+1, counter+1,maxCounter);
			}
			
		}
		return FitnessSpecialValue(index+1, 0, maxCounter);
	}

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

	public ArrayList<Character> getGenes() {
		return this.genes;
	}

	public ArrayList<Rectangle2D.Double> getGeneImage() {
		return this.geneImage;
	}

	public void drawOn(Graphics2D g2) {
		// TODO Auto-generated method stub
		for (int i = 0, j = 0; i < this.genes.size(); i++) {
			if (i % 10 == 0 && i != 0) {
				j += 30;
			}
			if (this.genes.get(i) == '0') {
				g2.setColor(Color.BLACK);
			} else {
				g2.setColor(Color.GREEN);
			}
			g2.fillRect(30 * (i % 10), j, 30, 30);
			g2.setColor(Color.YELLOW);
			g2.drawRect(30 * (i % 10), j, 30, 30);

		}

	}

	@Override
	public int compareTo(Chromosome other) {
		return this.FitnessValue() - other.FitnessValue();

	}
}
