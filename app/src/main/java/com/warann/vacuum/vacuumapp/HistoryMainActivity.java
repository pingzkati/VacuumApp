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
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class HistoryMainActivity extends AppCompatActivity{
    static String MQTTHOST = "tcp://159.89.198.162:1883";

    String sub_topic = "topic/detailHistory" ;
    String one,two,three,four,five;

    MqttAndroidClient client,client2,client3;
    TextView subText9,date1,date2,date3,date4,date5;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_main);

        Button back = (Button)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryMainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        int[] resId = {R.drawable.cell_shape, R.drawable.cell_shape,
//                R.drawable.cell_shape,R.drawable.cell_shape,
//                R.drawable.cell_shape};

//        int[] resId = { R.drawable.aerithgainsborough
//                , R.drawable.barretwallace, R.drawable.caitsith
//                , R.drawable.cidhighwind, R.drawable.cloudstrife
//                , R.drawable.redxiii, R.drawable.sephiroth
//                , R.drawable.tifalockhart, R.drawable.vincentvalentine
//                , R.drawable.yuffiekisaragi, R.drawable.zackfair };




        subText9 = (TextView)findViewById(R.id.textView9);
        date1 = (TextView)findViewById(R.id.textView1);
        date2 = (TextView)findViewById(R.id.textView2);
        date3 = (TextView)findViewById(R.id.textView3);
        date4 = (TextView)findViewById(R.id.textView4);
        date5 = (TextView)findViewById(R.id.textView5);


        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId);

        String clientId2 = MqttClient.generateClientId();
        client2 = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId2);

        String clientId3 = MqttClient.generateClientId();
        client3 = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId3);

        MqttConnectOptions options = new MqttConnectOptions();
        MqttConnectOptions options2 = new MqttConnectOptions();
        MqttConnectOptions options3 = new MqttConnectOptions();

        /////////////////////   1    /////////////////////
        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(HistoryMainActivity.this,"Connected!!",Toast.LENGTH_LONG).show();
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(HistoryMainActivity.this,"Connection Failed !!",Toast.LENGTH_LONG).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String st = new String(message.getPayload());
                //subText9.setText(new String(message.getPayload()));
                String[] ary = st.split(",");

                one = ary[0];
                two = ary[1];
                three = ary[2];
                four = ary[3];
                five = ary[4];

                date1.setText(one);
                date2.setText(two);
                date3.setText(three);
                date4.setText(four);
                date5.setText(five);

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });

//        final String[] list = {one,two,three,four,five};
//
//        CustomAdapter adapter = new CustomAdapter(getApplicationContext(), list, resId);
//
//        ListView listView = (ListView)findViewById(R.id.listView1);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new OnItemClickListener() {
//            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//            }
//        });
    }
    private void setSubscription(){

        try{
            client.subscribe(sub_topic,0);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }

}