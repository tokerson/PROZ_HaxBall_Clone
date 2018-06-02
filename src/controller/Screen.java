package controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import view.*;

public class Screen extends JFrame  {

    MainMenuPanel mainMenuPanel = new MainMenuPanel();
    GameView gv;

    public Screen()
    {
        add(mainMenuPanel);
        mainMenuPanel.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Game();
            }
        });
        setTitle(Constants.TITLE);
        setSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        setMinimumSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Screen();
            }
        });
    }
}
