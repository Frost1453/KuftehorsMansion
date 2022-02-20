package me.frost.wizardgame.framework;

import me.frost.wizardgame.game.Game;

public class Camera {

    private float x,y;

    public Camera(float x,float y) {
        this.x = x;
        this.y = y;
    }

    public void update(GameObject obj) {

        x += ((obj.getX() - x)- Game.WIDTH/2)*0.05f;
        y += ((obj.getY() - y)- Game.HEIGHT/2)*0.05f;

        if (x <= 0) x=0;
        if (x >= Game.WIDTH*2) x= Game.WIDTH*2;

        if (y <= 0) y = 0;
        if (y >= Game.HEIGHT+1400) y= Game.HEIGHT+1400;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
}
