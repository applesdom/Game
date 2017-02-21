package dom.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class ExampleLogoScene extends Scene
{
	private int counter;
	private double fade;
	
	//
	
	public ExampleLogoScene()
	{
		super(60, 400, 400);
	}

	//
	
	public void init()
	{
		counter = 0;
		
		fade = 0;
	}

	public void tick()
	{

		fade = ((200 - counter) * counter) / 30.0;
		
		if(fade > 255)
		{
			fade = 255;
		}
				
		if(counter > 200)
		{
			Game.loadScene(new ExampleMenuScene());
		}
		
		counter ++;
	}

	public void render(Graphics2D g)
	{
		g.setColor(new Color((int) fade, (int) fade, (int) fade));
		g.fillRect(100, 100, 200, 200);
	}

}
