package com.fejero.connection;

import java.util.ArrayList;
import java.util.List;

import com.fejero.snake.R;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BTConnection extends Activity {

    private final int REQUEST_ENABLE_BT = 1;
    private List<String> mArrayAdapter;
    private EditText editText;
    /*
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
	    public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        // When discovery finds a device
	        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
	            // Get the BluetoothDevice object from the Intent
	            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	            // Add the name and address to an array adapter to show in a ListView
	            mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
	            editText.setText(editText.getText() + device.getName() + "\n" + device.getAddress() + "\n");
	        }
	    }
	};*/

    // Register the BroadcastReceiver

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connection_page);
        editText = (EditText) findViewById(R.id.editText1);
        Button connectButton = (Button) findViewById(R.id.connectButton);
        connectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //editText.setText(editText.getText()+"hello"+"\n");


                // ListView lv1 = (ListView) findViewById(R.id.myListView1);
                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


                final BroadcastReceiver mReceiver = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        String action = intent.getAction();
                        // When discovery finds a device 
                        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                            // Get the BluetoothDevice object from the Intent
                            BluetoothDevice device = intent.getParcelableExtra(
                                    BluetoothDevice.EXTRA_DEVICE);
                            editText.setText(editText.getText() + "\n" + device.getName());
                            // Log.v("BlueTooth Testing",device.getName() + "\n"
                            // + device.getAddress());
                        }
                    }
                };

                String aDiscoverable = BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE;
                startActivityForResult(new Intent(aDiscoverable), 1);
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                registerReceiver(mReceiver, filter);
                mBluetoothAdapter.startDiscovery();
            }
        });
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

}
