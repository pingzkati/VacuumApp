package com.warann.vacuum.vacuumapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.warann.vacuum.vacuumapp.ConclusionActivity;
import com.warann.vacuum.vacuumapp.SelectPlanActivity;


public class MainActivity extends AppCompatActivity {

    String roommap = "##########" + "\n"
            + "#        #" + "\n"
            + "#        #" + "\n"
            + "#        #" + "\n"
            + "#        #" + "\n"
            + "#        #" + "\n"
            + "#        #" + "\n"
            + "#        #" + "\n"
            + "#        #" + "\n"
            + "##########";
    TableLayout allTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_plan);
        allTable = (TableLayout)findViewById(R.id.tableLayout);
//        ConclusionActivity con = new ConclusionActivity();
//        con.setPlan(roommap, allTable);
        SelectPlanActivity select = new SelectPlanActivity();
    }


}
