package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Constants;


public class MainMenuPanel extends JPanel {

    private Box box = Box.createVerticalBox();
    private JButton startButton ;
    private JButton exitButton ;
    private Image img;

    public MainMenuPanel(){

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        img = toolkit.getImage("menuBackground.jpg");

        JLabel title = new JLabel("ProzBall");
        title.setForeground(Color.white);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Rockwell", Font.BOLD, 45));
        title.setHorizontalAlignment(WIDTH/2);
        setPreferredSize(new Dimension(Constants.WIDTH/2,Constants.HEIGHT));

        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Rockwell", Font.ITALIC, 30));

        exitButton = new JButton("      Exit      " );
        exitButton.setFont(new Font("Rockwell", Font.ITALIC, 30));
        exitButton.addActionListener(e -> System.exit(0));

        box.add(Box.createVerticalStrut(50));
        box.add(title);
        box.add(Box.createVerticalStrut(100));
        box.add(startButton);
        box.add(Box.createVerticalStrut(25));
        box.add(exitButton);

        add(box);

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(img,0,0,Constants.WIDTH/2,Constants.HEIGHT,this);

    }

    public JButton getStartButton() {
        return startButton;
    }
}
