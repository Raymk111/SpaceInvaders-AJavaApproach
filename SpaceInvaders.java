import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;

public class SpaceInvaders extends JFrame implements Runnable, KeyListener
{
	private static final long serialVersionUID = 1L;
	private static final Dimension WindowSize = new Dimension(800, 800);
	private static final int NUMALIENS = 15;
	private static final int NUMBULLETS = 5;
	private Alien[] aliens = new Alien[NUMALIENS];
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>(NUMBULLETS);
	private Invader spaceInvader;
	private Image spaceInvaderImg;
	private Image alienImg;
	private Image alienImg2;
	private Image bullet;
	private Image winner;
	public static String workingDirectory;
	private BufferStrategy strategy;
	private boolean isInitialised = false;
	private int paintCount = 0;
	private int numAlive = NUMALIENS;
	
	public SpaceInvaders()
	{
		int xObj = 0, yObj = 0;
		this.setTitle("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon(workingDirectory + "//SpaceShip.png");
		spaceInvaderImg = icon.getImage();
		
		xObj = 350;
		yObj = 600;
		spaceInvader = new Invader(xObj, yObj, spaceInvaderImg);
		
		icon = new ImageIcon(workingDirectory + "//Bullet.png");
		bullet = icon.getImage();
		
		icon = new ImageIcon(workingDirectory + "//harold-O.jpg");
		winner = icon.getImage();
		
		icon = new ImageIcon(workingDirectory + "//Alien_up.png");
		alienImg = icon.getImage();
		icon = new ImageIcon(workingDirectory + "//alien_down.png");
		alienImg2 = icon.getImage();
		
		for(int i = 0, j = 0; i < NUMALIENS; i++)
		{
			xObj = (i%5)*50;
			yObj = j*50 + 33;
			
			if(i%5 == 4)
			{
				j++;
			}

			aliens[i] = new Alien(xObj, yObj, alienImg, alienImg2);
			aliens[i].setxSpeed(5);
		}
		
		addKeyListener(this);
		
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) screensize.getWidth()/2 - WindowSize.width/2;
		int y = (int) screensize.getHeight()/2 - WindowSize.height/2;
		setBounds(x,  y, 800, 800);
		setVisible(true);
		
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		isInitialised = true;
		
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			spaceInvader.move(20);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			spaceInvader.move(-20);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			spaceInvader.getX();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if(bullets.size() < NUMBULLETS)
			{
				Bullet fire = new Bullet(spaceInvader.getX()+38, 550, bullet);
				fire.unLoad();
				bullets.add(fire);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
	}

	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				for(int i = 0; i < NUMALIENS; i++)
				{
					if(aliens[i].move(0))
					{
						for(int j = 0; j <= i; j++)
						{
							aliens[j].revertDirection();
						}
						break;
					}
				}
				repaint();
				Thread.sleep(20);
			}catch(InterruptedException e)
			{
				
			}
			
		}
	}
	
	public void paint(Graphics g)
	{
		paintCount++;
		paintCount = paintCount%50;
		numAlive = 0;
		if(isInitialised)
		{
			g = strategy.getDrawGraphics();
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0,  0,  800,  800);
			if(paintCount == 0)
			{
				for(int i = 0; i < NUMALIENS; i++)
				{
					aliens[i].changeMyImage();
					aliens[i].paint(g);
					numAlive = (!aliens[i].getDead())? numAlive+1 : numAlive;
				}
			}
			else
			{
				for(int i = 0; i < NUMALIENS; i++)
				{
					aliens[i].paint(g);
					numAlive = (!aliens[i].getDead())? numAlive+1 : numAlive;
				}
			}
			
			if(numAlive == 0)
			{
				g.fillRect(0,  0,  800,  800);
				g.drawImage(winner,  0, 0, null);
				g.dispose();
				strategy.show();
				return;
			}
			
			spaceInvader.paint(g);
			
			if(bullets.size()>0)
			{
				ArrayList<Bullet> deleteBullet = new ArrayList<Bullet>();
				for(Bullet bullet1 : bullets)
				{
					bullet1.move();
					bullet1.paint(g);
					//collision check
					for(Alien alien : aliens)
					{
						if(bullet1.checkCollision(alien.getX(), alien.getY()) && !alien.getDead() && bullet1.getFired())
						{
							alien.dead();
							bullet1.load();
							deleteBullet.add(bullet1);
						}
					}
					if(!bullet1.getFired())
					{
						deleteBullet.add(bullet1);
					}
				}
				
 				while(deleteBullet.size() > 0 && bullets.size() > 0)
				{
 					bullets.remove(deleteBullet.get(0));
 					deleteBullet.remove(0);

				}
			}
			System.out.println("Number Alive:\n" + numAlive);
			g.dispose();
			strategy.show();
		}
	}
	
	public static void main(String[] args)
	{
		workingDirectory = System.getProperty("user.dir");
		System.out.println(workingDirectory);
		new SpaceInvaders();
	}
}
