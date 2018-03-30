package com.warann.vacuum.vacuumapp;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Button;
import android.content.Intent;
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

    static String MQTTHOST = "tcp://159.89.198.162:1883";
    String pub_topic = "topic/map";
    String sub_topic = "topic/position" ;
    MqttAndroidClient client;
    TextView subText;
    Vibrator vibrator;

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

        subText = (TextView)findViewById(R.id.subText);
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId);

        MqttConnectOptions options = new MqttConnectOptions();

        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(ConclusionActivity.this,"Connected!!",Toast.LENGTH_LONG).show();
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(ConclusionActivity.this,"Connection Failed !!",Toast.LENGTH_LONG).show();

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
                subText.setText(new String(message.getPayload()));
                vibrator.vibrate(500);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

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

    public void pub (View v){
        String pub_message = roommap;
        try {
            client.publish(pub_topic, pub_message.getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void setSubscription(){

        try{
            client.subscribe(sub_topic,0);
        }catch (MqttException e){
            e.printStackTrace();
        }
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
