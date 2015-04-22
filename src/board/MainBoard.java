package board;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import rider.Rider;

public class MainBoard extends Observable {
	private ArrayList<Rider> riders = new ArrayList<Rider>();
	private List<Color[]> height = new ArrayList<Color[]>();
	private ArrayList<Color> c = new ArrayList<Color>();
	public static int[] rider_score;
	private boolean game_over = false;

	public MainBoard(int width, int height) {
		initialize(width, height);
	}

	private void initialize(int x, int y) {
		for (int i = 0; i < y; i++) {
			height.add(new Color[x]);
		}
	}

	public void addRider(int x, int y) {
		riders.add(new Rider(x, y));
		rider_score = new int[riders.size()];
		height.get(y)[x] = riders.get(riders.size() - 1).getColor();
	}

	public boolean isGameOver() {
		return game_over;
	}

	public void moveLeft(int rider_n) {
		riders.get(rider_n).moveLeft();
		setChanged();
		notifyObservers();
	}

	public void moveRight(int rider_n) {
		riders.get(rider_n).moveRight();
		setChanged();
		notifyObservers();
	}

	public void moveDown(int rider_n) {
		riders.get(rider_n).moveDown();
		setChanged();
		notifyObservers();
	}

	public void moveUp(int rider_n) {
		riders.get(rider_n).moveUp();
		setChanged();
		notifyObservers();
	}

	public void update() {
		for (int i = 0; i < riders.size(); i++) {
			riders.get(i).move();
			try {
				if (height.get(riders.get(i).y())[riders.get(i).x()] != null) {
					gameOver(i);
				} else {
					height.get(riders.get(i).y())[riders.get(i).x()] = riders
							.get(i).getColor();
					setChanged();
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				gameOver(i);
				break;
			} catch (IndexOutOfBoundsException e) {
				gameOver(i);
				break;
			}
		}
		notifyObservers();
	}

	private void gameOver(int n) {
		game_over = true;
		rider_score[n] = rider_score[n] + 1;
		setChanged();
	}

	public void setColor(int n, Color c) {
		this.c.add(c);
		riders.get(n).setColor(c);
	}

	// public Color getColor(int n)
	// {
	// return riders.get(n).getColor();
	// }
	public int height() {
		return height.size();
	}

	public int width() {
		return height.get(0).length;
	}

	public ArrayList<Color[]> list() {
		return (ArrayList<Color[]>) height;
	}

	// public Rider getRider(int n)
	// {
	// return riders.get(n);
	// }
	public String toString() {
		String s = "";
		for (int y = 0; y < height.size(); y++) {
			for (int x = 0; x < height.size(); x++) {
				if (height.get(y)[x] != null) {
					s += "[X]";
				} else {
					s += "[ ]";
				}
			}
			s += "\n";
		}
		return s;
	}
}
