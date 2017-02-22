package dom.game;

public class ExampleGame
{
	public static void main(String[] args)
	{
		Game.launch(60, 1200, 800);
		
		Game.loadScene(new ExampleMenuScene());
	}
}
