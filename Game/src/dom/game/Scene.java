package dom.game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * This class serves as the basic component of a {@link dom.game.Game Game}.
 * It allows subclasses to access the tick() and render() methods of
 * the game loop and lets them set their own width, height, and FPS.
 * This class implements the {@link java.awt.event.KeyListener KeyListener}, {@link java.awt.event.MouseListener MouseListener},
 * {@link java.awt.event.MouseWheelListener MouseWheelListener}, and {@link java.awt.event.KeyListener MouseMotionListener} classes,
 * allowing user inputs to be accessed.
 * 
 * @author Dominic Voto
 * @version 1.0
 * @see dom.game.Game
 */
public abstract class Scene implements KeyListener, MouseListener, MouseWheelListener, MouseMotionListener
{
	private final int fps;
	
	private final int width, height;
	
	//
	
	/**
	 * This method should be overwritten by a subclass to specify
	 * the width, height, and FPS for the scene.  Subclasses that
	 * require no access to the game loop should specify an FPS of
	 * -1.
	 * 
	 * @param  fps
     *         The FPS of the scene
     * @param  width
     *         The width of the scene
     * @param  height
     *         The height of the scene
	 */
	public Scene(int fps, int width, int height)
	{
		this.fps = fps;
		
		this.width = width;
		this.height = height;
	}
	
	//
	
	/**
	 * Called once when the scene is loaded by the {@link dom.game.Game Game}.
	 * All instance variables should be instantialized in this method.
	 */
	public abstract void init();
	
	/**
	 * Called every time the game loop ticks (FPS times
	 * per second).  All updates to variables should be made in this method.
	 */
	public abstract void tick();
	
	/**
	 * Called every time the render loop ticks OR the screen is resized.
	 * NO variables accessed by the tick method should be changed in this
	 * method, otherwise an error may occur.
	 * 
	 * @param  g
     *         A graphics object provided by the {@link dom.game.Game Game} class during rendering.
	 */
	public abstract void render(Graphics2D g);
	
	//
	
	public int getFPS()
	{
		return fps;
	}

	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	//

	public void keyPressed(KeyEvent e)
	{
		
	}

	public void keyReleased(KeyEvent e)
	{
		
	}

	public void keyTyped(KeyEvent e)
	{
		
	}

	
	public void mouseClicked(MouseEvent e)
	{
		
	}

	public void mouseEntered(MouseEvent e)
	{
		
	}
	
	public void mouseExited(MouseEvent e)
	{
		
	}

	public void mousePressed(MouseEvent e)
	{
		
	}

	public void mouseReleased(MouseEvent e)
	{
		
	}
	
	
	public void mouseDragged(MouseEvent e)
	{
		
	}
	
	public void mouseMoved(MouseEvent e)
	{
		
	}

	
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		
	}
}
