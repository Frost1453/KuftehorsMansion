package me.frost.kuftehorsmansion.objects;

import me.frost.kuftehorsmansion.assets.SpriteSheet;
import me.frost.kuftehorsmansion.framework.GameObject;
import me.frost.kuftehorsmansion.framework.ID;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends GameObject {

    private BufferedImage walls;

    public Block(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);

        walls = ss.grabImage(5,15,32,32);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(walls,x,y,null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
}
