package rider;

public enum Direction 
{
	NORTH('n'),
	SOUTH('s'),
	WEST('w'),
	EAST('e');
	private char e;
	private Direction(char the_e)
	{
		e = the_e;
	}
	public Direction left()
	{
	Direction result;
    
    switch (this)
    {
      case NORTH: 
        result = WEST;
        break;
      
      case WEST:
        result = SOUTH;
        break;
      
      case SOUTH:
        result = EAST;
        break;
      
      case EAST:
        result = NORTH;
        break;
        
      default:
        result = null;
        break;
    }
    return result;
	}
	public Direction right()
	{
		return left().left().left();
	}
	public char direction()
	{
		return e;
	}
}
