/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3.beach;

/**
 *
 * @author rbeach4
 */
public class HighScore implements Comparable<HighScore> {

    private String name;
    private int score;

    /**
     * constructor for the highScore     
     * @param _name - name of the player
     * @param _score - score of the player
     */
    public HighScore(String _name, int _score) {
        this.name = _name;
        this.score = _score;
    }

    /**
     * returns the name of the player
     * @return name- their name
     */
    public String getName() {
        return name;
    }

    /**
     * returns the score of the player
     * @return score - their score
     */
    public int getScore() {
        return score;
    }

    /**
     * compare to method to sort through the array list to display it
     * @param hsl - high score that is being compared to this one
     * @return retValue - is which way their score is going in terms of position
     */
    @Override
    public int compareTo(HighScore hsl) {
        int retValue = 0;
        if (this.score > hsl.getScore()) {
            retValue = 1;
        } else if (this.score < hsl.getScore()) {
            retValue = -1;
        }
        return retValue;
    }

    /**
     * String version of the name and score
     * @return (name + " " + score)
     */
    @Override
    public String toString() {
        return (name + " " + score);
    }

}
