package com.game.main;
import javax.swing.*;
import java.awt.Canvas;
import java.awt.Dimension;

public class Window extends Canvas{
    private static final long serialVersionUID = -3528194092521108876L;//don't care
    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);

        frame.setPreferredSize((new Dimension(width,height)));//fixed window size
        frame.setMaximumSize((new Dimension(width,height)));
        frame.setMinimumSize((new Dimension(width,height)));
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);//window starts in the middle of the screen
        frame.add(game);
        frame.setVisible(true);

        game.start();
    }
}
