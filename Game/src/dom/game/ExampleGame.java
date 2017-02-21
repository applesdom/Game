package dom.game;

public class ExampleGame
{
	public static void main(String[] args)
	{
		Game.launch(1200, 800);
		Game.loadScene(new ExampleMenuScene());
	}
}
