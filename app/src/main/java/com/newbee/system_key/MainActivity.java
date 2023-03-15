package com.newbee.system_key;

import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;




import com.newbee.system_key.util.ActivityKeyDownListUtil;
import com.newbee.system_key.util.KeyCodesEventType;
import com.newbee.system_key_lib.systemkey.SystemKeyEvent;
import com.newbee.system_key_lib.systemkey.SystemKeyEventListen;



public class MainActivity extends AppCompatActivity {


    protected SystemKeyEvent keyEventUtil = new SystemKeyEvent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keyEventUtil.setListen(keyEventListen);
        keyEventUtil.setKeyCodesToDoEvent(KeyCodesEventType.LEFT.ordinal(), ActivityKeyDownListUtil.toLeftList());
        keyEventUtil.setKeyCodesToDoEvent(KeyCodesEventType.RIGHT.ordinal(), ActivityKeyDownListUtil.toRightList());
        keyEventUtil.setKeyCodesToDoEvent(KeyCodesEventType.QUE.ordinal(), ActivityKeyDownListUtil.queOk1());
        keyEventUtil.setKeyCodesToDoEvent(KeyCodesEventType.QUE.ordinal(), ActivityKeyDownListUtil.queOk2());
        keyEventUtil.setKeyCodesToDoEvent(KeyCodesEventType.BACK.ordinal(), ActivityKeyDownListUtil.toBackList());
        keyEventUtil.start();
    }





    private final String tag="test_system_key";

    private SystemKeyEventListen keyEventListen = new SystemKeyEventListen() {
        @Override
        public void nowCanDoEvent(int eventTypeInt) {
            KeyCodesEventType eventType=KeyCodesEventType.values()[eventTypeInt];
            Log.i(tag,"kankannowCanDoEvent:"+eventType);
            switch (eventType) {
                case NONE:
                    break;
                case LEFT:

                    break;
                case RIGHT:

                    break;
                case QUE:

                    break;
            }

        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyEventUtil.nowClickKeyCode(event)) {
            return  true ;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 截获按键事件.发给ScanGunKeyEventHelper
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (keyEventUtil == null || event == null){
            return false;
        }
        if (keyEventUtil.nowClickKeyCode(event)) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

}