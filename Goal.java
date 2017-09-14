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
 * @author Richard H Beach
 */
public class Goal {

    private int x;
    private int y;
    private int height = 80;
    private int width = 20;

    /**
     * constructor for the Goal
     *
     * @param _x - sets x-coordinate
     * @param _y - sets y-coordinate
     */
    public Goal(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }

    /**
     * paints the goals and fills it
     *
     * @param g - access to the graphics aspect
     */
    public void paint(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height);
    }

    /**
     * getter returns the x-coordinate of the goal
     *
     * @return x - x-coordinate is the width
     */
    public int getX() {
        return x;
    }

    /**
     * returns the y-coordinate of the goal
     * @return y - y-coordinate is the height
     */
    public int getY() {
        return y;
    }

    /**
     * gets the height of the goal
     * @return height- is the height of the goal
     */
    public int getHeight() {
        return height;
    }

    /**
     * returns the width of the goal
     * @return width- width of the goal
     */
    public int getWidth() {
        return width;
    }

}
