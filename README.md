# system_key_lib
newbee黎潇专属的系统键值工具

[![](https://jitpack.io/v/lixiao-nb666/system_key_lib.svg)](https://jitpack.io/#lixiao-nb666/system_key_lib)

2个大类及使用方式

SystemKeyEvent

第一步:初始化

SystemKeyEvent keyEventUtil = new SystemKeyEvent();

第二步：设置监听   

keyEventUtil.setListen(keyEventListen);

keyEventUtil.setKeyCodesToDoEvent(KeyCodesEventType.LEFT.ordinal(), ActivityKeyDownListUtil.toLeftList());

keyEventUtil.setKeyCodesToDoEvent(KeyCodesEventType.RIGHT.ordinal(), ActivityKeyDownListUtil.toRightList());

keyEventUtil.setKeyCodesToDoEvent(KeyCodesEventType.QUE.ordinal(), ActivityKeyDownListUtil.queOk1());

keyEventUtil.setKeyCodesToDoEvent(KeyCodesEventType.QUE.ordinal(), ActivityKeyDownListUtil.queOk2());

keyEventUtil.setKeyCodesToDoEvent(KeyCodesEventType.BACK.ordinal(), ActivityKeyDownListUtil.toBackList());

第三步：启动

keyEventUtil.start();

第四步:活动暂停的时候，如果不需要事件了，可以暂停掉

keyEventUtil.pause();

第五步：关闭的时候，调用关闭方法

keyEventUtil.close();

下面为：辅助类

public enum KeyCodesEventType {

    NONE,//无效事件，系统处理

    LEFT, //往左

    RIGHT, //往右

    QUE,//确定

    BACK,//返回

}

public class ActivityKeyDownListUtil {

    //中间键

    public static List<Integer> queOk1() {

        List<Integer> needKeyCodes = new ArrayList<>();

        needKeyCodes.add(KeyEvent.KEYCODE_DPAD_CENTER);

        return needKeyCodes;

    }

    //确定按钮

    public static List<Integer> queOk2() {

        List<Integer> needKeyCodes = new ArrayList<>();

        needKeyCodes.add(KeyEvent.KEYCODE_ENTER);


        return needKeyCodes;

    }

    //往左

    public static List<Integer> toLeftList() {

        List<Integer> needKeyCodes = new ArrayList<>();

        needKeyCodes.add(KeyEvent.KEYCODE_DPAD_LEFT);

        return needKeyCodes;

    }

    //往右

    public static List<Integer> toRightList() {

        List<Integer> needKeyCodes = new ArrayList<>();

        needKeyCodes.add(KeyEvent.KEYCODE_DPAD_RIGHT);

        return needKeyCodes;

    }

    //返回

    public static List<Integer> toBackList() {

        List<Integer> needKeyCodes = new ArrayList<>();

        needKeyCodes.add(KeyEvent.KEYCODE_BACK);

        return needKeyCodes;

    }

}

-----------------------------------------------------------

SystemKeyCodeInput

第一步:初始化

SystemKeyCodeInput keyCodeInput=new SystemKeyCodeInput();

第二步：設置監聽（注意注意，不設置也可以運行，就是沒執行情況返回）

keyCodeInput.setListen(keyCodeInputListen);

第三步：关闭的时候，调用关闭方法

keyCodeInput.close();

--------------------------------------------------------------

无关人等，莫要抄袭