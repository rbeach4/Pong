/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3.beach;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author rbeach4
 */
public class Ball {

    private int x = AirPong.WINDOW_WIDTH / 2;
    private int y = AirPong.WINDOW_HEIGHT / 2;

    final private int velocity = 12;

    private int xVelocity = -velocity;
    private int yVelocity = velocity;

    private int size = 15;
    private Ball instance;

    /**
     * ball to be set into timer
     */
    public Ball() {
        instance = this;
    }

    /**
     * updtae method sets bounds of the ball etc.
     */
    public void update() {
        x = x + xVelocity;
        y = y + yVelocity;
        // hits pixel 0 (left side) bounce off
        if (x < 0) {
            xVelocity = velocity;

        } else if (x + size > AirPong.WINDOW_WIDTH - 28) {
            xVelocity = -velocity;
        }
        if (y < 49) {
            yVelocity = velocity;
        } else if (y + size > AirPong.WINDOW_HEIGHT - 45) {
            yVelocity = -velocity;
        }
    }

    /**
     * paints and sets the size of the ball
     * @param g - access to graphics
     */
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, size, size);
    }

    /**
     * sets the xVelocity of the ball
     * @param _x - new value to set x too
     */
    public void setXVelocity(int _x) {
        this.xVelocity = _x;
    }

    /**
     * swaps the yVelocity
     */
    public void bounceOffY() {
        yVelocity = -yVelocity;
    }

    /**
     * resets the ball in the center of the screen
     */
    private void resetBall() {
        xVelocity = 0;
        yVelocity = 0;
        x = AirPong.WINDOW_WIDTH / 2;
        y = AirPong.WINDOW_HEIGHT / 2;
        final Timer time = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instance.xVelocity = -velocity;
                instance.yVelocity = velocity;

            }
        });
        time.setRepeats(false);
        time.start();
    }

    /**
     * checks collision for the player
     * @param p1 - is the player that collision is being checked for
     * @return collide - to see is the ball has collided with the player 
     */
    public boolean checkCollision(Player p1) {
        boolean collide = false;
        if ((this.x > p1.getX() - p1.getWidth()) && (this.x < p1.getX() + p1.getWidth())) {
            if (this.y > p1.getY() - 20 && this.y < p1.getY() + p1.getHeight()) {
                collide = true;

            }
        }

        return collide;
    }

    /**
     * checks collision for the goal
     * @param g1 - is the goal that collision is being checked for
     * @return isInGoal - to see is the ball has collided with the goal
     */
    public boolean checkCollisionGoal(Goal g1) {
        boolean isInGoal = false;
        if ((this.x > g1.getX() - g1.getWidth()) && (this.x < g1.getX() + g1.getWidth())) {
            if (this.y > g1.getY() - 20 && this.y < g1.getY() + g1.getHeight()) {
                resetBall();
                isInGoal = true;
            }
        }
        return isInGoal;
    }

    /**
     * gets x of the ball
     * @return x - x of the ball
     */
    public int getX() {
        return x;
    }

    /**
     * gets y of the ball
     * @return y - y of the ball
     */
    public int getY() {
        return y;
    }
}
