package me.frost.wizardgame.framework;

import java.awt.*;

public abstract class GameObject {

    protected int x, y;
    protected float velX = 0, velY = 0;
    protected ID id;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void update();
    public abstract void draw(Graphics g);
    public abstract Rectangle getBounds();

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public float getVelX() {
        return velX;
    }
    public float getVelY() {
        return velY;
    }
    public ID getID() {
        return id;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setVelX(float velX) {
        this.velX = velX;
    }
    public void setVelY(float velY) {
        this.velY = velY;
    }
    public void setID(ID id) {
        this.id = id;
    }
}
