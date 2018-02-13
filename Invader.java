import java.awt.Image;

public class Invader extends Sprite2D
{
	private static final long serialVersionUID = 1L;
	
	public Invader(int x, int y, Image myImage)
	{
		super(x, y, myImage);
		// TODO Auto-generated constructor stub
	}
	
	public void move(int xDiff)
	{
		setX(getX()+xDiff);
		setY(600);
		setX(getX() < 0 ? getX() + 750 : getX());
		setX(getX() > 750 ? 0 : getX());
	}

}
