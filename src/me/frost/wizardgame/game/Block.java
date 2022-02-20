package me.frost.wizardgame.game;

import me.frost.wizardgame.framework.GameObject;
import me.frost.wizardgame.framework.ID;

import java.awt.*;

public class Block extends GameObject {

    public Block(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x,y,32,32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
}
