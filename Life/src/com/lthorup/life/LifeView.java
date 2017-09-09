package com.lthorup.life;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class LifeView extends JPanel {

	final int RESX = 128;
	final int RESY = 128;
	final int D = 3;
	final int MODX = 0x7f;
	final int MODY = 0x7f;
	JLabel population = null;
	
	boolean[][] world = new boolean[RESX][RESY];
	int[][] cnt = new int[RESX][RESY];
	
	class MyTimerTask extends TimerTask {
		@Override
		public void run() {
			update();
		}
	}
	
	/**
	 * Create the panel.
	 */
	public LifeView() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);
		
		restart();
		
		TimerTask timerTask = new MyTimerTask();
		Timer timer = new Timer();
		timer.schedule(timerTask, 100, 100);
	}
	
	public void setPopulationLabel(JLabel pop) {
		population = pop;
	}
	
	public void restart() {
		for (int y = 0; y < RESY; y++)
			for (int x = 0; x < RESX; x++)
				world[x][y] = randomLife();		
	}

	public void update() {
		for (int y = 0; y < RESY; y++)
			for (int x = 0; x < RESX; x++)
			{
				int X = x + RESX;
				int Y = y + RESY;
				cnt[x][y] = 0;
				if (world[(X-1)&MODX][(Y-1)&MODY])
					cnt[x][y]++;
				if (world[(X-1)&MODX][Y&MODY])
					cnt[x][y]++;
				if (world[(X-1)&MODX][(Y+1)&MODY])
					cnt[x][y]++;
				
				if (world[X&MODX][(Y-1)&MODY])
					cnt[x][y]++;
				if (world[X&MODX][(Y+1)&MODY])
					cnt[x][y]++;

				if (world[(X+1)&MODX][(Y-1)&MODY])
					cnt[x][y]++;
				if (world[(X+1)&MODX][Y&MODY])
					cnt[x][y]++;
				if (world[(X+1)&MODX][(Y+1)&MODY])
					cnt[x][y]++;
			}
		int pop = 0;
		for (int y = 0; y < RESY; y++)
			for (int x = 0; x < RESX; x++) {
				if (world[x][y]) {
					if (cnt[x][y] < 2 || cnt[x][y] > 3)
						world[x][y] = false;
				}
				else {
					if (cnt[x][y] == 3)
						world[x][y] = true;
				}
				if (world[x][y])
					pop++;
			}
		population.setText(String.valueOf(pop));
		repaint();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 1000);
		
		g.setColor(Color.GREEN);
		for (int y = 0; y < RESY; y++)
			for (int x = 0; x < RESX; x++)
				if (world[x][y])
				g.fillRect(x*D, y*D, D, D);
	}
	
	private boolean randomLife() {
		return Math.random() >= 0.6;
	}
}
