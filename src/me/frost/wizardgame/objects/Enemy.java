package me.frost.wizardgame.objects;

import me.frost.wizardgame.assets.SpriteSheet;
import me.frost.wizardgame.framework.GameObject;
import me.frost.wizardgame.framework.Handler;
import me.frost.wizardgame.framework.ID;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject {

    private Handler handler;
    Random r = new Random();
    int choose = 0;
    int hp = 100;

    private BufferedImage foeImg;

    public Enemy(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;

        foeImg = ss.grabImage(4,1,32,32);
    }

    @Override
    public void update() {
        x += velX;
        y += velY;

        choose = r.nextInt(10);

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject obj = handler.objects.get(i);

            if (obj.getID() == ID.Block) {
                if (getBoundsBig().intersects(obj.getBounds())) {
                    x += (velX*2) * -1;
                    y += (velY*2) * -1;
                }
            }

            if (obj.getID() == ID.Bullet) {
                if (getBoundsBig().intersects(obj.getBounds())) {
                    hp -= 50;
                    handler.removeObject(obj);

                }
            }
            if (hp <= 0) handler.removeObject(this);
        }

        if (choose == 0) {
            velX = (r.nextInt(4 - -4)+ -4);
            velY = (r.nextInt(4 - -4)+ -4);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(foeImg,x,y,null);
        //g.setColor(new Color(30,60,30));
        //g.fillRect(x,y,32,32);

        Graphics2D g2 = (Graphics2D) g;

        g.setColor(new Color(30,60,30));
        //g2.draw(getBoundsBig());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    public Rectangle getBoundsBig() {
        return new Rectangle(x-16,y-16,64,64);
    }

}

