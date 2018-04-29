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

/**
 * Created by PINGZ on 30/03/2018.
 */
public class ConclusionIntent extends AppCompatActivity {
    static String MQTTHOST = "tcp://159.89.198.162:1883";

    String sub_topic = "topic/finishMap" ;
    String sub_topic2 = "topic/finishAllStep" ;
    String sub_topic3 = "topic/finishTimeAll" ;
    MqttAndroidClient client,client2,client3;
    TextView subText;
    TextView subText9;
    TextView subText10;

    TableLayout allTable;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish);


        Button back = (Button)findViewById(R.id.buttonBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConclusionIntent.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        subText = (TextView)findViewById(R.id.textView); // map
        subText9 = (TextView)findViewById(R.id.textView9); // step
        subText10 = (TextView)findViewById(R.id.textView10); // time


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
                    Toast.makeText(ConclusionIntent.this,"Connected!!",Toast.LENGTH_LONG).show();
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(ConclusionIntent.this,"Connection Failed !!",Toast.LENGTH_LONG).show();

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
                String map = new String(message.getPayload());
                roommap = map;
                allTable = (TableLayout)findViewById(R.id.tableLayout02);
                setPlan(roommap, allTable);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });

        /////////////////////   2   ///////////////////
        try {
            IMqttToken token2 = client2.connect(options2);
            token2.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(ConclusionIntent.this,"Connected!!",Toast.LENGTH_LONG).show();
                    setSubscription2();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(ConclusionIntent.this,"Connection Failed !!",Toast.LENGTH_LONG).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
        client2.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                subText9.setText(new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });

        //////////////////////   3   /////////////////////////
        try {
            IMqttToken token3 = client3.connect(options3);
            token3.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(ConclusionIntent.this,"Connected!!",Toast.LENGTH_LONG).show();
                    setSubscription3();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(ConclusionIntent.this,"Connection Failed !!",Toast.LENGTH_LONG).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
        client3.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                subText10.setText(new String(message.getPayload()));
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
    private void setSubscription2(){

        try{
            client2.subscribe(sub_topic2,0);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }
    private void setSubscription3(){

        try{
            client3.subscribe(sub_topic3,0);
        }catch (MqttException e){
            e.printStackTrace();
        }
    }


}