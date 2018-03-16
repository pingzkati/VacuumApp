package com.warann.vacuum.vacuumapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.content.Intent;
import android.widget.ToggleButton;
import android.widget.CheckBox;


public class MainActivity extends AppCompatActivity {

//    String roommap = "##########" + "\n"
//            + "#        #" + "\n"
//            + "#        #" + "\n"
//            + "#        #" + "\n"
//            + "#        #" + "\n"
//            + "#        #" + "\n"
//            + "#        #" + "\n"
//            + "#        #" + "\n"
//            + "#        #" + "\n"
//            + "##########";
//    TableLayout allTable;
    int table = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_plan);
//        allTable = (TableLayout)findViewById(R.id.tableLayout);

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox_id);
        if (checkBox.isChecked()) {
            table = 1;
        }

        Button next = (Button)findViewById(R.id.sendPlan);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConclusionActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
