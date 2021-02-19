package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class PopulationComponent extends JComponent {
	private Population pop;
	
	public PopulationComponent() {
		this.pop = null;
	}
	
	public void setPop(Population pop) {
		this.pop = pop;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (this.pop != null) {
			
		}

	}

}
