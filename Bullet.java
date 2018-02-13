import java.awt.*;
import java.awt.Image;

public class Bullet extends Sprite2D
{
	private static final long serialVersionUID = 1L;
	private boolean fired = false;
	public Bullet(int x, int y, Image myImage)
	{
		super(x, y, myImage);
	}
	
	public void unLoad()
	{
		fired = true;
	}
	
	public void load()
	{
		fired = false;
	}
	
	public boolean getFired()
	{
		return fired;
	}
	
	public void move()
	{
		setY(getY()-10);
		if(getY() < 0)
		{
			load();
		}
	}
	
	public boolean checkCollision(int x, int y)
	{
		if(getX() >= x && getX()+13 <= x+50)
		{
			if(getY() <= y+50 && getY() >= y)
			{
				System.out.println("dead");
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void paint(Graphics g)
	{
		if(fired)
		{
			g.setColor(Color.GREEN);
			g.drawImage(getMyImage(), getX(), getY(), null);
		}
	}
	
}
