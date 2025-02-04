package com.newbie.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.newbie.main.Game;

public class Player extends Entity{
	
	public boolean right, up, left, down, atk;
	public double speed = 1.7;
	public int right_dir = 0, left_dir = 1;
	public int dir = right_dir;
	
	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 7, index2 = 0, maxIndex2 = 1;
	private int iAtk = 0, maxIAtk = 4;
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] sRightPlayer;
	private BufferedImage[] sLeftPlayer;
	private BufferedImage[] rightAtk;
	private BufferedImage[] leftAtk;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		
		rightPlayer = new BufferedImage[8];
		leftPlayer = new BufferedImage[8];
		sRightPlayer = new BufferedImage[2];
		sLeftPlayer = new BufferedImage[2];
		leftAtk = new BufferedImage[5];
		rightAtk = new BufferedImage[5];
		
		for(int i = 0; i < 8; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(0 + (i*24), 48, 24, 24);
		}
		for(int i = 0; i < 8; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(0 + (i*24), 24, 24, 24);
		}
		for(int i = 0; i < 2; i++) {
			sRightPlayer[i] = Game.spritesheet.getSprite(48 + (i*24), 0, 24, 24);
		}
		for(int i = 0; i < 2; i++) {
			sLeftPlayer[i] = Game.spritesheet.getSprite(0 + (i*24), 0, 24, 24);
		}
		for(int i = 0; i < 5; i++) {
			rightAtk[i] = Game.atkdie.getSprite(0 + (i*24), 48, 24, 24);
		}
		for(int i = 0; i < 5; i++) {
			leftAtk[i] = Game.atkdie.getSprite(0 + (i*24), 0, 24, 24);
		}
		
		
		
	}

	public void tick() {
		
		moved = false;
		
		if(right) {
			moved = true;
			dir = right_dir;
			x+=speed;
		}
		else if(left) {
			moved = true;
			dir = left_dir;
			x-=speed;
		}
		if(up) {
			y-=speed;
		}
		else if(down) {
			y+=speed;
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}
		if(!moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index2++;
				if(index2 > maxIndex2) {
					index2 = 0;
				}
			}
		}
		if(atk) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				iAtk++;
				if(iAtk > maxIAtk) {
					iAtk = 0;
					atk = false;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		
		if(dir == right_dir && moved) {
			g.drawImage(rightPlayer[index], this.getX(), this.getY(), null);
		}
		else if(dir == left_dir && moved) {
			g.drawImage(leftPlayer[index], this.getX(), this.getY(), null);
		}
		
		if(Game.last && !moved) {
			g.drawImage(sRightPlayer[index2], this.getX(), this.getY(), null);
			if(atk) {
				g.drawImage(rightAtk[iAtk],this.getX(), this.getY(), null);
			}
		}
		else if(!Game.last && !moved) {
			g.drawImage(sLeftPlayer[index2], this.getX(), this.getY(), null);
			if(atk) {
				g.drawImage(leftAtk[iAtk],this.getX(), this.getY(), null);
			}
		}
	}
	
}
