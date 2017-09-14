/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3.beach;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author rbeach4
 */
public class Player {

    private int x;
    private int y;
    private int yVelocity = 0;
    private int width = 15;
    private int height = 55;
    private String playerName;

    /**
     * constructor for the player
     * @param _x - position of the player
     * @param _y - position of the player
     * @param _playerName - name of the player
     */
    public Player(int _x, int _y, String _playerName) {
        this.x = _x;
        this.y = _y;
        this.playerName = _playerName;
    }

    /**
     * sets the bounds for the player to move
     */
    public void update() {
        y += yVelocity;
        if (y <= 45) {
            y = 45;
        } else if ((y + height) > (AirPong.WINDOW_HEIGHT - 38)) {
            y = (AirPong.WINDOW_HEIGHT - height - 38);
        }
    }

    /**
     * paints and fills player object
     * @param g
     */
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    /**
     * sets the yVelocity of the player object
     * @param speed
     */
    public void setYVelocity(int speed) {
        yVelocity = speed;
    }

    /**
     * resets the bounds of the player 
     * @param _x - reset to x value
     * @param _y - reset to y value
     */
    public void resetBounds(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }

    /**
     * returns x
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * returns y
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * returns width
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * returns height
     * @return height
     */
    public int getHeight() {
        return height;
    }

}
