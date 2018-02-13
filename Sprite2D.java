import java.awt.*;
import javax.swing.*;

public class Sprite2D extends JFrame
{
	private static final long serialVersionUID = 1L;
	private int x, y;
	private int xSpeed;
	private Image myImage;

	public Sprite2D(int x, int y, Image myImage)
	{
		this.x = x;
		this.y = y;
		this.myImage = myImage;
	}
	
	public int getxSpeed()
	{
		return xSpeed;
	}

	public void setxSpeed(int xSpeed)
	{
		this.xSpeed = xSpeed;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}
	
	public Image getMyImage()
	{
		return myImage;
	}

	public void setMyImage(Image myImage)
	{
		this.myImage = myImage;
	}

	public void paint(Graphics g)
	{
		g.drawImage(myImage,  x,  y, null);
	}
}
