package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Constants;


public class MainMenuPanel extends JPanel {


    private BoxLayout boxLayout = new BoxLayout(this,BoxLayout.Y_AXIS);
    private Box box = Box.createVerticalBox();
    private Button startButton ;
    private Button exitButton ;
    private JLabel title;

    public MainMenuPanel(){

        JLabel title = new JLabel("ProzBall");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Courier New", Font.BOLD, 40));
        title.setHorizontalAlignment(WIDTH/2);
        setPreferredSize(new Dimension(Constants.WIDTH/2,Constants.HEIGHT));
//        setLayout(boxLayout);

        Box.Filler glue = (Box.Filler) Box.createVerticalGlue();
        glue.changeShape(glue.getMinimumSize(), new Dimension(0,200),glue.getMaximumSize());

        startButton = new Button("Start Game");
        startButton.setFont(new Font("Courier New", Font.ITALIC, 30));

        exitButton = new Button("EXIT");
        exitButton.setFont(new Font("Courier New", Font.ITALIC, 30));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
//
        box.add(title);
        box.add(Box.createVerticalStrut(50));
        box.add(startButton);
        box.add(glue);
        box.add(exitButton);

        add(box);

    }

    public Button getStartButton() {
        return startButton;
    }
}
