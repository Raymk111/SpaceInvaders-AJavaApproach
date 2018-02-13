import java.awt.Graphics;
import java.awt.Image;

public class Alien extends Sprite2D
{
	private static final long serialVersionUID = 1L;
	private static boolean reverseX = false;
	private static boolean reverseY = false;
	private boolean switched = false;
	private int change;
	private Image image1;
	private Image image2;
	private boolean dead;
	
	public Alien(int x, int y, Image myImage, Image myImage2)
	{
		super(x, y, myImage);
		image1 = myImage;
		image2 = myImage2;
		dead = false;
	}
	
	public boolean move(int xDiff)
	{
		change = getX();
		if(reverseX)
		{
			setxSpeed(getxSpeed() - xDiff);
			setX(getX() - getxSpeed());
		}
		else
		{
			setxSpeed(getxSpeed() + xDiff);
			setX(getX() + getxSpeed());
		}
			
		if(getX() == 750)
		{
			if(getY()+10 > 300 || getY()-10 < 23)
			{
				reverseY = !reverseY;
			}
			reverseX = true;
			return true;
		}
		
		if(getX() == 0)
		{
			if(getY()+10 > 300 || getY()-10 < 23)
			{
				reverseY = !reverseY;
			}
			reverseX = false;
			return true;
		}
		
		if(reverseX != switched)
		{
			if(reverseY)
			{
				setY(getY() - 10);
			}
			else
			{
				setY(getY() + 10);
			}
			switched = !switched;
		}
		return false;
	}
	
	public void revertDirection()
	{
		setX(change);
	}
	
	public void changeMyImage()
	{
		if(getMyImage() == image1)
		{
			setMyImage(image2);
		}
		else
		{
			setMyImage(image1);
		}
	}
	
	public void dead()
	{
		dead = true;
	}
	
	public boolean getDead()
	{
		return dead;
	}
	
	@Override
	public void paint(Graphics g)
	{
		if(!dead)
		{
			g.drawImage(getMyImage(),  getX(),  getY(), null);
		}
	}
}
