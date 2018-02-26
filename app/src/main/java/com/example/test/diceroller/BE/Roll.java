package com.example.test.diceroller.BE;

import java.sql.Time;
import java.util.ArrayList;

/**
 * Created by thomas on 26-02-2018.
 */

public class Roll {
    private int id;
    private Time time;



    private ArrayList<Integer> discs = new ArrayList<>();

    public Roll(int id, Time time, ArrayList<Integer> discs) {
        this.id = id;
        this.time = time;
        this.discs = discs;

    }

    public ArrayList<Integer> getDiscs() {

        return discs;
    }

    public void setDiscs(ArrayList<Integer> discs) {
        this.discs = discs;
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
