package me.frost.kuftehorsmansion.window;

import me.frost.kuftehorsmansion.game.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window {

    BufferedImage iconImage;
    File icon = new File("assets\\icon\\berkecanIcon.png");

    public Window(int width, int height, String title, Game game) {

        try {
            iconImage = ImageIO.read(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
