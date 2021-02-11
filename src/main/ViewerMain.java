package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Viewer class for chromosome editor
 * 
 * @author Vidhu Naik and William Chong
 *
 */
public class ViewerMain {
	private String fileName; // start with 20.txt
	private Chromosome chromosome;
	private ChromosomeComponent c;

	public ViewerMain() throws FileNotFoundException {

		JFrame frame = new JFrame("ChromosomeEditor");
		JTextArea chromosomeName = new JTextArea("please load a chromosome to edit");
		chromosomeName.setEditable(false);
		JPanel buttonPanel = new JPanel();

		c = new ChromosomeComponent();
		c.setPreferredSize(new Dimension(300, 300));

		JButton selectFile = new JButton("Load");
		selectFile.addActionListener(new FileLoader(this, c, chromosomeName));

		JButton saveFile = new JButton("Save");
		saveFile.addActionListener(new FileSaver(this));

		JButton mutate = new JButton("Mutate");
		JTextField mutateText = new JTextField(5);
		JTextArea rateDisplay = new JTextArea("M Rate: _/N");
		mutate.addActionListener(new MutateListener(mutateText, c, this));

		c.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				Chromosome chromosome = getChromosome();
				for (int i = 0; i < chromosome.getGeneImage().size(); i++) {
					Rectangle2D.Double geneI = chromosome.getGeneImage().get(i);
					char gene = chromosome.getGenes().get(i);
					if (geneI.x < e.getX() && e.getX() < geneI.x + geneI.width && geneI.y < e.getY()
							&& e.getY() < geneI.y + geneI.height) {
						if (gene == '0') {
							chromosome.getGenes().set(i, '1');
						} else {
							chromosome.getGenes().set(i, '0');
						}
					}
				}
				c.repaint();

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});

		buttonPanel.add(selectFile);
		buttonPanel.add(saveFile);
		buttonPanel.add(mutate);
		buttonPanel.add(rateDisplay);
		buttonPanel.add(mutateText);

		frame.add(chromosomeName, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(c, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * @param args
	 * @throws FileNotFoundException
	 * 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		new ViewerMain();
	}

	public void setFileName(String newName) throws FileNotFoundException {
		this.fileName = newName;
		this.chromosome = new Chromosome(this.fileName);
		c.setChromosome(this.chromosome);

	}

	public Chromosome getChromosome() {
		return this.chromosome;
	}

	public String getFileName() {
		return this.fileName;
	}

}