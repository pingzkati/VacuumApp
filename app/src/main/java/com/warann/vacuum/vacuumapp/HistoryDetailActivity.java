package com.warann.vacuum.vacuumapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class HistoryDetailActivity extends AppCompatActivity {
    static String MQTTHOST = "tcp://159.89.198.162:1883";

    String sub_topic = "topic/detailHistory" ;
    String one,two,three,map;

    MqttAndroidClient client;
    TextView subText9,step,time;

    TableLayout allTable;
    String roommap ;

    public void setPlan(String roommap, TableLayout allTable) {
        View[][] table = new View[10][10];
//        setWalls(walls);
        String[] ary = roommap.split("0");
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_detail);

        subText9 = (TextView) findViewById(R.id.textView15);
        step = (TextView) findViewById(R.id.textView9);
        time = (TextView) findViewById(R.id.textView10);


        Button back = (Button)findViewById(R.id.buttonBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryDetailActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId);
        MqttConnectOptions options = new MqttConnectOptions();

        /////////////////////   1    /////////////////////
        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(HistoryDetailActivity.this, "Connected!!", Toast.LENGTH_LONG).show();
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(HistoryDetailActivity.this, "Connection Failed !!", Toast.LENGTH_LONG).show();

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

                map = ary[1];
                two = ary[2];
                three = ary[3];

                subText9.setText(ary[0]);
                time.setText(two);
                step.setText(three);

                roommap = map;
                allTable = (TableLayout)findViewById(R.id.tableLayout02);
                setPlan(roommap, allTable);

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });

    }

    private void setSubscription(){

        try{
            client.subscribe(sub_topic,0);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }

}