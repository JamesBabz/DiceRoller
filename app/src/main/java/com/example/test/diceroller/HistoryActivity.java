package com.example.test.diceroller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.test.diceroller.BE.Roll;

import java.util.ArrayList;

/**
 * Created by James on 26-02-2018.
 */

public class HistoryActivity extends Activity {

    ListView historyList;

    private ArrayAdapter<Integer> itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        historyList = findViewById(R.id.listViewRolls);

        Bundle extras = getIntent().getExtras();

        ArrayList<Roll> rolls = ((ArrayList<Roll>) extras.getSerializable("Rolls"));
        ArrayList<Integer> rollIds = new ArrayList<>();

        for (Roll roll:rolls
             ) {
            rollIds.add(roll.getId());

        }
        itemsAdapter =
                new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, rollIds);
        historyList.setAdapter(itemsAdapter);
    }

    private void clearAll(View view){

        Context context = getApplicationContext();
        CharSequence text = "History is going to be cleared";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
