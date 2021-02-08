package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;

public class ChromosomeComponent extends JComponent {
	
	private Chromosome chromosome;
	
	public ChromosomeComponent(Chromosome chromosome) {
		this.chromosome = chromosome;
	}
	
	public void setChromosome(Chromosome chromosome) {
		this.chromosome = chromosome;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		this.chromosome.drawOn(g2);
		
	}
	

}
