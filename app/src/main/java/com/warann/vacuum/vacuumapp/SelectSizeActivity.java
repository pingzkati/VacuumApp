package com.warann.vacuum.vacuumapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by PINGZ on 29/03/2018.
 */

public class SelectSizeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_size);

        Button room2_2 = (Button)findViewById(R.id.room2_2);

        room2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectSizeActivity.this, SelectPlanActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button back = (Button)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectSizeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



}


