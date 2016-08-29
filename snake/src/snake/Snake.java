package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {
	
	public static Snake snake;
	public final int HEIGHT = 1600, WIDTH = 1600;
	public Renderer renderer;
	public Rectangle head, apple;
	boolean gameOver, started;
	int score;
	int xMotion, yMotion;
	public ArrayList<Rectangle> borders;
	public ArrayList<Rectangle> Snake;
	Random ran;
	
	public Snake(){
		JFrame jframe = new JFrame();
		
		Timer timer = new Timer(80, this);
		ran = new Random();
		
		renderer = new Renderer();
		
		jframe.add(renderer);
		jframe.setTitle("Snake");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addKeyListener(this);
		jframe.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		jframe.setResizable(false);
		jframe.pack();
		jframe.setVisible(true);
		
		head = new Rectangle(WIDTH / 2, HEIGHT / 2, 20, 20);
		
		int x = 1 + ran.nextInt(78);
		int y = 1 + ran.nextInt(78);
		apple = new Rectangle(x * 20, y * 20, 20, 20);
		Snake = new ArrayList<Rectangle>();
		Snake.add(head);
		
		borders = new ArrayList<Rectangle>();
		borders.add(new Rectangle(0, 0, 20, HEIGHT));
		borders.add(new Rectangle(0, 0, WIDTH, 20));
		borders.add(new Rectangle(WIDTH - 20, 0, 20, HEIGHT));
		borders.add(new Rectangle(0, HEIGHT - 20, WIDTH, 20));
		
		timer.start();
	}
	
	public void repaint(Graphics g) {
		g.setColor(new Color(145, 73, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(new Color(106, 53, 0));
		g.fillRect(0, 0, 20, HEIGHT);
		g.fillRect(0, 0, WIDTH, 20);
		g.fillRect(WIDTH - 20, 0, 20, HEIGHT);
		g.fillRect(0, HEIGHT - 20, WIDTH, 20);
		
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 100));
		
		if (!started){
			g.drawString("Press arrow key to start!", 250, HEIGHT / 2 - 50);
		}
		
		if (gameOver){
			g.drawString("Game Over!", 500, HEIGHT / 2 - 50);
		}
		
		if (!gameOver && started){
			g.drawString(String.valueOf(score), WIDTH / 2 -25, 125);
		}
		
		g.setColor(Color.red);
		g.fillOval(apple.x, apple.y, 20, 20);
		
		g.setColor(Color.green.darker());
		g.fillOval(head.x, head.y, 20, 20);
		
		for (Rectangle body: Snake){
			g.fillOval(body.x, body.y, 20, 20);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (started){
			
			
			Rectangle last = Snake.get(Snake.size()-1);
			for (int i = Snake.size()-1 ; i > 0 ; i--){
				Rectangle cur = Snake.get(i);
				Rectangle next = Snake.get(i-1);
				cur.x = next.x;
				cur.y = next.y;
			}
			
			head.x += xMotion;
			head.y += yMotion;
			
			for (Rectangle border: borders){
				if (border.intersects(head)){
					gameOver = true;
					xMotion = 0;
					yMotion = 0;
				}
			}

			if (apple.intersects(head)){
				score++;
				int x = 1 + ran.nextInt(78);
				int y = 1 + ran.nextInt(78);
				apple = new Rectangle(x * 20, y * 20, 20, 20);
				
				Snake.add(new Rectangle(last.x, last.y, 20, 20));
			}
			
			for (Rectangle body: Snake){
				if (!body.equals(head)){
					if (body.intersects(head)){
						gameOver = true;
						xMotion = 0;
						yMotion = 0;
					}
				}
			}
		}
		renderer.repaint();
	}
	
	public void move(int x, int y){
		if (gameOver){
			head = new Rectangle(WIDTH / 2, HEIGHT / 2, 20 ,20);
			score = 0;
			yMotion = -20;
			xMotion = 0;
			
			Snake.clear();
			Snake.add(head);
			
			gameOver = false;			
		}
		
		if (!started){
			started = true;
			yMotion = y*20;
			xMotion = x*20;
		} else if (!gameOver) {
			yMotion = y*20;
			xMotion = x*20;
		}
	}

	public static void main(String[] args) {
		snake = new Snake();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()){
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_KP_RIGHT:
				move(1,0);
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_KP_LEFT:
				move(-1,0);
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
				move(0,-1);
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
				move(0,1);
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
