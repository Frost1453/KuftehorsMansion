package me.frost.wizardgame.inputs;

import me.frost.wizardgame.assets.SpriteSheet;
import me.frost.wizardgame.framework.Camera;
import me.frost.wizardgame.framework.GameObject;
import me.frost.wizardgame.framework.Handler;
import me.frost.wizardgame.framework.ID;
import me.frost.wizardgame.game.Game;
import me.frost.wizardgame.objects.Bullet;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private Handler handler;
    private Camera cam;
    private Game game;
    private SpriteSheet ss;

    public MouseInput(Handler handler, Camera cam, Game game, SpriteSheet ss) {
        this.handler = handler;
        this.cam = cam;
        this.game = game;
        this.ss = ss;
    }

    public void mousePressed(MouseEvent e) {
        int mx = (int) (e.getX()+ cam.getX());
        int my = (int) (e.getY()+ cam.getY());

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject obj = handler.objects.get(i);

            if (obj.getID() == ID.Player) {
                if (game.ammo > 0) {
                    handler.addObject(new Bullet(obj.getX() + 16, obj.getY() + 24, ID.Bullet, handler, mx, my, ss));
                    game.ammo--;
                }
            }
        }
    }
}
