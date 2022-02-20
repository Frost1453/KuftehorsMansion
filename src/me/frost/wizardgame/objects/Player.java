package me.frost.wizardgame.objects;

import me.frost.wizardgame.framework.GameObject;
import me.frost.wizardgame.framework.Handler;
import me.frost.wizardgame.framework.ID;
import me.frost.wizardgame.inputs.KeyInput;

import java.awt.*;

public class Player extends GameObject {

    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void update() {
        x += velX;
        y += velY;

        collison();
    }

    private void collison() {
        for (int i = 0; i < handler.objects.size();i++) {
            GameObject obj = handler.objects.get(i);

            if (obj.getID() == ID.Block) {

                if (getBounds().intersects(obj.getBounds())){
                    x += velX * -1;
                    y += velY * -1;
                }

            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x,y,32,48);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,48);
    }
}
