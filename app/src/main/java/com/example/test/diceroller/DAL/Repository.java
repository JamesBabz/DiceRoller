package com.example.test.diceroller.DAL;

import com.example.test.diceroller.BE.Roll;

import java.util.ArrayList;

/**
 * Created by thomas on 26-02-2018.
 */

public class Repository {

    ArrayList<Roll> allRolls;

    public Repository() {
        allRolls = new ArrayList<>();
    }

    public ArrayList<Roll> getAllRolls(){
        return allRolls;
    }

    public void addRollToList(Roll roll){
        allRolls.add(roll);
    }

    public void DeleteAllRolls()
    {
        allRolls.clear();
    }
}
