package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import controller.Screen;
import model.*;


public class GameView extends JPanel {

    private ArrayList<Player> players;
    private ArrayList<Ball> balls;
    private Stadium stadium;
    private JLabel scoreLabel;


    public GameView(ArrayList<Player> players,ArrayList<Ball> balls,Stadium stadium){
        setLayout(new BorderLayout());
        setFocusable(true);
        this.players = players;
        this.balls = balls;
        this.stadium = stadium;

        scoreLabel = new JLabel("0:0");
        scoreLabel.setOpaque(false);
        scoreLabel.setFont(new Font("Courier New", Font.BOLD, 40));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setVerticalAlignment(JLabel.CENTER);
        add(scoreLabel,BorderLayout.NORTH);

        setPreferredSize(new Dimension(Constants.WIDTH,Constants.HEIGHT));
    }

    public void updateScore(int score1,int score2){
        scoreLabel.setText(score1 + ":" + score2);
    }

    private void drawPlayersAndBall(Graphics2D g){
        for(Player player:players) {
            g.drawImage(player.getImage(), (int)player.getX(), (int)player.getY(), 2*player.getRadius(), 2*player.getRadius(), this);
        }
        for(Ball ball : balls) {
            g.drawImage(ball.getImage(), (int) ball.getX(), (int) ball.getY(), 2 * ball.getRadius(), 2 * ball.getRadius(), this);
        }
    }

    public void showWinner(int i ){

        JLabel winnerText = new JLabel("<html>PLAYER " + i + " WON!<br/>Press Esc to continue</html>");
        winnerText.setAlignmentX(WIDTH/2);
        winnerText.setAlignmentY(HEIGHT/2);
        winnerText.setFont(new Font("Rockwell", Font.BOLD, 40));
        winnerText.setForeground(Color.getHSBColor(184,68,66));
        winnerText.setHorizontalAlignment(JLabel.CENTER);
        winnerText.setVerticalAlignment(JLabel.CENTER);

        add(winnerText,BorderLayout.CENTER);

    }

    public void backToMainMenu() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setVisible(false);
        new Screen();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(stadium.getImage(),0,0,Constants.WIDTH,Constants.HEIGHT,this);
        drawPlayersAndBall(g2);

    }




}
