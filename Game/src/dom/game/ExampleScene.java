package dom.game;

import java.awt.Color;
import java.awt.Graphics2D;

public class ExampleScene extends Scene
{
	private int joe;
	
	//
	
	public ExampleScene()
	{
		super(60, 400, 300);
	}

	//
	
	public void init()
	{
		joe = 0;
	}

	public void tick()
	{
		joe ++;
	}

	public void render(Graphics2D g)
	{
		g.setColor(Color.RED);
		g.fillRect(joe, joe, 56, 56);
	}
}
