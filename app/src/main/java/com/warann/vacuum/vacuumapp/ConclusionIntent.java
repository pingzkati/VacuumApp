package com.warann.vacuum.vacuumapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * Created by PINGZ on 30/03/2018.
 */
public class ConclusionIntent extends AppCompatActivity {
    TableLayout allTable;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);

        allTable = (TableLayout)findViewById(R.id.tableLayout02);
        setPlan(roommap, allTable);
    }

    public void setPlan(String roommap, TableLayout allTable) {
        View[][] table = new View[10][10];
//        setWalls(walls);
        String[] ary = roommap.split("\n");
        for (int i = 0; i < allTable.getChildCount(); i++) {
            View tempTableRow = allTable.getChildAt(i);
            if (tempTableRow instanceof TableRow) {
                TableRow row = (TableRow) tempTableRow;
                for (int j = 0; j < row.getChildCount(); j++) {
                    table[i][j] = row.getChildAt(j);
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (ary[i].charAt(j) == '#') {
                    table[i][j].setBackgroundColor(0x22222222);
                }
                if (ary[i].charAt(j) == 'o') {
                    table[i][j].setBackgroundColor(0x22222222);
                }
                if (ary[i].charAt(j) == '.') {
                    table[i][j].setBackgroundColor(0x22222222);
                }
            }
        }
    }
}

