package dom.game;

public class ExampleGame extends Game
{
	public static void main(String[] args)
	{
		launch(1200, 800);
		loadScene(new ExampleMenuScene());
	}
}
