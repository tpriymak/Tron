package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import board.MainBoard;

public class Starter {
	private static final int MULTIPLIER = TronPanel.MULTIPLIER;
	private static final int INITIAL_DIMENSION = 100;
	protected static Color[] rider_colors;
	private JFrame frame = new JFrame("Tron");
	private TronPanel board;
	private Title title_panel;
	protected static Score score_board;

	public Starter() {
		initialize();
	}

	private void initialize() {
		board = new TronPanel(INITIAL_DIMENSION, INITIAL_DIMENSION);
		rider_colors = new Color[MainBoard.rider_score.length];
		title_panel = new Title(ImageObserver.WIDTH, 100);
		score_board = new Score(board.getHeight());
		score_board.setBoard(board.getBoard());
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(board, BorderLayout.WEST);
		frame.add(score_board, BorderLayout.EAST);
		frame.setJMenuBar(addButtons());
		frame.setVisible(true);
		frame.pack();
		frame.setResizable(true);
		frame.getRootPane().addComponentListener(new ResizeWindow());
		frame.addWindowListener(new OpenFrame());
		//frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
	}

	private JMenuBar addButtons() {
		JMenuBar bar = new JMenuBar();
		JMenu game = new JMenu("Game");
		JMenuItem start = new JMenuItem("Start");
		JMenuItem pause = new JMenuItem("Pause");
		//JMenuItem restart = new JMenuItem("Restart");
		//restart.addActionListener(new Restart());
		JMenu player = new JMenu("Player");
		JMenuItem playera = new JMenuItem("Player 1");
		player.add(playera);
		playera.addActionListener(new ColorChoose(0));
		JMenuItem playerb = new JMenuItem("Player 2");
		player.add(playerb);
		playerb.addActionListener(new ColorChoose(1));
		bar.add(game);
		bar.add(player);
		game.add(start);
		game.add(pause);
		//game.add(restart);
		start.addActionListener(new Start());
		pause.addActionListener(new Pause());
		return bar;
	}

	private class Start implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			board.startTimer();
		}
	}

	private class Pause implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			board.stopTimer();
		}

	}

	/*private class Restart implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			board.stopTimer();
			frame.remove(board);
			board = new TronPanel(board.getWidth() / MULTIPLIER,
					board.getHeight() / MULTIPLIER);
			frame.add(board, BorderLayout.EAST);
			score_board.setBoard(board.getBoard());
			frame.pack();
		}
	}*/

	protected static Color getColor(int rider_n) {
		return rider_colors[rider_n];
	}

	private class ColorChoose implements ActionListener {
		private int n_rider;

		public ColorChoose(int n) {
			n_rider = n;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Color c = JColorChooser.showDialog(null,
					"Player " + String.valueOf(n_rider), Color.CYAN);
			board.getBoard().setColor(n_rider, c);
			rider_colors[n_rider] = c;
		}
	}

	private class ResizeWindow implements ComponentListener {
		@Override
		public void componentHidden(ComponentEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void componentMoved(ComponentEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void componentResized(ComponentEvent arg0) {
			frame.remove(board);
			board = new TronPanel((board.getWidth() / MULTIPLIER),
					(board.getHeight() / MULTIPLIER));
			frame.add(board);
			score_board.setBoard(board.getBoard());
			board.requestFocusInWindow();
				frame.pack();
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	private class OpenFrame implements WindowListener {

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			board.stopTimer();
			frame.dispose();

		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			for (int i = 0; i < MainBoard.rider_score.length; i++) {
				Color c = JColorChooser.showDialog(null,
						"Player " + String.valueOf(i + 1)
								+ "Please Select Your Color", Color.CYAN);
				rider_colors[i] = c;
				board.getBoard().setColor(i, c);
			}
		}

	}
}
