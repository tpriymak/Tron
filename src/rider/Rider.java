package rider;

import java.awt.Color;
/**
 * 
 * @author Timur
 * A Tron rider.
 */
public class Rider {
	/**
	 * Current x position.
	 */
	private int x_pos;
	/**
	 * Current y position.
	 */
	private int y_pos;
	/**
	 * Current direction.
	 */
	private Direction current_direction = Direction.NORTH;
	/**
	 * Current color.
	 */
	private Color ride_color;
	/**
	 * Constructs a new rider at a given x and y point.
	 * @param x Initial x-position.
	 * @param y Initial y-position.
	 */
	public Rider(int x, int y) {
		x_pos = x;
		y_pos = y;
	}
	/**
	 * Returns the current x.
	 * @return x_pos The current x position.
	 */
	public int x() {
		return x_pos;
	}
	/**
	 * Sets the given color of the rider.
	 * @param c Current color.
	 */
	public void setColor(Color c)
	{
		ride_color = c;
		if(c == null)
		{
			ride_color = Color.CYAN;
		}
	}
	/**
	 * Returns the color of the rider.
	 * @return Current color of the rider.
	 */
	public Color getColor()
	{
		return ride_color;
	}
	/**
	 * Returns y position.
	 * @return y_pos Current y position.
	 */
	public int y() 
	{
		return y_pos;
	}
	/**
	 * Changes the riders direction to North.
	 */
	public void moveUp() 
	{
		//if(current_direction!=Direction.SOUTH)
		//{
		current_direction = Direction.NORTH;
		//}
	}
	/**
	 * Changes the riders direction to South.
	 */
	public void moveDown() 
	{
		//if(current_direction != Direction.NORTH)
		//{
		current_direction = Direction.SOUTH;
		//}
	}
	/**
	 * Advances the rider.
	 */
	public void move() 
	{
		switch (current_direction) 
		{
		case NORTH:
			y_pos--;
			break;
		case SOUTH:
			y_pos++;
			break;
		case WEST:
			x_pos--;
			break;
		case EAST:
			x_pos++;
			break;
		default:
			break;
		}

	}
	/**
	 * Changes the riders direction to West.
	 */
	public void moveLeft() {
		//if(current_direction != Direction.EAST)
		//{
		current_direction = Direction.WEST;
		//}
	}
	/**
	 * Changes the riders direction to East.
	 */
	public void moveRight() {
		//if(current_direction != Direction.WEST)
		//{
		current_direction = Direction.EAST;
		//}
	}
	/**
	 * Returns the current direction.
	 * @return current_direction The current direction.
	 */
	public Direction currentDirection() {
		return current_direction;
	}
}
