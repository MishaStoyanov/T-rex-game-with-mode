package gameobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

public class Meteor extends Enemy {

	public int Y_LAND = 225;
	
	private int posX;
	private int width;
	private int height;
	private float posY;
	private BufferedImage image;
	private MainCharacter mainCharacter;
	
	private Rectangle rectBound;
	
	public Meteor(MainCharacter mainCharacter, int posX,int posY, int width, int height, BufferedImage image) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.image = image;
		this.mainCharacter = mainCharacter;
		rectBound = new Rectangle();
	}
	
	public void update() {
		posX -= mainCharacter.getSpeedX();
		if (posY<195 && mainCharacter.getSpeedX()>5) {
			posY +=0.25 * mainCharacter.getSpeedX();
		} else
			if (posY<195) {
				posY += 0.9;
			}
	}
	
	public void draw(Graphics g) {
			g.drawImage(image, posX,  (int)posY, null);
			
		g.setColor(Color.red);

	}
	
	public Rectangle getBound() {
		rectBound = new Rectangle();
		rectBound.x = (int) posX + (image.getWidth() - width)/2;
		
		rectBound.y = (int) posY ;
		rectBound.width = width;
		rectBound.height = height;
		return rectBound;
	}

	@Override
	public boolean isOutOfScreen() {
		if(posX < -image.getWidth()) {
			return true;
		}
		return false;
	}
}
