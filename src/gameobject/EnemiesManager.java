package gameobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.Resource;

public class EnemiesManager {

	private BufferedImage cactus1;
	private BufferedImage cactus2;
	private Random rand;
	private BufferedImage bullet;
	private BufferedImage meteor; 
	
	private List<Enemy> enemies;
	private MainCharacter mainCharacter;
	
	public EnemiesManager(MainCharacter mainCharacter) {
		rand = new Random();
		cactus1 = Resource.getResouceImage("data/cactus1.png");
		cactus2 = Resource.getResouceImage("data/cactus2.png");
		bullet = Resource.getResouceImage("data/bullet1.png");
		meteor = Resource.getResouceImage("data/meteor1.png");
		enemies = new ArrayList<Enemy>();
		this.mainCharacter = mainCharacter;
		enemies.add(createEnemy());
	}
	
	public void update() {
		for(Enemy e : enemies) {
			e.update();
		}
		Enemy enemy = enemies.get(0);
		if(enemy.isOutOfScreen()) {
			mainCharacter.upScore();
			enemies.clear();
			enemies.add(createEnemy());
		}
	}
	
	public void draw(Graphics g) {
		for(Enemy e : enemies) {
			e.draw(g);
		}
	}
	
	private Enemy createEnemy() {
		int type = rand.nextInt(4);
		int type2 = rand.nextInt(3);
		if(type == 0) {
			return new Cactus(mainCharacter, 800, cactus1.getWidth() - 10, cactus1.getHeight() - 10, cactus1);
		} else if (type == 1){
			return new Cactus(mainCharacter, 800, cactus2.getWidth() - 10, cactus2.getHeight() - 10, cactus2);
		} else if (type == 2) {
		if(type2 == 0)
			return new Bullet(mainCharacter, 800, bullet.getWidth() - 10, bullet.getHeight() - 10, bullet,205); else
				if (type2 == 1)
				return new Bullet(mainCharacter, 800, bullet.getWidth() - 10, bullet.getHeight() - 10, bullet,190);
				else 
					return new Bullet(mainCharacter, 800, bullet.getWidth() - 10, bullet.getHeight() - 10, bullet,180);
		} else
			if (type == 3) {
				if (type2 == 0 || type2 == 1)
		return new Meteor(mainCharacter, 800,55,  meteor.getWidth() - 10,  meteor.getHeight() - 10, meteor);
				else 
				if (type2 == 2) {
					return new Meteor(mainCharacter, 800,-15,  meteor.getWidth() - 10,  meteor.getHeight() - 10, meteor);
				}
			}
		
	return null;
	}
	
	public boolean isCollision() {
		for(Enemy e : enemies) {
			if (mainCharacter.getBound().intersects(e.getBound())) {
				return true;
			}
		}
		return false;
	}
	
	public void reset() {
		enemies.clear();
		enemies.add(createEnemy());
	}
	
}
