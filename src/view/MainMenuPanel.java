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
        title.setForeground(Color.black);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        title.setFont(new Font("Courier New", Font.BOLD, 40));
        title.setHorizontalAlignment(WIDTH/2);
        setPreferredSize(new Dimension(Constants.WIDTH/2,Constants.HEIGHT));
//        setLayout(boxLayout);

        Box.Filler glue = (Box.Filler) Box.createVerticalGlue();
        glue.changeShape(glue.getMinimumSize(), new Dimension(0,200),glue.getMaximumSize());

        startButton = new Button("Start Game");
        startButton.setFont(new Font("SERIF", Font.ITALIC, 30));

        exitButton = new Button("EXIT");
        exitButton.setFont(new Font("SERIF", Font.ITALIC, 30));
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

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage("menuBackground.png");

        g2.drawImage(img,0,0,Constants.WIDTH/2,Constants.HEIGHT,this);


    }

    public Button getStartButton() {
        return startButton;
    }
}
