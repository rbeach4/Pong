/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3.beach;

import java.io.IOException;
import javax.swing.JFrame;

/**
 * tutorial video from evilfrenchguy on youtube
 *
 * @author rbeach4
 */
public class AirPong extends JFrame {

    final static int WINDOW_WIDTH = 725;
    final static int WINDOW_HEIGHT = 475;

    /**
     * is the JFrame set up for the game
     *
     * @throws java.io.IOException
     */
    public AirPong() throws IOException {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(true);
        setTitle("Ricky's Pong Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new GamePanel());
        setVisible(true);
        //setLocationRelativeTo(null);
    }

    /**
     * main method to run the game
     *
     * @param args - argument passed in
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        new AirPong();
    }

}
