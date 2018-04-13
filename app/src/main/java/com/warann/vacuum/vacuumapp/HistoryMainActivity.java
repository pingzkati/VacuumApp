package com.warann.vacuum.vacuumapp;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class HistoryMainActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        int[] resId = {R.drawable.cell_shape, R.drawable.cell_shape,
                R.drawable.cell_shape,R.drawable.cell_shape,
                R.drawable.cell_shape};

//        int[] resId = { R.drawable.aerithgainsborough
//                , R.drawable.barretwallace, R.drawable.caitsith
//                , R.drawable.cidhighwind, R.drawable.cloudstrife
//                , R.drawable.redxiii, R.drawable.sephiroth
//                , R.drawable.tifalockhart, R.drawable.vincentvalentine
//                , R.drawable.yuffiekisaragi, R.drawable.zackfair };

        String[] list = { "Date/Time", "Date/Time", "Date/Time"
                , "Date/Time", "Date/Time"};

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), list, resId);

        ListView listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            }
        });
    }

}