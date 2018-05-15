import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JFrame  {

    JPanel panel = new JPanel();

    MainMenuPanel mainMenuPanel = new MainMenuPanel();
    GameView gv;

    public Screen()
    {
        super();
        panel.add(mainMenuPanel,"MainMenuPanel");
        mainMenuPanel.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //panel.removeAll();
                panel.removeAll();
                gv = Game.getGameView();
                panel.add(gv);
                gv.requestFocusInWindow();
                pack();
                revalidate();
                repaint();

            }
        });
        add(panel);
        panel.requestFocusInWindow();
        setTitle(Constants.TITLE);
        setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
    }

    private void createMainMenu(){

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
