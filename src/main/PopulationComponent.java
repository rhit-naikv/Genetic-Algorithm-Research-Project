package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;

import javax.swing.JComponent;
  
/**
 * 
 * This helps paint the population
 * @author Vidhu Naik and William Chong
 *
 */
public class PopulationComponent extends JComponent {
	private Population pop;
	
	/**
	 * constructs the population component
	 */
	public PopulationComponent() {
		this.pop = null;
	}
	
	/**
	 * sets the population
	 * @param pop
	 */
	public void setPop(Population pop) {
		this.pop = pop;
	}
	
	/**
	 * paints the population
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (this.pop != null) {
			try {
				this.pop.drawOn(g2);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
