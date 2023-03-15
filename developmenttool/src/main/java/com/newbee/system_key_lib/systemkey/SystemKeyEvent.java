package com.newbee.system_key_lib.systemkey;


import android.view.KeyEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemKeyEvent {


    private boolean canStart=false;
    private int lastCode=-1;
    private long lastEventTime=-1;
    private KeyCodesEventUtil keyCodesEventUtil;


    public void start() {
        canStart=true;

    }

    public void pause() {
       canStart=false;
    }

    public void close() {
       lastCode=-1;
        lastEventTime = -1;
        canStart=false;
        if (keyCodesEventUtil != null) {
            keyCodesEventUtil.close();
        }
    }

    public void setListen(SystemKeyEventListen listen) {
        keyCodesEventUtil = new KeyCodesEventUtil(listen);
    }

    public void setKeyCodesToDoEvent(int eventType, List<Integer> keyCodes) {
        keyCodesEventUtil.setKeyCodesToDoEvent(eventType, keyCodes);
    }




    private final String tag = getClass().getName() + ">>>>";

    public boolean nowClickKeyCode(KeyEvent keyEvent) {

        if (keyCodesEventUtil == null) {
            //不需要处理，就直接返回，用系统的自定义处理就可以了
            return false;
        }

        if (!canStart) {
            //如果还没初始化开始时间，或者当前时间比开始时间小于1秒直接返回过滤掉事件
            return false;
        }

        if (keyEvent.getAction() != KeyEvent.ACTION_DOWN) {
            //不是KEYDOWN事件，直接不做处理
            return false;
        }
        int nowKeyCode = keyEvent.getKeyCode();
        long nowEventTime=keyEvent.getEventTime();
        if(nowKeyCode==lastCode&&nowEventTime==lastEventTime){
            return false;
        }
        lastCode=nowKeyCode;
        lastEventTime=nowEventTime;
        return keyCodesEventUtil.nowClickKeyCode(nowKeyCode);
    }
}