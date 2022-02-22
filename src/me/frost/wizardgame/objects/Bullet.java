package me.frost.wizardgame.objects;

import me.frost.wizardgame.assets.SpriteSheet;
import me.frost.wizardgame.framework.Animation;
import me.frost.wizardgame.framework.GameObject;
import me.frost.wizardgame.framework.Handler;
import me.frost.wizardgame.framework.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject {

    private Handler handler;
    private ID id;
    int timer = 30;

    private BufferedImage bulletImg;
    private Animation bulletAnimation;

    public Bullet(int x, int y, ID id, Handler handler, int mx, int my, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.id = id;

        bulletAnimation = new Animation(5,
                ss.grabImage(5,3,16,16),
                ss.grabImage(6,3,16,16),
                ss.grabImage(7,3,16,16),
                ss.grabImage(8,3,16,16));

        bulletImg = ss.grabImage(7,2,16,16);

        velX = (mx - x) / 15;
        velY = (my - y) / 15;

        timerStart();

    }

    @Override
    public void update() {
        x += velX;
        y += velY;

        if (timer == 0) {
            timer = 30;
            handler.removeObject(this);
        }

        for (int i = 0; i<handler.objects.size(); i++) {
            GameObject obj = handler.objects.get(i);

            if (obj.getID() == ID.Block) {
                if (getBounds().intersects(obj.getBounds())){
                    handler.removeObject(this);
                }
            }
        }

        bulletAnimation.runAnimation();

    }

    public void timerStart() {
        timer--;
    }

    @Override
    public void draw(Graphics g) {

        bulletAnimation.drawAnimation(x,y,g);

        //g.drawImage(bulletImg,x,y,null);
        //g.setColor(Color.RED);
        //g.fillOval(x, y,8,8);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,16,16);
    }
}
