package dom.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ExampleMenuScene extends Scene
{	
	private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 48);
	
	private BufferedImage background;
	
	//
	
	public ExampleMenuScene()
	{
		super(0, 600, 400);
	}

	//
	
	public void init()
	{
		try
		{
			background = ExampleGame.loadImage("test.png");
		}
		catch(IOException e)
		{
			background = null;
		}
	}

	public void tick()
	{
		//Do nothing
	}

	public void render(Graphics2D g)
	{
		g.setColor(new Color(160, 80, 45));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.drawImage(background, 0, 0, getHeight(), getHeight(), null);
		
		g.setColor(Color.MAGENTA);
		g.setFont(TITLE_FONT);
		g.drawString("EXAMPLE GAME", (getWidth() / 2) - (g.getFontMetrics().stringWidth("EXAMPLE GAME") / 2), 100);
	}
}
