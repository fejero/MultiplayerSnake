package com.fejero.aspect;


import android.util.Log;

public aspect snakeAspect {
	
	pointcut callPointcut() : call(void *.addTail(..));
	after() : callPointcut() {
		Log.v("AspectJ", "Aspect has been executed");
	}
}
