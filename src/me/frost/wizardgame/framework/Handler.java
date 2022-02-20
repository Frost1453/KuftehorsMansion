package me.frost.wizardgame.framework;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> objects = new LinkedList<>();
    public boolean[] keys = new boolean[4];
    int i;


    public void update() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            obj.update();
        }

    }

    public void draw(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            obj.draw(g);
        }
    }

    public void addObject(GameObject obj) {
        objects.add(obj);
    }

    public void removeObject(GameObject obj) {
        objects.remove(obj);
    }

    public void setKeys(boolean[] keys,int i) {
        this.keys[i] = keys[i];
    }

    public boolean getKeys(boolean[] keys,int i) {
        return this.keys[i];
    }
}
