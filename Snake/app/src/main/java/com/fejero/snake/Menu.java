package com.fejero.snake;

import com.fejero.snake.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        Button startButton = (Button) findViewById(R.id.start);
        startButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent("com.fejero.snake.GAMESTARTER"));
                //mpButtonClick.start();
            }
        });

        Button connectButton = (Button) findViewById(R.id.connect);
        connectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent("com.fejero.connection.BTCONNECTION"));
            }
        });


    }

}
