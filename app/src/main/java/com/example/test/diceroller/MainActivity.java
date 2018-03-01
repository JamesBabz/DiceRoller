package com.example.test.diceroller;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.test.diceroller.BE.Roll;
import com.example.test.diceroller.DAL.Repository;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView ivDie1;
    ImageView ivDie2;
    ImageView ivDie3;
    ImageView ivDie4;
    ImageView ivDie5;
    ImageView ivDie6;
    List<ImageView> dice;
    LinearLayout llHistory;
    Button btnClearHistory;


    int amountOfRolls = 0;
    int diceNum1;
    int diceNum2;

    int dBlank = R.drawable.dice_blank;
    int d1 = R.drawable.dice1;
    int d2 = R.drawable.dice2;
    int d3 = R.drawable.dice3;
    int d4 = R.drawable.dice4;
    int d5 = R.drawable.dice5;
    int d6 = R.drawable.dice6;

    Repository repo;

    public MainActivity() {
        repo = new Repository();
        dice = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(2);
        integers.add(1);
        integers.add(4);
        repo.addRollToList(new Roll(1, new Time(System.currentTimeMillis()), integers));
        repo.addRollToList(new Roll(2, new Time(System.currentTimeMillis() + 1000), integers));

        CreateDieViews();

        resetDice();
    }


    private void CreateDieViews() {
        ivDie1 = findViewById(R.id.ivDie1);
        ivDie2 = findViewById(R.id.ivDie2);
        ivDie3 = findViewById(R.id.ivDie3);
        ivDie4 = findViewById(R.id.ivDie4);
        ivDie5 = findViewById(R.id.ivDie5);
        ivDie6 = findViewById(R.id.ivDie6);

        dice.add(ivDie1);
        dice.add(ivDie2);
        dice.add(ivDie3);
        dice.add(ivDie4);
        dice.add(ivDie5);
        dice.add(ivDie6);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.miShowHist:
                openHistoryActivity();
                return true;
            case R.id.miClose:
                closeApp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openHistoryActivity() {
        Intent intent = new Intent();
        intent.setClass(this, HistoryActivity.class);
        intent.putExtra("Rolls", repo.getAllRolls());
        startActivity(intent);


    }

    private void closeApp() {
        finish();
    }


    public void roll(View view) {
        amountOfRolls++;
        ArrayList<Integer> diceRolls = new ArrayList<>();
        for (int i = 0; i < dice.size(); i++) {
            int diceNum = getDieNum();
            diceRolls.add(diceNum);
            setDieImage(dice.get(i), diceNum);
        }
        repo.addRollToList(new Roll(amountOfRolls, new Time(System.currentTimeMillis()), diceRolls));
    }

    private void setDieImage(ImageView iv, int diceNum) {
        switch (diceNum) {
            case 1:
                iv.setImageResource(d1);
                break;
            case 2:
                iv.setImageResource(d2);
                break;
            case 3:
                iv.setImageResource(d3);
                break;
            case 4:
                iv.setImageResource(d4);
                break;
            case 5:
                iv.setImageResource(d5);
                break;
            case 6:
                iv.setImageResource(d6);
                break;
            default:
                iv.setImageResource(dBlank);
                break;
        }
    }

    private int getDieNum() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }


    private void resetDice() {
        for (int i = 0; i < dice.size(); i++) {
            setDieImage(dice.get(i), 0);
        }
    }


    @Override
    public void finish() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.finishAndRemoveTask();
        } else {
            super.finish();
        }
    }


}
