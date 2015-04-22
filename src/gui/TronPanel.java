package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.Timer;

import board.MainBoard;

public class TronPanel extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int TIMER_DELAY = 40;
	protected static final int MULTIPLIER = 5;
	/**
	 * Board Timer.
	 */
	private Timer timer;
	/**
	 * Main playing board.
	 */
	private MainBoard board;

	/**
	 * Creates a new tron board.
	 * 
	 * @param x
	 *            Width of board.
	 * @param y
	 *            Height of board.
	 */
	public TronPanel(int x, int y) {
		super();
		setPreferredSize(new Dimension(x * MULTIPLIER, y * MULTIPLIER));
		setBackground(Color.BLACK);
		initialize(x, y);
		timer = new Timer(TIMER_DELAY, new TimerListener());
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new KeyAction());
	}

	private void initialize(int x, int y) {
		board = new MainBoard(x, y);
		board.addRider(x / 2 + 7, y / 2);
		board.addRider(x / 2, y / 2);
		board.addObserver(this);
		if (Starter.rider_colors != null) {
			for (int i = 0; i < MainBoard.rider_score.length; i++) {
				board.setColor(i, Starter.getColor(i));
			}
		}
	}

	/**
	 * Main paint component.
	 * 
	 * @param g
	 *            Main graphics component.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		for (int y = 0; y < board.height(); y++) {
			for (int x = 0; x < board.width(); x++) {
				if (board.list().get(y)[x] != null) {
					g2d.setColor(board.list().get(y)[x]);
					g2d.fillRect(x * MULTIPLIER, y * MULTIPLIER, MULTIPLIER,
							MULTIPLIER);
				}
			}
		}

	}

	/**
	 * a
	 * 
	 * @return board Main game board.
	 */
	protected MainBoard getBoard() {
		return board;
	}

	protected void startTimer() {
		timer.start();
		requestFocusInWindow();
	}

	protected void stopTimer() {
		timer.stop();
	}

	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			board.update();
		}

	}

	private class KeyAction implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			switch (code) {
			case KeyEvent.VK_LEFT:
				board.moveLeft(0);
				break;
			case KeyEvent.VK_RIGHT:
				board.moveRight(0);
				break;
			case KeyEvent.VK_UP:
				board.moveUp(0);
				break;
			case KeyEvent.VK_DOWN:
				board.moveDown(0);
				break;
			case KeyEvent.VK_A:
				board.moveLeft(1);
				break;
			case KeyEvent.VK_D:
				board.moveRight(1);
				break;
			case KeyEvent.VK_W:
				board.moveUp(1);
				break;
			case KeyEvent.VK_S:
				board.moveDown(1);
				break;
			default:
				break;
			}
			if(!timer.isRunning())
			{
				timer.start();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (board.isGameOver()) {
			timer.stop();
			// int response = JOptionPane.showOptionDialog(this,
			// "Game Over Bitches.\nPlay Again?", null,
			// JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null,
			// null);
			// if(response == 0)
			// {
			initialize(board.width(), board.height());
			Starter.score_board.setBoard(board);
		}
		repaint();
	}

}