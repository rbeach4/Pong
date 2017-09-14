/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3.beach;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import javax.swing.Timer;

/**
 * obtained help from Joey Burt, Ben Humphries, and Ben Marshall, Andrew Wassell
 *
 * @author rbeach4
 */
public class GamePanel extends JPanel implements ActionListener, KeyListener {

    private String player1Name = getPlayer1Name();
    private String player2Name = getPlayer2Name();
    private Player p1;
    private Player p2;
    private Ball ball;
    private Goal g1;
    private Goal g2;
    private HighScore hs;
    private ArrayList<HighScore> obj;
    private final int xVelocity = 5;
    private int scoreP1 = 0;
    private int scoreP2 = 0;
    private int seconds = 0;
    private int minutes = 0;
    private Timer timer;
    private Timer time;
    private final String fileName = "HighScoresList.txt";
    private boolean gameOver = false;
    private final int winScore = 7;

    /**
     * constructor for the GamePanel
     *
     * @throws java.io.IOException
     */
    public GamePanel() throws IOException {

        p1 = new Player(50, AirPong.WINDOW_HEIGHT / 2, player1Name);
        p2 = new Player(AirPong.WINDOW_WIDTH - 75, AirPong.WINDOW_HEIGHT / 2, player2Name);
        ball = new Ball();
        g1 = new Goal(0, AirPong.WINDOW_HEIGHT / 2);
        g2 = new Goal(AirPong.WINDOW_WIDTH - 35, AirPong.WINDOW_HEIGHT / 2);
        obj = new ArrayList<HighScore>();
        time = new Timer(50, this);
        time.start();
        this.addKeyListener(this);
        this.setFocusable(true);
        load(fileName);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                if (seconds == 60) {
                    minutes++;
                    seconds = 0;
                }
            }

        });
        timer.start();

    }

    /**
     * updates all the objects
     */
    private void update() throws IOException {
        p1.update();
        ball.update();
        p2.update();
        boolean collide1 = ball.checkCollision(p1);
        if (collide1) {
            if (ball.getX() > p1.getX()) {
                ball.setXVelocity(xVelocity);
            } else if (ball.getX() < p1.getX()) {
                ball.setXVelocity(-xVelocity);
            }
        }
        boolean collide2 = ball.checkCollision(p2);
        if (collide2) {
            if (ball.getX() < p2.getX()) {
                ball.setXVelocity(-xVelocity);
            } else if (ball.getX() > p2.getX()) {
                ball.setXVelocity(xVelocity);
            }
        }
        boolean isInGoalG1 = ball.checkCollisionGoal(g1);
        if (isInGoalG1) {
            scoreP2++;
        }
        boolean isInGoalG2 = ball.checkCollisionGoal(g2);
        if (isInGoalG2) {
            scoreP1++;
        }
        updateTwo();
    }

    /**
     * fills all the colors and draws the strings on the screen
     *
     * @param g - for the graphics aspect to put it on the screen
     */
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, AirPong.WINDOW_WIDTH, AirPong.WINDOW_HEIGHT);
        g1.paint(g);
        g2.paint(g);
        p1.paint(g);
        p2.paint(g);
        ball.paint(g);
        g.drawLine(0, 45, AirPong.WINDOW_WIDTH, 45);
        g.drawLine(AirPong.WINDOW_WIDTH - 185, 0, AirPong.WINDOW_WIDTH - 185, 43);
        g.drawLine(185, 0, 185, 43);
        g.drawString(player1Name + ": " + scoreP1, AirPong.WINDOW_WIDTH - 677, 35);
        g.drawString(player2Name + ": " + scoreP2, AirPong.WINDOW_WIDTH - 100, 35);
        if (seconds < 10) {
            int temp = 0;
            g.drawString("Timer: " + this.minutes + ":" + temp + this.seconds, AirPong.WINDOW_WIDTH / 3, 35);
        } else {
            g.drawString("Timer: " + this.minutes + ":" + this.seconds, AirPong.WINDOW_WIDTH / 3, 35);
        }
        g.setColor(Color.BLACK);

    }

    /**
     * returns the ball from the ball class
     *
     * @return ball - of the ball type
     */
    public Ball getBall() {
        return ball;
    }

    /**
     * updates the game and updates the movement of objects
     *
     * @param e - actionEvent being used
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            update();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        repaint();
    }

    /**
     * not really used
     *
     * @param e - actionEvent being used
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * sets the velocity of the player depending on what key is pressed
     *
     * @param e - KeyEvent being used
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q) {
            p1.setYVelocity(-6);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            p1.setYVelocity(6);
        }
        if (e.getKeyCode() == KeyEvent.VK_O) {
            p2.setYVelocity(-6);
        } else if (e.getKeyCode() == KeyEvent.VK_L) {
            p2.setYVelocity(6);
        }

    }

    /**
     * when the key is released it stops the player by setting the y-velocity
     * back to 0
     *
     * @param e - KeyEvent performed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_A) {
            p1.setYVelocity(0);
        }
        if (e.getKeyCode() == KeyEvent.VK_O || e.getKeyCode() == KeyEvent.VK_L) {
            p2.setYVelocity(0);
        }
    }

    /**
     * gets the player 1 name from the user and returns it
     *
     * @return player1Name - is the name from the dialog box
     */
    private String getPlayer1Name() {
        String player1Name = JOptionPane.showInputDialog("Please enter your name Player 1: ");
        return player1Name;
    }

    /**
     * gets the player 2 name from the user and returns it
     *
     * @return player2Name - is the name from the dialog box
     */
    private String getPlayer2Name() {
        String player2Name = JOptionPane.showInputDialog("Please enter your name Player 2: ");
        return player2Name;
    }

    /**
     * finds the winner name 
     */
    private String winnerFoundName() {
        String winner = "";
        if (scoreP1 == winScore) {
            winner = player1Name;
        }
        if (scoreP2 == winScore) {
            winner = player2Name;
        }
        return winner;

    }

    /**
     * takes the minutes and seconds and converts them to all seconds
     *
     * @return retSecond - is all the time in seconds
     */
    public int timeToSeconds() {
        int retSecond = 0;
        if (minutes == 0) {
            retSecond = seconds;
        }
        if (minutes > 0) {
            retSecond = (minutes * 60) + seconds;
        }
        return retSecond;
    }

    /**
     * resets everything that has to do with the game
     */
    private void resetGame() {
        scoreP1 = 0;
        scoreP2 = 0;
        seconds = 0;
        minutes = 0;
        time.restart();
        timer.restart();
        gameOver = false;
    }
    /**
     * gets the score to be displayed on HSL
     * @param seconds - number of seconds on the clock including minutes converted
     * @return finalScore - the real score to be displayed
     */

    private int findActualScore(int seconds) {
        int finalScore = 0;
        finalScore = seconds * 7;
        return finalScore;
    }
    /**
     * saves the arrayList to a file using FileWriter
     * @param fileName - saved as this name
     * @param hs - arrayName to be saved
     * @throws IOException - input output exception
     */

    private void save(String fileName, ArrayList<HighScore> hs) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        for (HighScore highScore : hs) {
            fw.write(highScore.toString() + "\n");
        }
        fw.close();
    }

    /**
     *
     * @param fileName- load this file using FileReader
     * @throws FileNotFoundException - it knows if there is no file found
     * @throws IOException - input output exception
     */
    public void load(String fileName) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String hs1 = br.readLine();
        while (hs1 != null) {
            if (hs1.equals("")) {
                hs1 = br.readLine();
            } else {
                String[] s = hs1.split(" ");
                String name = s[0];
                int score = Integer.parseInt(s[1]);
                HighScore hs = new HighScore(name, score);
                obj.add(hs);
                hs1 = br.readLine();
            }
        }
        fr.close();
        br.close();
        Collections.sort(obj);
    }

    /**
     * clears contents of arrayList
     */
    public void clear() {
        obj.clear();
    }

    /**
     * takes the arrayList and puts it into a string
     * @return retStr - is the final String to be displayed
     */
    public String scoreListToString() {
        int counter = 1;
        String retStr = "";
        for (HighScore hs : obj) {
            if (counter <= 10) {
                retStr += ("" + counter + ") " + hs.toString() + "\n");
                counter++;
            }
        }
        return retStr;
    }
    /**\
     * second part to the original update method
     * @throws IOException - input output exception
     */

    private void updateTwo() throws IOException {
        if ((scoreP1 == winScore || scoreP2 == winScore) && !gameOver) {
            gameOver = true;
            timer.stop();
            hs = new HighScore(winnerFoundName(), findActualScore(timeToSeconds()));
            obj.add(hs);
            Collections.sort(obj);
            if (gameOver) {
                save(fileName, obj);
            }
            clear();
            load(fileName);
            JOptionPane.showMessageDialog(null, "High Score List!!!\n" + scoreListToString());
            String[] options = {"Play again", "Quit Game"};
            int optionChosen = JOptionPane.showOptionDialog(null, "The winner is " + winnerFoundName() + "!!! \n What would you like to do next?",
                    "Option Dialog Box", 0, JOptionPane.QUESTION_MESSAGE, null, options, "Play again");
            switch (optionChosen) {
                case 0:
                    resetGame();
                    break;
                case 1:
                    System.exit(0);
                    break;
            }
        }
    }
}
