package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import board.MainBoard;

public class Score extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int MULTIPLIER = 5;
	private static final int DRAWING_M = 60;
	private int[] rider;

	public Score(int y) {
		setPreferredSize(new Dimension(100, y * MULTIPLIER));
		this.setBackground(Color.DARK_GRAY);
		rider = new int[MainBoard.rider_score.length];
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.CYAN);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		for (int i = 0; i < rider.length; i++) {
			g2d.setFont(new Font(Font.SERIF, Font.ROMAN_BASELINE, 15));
			g2d.drawString("Player " + String.valueOf(i + 1), 35, (i + 1)
					* DRAWING_M);
			g2d.setFont(new Font(Font.SERIF, Font.ROMAN_BASELINE, 40));
			g2d.drawString(String.valueOf(rider[i]), 45,
					((i + 1) * DRAWING_M) + 35);
		}
	}

	public void setBoard(MainBoard b) {
		b.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		for (int i = 0; i < rider.length; i++) {
			rider[i] += MainBoard.rider_score[i];
		}
		repaint();
	}
}
