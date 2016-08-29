package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener {
	
	public static Snake snake;
	public final int HEIGHT = 1600, WIDTH = 1600;
	public Renderer renderer;
	public boolean[][] board;
	public Rectangle head;
	
	public Snake(){
		JFrame jframe = new JFrame();
		
		Timer timer = new Timer(20, this);
		
		renderer = new Renderer();
		
		jframe.add(renderer);
		jframe.setTitle("Snake");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		jframe.setResizable(false);
		jframe.pack();
		jframe.setVisible(true);
		
		head = new Rectangle(WIDTH / 2, HEIGHT / 2, 20, 20);
		board = new boolean[HEIGHT / 20][WIDTH / 20];
		
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
		
		g.setColor(Color.green.darker());
		g.fillOval(head.x, head.y, 20, 20);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

	public static void main(String[] args) {
		snake = new Snake();
	}

}
