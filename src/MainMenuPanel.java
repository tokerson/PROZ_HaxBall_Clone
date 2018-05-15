



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {


    Box box ;
    Button startButton ;
    Button exitButton ;

    MainMenuPanel(){
//        hBox.setSpacing(100);
        box = Box.createVerticalBox();
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
        box.add(startButton);
        box.add(glue);
        box.add(exitButton);
        add(box);

    }




}
