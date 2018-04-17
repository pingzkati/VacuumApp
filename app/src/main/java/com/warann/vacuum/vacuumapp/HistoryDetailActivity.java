package com.warann.vacuum.vacuumapp;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

public class HistoryDetailActivity extends AppCompatActivity {

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
                    table[i][j].setBackgroundColor(getResources().getColor(android.R.color.black));
                }
                if (ary[i].charAt(j) == 'o') {
                    table[i][j].setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                }
                if (ary[i].charAt(j) == '.') {
                    table[i][j].setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                }
            }
        }
    }
}