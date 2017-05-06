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

    private final int KEYBOARD_NORMAL = 1;
    private final int KEYBOARD_UPPER = 2;

    private int now_keyboard = KEYBOARD_NORMAL;

    private KeyboardView keyboardView;


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onInitializeInterface() {
        super.onInitializeInterface();

    }

    private void changeKeyboard(int keyboard_code){

        if(keyboard_code == now_keyboard){
            return;
        }

        now_keyboard = keyboard_code;

        Keyboard m_keyboard;

        if(keyboard_code == KEYBOARD_NORMAL){
            m_keyboard = new Keyboard(this,R.xml.bopomofo_keys);
        }else {
            m_keyboard = new Keyboard(this,R.xml.bopomofo_keys_uppercase);
        }

        keyboardView.setKeyboard(m_keyboard);
        keyboardView.invalidateAllKeys();
    }

    @Override
    public View onCreateInputView() {
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.my_keyboard_view, null, false);
        keyboardView.setKeyboard(new Keyboard(this,R.xml.bopomofo_keys));
        keyboardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
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
                        changeKeyboard(KEYBOARD_NORMAL);
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

        return keyboardView;
    }

//    private int changeChar(int original_char){
//        if(now_keyboard == KEYBOARD_NORMAL){
//            return original_char;
//        }
//
//        if(original_char >= 97 && original_char <= 122 ){
//            return original_char + 32;
//        }
//
//        switch (original_char){
//
//        }
//    }
}
