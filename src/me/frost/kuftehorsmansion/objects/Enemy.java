package me.frost.kuftehorsmansion.objects;

import me.frost.kuftehorsmansion.assets.SpriteSheet;
import me.frost.kuftehorsmansion.framework.Animation;
import me.frost.kuftehorsmansion.framework.GameObject;
import me.frost.kuftehorsmansion.framework.Handler;
import me.frost.kuftehorsmansion.framework.ID;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject {

    private Handler handler;
    Random r = new Random();
    int choose = 0;
    int hp = 100;

    private BufferedImage foeImg;
    private Animation foeAnimDown;

    public Enemy(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;

        foeAnimDown = new Animation(1,
                ss.grabImage(5, 1, 32,32),
                ss.grabImage(6,1,32,32),
                ss.grabImage(7,1,32,32));

        foeImg = ss.grabImage(5,1,32,32);
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

        foeAnimDown.runAnimation();

    }

    @Override
    public void draw(Graphics g) {

        // IDLE
        if (velX == 0 && velY == 0) {
            g.drawImage(foeImg,x,y,null);
        }

        // RIGHT ANIMATION
        if (velX > 0 && velY != 0){
            foeAnimDown.drawAnimation(x,y,g);
        }

        // LEFT ANIMATION
        if (velX < 0 && velY != 0) {
            foeAnimDown.drawAnimation(x,y,g);
        }

        // UP ANIMATON
        if (velY > 0 && velX == 0) {
            foeAnimDown.drawAnimation(x,y,g);
        }

        // DOWN ANIMATION
        if (velY < 0 && velX == 0) {
            foeAnimDown.drawAnimation(x,y,g);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }

    public Rectangle getBoundsBig() {
        return new Rectangle(x-16,y-16,64,64);
    }

}

