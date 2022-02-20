package me.frost.wizardgame.objects;

import me.frost.wizardgame.framework.GameObject;
import me.frost.wizardgame.framework.Handler;
import me.frost.wizardgame.framework.ID;

import java.awt.*;

public class Bullet extends GameObject {

    private Handler handler;
    private ID id;

    public Bullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.id = id;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
