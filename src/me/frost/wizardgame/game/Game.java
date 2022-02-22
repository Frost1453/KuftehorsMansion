package me.frost.wizardgame.game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import me.frost.wizardgame.assets.ImageLoader;
import me.frost.wizardgame.assets.SpriteSheet;
import me.frost.wizardgame.framework.Camera;
import me.frost.wizardgame.framework.Handler;
import me.frost.wizardgame.framework.ID;
import me.frost.wizardgame.inputs.KeyInput;
import me.frost.wizardgame.inputs.MouseInput;
import me.frost.wizardgame.objects.Block;
import me.frost.wizardgame.objects.Chest;
import me.frost.wizardgame.objects.Enemy;
import me.frost.wizardgame.objects.Player;
import me.frost.wizardgame.window.Window;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1280, HEIGHT = WIDTH/16*9;
    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private Camera cam;
    private SpriteSheet ss;

    public int ammo = 16;

    private BufferedImage level = null;
    private BufferedImage spriteSheet = null;
    private BufferedImage floor = null;

    public Game() {
        new Window(WIDTH,HEIGHT,"Top-Down Game",this);
        start();
        create();
    }

    public void create() {
        handler = new Handler();
        cam = new Camera(0,0);
        this.addKeyListener(new KeyInput(handler));

        ImageLoader loader = new ImageLoader();
        level = loader.loadImage("/level.png");
        spriteSheet = loader.loadImage("/sprite_sheet.png");

        ss = new SpriteSheet(spriteSheet);
        floor = ss.grabImage(4,15,32,32);

        this.addMouseListener(new MouseInput(handler,cam,this, ss));

        loadLevel(level);


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

        for (int i = 0; i < handler.objects.size(); i++) {
            if(handler.objects.get(i).getID() == ID.Player) {
                cam.update(handler.objects.get(i));
            }
        }

        handler.update();
    }

    public void draw() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        //g.setColor(new Color(95,228,235));
        //g.fillRect(0,0,Game.WIDTH, Game.HEIGHT);



        g2d.translate(-cam.getX(), -cam.getY());

        for (int xx = 0; xx < 40*100; xx+=32 ) {
            for (int yy = 0; yy < 32*100; yy+=32) {
                g.drawImage(floor, xx,yy,null);
            }
        }

        handler.draw(g);

        g2d.translate(-cam.getX(), -cam.getY());

        g.dispose();
        bs.show();
    }

    private void loadLevel(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        for (int xx = 0; xx < w; xx++){
            for (int yy = 0; yy < h; yy++){

                int pixel = image.getRGB(xx,yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 255 && green == 0 && blue == 0) {
                    handler.addObject(new Block(xx*32, yy*32,ID.Block, ss));
                }

                if (red == 0 && green == 0 && blue == 255) {
                    handler.addObject(new Player(xx*32, yy*32, ID.Player,handler, this, ss));
                }

                if (red == 0 && green == 255 && blue == 0) {
                    handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy,handler, ss));
                }

                if (red == 0 && green == 255 && blue == 255) {
                    handler.addObject(new Chest(xx*32, yy*32, ID.Chest, ss));
                }
            }
        }
    }

    public int clamp(int value, int max, int min) {
        if (value <= min) min = value;
        else if (value >= max) max = value;
        return value;
    }
}
