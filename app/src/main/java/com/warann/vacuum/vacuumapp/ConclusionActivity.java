package com.warann.vacuum.vacuumapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Button;
import android.content.Intent;

/**
 * Created by PINGZ on 05/03/2018.
 */


public class ConclusionActivity extends AppCompatActivity {

    int[][] walls = null;
    int[] robot = {1 ,1};
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conclusion);

//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            bundle.putSerializable("tablePlan", walls);
//        }
        Object[] objectArray = (Object[]) getIntent().getExtras().getSerializable("tablePlan");
        if(objectArray!=null){
            walls = new int[objectArray.length][];
            for(int i=0;i<objectArray.length;i++){
                walls[i]=(int[]) objectArray[i];
            }
        }

        allTable = (TableLayout)findViewById(R.id.tableLayout02);

        setPlan(roommap, allTable);
        setRobot(roommap, robot);
        Button back = (Button)findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConclusionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button start = (Button)findViewById(R.id.startVacuum);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConclusionActivity.this, WorkingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void setWalls(int[][] walls){
//        if(walls[1][1]==1){
//
//        }
//        for(int i = 0; i<10;i++){
//            for(int j =0; j<10; j++){
//                walls[i][j] = 0;
//            }
//        }
//        for(int i = 0; i<5;i++){
//            for(int j =0; j<5; j++){
//                walls[i][j] = 1;
//            }
//        }

    }

    public void setPlan(String roommap, TableLayout allTable) {
        View[][] table = new View[10][10];
//        setWalls(walls);
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
//                if(ary[i].charAt(j) == '#'){
//                    table[i][j].setBackgroundColor(0x22222222);
//                }
                if(walls[i][j] == 1){
                    table[i][j].setBackgroundColor(0x22222222);
                }
            }
        }

//        if(walls[1][1]==1){
//            table[1][1].setBackgroundColor(0x22222222);
//        }
    }

    public void setRobot(String roommap, int[] robot){
        View[][] table = new View[10][10];
        for(int i=0;i<allTable.getChildCount();i++){
            View tempTableRow = allTable.getChildAt(i);
            if (tempTableRow instanceof TableRow){
                TableRow row = (TableRow) tempTableRow;
                for(int j=0;j<row.getChildCount();j++){
                    table[i][j] = row.getChildAt(j);
                }
            }
        }


//        if(tableset==1){
//            table[1][1].setBackgroundColor(0x22222222);
//        }

//        int i = robot[0];
//        int j = robot[1];
//        table[i][j].setBackgroundColor(0xFF4081);
    }

}
