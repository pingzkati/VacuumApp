package com.warann.vacuum.vacuumapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.content.Intent;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CheckBox;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;


public class MainActivity extends AppCompatActivity {
    static String MQTTHOST = "tcp://159.89.198.162:1883";

    String pub_topic = "topic/History";
    MqttAndroidClient client;


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

        Button history = (Button)findViewById(R.id.history);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pub_message = "all" ;
                try {
                    client.publish(pub_topic, pub_message.getBytes(),0,false);
                } catch (MqttException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(MainActivity.this, HistoryMainActivity.class);
                startActivity(intent);

                finish();
            }
        });
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId);

        MqttConnectOptions options = new MqttConnectOptions();

        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MainActivity.this,"Connected!!",Toast.LENGTH_LONG).show();

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(MainActivity.this,"Connection Failed !!",Toast.LENGTH_LONG).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
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