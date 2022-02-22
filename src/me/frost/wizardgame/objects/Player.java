package me.frost.wizardgame.objects;

import me.frost.wizardgame.assets.SpriteSheet;
import me.frost.wizardgame.framework.Animation;
import me.frost.wizardgame.framework.GameObject;
import me.frost.wizardgame.framework.Handler;
import me.frost.wizardgame.framework.ID;
import me.frost.wizardgame.game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject {

    Handler handler;
    Game game;
    Random r = new Random();

    private BufferedImage playerImg;
    private Animation playerWalkDown;
    private Animation playerWalkUp;
    private Animation playerWalkLeft;
    private Animation playerWalkRight;
    private Animation playerClearer;

    public Player(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
        super(x, y, id,ss);
        this.handler = handler;
        this.game = game;

        playerWalkDown = new Animation(1,
                ss.grabImage(1,1,32,48),
                ss.grabImage(2,1,32,48),
                ss.grabImage(3,1,32,48),
                ss.grabImage(4,1,32,48),
                ss.grabImage(4,3,32,48),
                ss.grabImage(3,3,32,48),
                ss.grabImage(2,3,32,48),
                ss.grabImage(1,3,32,48));

        playerWalkUp = new Animation(1,
                ss.grabImage(1,5,32,48),
                ss.grabImage(2,5,32,48),
                ss.grabImage(3,5,32,48),
                ss.grabImage(4,5,32,48),
                ss.grabImage(4,7,32,48),
                ss.grabImage(3,7,32,48),
                ss.grabImage(2,7,32,48),
                ss.grabImage(1,7,32,48));

        playerWalkRight = new Animation(1,
                ss.grabImage(1,9,32,48),
                ss.grabImage(2,9,32,48),
                ss.grabImage(3,9,32,48),
                ss.grabImage(4,9,32,48),
                ss.grabImage(3,9,32,48),
                ss.grabImage(2,9,32,48),
                ss.grabImage(1,9,32,48));

        playerWalkLeft = new Animation(1,
                ss.grabImage(4,11,32,48),
                ss.grabImage(3,11,32,48),
                ss.grabImage(2,11,32,48),
                ss.grabImage(1,11,32,48),
                ss.grabImage(2,11,32,48),
                ss.grabImage(3,11,32,48),
                ss.grabImage(4,11,32,48));

        playerClearer = new Animation(1,ss.grabImage(1,13,32,48));

        playerImg = ss.grabImage(1,1,32,48);
    }

    @Override
    public void update() {
        x += velX;
        y += velY;

        collison();

        playerWalkDown.runAnimation();
        playerWalkUp.runAnimation();
        playerWalkRight.runAnimation();
        playerWalkLeft.runAnimation();
        playerClearer.runAnimation();
    }

    private void collison() {
        for (int i = 0; i < handler.objects.size();i++) {
            GameObject obj = handler.objects.get(i);

            if (obj.getID() == ID.Block) {
                if (getBounds().intersects(obj.getBounds())){
                    x += velX * -1;
                    y += velY * -1;
                }
            }

            if (obj.getID() == ID.Chest) {

                if (getBounds().intersects(obj.getBounds())){
                    game.ammo += r.nextInt(10)+3;
                    handler.removeObject(obj);
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        if (velX == 0 && velY == 0){
            g.drawImage(playerImg,x,y,null);
        }

        if (velY > 0 && velX == 0){
            playerWalkDown.drawAnimation(x,y,g);
        }

        if(velY < 0 && velX == 0) {
            playerWalkUp.drawAnimation(x,y,g);
        }

        if(velX > 0 && velY == 0) {
            playerWalkRight.drawAnimation(x,y,g);
        }

        if (velX < 0 && velY == 0) {
            playerWalkLeft.drawAnimation(x,y,g);
        }

        if (velX > 0 && velY != 0) {
            playerWalkRight.drawAnimation(x,y,g);
        }

        if (velX < 0 && velY != 0) {
            playerWalkLeft.drawAnimation(x,y,g);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,48);
    }
}
