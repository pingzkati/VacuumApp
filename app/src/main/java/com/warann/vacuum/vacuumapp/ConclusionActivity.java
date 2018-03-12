package com.warann.vacuum.vacuumapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Button;

/**
 * Created by PINGZ on 05/03/2018.
 */


public class ConclusionActivity extends AppCompatActivity {

    public void setPlan(String roommap, TableLayout allTable) {
        View[][] table = new View[10][10];
        String[] ary = roommap.split("\n");
        for(int i=0;i<allTable.getChildCount();i++){
            View tempTableRow = allTable.getChildAt(i);
            if (tempTableRow instanceof TableRow){
                TableRow row = (TableRow) tempTableRow;
                for(int j=0;j<row.getChildCount();j++){
                    table[i][j] = row.getChildAt(j);
                }
            }
        }
        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++){
                if(ary[i].charAt(j) == '#'){
                    table[i][j].setBackgroundColor(0x22222222);
                }
            }
        }
    }


}
