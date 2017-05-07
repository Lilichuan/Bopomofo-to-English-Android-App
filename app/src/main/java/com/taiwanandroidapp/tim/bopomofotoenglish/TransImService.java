package com.taiwanandroidapp.tim.bopomofotoenglish;

import android.app.Dialog;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.IBinder;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by tim on 2016/12/15.
 */

public class TransImService extends InputMethodService {

    private final int KEYBOARD_NORMAL = 1;
    private final int KEYBOARD_UPPER = 2;

    // define in bopomofo_keys.xml and bopomofo_keys_uppercase.xml
    private final int SIGNAL_TO_UPPER = -1;
    private final int SIGNAL_TO_NORMAL = -2;
    private final int SIGNAL_DELETE = 8;
    private final int SIGNAL_CHANGE_IME = -101;
    private final int SIGNAL_HIDE_KEYBOARD = -3;

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

                InputMethodManager mInputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);


                switch (i){
                    case SIGNAL_DELETE:
                        getCurrentInputConnection().deleteSurroundingText(1,0);
                        break;
                    case SIGNAL_TO_UPPER:
                        changeKeyboard(KEYBOARD_UPPER);
                        break;
                    case SIGNAL_TO_NORMAL:
                        changeKeyboard(KEYBOARD_NORMAL);
                        break;
                    case SIGNAL_CHANGE_IME:
                        //mInputMethodManager.switchToNextInputMethod(getToken(), false);
                        mInputMethodManager.showInputMethodPicker();
                        break;
                    case SIGNAL_HIDE_KEYBOARD:
                        mInputMethodManager.hideSoftInputFromInputMethod(getToken(), 0);
                        //mInputMethodManager.showInputMethodPicker();
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

    private IBinder getToken() {
        final Dialog dialog = getWindow();
        if (dialog == null) {
            return null;
        }
        final Window window = dialog.getWindow();
        if (window == null) {
            return null;
        }
        return window.getAttributes().token;
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
