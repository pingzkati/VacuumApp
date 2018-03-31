package com.warann.vacuum.vacuumapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by PINGZ on 17/03/2018.
 */

public class WorkingActivity extends AppCompatActivity {

    boolean finish = false;

    BooVariable listener = new BooVariable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.working);
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
