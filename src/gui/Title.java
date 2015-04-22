package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Title extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Title(int x, int y) {
		super();
		setPreferredSize(new Dimension(x, y));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		try {
			g2d.drawImage(ImageIO.read(new File("spire-city.png")), 0, 0,
					this.getWidth(), this.getHeight(), null);
		} catch (IOException e) {
			System.out.println("Image cannot be loaded.");
		}
	}
}
