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
import android.widget.TextView;


public class SelectPlanActivity extends AppCompatActivity {

    int[][] tablePlan = new int[10][10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_plan);

        for (int i = 0; i< 10; i++){
            if(i == 0 || i == 9){
                for (int j = 0; j<10; j++){
                    tablePlan[i][j] = 1;
                }
            }
            else {
                tablePlan[i][0] =1;
                tablePlan[i][9] =1;
            }
        }

        TextView err = (TextView)findViewById(R.id.TextCount);

        Button next = (Button)findViewById(R.id.sendPlan);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectPlanActivity.this, ConclusionActivity.class);
                Bundle Bundle = new Bundle();
                Bundle.putSerializable("tablePlan", tablePlan);
                intent.putExtras(Bundle);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.check1_1:
                if (checked){
                        tablePlan[1][1] = 1;
                }
                else {
                    tablePlan[1][1] = 0;
                }
                break;
            case R.id.check1_2:
                if (checked){
                    tablePlan[1][2] = 1;
                }
                else {
                    tablePlan[1][2] = 0;
                }
                break;
            case R.id.check1_3:
                if (checked){
                    tablePlan[1][3] = 1;
                }
                else {
                    tablePlan[1][3] = 0;
                }
                break;
            case R.id.check1_4:
                if (checked){
                    tablePlan[1][4] = 1;
                }
                else {
                    tablePlan[1][4] = 0;
                }
                break;
            case R.id.check1_5:
                if (checked){
                    tablePlan[1][5] = 1;
                }
                else {
                    tablePlan[1][5] = 0;
                }
                break;
            case R.id.check1_6:
                if (checked){
                    tablePlan[1][6] = 1;
                }
                else {
                    tablePlan[1][6] = 0;
                }
                break;
            case R.id.check1_7:
                if (checked){
                    tablePlan[1][7] = 1;
                }
                else {
                    tablePlan[1][7] = 0;
                }
                break;
            case R.id.check1_8:
                if (checked){
                    tablePlan[1][8] = 1;
                }
                else {
                    tablePlan[1][8] = 0;
                }
                break;
            case R.id.check2_1:
                if (checked){
                    tablePlan[2][1] = 1;
                }
                else {
                    tablePlan[2][1] = 0;
                }
                break;
            case R.id.check2_2:
                if (checked){
                    tablePlan[2][2] = 1;
                }
                else {
                    tablePlan[2][2] = 0;
                }
                break;
            case R.id.check2_3:
                if (checked){
                    tablePlan[2][3] = 1;
                }
                else {
                    tablePlan[2][3] = 0;
                }
                break;
            case R.id.check2_4:
                if (checked){
                    tablePlan[2][4] = 1;
                }
                else {
                    tablePlan[2][4] = 0;
                }
                break;
            case R.id.check2_5:
                if (checked){
                    tablePlan[2][5] = 1;
                }
                else {
                    tablePlan[2][5] = 0;
                }
                break;
            case R.id.check2_6:
                if (checked){
                    tablePlan[2][6] = 1;
                }
                else {
                    tablePlan[2][6] = 0;
                }
                break;
            case R.id.check2_7:
                if (checked){
                    tablePlan[2][7] = 1;
                }
                else {
                    tablePlan[2][7] = 0;
                }
                break;
            case R.id.check2_8:
                if (checked){
                    tablePlan[2][8] = 1;
                }
                else {
                    tablePlan[2][8] = 0;
                }
                break;
            case R.id.check3_1:
                if (checked){
                    tablePlan[3][1] = 1;
                }
                else {
                    tablePlan[3][1] = 0;
                }
                break;
            case R.id.check3_2:
                if (checked){
                    tablePlan[3][2] = 1;
                }
                else {
                    tablePlan[3][2] = 0;
                }
                break;
            case R.id.check3_3:
                if (checked){
                    tablePlan[3][3] = 1;
                }
                else {
                    tablePlan[3][3] = 0;
                }
                break;
            case R.id.check3_4:
                if (checked){
                    tablePlan[3][4] = 1;
                }
                else {
                    tablePlan[3][4] = 0;
                }
                break;
            case R.id.check3_5:
                if (checked){
                    tablePlan[3][5] = 1;
                }
                else {
                    tablePlan[3][5] = 0;
                }
                break;
            case R.id.check3_6:
                if (checked){
                    tablePlan[3][6] = 1;
                }
                else {
                    tablePlan[3][6] = 0;
                }
                break;
            case R.id.check3_7:
                if (checked){
                    tablePlan[3][7] = 1;
                }
                else {
                    tablePlan[3][7] = 0;
                }
                break;
            case R.id.check3_8:
                if (checked){
                    tablePlan[3][8] = 1;
                }
                else {
                    tablePlan[3][8] = 0;
                }
                break;
            case R.id.check4_1:
                if (checked){
                    tablePlan[4][1] = 1;
                }
                else {
                    tablePlan[4][1] = 0;
                }
                break;
            case R.id.check4_2:
                if (checked){
                    tablePlan[4][2] = 1;
                }
                else {
                    tablePlan[4][2] = 0;
                }
                break;
            case R.id.check4_3:
                if (checked){
                    tablePlan[4][3] = 1;
                }
                else {
                    tablePlan[4][3] = 0;
                }
                break;
            case R.id.check4_4:
                if (checked){
                    tablePlan[4][4] = 1;
                }
                else {
                    tablePlan[4][4] = 0;
                }
                break;
            case R.id.check4_5:
                if (checked){
                    tablePlan[4][5] = 1;
                }
                else {
                    tablePlan[4][5] = 0;
                }
                break;
            case R.id.check4_6:
                if (checked){
                    tablePlan[4][6] = 1;
                }
                else {
                    tablePlan[4][6] = 0;
                }
                break;
            case R.id.check4_7:
                if (checked){
                    tablePlan[4][7] = 1;
                }
                else {
                    tablePlan[4][7] = 0;
                }
                break;
            case R.id.check4_8:
                if (checked){
                    tablePlan[4][8] = 1;
                }
                else {
                    tablePlan[4][8] = 0;
                }
                break;
            case R.id.check5_1:
                if (checked){
                    tablePlan[5][1] = 1;
                }
                else {
                    tablePlan[5][1] = 0;
                }
                break;
            case R.id.check5_2:
                if (checked){
                    tablePlan[5][2] = 1;
                }
                else {
                    tablePlan[5][2] = 0;
                }
                break;
            case R.id.check5_3:
                if (checked){
                    tablePlan[5][3] = 1;
                }
                else {
                    tablePlan[5][3] = 0;
                }
                break;
            case R.id.check5_4:
                if (checked){
                    tablePlan[5][4] = 1;
                }
                else {
                    tablePlan[5][4] = 0;
                }
                break;
            case R.id.check5_5:
                if (checked){
                    tablePlan[5][5] = 1;
                }
                else {
                    tablePlan[5][5] = 0;
                }
                break;
            case R.id.check5_6:
                if (checked){
                    tablePlan[5][6] = 1;
                }
                else {
                    tablePlan[5][6] = 0;
                }
                break;
            case R.id.check5_7:
                if (checked){
                    tablePlan[5][7] = 1;
                }
                else {
                    tablePlan[5][7] = 0;
                }
                break;
            case R.id.check5_8:
                if (checked){
                    tablePlan[5][8] = 1;
                }
                else {
                    tablePlan[5][8] = 0;
                }
                break;
            case R.id.check6_1:
                if (checked){
                    tablePlan[6][1] = 1;
                }
                else {
                    tablePlan[6][1] = 0;
                }
                break;
            case R.id.check6_2:
                if (checked){
                    tablePlan[6][2] = 1;
                }
                else {
                    tablePlan[6][2] = 0;
                }
                break;
            case R.id.check6_3:
                if (checked){
                    tablePlan[6][3] = 1;
                }
                else {
                    tablePlan[6][3] = 0;
                }
                break;
            case R.id.check6_4:
                if (checked){
                    tablePlan[6][4] = 1;
                }
                else {
                    tablePlan[6][4] = 0;
                }
                break;
            case R.id.check6_5:
                if (checked){
                    tablePlan[6][5] = 1;
                }
                else {
                    tablePlan[6][5] = 0;
                }
                break;
            case R.id.check6_6:
                if (checked){
                    tablePlan[6][6] = 1;
                }
                else {
                    tablePlan[6][6] = 0;
                }
                break;
            case R.id.check6_7:
                if (checked){
                    tablePlan[6][7] = 1;
                }
                else {
                    tablePlan[6][7] = 0;
                }
                break;
            case R.id.check6_8:
                if (checked){
                    tablePlan[6][8] = 1;
                }
                else {
                    tablePlan[6][8] = 0;
                }
                break;
            case R.id.check7_1:
                if (checked){
                    tablePlan[7][1] = 1;
                }
                else {
                    tablePlan[7][1] = 0;
                }
                break;
            case R.id.check7_2:
                if (checked){
                    tablePlan[7][2] = 1;
                }
                else {
                    tablePlan[7][2] = 0;
                }
                break;
            case R.id.check7_3:
                if (checked){
                    tablePlan[7][3] = 1;
                }
                else {
                    tablePlan[7][3] = 0;
                }
                break;
            case R.id.check7_4:
                if (checked){
                    tablePlan[7][4] = 1;
                }
                else {
                    tablePlan[7][4] = 0;
                }
                break;
            case R.id.check7_5:
                if (checked){
                    tablePlan[7][5] = 1;
                }
                else {
                    tablePlan[7][5] = 0;
                }
                break;
            case R.id.check7_6:
                if (checked){
                    tablePlan[7][6] = 1;
                }
                else {
                    tablePlan[7][6] = 0;
                }
                break;
            case R.id.check7_7:
                if (checked){
                    tablePlan[7][7] = 1;
                }
                else {
                    tablePlan[7][7] = 0;
                }
                break;
            case R.id.check7_8:
                if (checked){
                    tablePlan[7][8] = 1;
                }
                else {
                    tablePlan[7][8] = 0;
                }
                break;
            case R.id.check8_1:
                if (checked){
                    tablePlan[8][1] = 1;
                }
                else {
                    tablePlan[8][1] = 0;
                }
                break;
            case R.id.check8_2:
                if (checked){
                    tablePlan[8][2] = 1;
                }
                else {
                    tablePlan[8][2] = 0;
                }
                break;
            case R.id.check8_3:
                if (checked){
                    tablePlan[8][3] = 1;
                }
                else {
                    tablePlan[8][3] = 0;
                }
                break;
            case R.id.check8_4:
                if (checked){
                    tablePlan[8][4] = 1;
                }
                else {
                    tablePlan[8][4] = 0;
                }
                break;
            case R.id.check8_5:
                if (checked){
                    tablePlan[8][5] = 1;
                }
                else {
                    tablePlan[8][5] = 0;
                }
                break;
            case R.id.check8_6:
                if (checked){
                    tablePlan[8][6] = 1;
                }
                else {
                    tablePlan[8][6] = 0;
                }
                break;
            case R.id.check8_7:
                if (checked){
                    tablePlan[8][7] = 1;
                }
                else {
                    tablePlan[8][7] = 0;
                }
                break;
            case R.id.check8_8:
                if (checked){
                    tablePlan[8][8] = 1;
                }
                else {
                    tablePlan[8][8] = 0;
                }
                break;
        }
    }

}