package me.frost.wizardgame.window;

import me.frost.wizardgame.assets.ImageLoader;
import me.frost.wizardgame.game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;

public class Window {

    BufferedImage iconImage;

    public Window(int width, int height, String title, Game game) {
        Window window = this;

        ImageLoader loader = new ImageLoader();
        iconImage = loader.loadImage("/berkecanicon.png");


        JFrame jf = new JFrame(title);
        jf.setPreferredSize(new Dimension(width, height));
        jf.setMinimumSize(new Dimension(width, height));
        jf.setMaximumSize(new Dimension(width, height));

        jf.add(game);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setIconImage(iconImage);
        jf.setVisible(true);
    }
}
