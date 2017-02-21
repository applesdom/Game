package dom.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * This class provides the basic framework for game creation with
 * {@link dom.game.Scene Scene}s.  It creates a game loop and a screen
 * to display the scenes on.
 * 
 * @author Dominic Voto
 * @version 1.0
 * @see dom.game.Scene
 */
public abstract class Game
{
	private static final int DEFAULT_FPS = 60;
	
	private static JFrame frame;
	private static JComponent canvas;
	
	private static GameInputHelper gameInputHelper;
	
	private static Timer tickTimer;
	private static Timer renderTimer;
	
	private static Scene currentScene = null;
	
	//

	/**
	 * Opens the game window with the specified dimensions.  This method
	 * is automatically called if no window is open when a {@link dom.game.Scene Scene} is
	 * loaded.  {@link dom.game.Scene Scene}s will be magnified to fit any screen size, regardless
	 * of their specified size, although the aspect ratio will be preserved.
	 * 
	 * @param  width
	 * 		   The width of the game window
	 * @param  width
	 * 		   The width of the game window
	 */
	public static void launch(int width, int height)
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		canvas = new JComponent()
				{
					private static final long serialVersionUID = 901053358288840310L;

					public void paintComponent(Graphics g)
					{	
						super.paintComponent(g);
						
						g.setColor(Color.BLACK);
						g.fillRect(0, 0, getWidth(), getHeight());
						
						if(currentScene != null)
						{
							Graphics2D g2 = (Graphics2D) g.create();
							
							if((double) getWidth() / getHeight() >= (double) currentScene.getWidth() / currentScene.getHeight())
							{
								double scaleFactor = (double) getHeight() / currentScene.getHeight();
								
								g2.scale(scaleFactor, scaleFactor);
								
								g2.translate(((getWidth() / scaleFactor) - currentScene.getWidth()) / 2, 0);
								
								currentScene.render(g2);
							}
							else
							{
								double scaleFactor = (double) getWidth() / currentScene.getWidth();
								
								g2.scale(scaleFactor, scaleFactor);
								
								g2.translate(0, ((getHeight() / scaleFactor) - currentScene.getHeight()) / 2);
								
								currentScene.render(g2);
							}
						}
					}
				};
		canvas.setPreferredSize(new Dimension(width, height));
		frame.add(canvas);
		
		frame.pack();
		frame.setVisible(true);
		
		gameInputHelper = new GameInputHelper();
		canvas.addKeyListener(gameInputHelper);
		canvas.addMouseListener(gameInputHelper);
		canvas.addMouseMotionListener(gameInputHelper);
		canvas.addMouseWheelListener(gameInputHelper);
		
		tickTimer = new Timer(1000 / DEFAULT_FPS, new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(currentScene != null)
						{
							currentScene.tick();
						}
					}
				});
		tickTimer.setInitialDelay(0);
		tickTimer.start();
		
		renderTimer = new Timer(1000 / DEFAULT_FPS, new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				canvas.repaint();
			}
		});
		renderTimer.setInitialDelay(0);
		renderTimer.start();
	}
	
	/**
	 * Loads a {@link dom.game.Scene Scene} into the game.  If no window is currently open,
	 * the launch method is called.  As soon as a scene is loaded, the tick and render processes
	 * will begin with the delay specified by the {@link dom.game.Scene Scene}.
	 * 
	 * @param  scene
	 * 		   The scene to be loaded
	 */
	public static void loadScene(Scene scene)
	{
		if(frame == null || canvas == null || tickTimer == null || renderTimer == null)
		{
			launch(scene.getWidth(), scene.getHeight());
		}
		
		scene.init();
		
		tickTimer.stop();
		renderTimer.stop();
		
		if(scene.getFPS() > 0)
		{
			tickTimer.setDelay(1000 / scene.getFPS());
			renderTimer.setDelay(1000 / scene.getFPS());
			
			tickTimer.start();
			renderTimer.start();
		}
		else
		{
			renderTimer.setDelay(1000 / DEFAULT_FPS);
			renderTimer.start();
		}
		
		currentScene = scene;
	}
	
	/**
	 * Shuts down the game.
	 * 
	 * @param  scene
	 * 		   The scene to be loaded
	 */
	public static void close()
	{
		currentScene = null;
		
		tickTimer.stop();
		renderTimer.stop();
		
		frame.dispose();
	}

	//
	
	public static BufferedImage loadImage(String imagePath) throws IOException
	{
		return ImageIO.read(ClassLoader.getSystemResource("test.png"));
	}
	
	//
	
	private static class GameInputHelper implements KeyListener, MouseListener, MouseWheelListener, MouseMotionListener
	{
		private void adjustPoint(MouseEvent e)
		{
			if((double) canvas.getWidth() / canvas.getHeight() >= (double) currentScene.getWidth() / currentScene.getHeight())
			{
				double scaleFactor = (double) canvas.getHeight() / currentScene.getHeight();
				
				e.translatePoint((int) -(canvas.getWidth() - (currentScene.getWidth() * scaleFactor)) / 2, 0);
				
				e.translatePoint((int) (e.getX() * ((1 / scaleFactor) - 1)), (int) (e.getY() * ((1 / scaleFactor) - 1)));
				
				e.translatePoint(-1, -1);
			}
			else
			{
				double scaleFactor = (double) canvas.getWidth() / currentScene.getWidth();
				
				e.translatePoint(0, (int) -(canvas.getHeight() - (currentScene.getHeight() * scaleFactor)) / 2);
				
				e.translatePoint((int) (e.getX() * ((1 / scaleFactor) - 1)), (int) (e.getY() * ((1 / scaleFactor) - 1)));
				
				e.translatePoint(-1, -1);
			}
		}
		
		//
		
		public void keyPressed(KeyEvent e)
		{
			if(currentScene != null)
			{
				currentScene.keyPressed(e);
			}
		}
	
		public void keyReleased(KeyEvent e)
		{
			if(currentScene != null)
			{
				currentScene.keyReleased(e);
			}
		}
	
		public void keyTyped(KeyEvent e)
		{
			if(currentScene != null)
			{
				currentScene.keyTyped(e);
			}
		}
	
		
		public void mouseClicked(MouseEvent e)
		{
			if(currentScene != null)
			{	
				adjustPoint(e);
				
				currentScene.mouseClicked(e);
			}
		}
	
		public void mouseEntered(MouseEvent e)
		{
			if(currentScene != null)
			{
				adjustPoint(e);
				
				currentScene.mouseEntered(e);
			}
		}
		
		public void mouseExited(MouseEvent e)
		{
			if(currentScene != null)
			{
				adjustPoint(e);
				
				currentScene.mouseExited(e);
			}
		}
	
		public void mousePressed(MouseEvent e)
		{
			if(currentScene != null)
			{
				adjustPoint(e);
				
				currentScene.mousePressed(e);
			}
		}
	
		public void mouseReleased(MouseEvent e)
		{
			if(currentScene != null)
			{
				adjustPoint(e);
				
				currentScene.mouseReleased(e);
			}
		}
		
		
		public void mouseDragged(MouseEvent e)
		{
			if(currentScene != null)
			{
				adjustPoint(e);
				
				currentScene.mouseDragged(e);
			}
		}
		
		public void mouseMoved(MouseEvent e)
		{
			if(currentScene != null)
			{
				adjustPoint(e);
				
				currentScene.mouseMoved(e);
			}
		}
	
		
		public void mouseWheelMoved(MouseWheelEvent e)
		{
			if(currentScene != null)
			{
				currentScene.mouseWheelMoved(e);
			}
		}
	}
}