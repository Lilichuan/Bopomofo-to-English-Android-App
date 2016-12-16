package com.taiwanandroidapp.tim.bopomofotoenglish;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
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
        return getLayoutInflater().inflate(R.layout.my_keyboard_view, null, false);
    }
}
