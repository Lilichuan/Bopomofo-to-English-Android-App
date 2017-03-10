package com.taiwanandroidapp.tim.bopomofotoenglish;

import android.graphics.BitmapRegionDecoder;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;

import com.taiwanandroidapp.tim.bopomofotoenglish.UI.MyKeyboardView;

/**
 * Created by tim on 2016/12/15.
 */

public class TransImService extends InputMethodService {

    private final int KEYBOARD_NORNAL = 1;
    private final int KEYBOARD_UPPER = 2;

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

    private void changeKeyboard(int keyboard_code){
        Keyboard keyboard;

        if(keyboard_code == KEYBOARD_NORNAL){
            keyboard = new Keyboard(this,R.xml.bopomofo_keys);
        }else {
            keyboard = new Keyboard(this,R.xml.bopomofo_keys_uppercase);
        }


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

                switch (i){
                    case 8:
                        // define in bopomofo_keys.xml
                        // 8 is delete key signal
                        getCurrentInputConnection().deleteSurroundingText(1,0);
                        break;
                    case -1:
                        changeKeyboard(KEYBOARD_UPPER);
                        break;
                    case -2:
                        changeKeyboard(KEYBOARD_NORNAL);
                        break;
                    default:
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
