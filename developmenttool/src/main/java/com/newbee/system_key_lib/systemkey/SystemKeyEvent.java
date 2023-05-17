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

    private long startTime;
    private final long mustChaTime=300;
    public void start() {
        canStart=true;
        /**
         * 必须做这个，有时候有些引擎还没优化成功
         */
        startTime=System.currentTimeMillis();
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

    public boolean nowClickKeyCode(KeyEvent keyEvent) {

        if (keyCodesEventUtil == null) {
            //不需要处理，就直接返回，用系统的自定义处理就可以了
            return false;
        }

        if (!canStart||System.currentTimeMillis()-startTime<mustChaTime) {
            //如果还没初始化开始时间，或者当前时间比开始时间小于1秒直接返回过滤掉事件
            return false;
        }



        if(lastCode==-1){
            //第一次的话什么事件都不拦截
        }else {
            //后面只接受keyDown事件
            if(keyEvent.getAction() !=KeyEvent.ACTION_DOWN){
                return false;
            }
        }

        long nowEventTime=keyEvent.getEventTime();
        int nowKeyCode = keyEvent.getKeyCode();

        if(nowKeyCode==lastCode&&nowEventTime==lastEventTime){
            //此处处理重复事件
            return false;
        }
        lastCode=nowKeyCode;
        lastEventTime=nowEventTime;
        return keyCodesEventUtil.nowClickKeyCode(nowKeyCode);
    }
}
