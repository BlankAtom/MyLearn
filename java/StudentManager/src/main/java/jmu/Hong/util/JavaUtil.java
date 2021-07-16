package jmu.Hong.util;

import jmu.Hong.pojo.RetMessage;

public class JavaUtil {
    public static RetMessage getReturn(int state, String msg1, String msg2){
        if(state==0){
            return getReturn(0, msg2);
        }
        else{
            return getReturn(1, msg1);
        }
    }
    public static RetMessage getReturn(boolean state, String msg){
        if(state){
            return getReturn(1, msg);
        }
        else{
            return getReturn(0, msg);
        }
    }
    public static RetMessage getReturn(int state, String msg){
        if(state==0){
            return new RetMessage("false", msg);
        }
        else{
            return new RetMessage("true", msg);
        }
    }
}

