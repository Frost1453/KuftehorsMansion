package me.frost.wizardgame.game;

import java.awt.*;
import java.awt.image.BufferStrategy;

import me.frost.wizardgame.framework.Handler;
import me.frost.wizardgame.framework.ID;
import me.frost.wizardgame.inputs.KeyInput;
import me.frost.wizardgame.objects.Player;
import me.frost.wizardgame.window.Window;

public class Game extends Canvas implements Runnable {

    private static final int WIDTH = 1024,HEIGHT = 768;
    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;

    public Game() {
        new Window(WIDTH,HEIGHT,"Top-Down Game",this);
        start();
        create();
    }

    public void create() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        handler.addObject(new Player(100,100, ID.Player,handler));


    }

    public synchronized void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfUpdates = 60.0;
        double ns = 1000000000 / amountOfUpdates;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            draw();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    public void update() {
        handler.update();
    }

    public void draw() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(95,228,235));
        g.fillRect(0,0,Game.WIDTH, Game.HEIGHT);

        handler.draw(g);

        g.dispose();
        bs.show();
    }
}
