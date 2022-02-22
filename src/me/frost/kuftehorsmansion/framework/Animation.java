package me.frost.kuftehorsmansion.framework;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {
    private int speed;
    private int frames;

    private int index = 0;
    private int count = 0;

    private BufferedImage[] images;
    private BufferedImage currentImg;

    public Animation(int speed, BufferedImage... args) {
        this.speed = speed;
        images = new BufferedImage[args.length];
        for (int i = 0; i < args.length; i++) {
            images[i] = args[i];
        }
        frames = args.length;
    }

    public void runAnimation() {
        index++;
        if (index >= speed) {
            nextFrame();
        }
    }

    private void nextFrame() {
        for (int i = 0; i < frames; i++) {
            if (count == i)
                currentImg = images[i];
        }

        count++;

        if (count > frames)
            count = 0;
    }

    public void drawAnimation(int x, int y, Graphics g) {
        g.drawImage(currentImg,x,y,null);
    }

    public void drawAnimation(int x, int y, Graphics g, int scaleX, int scaleY) {
        g.drawImage(currentImg,x, y, scaleX, scaleY,null);
    }
}
