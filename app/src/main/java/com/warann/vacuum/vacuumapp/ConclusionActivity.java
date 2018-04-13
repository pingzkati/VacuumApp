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
    int[] robot = {1,1}; //initial position from server
    int one,two;
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
    String pub_topic = "topic/cango";
    String sub_topic = "topic/position" ;
    String sub_topic2 = "topic/allStep" ;
    String sub_topic3 = "topic/timeAll" ;
    MqttAndroidClient client,client2,client3;
    //TextView subText;
    TextView subText2;
    TextView subText3;


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
        roommap = getIntent().getStringExtra("roommap");

        allTable = (TableLayout)findViewById(R.id.tableLayout02);

        setPlan(roommap, allTable);

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
                String pub_message = "Yes";
                try {
                    client.publish(pub_topic, pub_message.getBytes(),0,false);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                String pub_message2 = "Waiting";
                try {
                    client.publish(pub_topic, pub_message2.getBytes(),0,false);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });

        //subText = (TextView)findViewById(R.id.subText);
        subText2 = (TextView)findViewById(R.id.subText2);
        subText3 = (TextView)findViewById(R.id.subText3);


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
                //subText.setText(new String(message.getPayload()));
                String str_robot = new String(message.getPayload());
                String[] ary = str_robot.split(" ");
                String s1 = ary[0];
                String s2 = ary[1];
                one = Integer.valueOf(s1);
                two = Integer.valueOf(s2);

                setRobot(roommap,one,two);

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
                    Toast.makeText(ConclusionActivity.this,"Connected!!",Toast.LENGTH_LONG).show();
                    setSubscription2();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(ConclusionActivity.this,"Connection Failed !!",Toast.LENGTH_LONG).show();

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
                subText2.setText(new String(message.getPayload()));
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
                    Toast.makeText(ConclusionActivity.this,"Connected!!",Toast.LENGTH_LONG).show();
                    setSubscription3();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(ConclusionActivity.this,"Connection Failed !!",Toast.LENGTH_LONG).show();

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
                subText3.setText(new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });




    }

    public void publish (View v){
        String pub_message = "MAP";
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

    public void setRobot(String roommap, int x , int y){
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

        int i = y;
        int j = x;

        table[i][j].setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }


}
