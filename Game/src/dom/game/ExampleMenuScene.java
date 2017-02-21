package dom.game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

public class ExampleMenuScene extends Scene
{
	private Font titleFont;
	
	private Bubble[] bubbles;
	
	//
	
	public ExampleMenuScene()
	{
		super(60, 800, 600);
	}

	//
	
	public void init()
	{
		titleFont = new Font("TimesNewRoman", Font.PLAIN, 48);
		
		bubbles = new Bubble[4];
		
		for(int i = 0; i < bubbles.length; i ++)
		{
			int radius = (int) (Math.random() * 400);
			
			bubbles[i] = new Bubble(Math.random() * 800, -radius, radius, Color.CYAN);
		}
	}

	public void tick()
	{
		for(int i = 0; i < bubbles.length; i ++)
		{
			bubbles[i].tick();
		}
	}

	public void render(Graphics2D g)
	{
		g.setColor(new Color(240, 240, 240));
		g.fillRect(0, 0, 800, 600);
		
		for(int i = 0; i < bubbles.length; i ++)
		{
			bubbles[i].render(g);
		}
	}
	
	//
	
	private class Bubble
	{
		private double x, y;
		
		private int radius;
		private Color color;
		
		//
		
		public Bubble(double x, double y, int radius, Color color)
		{
			this.x = x;
			this.y = y;
			
			this.radius = radius;
			this.color = color;
		}
		
		//
		
		public void tick()
		{
			y ++;
		}

		public void render(Graphics2D g)
		{
			g.setColor(color);
			g.setStroke(new BasicStroke(1 + (radius / 50)));
			g.drawOval((int) x - radius, (int) y - radius, radius * 2, radius * 2);
		}
	}
}
