package me.frost.wizardgame.framework;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {

    public ArrayList<GameObject> objects = new ArrayList<>();


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
}
