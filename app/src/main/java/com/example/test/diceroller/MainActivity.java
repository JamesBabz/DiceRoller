package com.example.test.diceroller;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView ivDice1;
    ImageView ivDice2;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivDice1 = findViewById(R.id.ivDice1);
        ivDice2 = findViewById(R.id.ivDice2);
        llHistory = findViewById(R.id.llHistory);
        btnClearHistory = findViewById(R.id.btnClearHistory);
        registerForContextMenu(btnClearHistory);

        resetDice();
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
            case R.id.miSettings:
                showSettings();
                return true;
            case R.id.miAbout:
                showAbout();
                return true;
            case R.id.miClose:
                closeApp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSettings() {

    }

    private void showAbout() {

    }

    private void closeApp() {
        finish();
    }


    public void roll(View view) {
        amountOfRolls++;
        diceNum1 = getDiceNum();
        diceNum2 = getDiceNum();
        setDiceImage(ivDice1, diceNum1);
        setDiceImage(ivDice2, diceNum2);
        createHistoryLog();
    }

    private void createHistoryLog() {
        TextView tvHis = new TextView(this);
        tvHis.setText(amountOfRolls + ": " + diceNum1 + " - " + diceNum2);
        tvHis.setTag("HistText");
        if (llHistory.getChildCount() >= 7) {
            llHistory.removeViewAt(1);
        }
        llHistory.addView(tvHis, llHistory.getChildCount() - 1);
    }

    private void setDiceImage(ImageView iv, int diceNum) {
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

    private int getDiceNum() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }


    private void resetDice() {
        setDiceImage(ivDice1, 0);
        setDiceImage(ivDice2, 0);
    }

    public void clearHistory(View view) {
        clearHistory(view, true);
    }

    public void clearHistory(View view, boolean all) {

        int childCount = llHistory.getChildCount();
        if(llHistory.findViewWithTag("HistText") == null){
            return;
        }
        if(all){
            amountOfRolls = 0;
            for (int i = 0; i < childCount-2; i++) {
                llHistory.removeView(llHistory.findViewWithTag("HistText"));
            }
        }else{
            amountOfRolls--;
            llHistory.removeViewAt(childCount-2);
        }

        resetDice();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        if(view == btnClearHistory){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_clear_history, menu);
        }



    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.miClearAll:
                clearHistory(null, true);
                return true;
            case R.id.miClearLast:
                clearHistory(null, false);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void finish() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.finishAndRemoveTask();
        }
        else {
            super.finish();
        }
    }


}