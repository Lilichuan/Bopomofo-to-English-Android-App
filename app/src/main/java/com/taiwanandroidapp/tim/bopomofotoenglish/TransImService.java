package com.taiwanandroidapp.tim.bopomofotoenglish;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;

import com.taiwanandroidapp.tim.bopomofotoenglish.UI.MyKeyboardView;

/**
 * Created by tim on 2016/12/15.
 */

public class TransImService extends InputMethodService {

    private Keyboard bopomofo_keyboard;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onInitializeInterface() {
        super.onInitializeInterface();

        bopomofo_keyboard = new Keyboard(this,R.xml.bopomofo_keys);

    }

    @Override
    public View onCreateInputView() {
        KeyboardView view = (KeyboardView) getLayoutInflater().inflate(R.layout.my_keyboard_view, null, false);
        view.setKeyboard(bopomofo_keyboard);
        view.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
            @Override
            public void onPress(int i) {

            }

            @Override
            public void onRelease(int i) {

            }

            @Override
            public void onKey(int i, int[] ints) {
                if(i == 8){
                    // define in bopomofo_keys.xml
                    // 8 is delete key signal
                    getCurrentInputConnection().deleteSurroundingText(1,0);
                }else {
                    getCurrentInputConnection().commitText(String.valueOf((char) i), 1);
                }

            }

            @Override
            public void onText(CharSequence charSequence) {

            }

            @Override
            public void swipeLeft() {

            }

            @Override
            public void swipeRight() {

            }

            @Override
            public void swipeDown() {

            }

            @Override
            public void swipeUp() {

            }
        });

        return view;
    }
}
