package me.frost.wizardgame.objects;

import me.frost.wizardgame.assets.SpriteSheet;
import me.frost.wizardgame.framework.GameObject;
import me.frost.wizardgame.framework.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Chest extends GameObject {

    private BufferedImage chestImg;

    public Chest(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);

        chestImg = ss.grabImage(5,4,32,32);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(chestImg,x,y,null);
        //g.setColor(Color.ORANGE);
        //g.fillRect(x,y,32,16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y,32,32);
    }
}
