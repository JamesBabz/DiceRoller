package com.example.test.diceroller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.diceroller.BE.Roll;
import com.example.test.diceroller.DAL.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by James on 26-02-2018.
 */

public class HistoryActivity extends Activity {

    ListView historyList;
    ArrayList<Roll> rolls;
    RollAdapter adapter;
    Button btnClear;

    private ArrayAdapter<Integer> itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        historyList = findViewById(R.id.listViewRolls);
        btnClear = findViewById(R.id.btnClearHistory);

        Bundle extras = getIntent().getExtras();

       rolls = ((ArrayList<Roll>) extras.getSerializable("Rolls"));

        adapter = new RollAdapter(this, R.layout.cell_extended, rolls);
        historyList.setAdapter(adapter);

//        btnClear.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                setResult(1);
//                finish();
//            }
//        });
    }



    public void clearAll(View view){

        setResult(1);
        finish();

//        Context context = getApplicationContext();
//        CharSequence text = "History is going to be cleared";
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();

    }

}

class RollAdapter extends ArrayAdapter<Roll> {

    int dBlank = R.drawable.dice_blank;
    int d1 = R.drawable.dice1;
    int d2 = R.drawable.dice2;
    int d3 = R.drawable.dice3;
    int d4 = R.drawable.dice4;
    int d5 = R.drawable.dice5;
    int d6 = R.drawable.dice6;



    private ArrayList<Roll> rolls;

    static String TAG = "Pos";


    public RollAdapter(Context context, int textViewResourceId,
                         ArrayList<Roll> rolls) {
        super(context, textViewResourceId, rolls);
        this.rolls = rolls;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        if (v == null) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);

            v = li.inflate(R.layout.cell_extended, parent,false);
            Log.d(TAG, "Position: " + position + " View Reused");
        }
        else
            Log.d(TAG, "Position: " + position + " View Reused");



        Roll roll = rolls.get(position);

        TextView id = v.findViewById(R.id.twId);
        TextView time = v.findViewById(R.id.twTime);


        id.setText(roll.getId()+ "  ");
        time.setText(roll.getTime().toString());

        ArrayList<ImageView> dice = new ArrayList<>();

        ImageView dice1 = v.findViewById(R.id.dice1);
        ImageView dice2 = v.findViewById(R.id.dice2);
        ImageView dice3 = v.findViewById(R.id.dice3);
        ImageView dice4 = v.findViewById(R.id.dice4);
        ImageView dice5 = v.findViewById(R.id.dice5);
        ImageView dice6 = v.findViewById(R.id.dice6);


        dice.add(dice1);
        dice.add(dice2);
        dice.add(dice3);
        dice.add(dice4);
        dice.add(dice5);
        dice.add(dice6);


        for (int i = 0; i < roll.getDice().size(); i++) {
            setDieImage(dice.get(i), roll.getDice().get(i));
        }

        return v;
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



}
