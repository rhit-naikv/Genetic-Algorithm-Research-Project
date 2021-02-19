package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;

/**
 * 
 * @author Vidhu Naik and William Chong
 *
 */
public class ChromosomeComponent extends JComponent {

	//chromosome instance variable
	private Chromosome chromosome;

	/**
	 * constructs the component
	 */
	public ChromosomeComponent() {
		this.chromosome = null;
	}

	/**
	 * sets the chromosome for the component
	 * @param chromosome
	 */
	public void setChromosome(Chromosome chromosome) {
		this.chromosome = chromosome;
	}

	/**
	 * paints the chromosome
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (this.chromosome != null) {
			this.chromosome.drawOn(g2);
		}

	}

}
