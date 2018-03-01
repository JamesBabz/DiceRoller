package com.example.test.diceroller.BE;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by thomas on 26-02-2018.
 */

public class Roll implements Serializable {
    private int id;
    private Time time;



    private ArrayList<Integer> dice = new ArrayList<>();

    public Roll(int id, Time time, ArrayList<Integer> dice) {
        this.id = id;
        this.time = time;
        this.dice = dice;

    }

    public ArrayList<Integer> getDice() {

        return dice;
    }

    public void setDice(ArrayList<Integer> dice) {
        this.dice = dice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
