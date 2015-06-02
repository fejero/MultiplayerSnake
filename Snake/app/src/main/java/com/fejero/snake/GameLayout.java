package com.fejero.snake;

import android.content.Context;
import android.widget.LinearLayout;

public class GameLayout extends LinearLayout {

    public GameLayout(Context context) {
        super(context);
        addView(new Game(context));

        // TODO Auto-generated constructor stub
    }

}
