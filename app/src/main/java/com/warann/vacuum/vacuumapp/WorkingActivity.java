package com.warann.vacuum.vacuumapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
 * Created by PINGZ on 17/03/2018.
 */

public class WorkingActivity extends AppCompatActivity {

    boolean finish = false;
    BooVariable listener = new BooVariable();


    static String MQTTHOST = "tcp://159.89.198.162:1883";
    String sub_topic = "topic/finish" ;
    MqttAndroidClient client;
    TextView subText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.working);

        subText = (TextView)findViewById(R.id.textView4);
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId);

        MqttConnectOptions options = new MqttConnectOptions();

        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(WorkingActivity.this,"Connected!!",Toast.LENGTH_LONG).show();
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(WorkingActivity.this,"Connection Failed !!",Toast.LENGTH_LONG).show();

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
                Intent intent = new Intent(WorkingActivity.this, ConclusionIntent.class);
                startActivity(intent);
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


//    listener.setListener(new BooVariable.ChangeListener() {
////        @Override
////        public void onChange(){
////            setContentView(R.layout.conclusion);
////        }
//    });

//    public interface VariableChangeListener {
//        public void onVariableChanged(boolean finish);
//    }
//    public class MyCustomObject {
//        private MyCustomObjectListener listener;
//        // Constructor where listener events are ignored
//        public MyCustomObject() {
//            // set null or default listener or accept as argument to constructor
//            this.listener = null;
//        }
//
//        // Assign the listener implementing events interface that will receive the events
//        public void setCustomObjectListener(MyCustomObjectListener listener) {
//            this.listener = listener;
//        }
//    }


//    public ChangeListener(boolean finish){
////        Intent intent = new Intent(WorkingActivity.this, SelectSizeActivity.class);
////        startActivity(intent);
////        finish();
////    }


}
