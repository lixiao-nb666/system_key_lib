package com.newbee.system_key_lib.system_key_input;


import android.app.Instrumentation;


/**
 * @author lixiaogege!
 * @description: one day day ,no zuo no die !
 * @date :2021/1/12 0012 17:35
 */
public class SystemKeyCodeInput {
    private SystemKeyCodeInputListen listen;


    public void close(){
        if(null!=listen){
            listen=null;
        }
    }

    public void setListen(SystemKeyCodeInputListen listen){
        this.listen=listen;
    }



    public  void inputKeyCode(final int keyCode){

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
//                    RootShellCmd.getInstance().simulateKey(keyCode);
//                    RootUtil.doRootCmd("input keyevent "+keyCode);
                    inputEvent(keyCode);
//            Runtime runtime = Runtime.getRuntime();
//            runtime.exec("input keyevent " + keyCode);
//            long now=System.currentTimeMillis();
//            invokeInjectInputEvent(new KeyEvent(now,now,KeyEvent.ACTION_DOWN,keyCode,0));
//            invokeInjectInputEvent(new KeyEvent(now,now,KeyEvent.ACTION_UP,keyCode,0));
                    if(null!=listen){
                        listen.inputOk(keyCode);
                    }
                }catch (Exception e){
                    if(null!=listen){
                        listen.inputErr(keyCode,e.toString());
                    }
                }
//        try {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Instrumentation inst = new Instrumentation();
//                        // KeyEvent.KEYCODE_DPAD_DOWN 向下跳格
//                        inst.sendKeyDownUpSync(keyCode);
//                    }catch (Exception e){
//                        LG.i("inputKeyCode","inputKeyCode:1--"+e.toString());
//                    }
//                }
//            }).start();
//        } catch (Exception e) {
//            LG.i("inputKeyCode","inputKeyCode:"+e.toString());
//        }
            }
        }).start();

    }

    private  void inputEvent(int keyCode){
        Instrumentation inst = new Instrumentation();
        inst.sendKeyDownUpSync(keyCode);
    }


//    private static void invokeInjectInputEvent(KeyEvent inputEvent) {
//        Class cl = InputManager.class;
//        try {
//            Method method = cl.getMethod("getInstance");
//            Object result = method.invoke(cl);
//            InputManager im = (InputManager) result;
//            method = cl.getMethod("injectInputEvent", InputEvent.class, int.class);
//            method.invoke(im, inputEvent, 0);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }  catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//    }
}
