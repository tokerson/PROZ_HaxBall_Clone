package controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import view.*;

public class Screen extends JFrame  {

    public Screen()
    {
        MainMenuPanel mainMenuPanel = new MainMenuPanel();
        mainMenuPanel.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Game();
            }
        });

        add(mainMenuPanel);
        mainMenuPanel.repaint();

        setTitle(Constants.TITLE);
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
