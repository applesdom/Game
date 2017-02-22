package dom.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class ExampleMenuScene extends Scene
{
	private Bubble[] bubbles;
	
	private double titleAlpha;
	private double menuAlpha;
	
	private Font titleFont;
	private Font menuFont;

	//
	
	public void init()
	{
		bubbles = new Bubble[5];
		
		bubbles[0] = new Bubble(Color.CYAN);
		bubbles[1] = new Bubble(Color.PINK);
		bubbles[2] = new Bubble(Color.YELLOW);
		bubbles[3] = new Bubble(Color.ORANGE);
		bubbles[4] = new Bubble(Color.MAGENTA);
		
		titleAlpha = 0;
		menuAlpha = 0;
		
		titleFont = new Font("Arial", Font.BOLD, 72);
		menuFont = new Font("Arial", Font.BOLD, 24);
	}

	public void tick()
	{
		if(titleAlpha < 255)
		{
			titleAlpha += (255.0 / Game.getFPS());
			
			if(titleAlpha > 255)
			{
				titleAlpha = 255;
			}
		}
		else if(menuAlpha < 255)
		{
			menuAlpha += (255.0 / Game.getFPS());
			
			if(menuAlpha > 255)
			{
				menuAlpha = 255;
			}
		}
		
		for(int i = 0; i < bubbles.length; i ++)
		{
			bubbles[i].tick();
		}
	}

	public void render(Graphics2D g)
	{
		g.setColor(new Color(250, 250, 250));
		g.fillRect(0, 0, Game.getWidth(), Game.getHeight());
		
		for(int i = 0; i < bubbles.length; i ++)
		{
			bubbles[i].render(g);
		}
		
		g.setColor(new Color(20, 40, 250, (int) titleAlpha));
		g.setFont(titleFont);
		g.drawString("Example Game", (Game.getWidth() / 2) - (g.getFontMetrics().stringWidth("Example Game") / 2), (Game.getHeight() / 2) - 50);
		
		g.setColor(new Color(5, 5, 5, (int) menuAlpha));
		g.setFont(menuFont);
		g.drawString("(Click anywhere to begin...)", (Game.getWidth() / 2) - (g.getFontMetrics().stringWidth("(Click anywhere to begin...)") / 2), (Game.getHeight() / 2) + 100);
	}
	
	//
	
	public void mouseClicked(MouseEvent e)
	{
		Game.loadScene(new ExampleScene());
	}
	
	//
	
	private class Bubble
	{
		private double x, y;
		
		private int radius;
		private Color color;
		
		//
		
		public Bubble(Color color)
		{
			this.color = color;
			
			reset();
		}
		
		//
		
		public void tick()
		{
			y += 3 - ((double) radius * 4 / Game.getWidth());
			
			if(y > Game.getHeight() + radius)
			{
				reset();
			}
		}

		public void render(Graphics2D g)
		{
			g.setColor(color);
			g.setStroke(new BasicStroke(6));
			g.drawOval((int) x - radius, (int) y - radius, radius * 2, radius * 2);
		}
		
		//
		
		private void reset()
		{
			radius = (int) (Math.random() * Game.getWidth() * 0.25);
			
			x = Math.random() * Game.getWidth();
			y = -radius - Math.random() * Game.getWidth();
		}
	}
}
