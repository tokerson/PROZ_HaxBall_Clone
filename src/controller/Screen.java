package controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.*;
import view.*;

public class Screen extends JFrame  {

    MainMenuPanel mainMenuPanel = new MainMenuPanel();
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
    }

    private void addEverything(JPanel panel){
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Courier New", Font.ITALIC, 30));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Game();
            }
        });
        startButton.setPreferredSize(new Dimension(300,80));

        JButton exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Courier New", Font.ITALIC, 30));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        exitButton.setPreferredSize(new Dimension(300,80));

        panel.add(startButton);
        panel.add(exitButton);

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
