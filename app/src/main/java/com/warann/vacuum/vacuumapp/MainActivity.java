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
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button start = (Button)findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectSizeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//
//        int[] resId = {R.drawable.cell_shape, R.drawable.cell_shape,
//                R.drawable.cell_shape,R.drawable.cell_shape,
//                R.drawable.cell_shape};
//
////        int[] resId = { R.drawable.aerithgainsborough
////                , R.drawable.barretwallace, R.drawable.caitsith
////                , R.drawable.cidhighwind, R.drawable.cloudstrife
////                , R.drawable.redxiii, R.drawable.sephiroth
////                , R.drawable.tifalockhart, R.drawable.vincentvalentine
////                , R.drawable.yuffiekisaragi, R.drawable.zackfair };
//
//        String[] list = { "Date/Time", "Date/Time", "Date/Time"
//                , "Date/Time", "Date/Time"};
//
//        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), list, resId);
//
//        ListView listView = (ListView)findViewById(R.id.listView1);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new OnItemClickListener() {
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//
//            }
//        });
//    }

}